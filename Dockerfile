FROM openjdk:13
ADD /build/libs/Calculator-1.0-SNAPSHOT.jar /calculator.jar
ENTRYPOINT ["java", "-jar", "calculator.jar"]