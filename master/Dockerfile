FROM openjdk:8

EXPOSE 8080

WORKDIR /home/weai

ADD ./target/*.jar /home/weai/app.jar

CMD ["java","-jar", "/home/weai/app.jar"]

# docker build -t --no-cache we-ai-user-center-test .

# docker run -itd --name we-ai-user-center-test -p 14001:14001 we-ai-user-center-test

# docker logs we-ai-user-center-test -f