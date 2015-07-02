FROM java:8

COPY target/todolist-0.1.0.jar /apps/todolist-0.1.0.jar

EXPOSE 8181
ENV MYSQL_USERNAME blup
ENV MYSQL_HOST 192.168.59.103

CMD ["java","-jar","/apps/todolist-0.1.0.jar"]