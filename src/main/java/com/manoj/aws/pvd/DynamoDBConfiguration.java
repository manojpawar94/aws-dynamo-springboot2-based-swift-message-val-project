package com.manoj.aws.pvd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
public class DynamoDBConfiguration {

	/**
	 * Initializing the DynamoDB Client
	 * 
	 * @return
	 */
	@Bean
	public DynamoDB dynamoDB() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		DynamoDB dynamoDB = new DynamoDB(client);
		return dynamoDB;
	}
}
