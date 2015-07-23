FROM java:8

COPY target/todolist-cassandra-0.1.0.jar /apps/todolist-cassandra-0.1.0.jar

EXPOSE 8181

CMD java -DDB_HOST=$DB_HOST -jar /apps/todolist-cassandra-0.1.0.jar
