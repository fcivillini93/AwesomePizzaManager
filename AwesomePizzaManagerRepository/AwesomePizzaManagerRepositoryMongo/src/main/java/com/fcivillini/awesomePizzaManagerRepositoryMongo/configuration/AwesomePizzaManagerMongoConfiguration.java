package com.fcivillini.awesomePizzaManagerRepositoryMongo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Slf4j
@Configuration
@Import({
        AwesomePizzaManagerMongoMapperConfiguration.class,
        AwesmePizzaManagerMongoRepositoryConfiguration.class
})
public class AwesomePizzaManagerMongoConfiguration {

    public AwesomePizzaManagerMongoConfiguration() {
        log.info("springContext load [{}]", this.getClass().getSimpleName());
    }

    @Value("${spring.data.mongodb.uri}")
    private String mongoDbUri;

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        log.info("create connection to [{}]", mongoDbUri);
        return new SimpleMongoClientDatabaseFactory(mongoDbUri);
    }

    @Bean
    public MongoMappingContext mongoMappingContext() {
        return new MongoMappingContext();
    }
}
