FROM java:8
MAINTAINER muyi317 <296280063@qq.com>

# 添加参数
ARG JAR_FILE

# 添加 Spring Boot 包
ADD target/${JAR_FILE} app.jar

EXPOSE 8090

# 执行启动命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
