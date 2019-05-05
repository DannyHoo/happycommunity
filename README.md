# happycommunity
自己学习做的小Demo，系统包含5个模块，user（用户模块）、order（订单模块）、goods（商品模块）、business（业务模块）、gateway（网关模块），还有一个底层类库framework（包含对公共bean、基础工具类、消息队列、缓存、分布式锁等的封装）

整体集成：springboot

分布式远程调用：dubbo

ORM框架：mybatis

日志：logback

缓存：Redis

分布式锁：Redis分布式锁、Zookeeper分布式锁、DB分布式锁

消息队列：RocketMQ


![](HappyCommunity.png)


启动：
```
nohup java -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -jar **.jar
```