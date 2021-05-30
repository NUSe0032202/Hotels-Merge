FROM adoptopenjdk/openjdk11
MAINTAINER benjaminang
COPY target/HotelsMerge-0.0.1-SNAPSHOT.jar HotelsMerge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/HotelsMerge-0.0.1-SNAPSHOT.jar"]
