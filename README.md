# GoodCitizens
Good Citizens microservice 

This is a little Microservice developed with Maven, Java, Spring Boot and Apache Kafka.

COMPILE AND EXECUTION INFORMATION

A Maven installation is required to compile, to execute Junit Tests and to start the application.
Useful Maven Commands:
("mvn" for using the various commands):
- clean: command used to delete all files generated from a previous compilation
- install: command used to download libraries dipendencies and to execut JUnit tests.
- -DskipTests: command used to skip Junit Tests execution 


It is possible to combine those commands (e.g. mvn clean install -DskipTests).

The microservice uses Apache Kafka as notification system to manage and to notify data modification to the other microservices.
<br />
Kafka can be download a this page: https://kafka.apache.org/downloads.html 
<br />
Kafka up and running is necessary for starting the application.

From Kafka folder the following commands must be used in sequence to start Kafka:
1) start Zookeeper Broker: <br />
bin\windows\zookeeper-server-start.bat config\zookeeper.properties (on windows systems)
bin/zookeeper-server-start.bat config/zookeeper.properties (on linux systems)
2) start Kafka: <br />
bin\windows\kafka-server-start.bat config\server.properties (on windows systems)
bin/kafka-server-start.bat config/server.properties (on linux systems)

The following command can be used to read info from topics (where notification where send) After starting Kafka: <br />
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic create_citizens --from-beginning (on windows systems) <br />
bin/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic create_citizens --from-beginning (on linux systems) <br />

bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic update_citizens --from-beginning (on windows systems) <br />
bin/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic update_citizens --from-beginning (on linux systems) <br />

bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic delete_citizens --from-beginning (on windows systems) <br />
bin/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic delete_citizens --from-beginning (on linux systems) <br />

Show topic list: <br />
bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092 (on windows systems) <br />
bin/kafka-topics.bat --list --bootstrap-server localhost:9092 (on linux systems) <br />

The microservice creates automatically three topics:
- create_citizens : topic where info about new citizens created were notified
- update_citizens : topic where info about citizens updated were notified
- delete_citizens : topic where info about citizens deleted were notified

The microservice uses different topics, one for each data modification type (Create, Update, Delete), because those are different notification streams: another microservice could be interested only in one of them. The microservice uses the H2 in memory database with some citizen records, therefore any external database is not required: The database will be loaded during the bootstrap of the application.
<br />
The Bootstrap class is the class "Bootstrap". This class contained the main method.

MICROSERVICE DESCRIPTION

The microservice has an entire CRUD API for citizens managament:
1) Read Citizens with some optional filters as query param in AND or OR (name, surname, nickname, email, country) (GET method: http://localhost:8080/api/goodcitizens/citizen). By default filters are in AND         
2) Create Citizen (POST method: http://localhost:8080/api/goodcitizens/citizen)
3) Update Citizen (PUT method: http://localhost:8080/api/goodcitizens/citizen/{code})
4) Delete Citizen (DELETE method: http://localhost:8080/api/goodcitizens/citizen/{code})

For more information about the APIs: http://localhost:8080/v2/api-docs or http://localhost:8080/swagger-ui.html. <br />
For health check (implemented with spring actuator) : http://localhost:8080/actuator/health. <br />
All those rest calls can be invoked when the microservice is up and running. A postman collection is provided with all those rest calls. <br />

The scaffolding of the microservice is divided in three layer: 
1) Resource/Api : handler layer for the Rest Services
2) Business : layer for data management and notification management
3) Persistence : layer for data persistence on the database

All business logic is collected in the business layer. Here data are validated following basic syntax and domain rules (e.g valid emails format, unique nicknames and emails, space normalization) and custom exception are provided for uncorrected data.  <br />
The microservice uses functional interfaces for simplify management and implementation of new solutions avoiding massive refactoring.<br />
The microservice implements also a TO (Transfer Object) pattern for simplify object management. <br />
Also the microservice implements a tracking mechanism to better track information and activities: this mechanism is an id, correlatioId. The id is searched among the headers of each request and if it is not present, a new id is generated. The id is also present in every notifications on Kafka.
