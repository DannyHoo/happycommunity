#!/bin/sh
export USER=user/user-core/target/user-core-1.0-SNAPSHOT.jar
export GOODS=goods/goods-core/target/goods-core-1.0-SNAPSHOT.jar
export ORDER=order/order-core/target/order-core-1.0-SNAPSHOT.jar
export BUSINESS=business/business-core/target/business-core-1.0-SNAPSHOT.jar

export USER_port=8761
export GOODS_port=4001
export ORDER_port=9999
export BUSINESS_port=3001

case "$1" in

start)
        ## 启动eureka
        echo "--------eureka 开始启动--------------"
        nohup java -jar $USER >/dev/null 2>&1 &
        USER_pid=`lsof -i:$USER_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$USER_pid" ]
            do
              USER_pid=`lsof -i:$USER_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "USER pid is $USER_pid"
        echo "--------eureka 启动成功--------------"

        ## 启动config
        echo "--------开始启动GOODS---------------"
        nohup java -jar $GOODS >/dev/null 2>&1 &
        GOODS_pid=`lsof -i:$GOODS_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$GOODS_pid" ]
            do
              GOODS_pid=`lsof -i:$GOODS_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "GOODS pid is $GOODS_pid"
        echo "---------GOODS 启动成功-----------"

        ## 启动gateway
        echo "--------开始启动ORDER---------------"
        nohup java -jar $ORDER >/dev/null 2>&1 &
        ORDER_pid=`lsof -i:$ORDER_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$ORDER_pid" ]
            do
              ORDER_pid=`lsof -i:$ORDER_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "ORDER pid is $ORDER_pid"
        echo "---------ORDER 启动成功-----------"

        ## 启动auth
        echo "--------开始启动BUSINESS---------------"
        nohup java -jar $BUSINESS >/dev/null 2>&1 &
        BUSINESS_pid=`lsof -i:$BUSINESS_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$BUSINESS_pid" ]
            do
              BUSINESS_pid=`lsof -i:$BUSINESS_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "BUSINESS pid is $BUSINESS_pid"
        echo "---------BUSINESS 启动成功-----------"

        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $USER | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===USER process not exists or stop success"
        else
            kill -9 $P_ID
            echo "USER killed success"
        fi
		P_ID=`ps -ef | grep -w $GOODS | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===GOODS process not exists or stop success"
        else
            kill -9 $P_ID
            echo "GOODS killed success"
        fi
		 P_ID=`ps -ef | grep -w $ORDER | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===ORDER process not exists or stop success"
        else
            kill -9 $P_ID
            echo "ORDER killed success"
        fi
		 P_ID=`ps -ef | grep -w $BUSINESS | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===BUSINESS process not exists or stop success"
        else
            kill -9 $P_ID
            echo "BUSINESS killed success"
        fi

        echo "===stop success==="
        ;;

restart)
        $0 stop
        sleep 2
        $0 start
        echo "===restart success==="
        ;;
esac
exit 0