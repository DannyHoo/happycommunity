package com.happycommunity.user.dao;

import com.alibaba.fastjson.JSON;
import com.happycommunity.user.AbstractTest;
import com.happycommunity.user.domain.UserDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny
 * @Title: UserDAOTest
 * @Description:
 * @Created on 2018-11-28 00:07:32
 */
public class UserDAOTest extends AbstractTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void insertUserTest(){
        UserDO userDO=new UserDO().setUserName("Song5").setMobileNo("13094805984").setPassword("123456").setEmail("123@qq.com").setSalt("dfd").setRealName("dfd").setIdCardNo("12312");
        int i=userDAO.insertUserDO(userDO);
        System.out.println(JSON.toJSONString(userDO));
    }

    @Test
    public void insertBatcjUserTest(){
        List<UserDO> userDOList=new ArrayList<UserDO>();
        userDOList.add(new UserDO().setUserName("Song6").setMobileNo("13094805984").setSalt("XREybXwZ").setPassword("123456").setEmail("123@qq.com").setSalt("dfd").setRealName("丹尼胡").setIdCardNo("130732199202103227"));
        userDOList.add(new UserDO().setUserName("Song7").setMobileNo("13094805984").setSalt("XREybXwZ").setPassword("123456").setEmail("123@qq.com").setSalt("dfd").setRealName("丹尼胡").setIdCardNo("130732199202103227"));
        int i=userDAO.insertUserDOBatch(userDOList);
        System.out.println(JSON.toJSONString(i));
    }
}
