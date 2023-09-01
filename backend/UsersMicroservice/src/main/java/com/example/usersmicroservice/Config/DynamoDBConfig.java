package com.example.usersmicroservice.Config;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.*;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.usersmicroservice.Repositories")
public class DynamoDBConfig {

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @Value("${amazon.aws.region}")
    private String region;

    @Value("${amazon.dynamodb.endpoint}")
    private String endpoint;

    private AWSCredentialsProvider awsDynamoDBCredentials() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey));
    }

    @Primary
    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {

        return DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.EVENTUAL)
                .withTableNameOverride(null)
                .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.LAZY_LOADING)
                .build();
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,
                                         DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(awsDynamoDBCredentials()).build();

        // Check if table exists. If not, create it.
        if (!doesTableExist(amazonDynamoDB, "users")) {
            CreateTableRequest request = new CreateTableRequest()
                    .withTableName("users")
                    .withKeySchema(new KeySchemaElement("email", KeyType.HASH)) // email as primary key
                    .withAttributeDefinitions(new AttributeDefinition("email", ScalarAttributeType.S))
                    .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L)); // adjust based on your needs

            amazonDynamoDB.createTable(request);
        }

        return amazonDynamoDB;
    }

    private boolean doesTableExist(AmazonDynamoDB amazonDynamoDB, String tableName) {
        try {
            amazonDynamoDB.describeTable(tableName);
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }
}
