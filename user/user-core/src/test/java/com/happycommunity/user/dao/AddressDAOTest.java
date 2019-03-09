package com.happycommunity.user.dao;

import com.happycommunity.framework.core.util.RandomValueUtil;
import com.happycommunity.framework.core.util.StringUtil;
import com.happycommunity.user.AbstractTest;
import com.happycommunity.user.domain.AddressDO;
import com.happycommunity.user.domain.UserDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author Danny
 * @Title: AddressDAOTest
 * @Description:
 * @Created on 2019-02-25 18:36:59
 */
public class AddressDAOTest extends AbstractTest {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AddressDAO addressDAO;

    @Test
    public void batchInsertTest(){
        List<UserDO> userDOList=userDAO.findAll();
        for (UserDO userDO:userDOList){
            Map randomUserMap = RandomValueUtil.getAddress();
            addressDAO.saveAddress((AddressDO)new AddressDO()
                    .setUserName(userDO.getUserName())
                    .setReceiverName(randomUserMap.get("name").toString())
                    .setReceiverMobileNo(randomUserMap.get("tel").toString())
                    .setReceiverAddress(randomUserMap.get("road").toString())
                    .setIsDefault(10)
                    .setComment(randomUserMap.get("road").toString()));
        }
    }
}
