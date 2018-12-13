package com.happycommunity.gateway.controller.test;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.common.model.enums.ResultStatusEnum;
import com.happycommunity.framework.core.util.RSAUtil;
import com.happycommunity.gateway.response.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

/**
 * @author Danny
 * @Title: TestController
 * @Description:
 * @Created on 2018-11-23 16:36:40
 */
@Controller
@RequestMapping("/public/test")
public class PublicTestController {

    @RequestMapping("/getRandomData")
    @ResponseBody
    public ResponseData getRandomData(HttpServletRequest httpServletRequest){
        ResponseData responseData=new ResponseData(ResultStatusEnum.SUCCESS,new Random().nextDouble());
        System.out.println(JSON.toJSONString(responseData));
        return responseData;
    }

    @RequestMapping("/rsaEncrypt")
    @ResponseBody
    public ResponseData rsaEncrypt(@RequestBody(required=true) Map<String,Object> map) throws Exception {
        String content=map.get("content").toString();
        String publicKey=map.get("publicKey").toString();
        String encryptContent= RSAUtil.encrypt(content,publicKey);
        return new ResponseData().setData(encryptContent);
    }
}
