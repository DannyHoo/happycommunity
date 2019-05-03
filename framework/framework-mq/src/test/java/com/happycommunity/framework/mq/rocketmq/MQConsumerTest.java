package com.happycommunity.framework.mq.rocketmq;

import com.happycommunity.framework.mq.AbstractSpringTest;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MQConsumerTest extends AbstractSpringTest {

    @Test
    public void sendMessageTest() throws InterruptedException, IOException, RemotingException, MQClientException, MQBrokerException {
        System.out.println("System Started!");
        read();
    }


    public void read(){
        System.out.println("ReadTest, Please Enter Data:");
        InputStreamReader is = new InputStreamReader(System.in);  //new构造InputStreamReader对象
        BufferedReader br = new BufferedReader(is);  //拿构造的方法传到BufferedReader中
        try{  //该方法中有个IOExcepiton需要捕获
            String name = br.readLine();
            System.out.println("ReadTest Output:" + name);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
