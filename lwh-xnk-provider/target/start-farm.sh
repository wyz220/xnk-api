#!/bin/sh
java -jar common-msht-web-0.0.1-SNAPSHOT.jar &      
echo $! > /data/pay_single/pid/common-msht-web-0.0.1-SNAPSHOT.pid

