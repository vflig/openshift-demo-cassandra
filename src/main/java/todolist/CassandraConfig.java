package todolist;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Created by Johannes Pieringer on 20.07.2015.
 */
@Configuration
@EnableCassandraRepositories(basePackages = { "todolist.repository" })
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    public String getKeyspaceName() {
        return "todolist";
    }

    @Override
    protected String getContactPoints() {
        return System.getProperty("DB_HOST");
    }


}