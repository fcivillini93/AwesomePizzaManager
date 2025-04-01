package com.fcivillini.awesomePizzaManagerRepositoryMongo.util;

import com.fcivillini.awesomePizzaManagerRepositoryMongo.configuration.AwesomePizzaManagerMongoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
        "spring.data.mongodb.database=test"
})
@ContextConfiguration(classes = {
        AwesomePizzaManagerMongoConfiguration.class,
})
public abstract class AbstractMongoTest {

    @Container
    protected static final MongoDBContainer mongoDbContainer = new MongoDBContainer(
            DockerImageName.parse("mongo:7.0.14"))
            .withExposedPorts(27017);

    @BeforeAll
    static void testClassSetup() {
        mongoDbContainer.start();
        String host = mongoDbContainer.getHost();
        Integer port = mongoDbContainer.getMappedPort(27017);
        String uri = "mongodb://" + host + ":" + port + "/test";

        System.setProperty("spring.data.mongodb.uri", uri);
        log.info("MongoDB started at {}", uri);
    }

    @AfterAll
    static void testClassCleanup() {
        mongoDbContainer.stop();
    }

}