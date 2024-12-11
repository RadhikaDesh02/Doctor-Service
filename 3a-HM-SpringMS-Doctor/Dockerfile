
FROM openjdk:8u131-jdk-alpine
EXPOSE 9092
COPY ./target/DoctorService.jar DoctorService.jar
ENTRYPOINT ["java","-jar","/DoctorService.jar"]