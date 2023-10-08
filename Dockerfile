FROM java:8

MAINTAINER WXX

COPY ./target/TextWxx.jar /tmp/TextWxx.jar

EXPOSE 6060

ENTRYPOINT java -jar /tmp/TextWxx.jar