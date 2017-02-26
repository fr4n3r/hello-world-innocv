package com.fran3r.conf;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.fran3r", includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Repository$"))
@Slf4j
public class MongoConfig extends AbstractMongoConfiguration  {

    @Value("${spring.data.mongodb.db}")
    private String databaseName;
    @Value("${spring.data.mongodb.port}")
    private Integer port;
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.timeout:10000}")
    private Integer timeout;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongo() throws Exception {

        return new MongoClient(new ServerAddress(host, port),
                               MongoClientOptions.builder().connectTimeout(timeout).build());
    }










    private void createCollection(Class clazz) {
        try {
            if (!mongoTemplate().collectionExists(clazz)) {
                mongoTemplate().createCollection(clazz);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to create collection ", e);
        }
    }
}
