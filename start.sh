#!/bin/bash
echo Starting application
nohup java -jar user/user-core/target/user-core-1.0-SNAPSHOT.jar &
nohup java -jar goods/goods-core/target/goods-core-1.0-SNAPSHOT.jar &
nohup java -jar order/order-core/target/order-core-1.0-SNAPSHOT.jar &
nohup java -jar business/business-core/target/business-core-1.0-SNAPSHOT.jar &