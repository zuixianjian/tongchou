#!/bin/bash
#判断进程是否存在，如果不存在就启动它如果存在就重启它
PIDS=`ps -ef |grep xboot-admin-1.0-SNAPSHOT.jar |grep -v grep | awk '{print $2}'`
if [ "$PIDS" != "" ]; then
	kill -9 $PIDS
fi

#ps -ef | grep testcode.jar | grep -v grep | awk '{print $2}' | xargs kill -9
pwd
cd /www/wwwroot/project.66hp.top
#一定要加上下面这行代码 否则nohup不会成功运行jar包
BUILD_ID=DONTKILLME 
nohup java -jar xboot-admin-1.0-SNAPSHOT.jar --spring.profiles.active=dev >xboot.out 2>&1 &