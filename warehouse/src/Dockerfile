FROM openjdk:15

ADD ./target/warehouse-0.0.1-SNAPSHOT.jar /usr/src/hotel-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "warehouse-0.0.1-SNAPSHOT.jar"]