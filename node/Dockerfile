FROM openjdk:8

EXPOSE 7070

WORKDIR /home/weai

ADD ./target/*.jar /home/weai/app.jar

CMD ["java","-jar", "/home/weai/app.jar"]
