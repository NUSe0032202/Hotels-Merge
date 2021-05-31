# Hotels Data Merge

## How to run

1. Run via Docker image  
   1. Pull the image `docker pull ghcr.io/nuse0032202/hotelmerge:v1`
   2. Run the image `docker run -p 8080:8080 ghcr.io/nuse0032202/hotelmerge:v1`
   3. You can specify another local port that the container binds to by changing the port after the -p tag
    
2. Run using JAR (Requires Java 11 to be installed on machine)  
   1. Navigate to https://github.com/NUSe0032202/Hotels-Merge/releases
   2. Under Assets download the JAR file
   3. Run the JAR file using the command `java -jar HotelsMerge-0.0.1-SNAPSHOT.jar`
