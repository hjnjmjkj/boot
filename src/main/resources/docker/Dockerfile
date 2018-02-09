FROM java:8
VOLUME /tmp
ADD ssm4-0.0.1-SNAPSHOT.jar ssm4.jar
RUN bash -c 'touch /ssm4.jar'
EXPOSE 8866
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/ ./urandom","-jar","/ssm4.jar"]