# goodcitizens
Good Citizens microservice 

This is a little Microservice developed with Maven, Java, Spring Boot and Apache Kafka.

COMPILE AND EXECUTION INFORMATION

A Maven installation is required to compile, to execute Junit Tests and to start the application.
Useful Maven Commands:
("mvn" for using the various commands):
- clean: command used to delete all files generated from a previous compilation
- install: command used to download libraries dipendencies and to execut JUnit tests.
- -DskipTests: command used to skip Junit Tests execution
It is possible to combine those commands (e.g. mvn clean install -DskipTests)

The microservice uses Apache Kafka as notification system to manage and to notify data modification to the other microservices.
Kafka can be download a this page: https://kafka.apache.org/downloads.html 
Kafka up and running is necessary for starting the application.

From Kafka folder the following commands must be used in sequence to start Kafka:
1) start Zookeeper broker
bin\windows\zookeeper-server-start.bat config\zookeeper.properties (on windows systems)
bin/zookeeper-server-start.bat config/zookeeper.properties (on linux systems)
2) Start Kafka
bin\windows\kafka-server-start.bat config\server.properties (on windows systems)
bin/kafka-server-start.bat config/server.properties (on linux systems)

The following command can be used to read info from topics (where notification where send) After starting Kafka:
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic create_citizens --from-beginning (on windows systems)
bin/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic create_citizens --from-beginning (on linux systems)
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic update_citizens --from-beginning (on windows systems)
bin/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic update_citizens --from-beginning (on linux systems)
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic delete_citizens --from-beginning (on windows systems)
bin/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic delete_citizens --from-beginning (on linux systems)

Show topic list:
bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092 (on windows systems)
bin/kafka-topics.bat --list --bootstrap-server localhost:9092 (on linux systems)

The microservice creates automatically three topics:
create_citizens : topic where info about new citizen created were notified
update_citizens : topic where info about citizen updated were notified
delete_citizens : topic where info about citizen deleted were notified

The microservice uses different topics, one for each data modification type, becouse those are different notification stream: another microservice could be interested only in one of them.

The microservice use the H2 in memory database with some citizen records, therefore any external database is not required: The database will load during the bootstrap of the application.

The Bootstrap class is the class "Bootstrap". This class contained the main method.

MICROSERVICE DESCRIPTION

The microservice has an entire CRUD API for citizens managament:
1) Read Citizen with some filter as query param in AND or OR (name, surname, nickname, email, country) (GET method: http://localhost:8080/api/goodcitizens/citizen)
2) Create Citizen (POST method: http://localhost:8080/api/goodcitizens/citizen)
3) Update Citizen (PUT method: http://localhost:8080/api/goodcitizens/citizen/{code})
4) Delete Citizen (DELETE method: http://localhost:8080/api/goodcitizens/citizen/{code})

For more information about the APIs: http://localhost:8080/v2/api-docs
For heath check (implemented with spring actuator) : http://localhost:8080/actuator/health
A postman collection is provided with all those rest calls.

The scaffolding microservice is divide in three layer: 
1) Resourse: handler layer for the Rest Services
2) Business : layer for data managament and notification management
3) Persistence : layer for data persistence on the database

All business logic is collected in the business layer. Here data ara validated following basic syntax and domain rules (e.g valid emails format, unique nicknames and emails, space normalization) and custom exeption are provided for uncorrect data.  
The microservice uses functional interfaces for simplify management and implementation of new solutions avoiding massive refactoring.
The microservice implements also a TO (Transfer Object) pattern for simplify object managament. 
