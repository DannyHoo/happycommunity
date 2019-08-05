package com.happycommunity.gateway.controller.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.core.util.MD5Util;
import com.happycommunity.framework.core.util.RSAUtil;
import com.happycommunity.gateway.controller.AbstractController;
import com.happycommunity.gateway.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Danny
 * @Title: TestController
 * @Description:
 * @Created on 2018-11-23 16:36:40
 */
@Controller
@RequestMapping("/public/test")
public class PublicTestController extends AbstractController {

    @RequestMapping("/getRandomData")
    @ResponseBody
    public ResponseData getRandomData(HttpServletRequest httpServletRequest) {
        ResponseData responseData = new ResponseData(ResultStatusEnum.SUCCESS, new Random().nextDouble());
        System.out.println(JSON.toJSONString(responseData));
        return responseData;
    }

    @RequestMapping("/rsaEncrypt")
    @ResponseBody
    public ResponseData rsaEncrypt(@RequestBody(required = true) Map<String, Object> map) throws Exception {
        String content = map.get("content").toString();
        String publicKey = map.get("publicKey").toString();
        String encryptContent = RSAUtil.encrypt(content, publicKey);
        return new ResponseData().setData(encryptContent);
    }

    @RequestMapping("/encoding")
    @ResponseBody
    public ResponseData encoding(@RequestBody(required = true) Map<String, Object> map) throws Exception {
        String content = new String(((String)map.get("msgContent")).getBytes(),"UTF-8");
        System.out.println("收到内容："+content);
        return new ResponseData().setData("返回内容："+content);
    }

    @RequestMapping(value = "/1/{channelCode}")
    @ResponseBody
    public ResponseEntity callback(@PathVariable("channelCode") String channelCode, HttpServletRequest request) throws IOException {
        String content = channelCode;
        String value1=request.getParameter("key1");
        String value2=request.getQueryString();
        String value3=HttpServletRequestUtil.getRequestBody(request);
        Map map1=request.getParameterMap();
        Map map2=HttpServletRequestUtil.getRequestParameters(request);
        Map map3=HttpServletRequestUtil.getHeadersInfo(request);
        System.out.println("收到内容："+value3);
        return new ResponseEntity(value3, HttpStatus.OK);
    }

    @RequestMapping("/getRequestBodyTest")
    @ResponseBody
    public ResponseData getRequestBodyTest(HttpServletRequest request) throws Exception {
        String result=getRequestBody(request);
        return new ResponseData().setData(result);
    }

    @RequestMapping("/getRequestParameterTest")
    @ResponseBody
    public String getRequestParameterTest(HttpServletRequest request) {
        String huaxiaCompanyId=request.getParameter("huaxiaCompanyId");
        String huaxiaOrderNo=request.getParameter("huaxiaOrderNo");
        return huaxiaCompanyId+huaxiaOrderNo;
    }

    @RequestMapping(value = "/v1/batch_pay_electronic_return/batch_download_apply", method = RequestMethod.GET)
    public ResponseData batchDownloadBatchPayElectronicReturnApply(@RequestHeader("merchantId") String merchantId, String date, HttpServletResponse response) {

        /*if(StringUtils.isBlank(merchantId) || StringUtils.isBlank(date)){
            throw new ReturnFileNotExistException("参数错误!");
        }*/
        System.out.println(merchantId+"-"+date);
        return new ResponseData().setMessage("d09we1nwe01i2je0123i12").setData("SUCCESS").setCode(100000);
    }

    @RequestMapping("/responseWrite")
    public void responseWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> parameterMap = getStringObjectMap(request);
        System.out.println("HxbankApiController.asyncStatusNotify request params:" + JSON.toJSONString(parameterMap));

        boolean isParameterMapEmpty = parameterMap == null || parameterMap.isEmpty();
        String recoverNo = isParameterMapEmpty ? "" : (String) parameterMap.get("recoverNo");
        String companyId = isParameterMapEmpty ? "" : (String) parameterMap.get("companyId");
        String amt = isParameterMapEmpty ? "" : (String) parameterMap.get("amt");
        String sign = isParameterMapEmpty ? "" : (String) parameterMap.get("sign");

        //签名校验
        String md5Key = "huaxia_md5_key_xxx8l9yyy";
        String signContent = getSignContent(amt, companyId, recoverNo, md5Key);
        //String realSign = MD5Util.sign(md5Key, signContent);
        String realSign = new MessageDigestPasswordEncoder("MD5").encodePassword(signContent,md5Key);
        if (StringUtils.isEmpty(realSign) || !realSign.equals(sign)) {
            response.getWriter().write("sign failed");
            return;
        }

        ExecutorService threadPool= Executors.newCachedThreadPool();
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程："+ Thread.currentThread().getName());
            }
        });

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程："+ Thread.currentThread().getName());
            }
        })*/

        //返回结果
        response.getWriter().write("success");

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行成功");*/


    }

    public static void main(String[] args) {
        System.out.println(MD5Util.sign("huaxia_md5_key_xxx819yyy","amt=0&companyId=test&recoverNo=test&huaxia_md5_key_xxx819yyy"));

        System.out.println(new MessageDigestPasswordEncoder("MD5").encodePassword("amt=0&companyId=test&recoverNo=test&huaxia_md5_key_xxx8l9yyy","huaxia_md5_key_xxx8l9yyy"));
        System.out.println(new MessageDigestPasswordEncoder("MD5").encodePassword("huaxia_md5_key_xxx819yyy","amt=0&companyId=test&recoverNo=test&huaxia_md5_key_xxx819yyy"));
    }

    private String getSignContent(String amt, String companyId, String recoverNo, String md5Key) {
        StringBuffer stringBuffer = new StringBuffer()
                .append("amt=").append(amt).append("&")
                .append("companyId=").append(companyId).append("&")
                .append("recoverNo=").append(recoverNo).append("&")
                .append(md5Key);
        return stringBuffer.toString();
    }

    /**
     * 解析请求参数为Map类型
     *
     * @param request
     * @return
     */
    private Map<String, Object> getStringObjectMap(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        if (properties == null || properties.isEmpty()) {
            return null;
        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();

        for (Map.Entry entry : properties.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            if (value instanceof String[]) {
                String[] values = (String[]) value;
                for (int i = 0; i < values.length; i++) {
                    parameterMap.put(key, values[i]);
                }
            }
        }

        return parameterMap;
    }
}
