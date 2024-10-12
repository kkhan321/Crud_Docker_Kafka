FROM openjdk:21
ADD target/task_docker_kafka.jar task_docker_kafka.jar
ENTRYPOINT ["java","-jar","/task_docker_kafka.jar"]
