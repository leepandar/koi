FROM registry.cn-hangzhou.aliyuncs.com/dockerhub_mirror/java:21-anolis

MAINTAINER lida <leepandar@163.com>

ENV TZ=Asia/Shanghai
ENV LANG C.UTF-8
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /koi-job-client

WORKDIR /koi-job-client

EXPOSE 7003

ADD ./target/koi-job-client.jar ./

CMD sleep 30;java $JAVA_OPTS -jar koi-job-client.jar
