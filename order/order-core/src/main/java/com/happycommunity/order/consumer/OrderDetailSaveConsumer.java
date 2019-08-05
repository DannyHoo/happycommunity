package com.happycommunity.order.consumer;

import com.alibaba.fastjson.JSON;
import com.happycommunity.framework.common.model.dto.order.OrderDetailDTO;
import com.happycommunity.framework.common.model.result.ServiceResult;
import com.happycommunity.framework.core.util.BeanUtil;
import com.happycommunity.framework.mq.rocketmq.MQTopicEnum;
import com.happycommunity.framework.mq.rocketmq.consumer.BaseMQConsumer;
import com.happycommunity.order.config.SystemConfig;
import com.happycommunity.order.domain.OrderDO;
import com.happycommunity.order.parameter.OrderDetailListParameter;
import com.happycommunity.order.parameter.OrderParameter;
import com.happycommunity.order.service.OrderDetailService;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class OrderDetailSaveConsumer extends BaseMQConsumer {

    private final String group = MQTopicEnum.ORDER_DETAIL_SAVE.getGroup();
    private final String topic = MQTopicEnum.ORDER_DETAIL_SAVE.getTopic();
    private final String tag = MQTopicEnum.ORDER_DETAIL_SAVE.getTag();

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private SystemConfig systemConfig;

    public OrderDetailSaveConsumer() {
    }

    @PostConstruct
    public void init(){
        try {
            setGroupAndNameSrvAddr(group,systemConfig.getMqConsumerNamesrvAddr());
            listen(topic, tag);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean doConsume(Object messageContent){
        OrderDetailListParameter orderDetailListParameter=null;
        try{
            orderDetailListParameter=(OrderDetailListParameter)messageContent;
            ServiceResult<List<OrderDetailDTO>> result= orderDetailService.saveOrderDetailList(orderDetailListParameter);
            if (result.isSuccess()){
                System.out.println("订单明细入库成功");
                return true;
            }else{
                System.out.println("订单明细入库失败："+ JSON.toJSONString(orderDetailListParameter));
                return false;
            }
        }catch (Exception e){
            if (e.getCause() != null && e.getCause().getCause() != null && StringUtils
                    .contains(e.getCause().getCause().getMessage(), "Duplicate entry")) {
                System.out.println("订单明细已存在："+ JSON.toJSONString(orderDetailListParameter));
                return true;
            }
            System.out.println("订单明细入库失败："+ JSON.toJSONString(orderDetailListParameter));
            e.printStackTrace();
            return false;
        }

        /*重复消费会因为逐渐冲突报错
        * Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'OD20190601221052010423-G20180614160305682062' for key 'orderNo'
	at sun.reflect.GeneratedConstructorAccessor37.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:404)
	at com.mysql.jdbc.Util.getInstance(Util.java:387)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:934)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3966)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3902)
	at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:2526)
	at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2673)
	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2549)
	at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1861)
	at com.mysql.jdbc.PreparedStatement.execute(PreparedStatement.java:1192)
	at com.alibaba.druid.pool.DruidPooledPreparedStatement.execute(DruidPooledPreparedStatement.java:498)
	at sun.reflect.GeneratedMethodAccessor38.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:59)
	at com.sun.proxy.$Proxy65.execute(Unknown Source)
	at org.apache.ibatis.executor.statement.PreparedStatementHandler.update(PreparedStatementHandler.java:46)
	at org.apache.ibatis.executor.statement.RoutingStatementHandler.update(RoutingStatementHandler.java:74)
	at org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:50)
	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)
	at org.apache.ibatis.executor.CachingExecutor.update(CachingExecutor.java:76)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:198)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:185)
	at sun.reflect.GeneratedMethodAccessor36.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:433)
	... 24 more
        * */
    }
}
