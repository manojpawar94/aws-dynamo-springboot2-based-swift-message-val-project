package com.manoj.aws.pvd;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.manoj.aws.pvd.model.ApiClientKeys;

@SpringBootTest
public class CreateDynamoDBApiClientTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateDynamoDBApiClientTest.class);

	@Autowired
	private DynamoDB dynamoDB;

	@Disabled
	@Test
	public void createApiClientTable() {

		try {
			LOGGER.info("Attempting to create table; please wait...");
			Table table = dynamoDB.createTable(ApiClientKeys.TABLE.keyName(),
					Arrays.asList(new KeySchemaElement(ApiClientKeys.API_KEY.keyName(), KeyType.HASH)),
					Arrays.asList(new AttributeDefinition(ApiClientKeys.API_KEY.keyName(), ScalarAttributeType.S)),
					new ProvisionedThroughput(10L, 10L));
			table.waitForActive();
			LOGGER.info("Success.  Table status: " + table.getDescription().getTableStatus());

		} catch (Exception e) {
			LOGGER.error("Unable to create table: ");
			LOGGER.error(e.getMessage());
		}
	}

	@ParameterizedTest
	@MethodSource("apiClientTestData")
	public void insertApiClientTable(final String apiKey, final String clientId, final String clientName,
			final List<String> authorities) {

		Table table = dynamoDB.getTable(ApiClientKeys.TABLE.keyName());

		try {
			System.out.println("Adding a new item...");
			PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey(ApiClientKeys.API_KEY.keyName(), apiKey)
					.withString(ApiClientKeys.CLIENT_ID.keyName(), clientId)
					.withString(ApiClientKeys.CLIENT_NAME.keyName(), clientName)
					.withList(ApiClientKeys.AUTHAURITIES.keyName(), authorities));

			System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

		} catch (Exception e) {
			System.err.println("Unable to add item: " + apiKey + " " + clientName);
			System.err.println(e.getMessage());
		}
	}

	@Disabled
	@Test
	public void deleteApiClientTable() {

		Table table = dynamoDB.getTable(ApiClientKeys.TABLE.keyName());

		try {
			System.out.println("Attempting to delete table; please wait...");
			table.delete();
			table.waitForDelete();
			System.out.print("Success.");

		} catch (Exception e) {
			System.err.println("Unable to delete table: ");
			System.err.println(e.getMessage());
		}
	}

	static Stream<Arguments> apiClientTestData() {
		return Stream.of(
				Arguments.of("210a0d7e-5d39-4bd0-9e22-90f4799b78e4", "1001", "Admin",
						Arrays.asList("hello", "validation", "health", "admin")),
				Arguments.of("5a862b8d-f2d2-45e0-9149-da4e84cfd012", "1002", "PayPal",
						Arrays.asList("hello", "validation")));
	}
}
