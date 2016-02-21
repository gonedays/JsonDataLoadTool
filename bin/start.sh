#!/bin/sh

cdir=$(cd "$(dirname "$0")"; pwd)
echo ${cdir}
cd ${cdir}

java -classpath ${cdir}/:${cdir}/libs/*:${cdir}/conf/:$CLASSPATH cn.com.pencho.ActivityTaskMain  >>./logs/console.$(date +%Y-%m-%d).log 2>&1  &

