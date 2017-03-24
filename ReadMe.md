# camel-building-etl
Apache Camel을 이용한 도로명주소 파일 ETL 통합

## 0. Preliminaries
The zipkin server must be running on the local machine.

ActiveMQ must be running on the local machine.

## 1. Package this project

    mvn clean package

## 2. Run this project with Camel maven plugin or

    mvn clean camel:run

## 3. Run this project with Spring boot maven plugin

    mvn clean spring-boot:run
