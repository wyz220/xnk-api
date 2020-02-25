#!/bin/sh
PID=$(cat /data/pay_single/pid/common-msht-web-0.0.1-SNAPSHOT.pid)
kill -9 $PID
