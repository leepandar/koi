FROM registry.cn-hangzhou.aliyuncs.com/dockerhub_mirror/mysql-server:8.0.32

MAINTAINER lida <leepandar@163.com>

ENV TZ=Asia/Shanghai
ENV LANG C.UTF-8

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./schema.sql /docker-entrypoint-initdb.d

COPY ./koi.sql /docker-entrypoint-initdb.d

COPY ./koi_config.sql /docker-entrypoint-initdb.d

COPY ./koi_job.sql /docker-entrypoint-initdb.d

COPY ./koi_bpm.sql /docker-entrypoint-initdb.d
