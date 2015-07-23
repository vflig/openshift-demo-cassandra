FROM java:8

COPY target/todolist-cassandra-0.1.0.jar /apps/todolist-cassandra-0.1.0.jar

EXPOSE 8181

CMD java -jar /apps/todolist-cassandra-0.1.0.jar --DB_HOST=$DB_HOST --DB_USERNAME=$DB_USERNAME --DB_PASSWORD=$DB_PASSWORD
