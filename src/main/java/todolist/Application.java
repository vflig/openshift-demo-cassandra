package todolist;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

        System.out.println("Connecting to db: " + System.getProperty("DB_HOST"));

        Cluster cluster = Cluster.builder()
                .addContactPoints(System.getProperty("DB_HOST").split(","))
                .build();

        Session session = cluster.connect();
        session.execute("CREATE KEYSPACE IF NOT EXISTS todolist WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 2 };");
        session.execute("CREATE TABLE IF NOT EXISTS todolist.todo (" +
                        "id int PRIMARY KEY," +
                        "text text);");
        SpringApplication.run(Application.class, args);
    }
}