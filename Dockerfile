FROM java:8

COPY target/todolist-0.1.0.jar /apps/todolist-0.1.0.jar

EXPOSE 8181

CMD ["java","-jar","/apps/todolist-0.1.0.jar"]