# camel-building-etl
Apache Camel을 이용한 도로명주소 파일 ETL 통합

## 0. Prerequisites
The zipkin server must be running on the local machine.

	docker run -d -p 9410:9410 -p 9411:9411 --name zipkin openzipkin/zipkin

ActiveMQ must be running on the local machine.

	$ACTIVEMQ_HOME/bin$ ./activemq

## 1. Package this project

    mvn clean package

## 2. Run this project with Camel maven plugin or

    mvn clean camel:run

## 3. Run this project with Spring boot maven plugin

    mvn clean spring-boot:run
