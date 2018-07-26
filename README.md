# Payments

This is a simple Java / Maven / Spring Boot (version 1.4.3) / Hbase Rest API
application. The idea was to keep the used case simple with many assumptions in the
incomming JSON body data, data store design, api security etc.. . Each of the said
has the level of technical competency - advantages and disadvantages.

The same can be discussed in detail while demo, touch basing the crutial aspects of
design and development. This application has the MVC pattern along with the RESTful
service. Simple JSP pages can be found with the controllers as /login,
/accountcreation, /viewbalance, /amounttransaction.

Requirements

1) Java - 1.8.x

2) Maven - 3.x.x

3) Install Hbase - 1.2

About the Service's

" /createaccount " - The service is used create the account of the user.

" /transferamount " - The service is used to transfer the amount to the Payee.

Here is what this simple application demonstrates:

1) Integration with the Spring Boot Framework: inversion of control, dependency
injection, etc.

2) Packaging as a single war with embedded container : No need to install a
container separately on the host just run using the java -jar command

3) Writing a RESTful service using annotation: supports JSON request / response;
simply use desired Accept header in your request

4) Exception mapping from application exceptions to the right HTTP response with
exception details in the body

5) Spring Data Integration with Hbase.

The app will start running at http://localhost:8080.

Endpoints Rest APIs - Use PostMan to run the below.

1) POST /createaccount
Accept: application/json
Content-Type: application/json

{
"amount":"200",
"accountId": "888888",
"name":"Kumar",
"age":"20"
}

RESPONSE: HTTP 200(OK)
Location header: http://localhost:8080/createaccount

2) POST /transferamount
Accept: application/json
Content-Type: application/json

{
"fromAccountId":"0",
"toAccountId"
: "1",
"amount":"90"
}

RESPONSE: HTTP 200(OK)
Location header: http://localhost:8080/transferamount

You can test them using postman or any other rest client. Through Curl as below.

curl -d '{"fromAccountId":"0","toAccountId":"1","amount":"90"}' -H "Content-Type:
application/json" -X POST http://localhost:8080/transferamount

Advantage of HBase:

HBase is a remarkable tool for indexing mass volumes of data
Schema-less in database
Can store large data sets on top of HDFS file storage and will aggregate and analyze
billions of rows present in the HBase tables
In HBase, the database can be shared
Operations such as data reading and processing will take small amount of time as
compared to traditional relational models
Random read and write operations
For online analytical operations, HBase is used extensively.
For example: In banking applications such as real-time data updates in ATM machines,
HBase can be used.

Limitations with HBase:

We cannot expect completely to use HBase as a replacement for traditional models.
Some of the traditional models features cannot support by HBase
HBase cannot perform functions like SQL. It doesn't support SQL structure, so it
does not contain any query optimizer
HBase is CPU and Memory intensive with large sequential input or output access while
as Map Reduce jobs are primarily input or output bound with fixed memory.
HBase integrated with Map-reduce jobs will result in unpredictable latencies
HBase integrated with pig and Hive jobs results in some time memory issues on
cluster
In a shared cluster environment, the set up requires fewer task slots per node to
allocate for HBase CPU requirements

Advantages of Spring Boot:

Spring Boot aims to enable production ready applications in quick time.
Spring Boot Auto Configuration
Simplified & version conflict free dependency management through the starter POMs.
We can quickly setup and run standalone, web applications and micro services at very
less time.
You can just assemble the jar artifact which comes with an embedded Tomact, Jetty or
Undertow application server.
Spring Boot provides HTTP endpoints to access application internals like detailed
metrics, application inner working, health status, etc.
No XML based configurations at all. Very much simplified properties. The beans are
initialized, configured and wired automatically.
The Spring Initializer provides a project generator to make you productive with the
certain technology stack from the beginning. You can create a skeleton project with
web, data access (relational and NoSQL datastores), cloud, or messaging support.

Run as

java -jar Payments-0.1.0.jar
