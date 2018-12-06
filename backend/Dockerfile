FROM java:8-alpine
COPY application/target/engine-application-*.jar /usr/bin/spacedefense.jar
WORKDIR /usr/bin

EXPOSE 8080

ENV "data-dir" /data/test
VOLUME "/data/test"

ENTRYPOINT ["java"]
CMD ["-jar", "/usr/bin/spacedefense.jar"]
