set cdir=%cd%
echo %cdir%
java -cp %cdir%/libs/*;%cdir%/conf/;%CLASSPATH% cn.com.nisong.Main >>./console.log