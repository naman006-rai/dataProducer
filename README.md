# dataProducer
Data Producer Service

Please use docker for running the rabbit MQ image. 
Download Docker from here-> https://www.docker.com/products/docker-desktop

Command is mentioned below for fetching it.

docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
docker ps


Please install the rabbit MQ and make sure it's running before running the application.

1)- It is using event driven architecture (Rabbit MQ). 
As soon as a the store and update endpoints will be triggered, 
this producer service send(data) the message pushed to queue(Exchange).

2)- Before triggering any endpoint please make sure that both dataProducer and dataConsumer Services are Up and running,
otherwise it will fail and give error because from data consumer service it's using "/read" endpoint 
for checking the existence of file while creating or updating.

3)- Post Request -> http://localhost:9000/store
 Request Header:
  fileType : csv Or xml
  
 Request body :     
 {
    "name":"M",
    "age":24,
    "salary":2,
    "dob":"12/09/2021"
 }
 
 Expected Response -> Data Stored Successfully
 
 4)- Post Request -> http://localhost:9000/update?name=M
 Request Header:
  fileType : csv Or xml
  
  Request Param :
  name  = M
 Request body :     
 {
    "name":"MM",
    "age":24,
    "salary":2,
    "dob":"12/09/2021"
 }
 
 Expected Response -> Data Updated Successfully
    

2)- It is exposing a rest get endpoint which will be used by consumer facing api for fetching the data stored in files(Accordance with fileType header).
While testing this endpoint dataConsumer service should be running because it's consuming the get endpoint of dataConsumer Service.

Get Request -> http://localhost:9000/read






