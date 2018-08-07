# use the official centos docker image
FROM allpay/jdk8
MAINTAINER Sanzhar Aubakirov, sanzhar.aubakirov@allpay.kz

######## JDK

WORKDIR /
VOLUME /tmp
ADD stairs.water-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
