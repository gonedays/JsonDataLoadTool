本项目采用springboot框架，用gradle构建.
将json的数据导入数据库，RMDB现在采用mysql,同时预留nosql库的接口，可日后扩展.
打包执行gradle build,在build/lib目录下生成jar及其依赖的jar包,配置文件在生成的build/conf文件夹下.

若实现没有安装gradle可执行gradle wrapper，之后执行gradlew build打包

所有配置文件可放在jar包外，执行时将配置文件所在路径加入classpath即可，例如:

linux:
cdir=$(cd "$(dirname "$0")"; pwd)
java  -classpath ${cdir}/:${cdir}/libs/*:${cdir}/conf/:$CLASSPATH cn.com.nisong.Main  >>./console.$(date +%Y-%m-%d).log 2>&1  &

Windows:
set cdir=%cd%
java -cp ${cdir}/;${cdir}/libs/*;${cdir}/conf/;$CLASSPATH cn.com.nisong.Main >>./console.log




