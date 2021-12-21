package com.manoj.aws.pvd;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.manoj.aws.pvd.model.ApiClientKeys;
import com.manoj.aws.pvd.model.SwiftValidationTransactionKeys;

@SpringBootTest
public class CreateDynamoDBSwiftMessageTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateDynamoDBApiClientTest.class);

	@Autowired
	private DynamoDB dynamoDB;

	@Test
	public void createSwiftMessageTxnTable() {

		try {
			LOGGER.info("Attempting to create table; please wait...");
			Table table = dynamoDB.createTable(SwiftValidationTransactionKeys.TABLE.keyName(),
					Arrays.asList(new KeySchemaElement(SwiftValidationTransactionKeys.TRANSACTION_ID.keyName(),
							KeyType.HASH)),
					Arrays.asList(new AttributeDefinition(SwiftValidationTransactionKeys.TRANSACTION_ID.keyName(),
							ScalarAttributeType.S)),
					new ProvisionedThroughput(10L, 10L));
			table.waitForActive();
			LOGGER.info("Success.  Table status: " + table.getDescription().getTableStatus());

		} catch (Exception e) {
			LOGGER.error("Unable to create table: ");
			LOGGER.error(e.getMessage());
		}
	}

	@Disabled
	@Test
	public void deleteSwiftMessageTxnTable() {

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
}
