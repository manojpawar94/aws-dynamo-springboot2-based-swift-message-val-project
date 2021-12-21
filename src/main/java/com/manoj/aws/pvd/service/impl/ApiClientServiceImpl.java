package com.manoj.aws.pvd.service.impl;

import static com.manoj.aws.pvd.model.ApiClientKeys.API_KEY;
import static com.manoj.aws.pvd.model.ApiClientKeys.AUTHAURITIES;
import static com.manoj.aws.pvd.model.ApiClientKeys.CLIENT_NAME;
import static com.manoj.aws.pvd.model.ApiClientKeys.TABLE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.manoj.aws.pvd.exceptions.ApiClientNotFoundException;
import com.manoj.aws.pvd.model.ApiClient;
import com.manoj.aws.pvd.service.ApiClientService;

/**
 * ApiClientServiceImpl provides the ApiClient Services
 * 
 */
@Service
public class ApiClientServiceImpl implements ApiClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiClientServiceImpl.class);

	private final DynamoDB dynamoDB;

	/**
	 * @param dynamoDB
	 */
	@Autowired
	public ApiClientServiceImpl(DynamoDB dynamoDB) {
		this.dynamoDB = dynamoDB;
	}

	@Override
	public ApiClient find(String apiKey) throws ApiClientNotFoundException {
		LOGGER.info("Finding the ApiClinet using API KEY: {}", apiKey);
		Table table = dynamoDB.getTable(TABLE.keyName());

		GetItemSpec spec = new GetItemSpec().withPrimaryKey(API_KEY.keyName(), apiKey);

		try {
			LOGGER.info("Attempting to read the item...");
			Item outcome = table.getItem(spec);
			LOGGER.info("GetItem succeeded: {}", outcome);
			ApiClient apiClient = new ApiClient();
			apiClient.setApiKey(outcome.getString(API_KEY.keyName()));
			apiClient.setName(outcome.getString(CLIENT_NAME.keyName()));
			apiClient.setAuthorities(outcome.getList(AUTHAURITIES.keyName()));
			return apiClient;
		} catch (Exception e) {
			LOGGER.error("Unable to find API Clinet for API KEY: {}", apiKey);
			LOGGER.error(e.getMessage());
			throw new ApiClientNotFoundException("Unable to find API Clinet for API KEY: " + apiKey);
		}
	}

}
