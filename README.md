# Hotels Data Merge

## How to run

1. Run via Docker image  
   1. Pull the image `docker pull ghcr.io/nuse0032202/hotelmerge:v1`
   2. Run the image `docker run -p 8080:8080 ghcr.io/nuse0032202/hotelmerge:v1`
   3. You can specify another local port that the container binds to by changing the port after the -p tag
    
2. Run using JAR (Requires at least Java 11 to be installed on host machine)  
   1. Navigate to https://github.com/NUSe0032202/Hotels-Merge/releases
   2. Under Assets download the JAR file
   3. Run the JAR file using the command `java -jar HotelsMerge-0.0.1-SNAPSHOT.jar`
    
3. Build and run via IDE
   1. IntelliJ can be used to build and run the project

For 1,2 and 3, the application merges the data during startup thus, allow some time for the application to start before
attempting to query the endpoints. The application should display similar logs to the picture attached below which would
indicate that it has started successfully.
    
## How to query for data  

1. Fetch results based on a list of hotel IDs (Using Postman)  
   1. Construct a post request to the endpoint `http://localhost:8080/retrieveHotelDetailsByHotelIds`
   2. Attach a JSON to the request body using the format: `{"ids":["f8c9","iJhz"]}`
   3. Send the request
    
2. Fetch results based on a given destination ID (Using Postman)  
   1. Construct a post request to the endpoint `http://localhost:8080/retrieveHotelDetailsByDestinationId`
   2. Attach a JSON to the request body using the format: `{"destinationId":5432}`
   3. Send the request
   
A txt file `merge.txt` explains the approach to the given problem
