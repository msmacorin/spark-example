FROM maven:3.6.3-jdk-14

ADD . /usr/src/macorin
WORKDIR /usr/src/macorin
EXPOSE 4567
ENTRYPOINT ["mvn", "clean", "verify", "exec:java"]
