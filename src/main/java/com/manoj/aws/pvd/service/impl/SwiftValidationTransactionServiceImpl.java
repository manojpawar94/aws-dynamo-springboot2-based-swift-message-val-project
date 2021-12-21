package com.manoj.aws.pvd.service.impl;

import static com.manoj.aws.pvd.model.SwiftValidationTransactionKeys.CLIENT_ID;
import static com.manoj.aws.pvd.model.SwiftValidationTransactionKeys.SWIFT_MESSAGE_TEXT;
import static com.manoj.aws.pvd.model.SwiftValidationTransactionKeys.SWIFT_MESSAGE_TYPE;
import static com.manoj.aws.pvd.model.SwiftValidationTransactionKeys.TABLE;
import static com.manoj.aws.pvd.model.SwiftValidationTransactionKeys.TRANSACTION_ID;
import static com.manoj.aws.pvd.model.SwiftValidationTransactionKeys.VALIDATION_AT;
import static com.manoj.aws.pvd.model.SwiftValidationTransactionKeys.VALIDATION_MESSAGE;
import static com.manoj.aws.pvd.model.SwiftValidationTransactionKeys.VALIDATION_STATUS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.manoj.aws.pvd.exceptions.SwiftValidationTransactionException;
import com.manoj.aws.pvd.model.SwiftValidationTransaction;
import com.manoj.aws.pvd.service.SwiftValidationTransactionService;

/**
 * 
 *
 */
@Service
public class SwiftValidationTransactionServiceImpl implements SwiftValidationTransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwiftValidationTransactionServiceImpl.class);

	@Autowired
	private DynamoDB dynamoDB;

	@Override
	public String save(SwiftValidationTransaction swiftValidationTransaction)
			throws SwiftValidationTransactionException {
		LOGGER.info("Creating new transaction...");

		Table table = dynamoDB.getTable(TABLE.keyName());

		String transactionId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS"));
		swiftValidationTransaction.setTransactionId(transactionId);
		try {
			Item item = new Item().withPrimaryKey(TRANSACTION_ID.keyName(), transactionId)
					.withString(CLIENT_ID.keyName(), swiftValidationTransaction.getClientId())
					.withString(SWIFT_MESSAGE_TYPE.keyName(), swiftValidationTransaction.getSwiftMessageType())
					.withString(SWIFT_MESSAGE_TEXT.keyName(), swiftValidationTransaction.getSwiftMessageText())
					.withBoolean(VALIDATION_STATUS.keyName(), swiftValidationTransaction.isValidationStatus())
					.withString(VALIDATION_MESSAGE.keyName(), swiftValidationTransaction.getValidationMessage())
					.withString(VALIDATION_AT.keyName(), swiftValidationTransaction.getValiadtionAt());
			PutItemOutcome putItemOutcome = table.putItem(item);

			LOGGER.info("Transaction created. {}", putItemOutcome);
		} catch (Exception e) {
			LOGGER.error("Error occur while creating the transaction. Error: {}", e.getMessage());
			LOGGER.error("Transaction Details: {}", swiftValidationTransaction);
			throw new SwiftValidationTransactionException("Error occur while creating transaction", e.getCause());
		}
		return transactionId;
	}

	@Override
	public SwiftValidationTransaction find(String transactionId) {
		LOGGER.info("Fetching new transaction for id {}...", transactionId);

		Table table = dynamoDB.getTable(TABLE.keyName());

		QuerySpec spec = new QuerySpec().withKeyConditionExpression("TransactionId = :v_Transaction_Id")
				.withValueMap(new ValueMap().withString(":v_Transaction_Id", transactionId));

		ItemCollection<QueryOutcome> items = table.query(spec);

		Iterator<Item> iterator = items.iterator();

		while (iterator.hasNext()) {
			LOGGER.info("Inside transaction iterator");
			SwiftValidationTransaction transaction = new SwiftValidationTransaction();
			Item item = iterator.next();
			transaction.setTransactionId(item.getString(TRANSACTION_ID.keyName()));
			transaction.setClientId(item.getString(CLIENT_ID.keyName()));
			transaction.setSwiftMessageType(item.getString(SWIFT_MESSAGE_TYPE.keyName()));
			transaction.setSwiftMessageText(item.getString(SWIFT_MESSAGE_TEXT.keyName()));
			transaction.setValidationStatus(item.getBoolean(VALIDATION_STATUS.keyName()));
			transaction.setValidationMessage(item.getString(VALIDATION_MESSAGE.keyName()));
			transaction.setValiadtionAt(item.getString(VALIDATION_AT.keyName()));
			LOGGER.info("SwiftValidationTransaction {}", transaction);
			return transaction;
		}

		return null;
	}

}
