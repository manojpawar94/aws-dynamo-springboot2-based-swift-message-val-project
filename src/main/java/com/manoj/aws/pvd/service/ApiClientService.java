package com.manoj.aws.pvd.service;

import com.manoj.aws.pvd.exceptions.ApiClientNotFoundException;
import com.manoj.aws.pvd.model.ApiClient;

/**
 * ApiClientService provide services to register new ApiClient
 *
 */
public interface ApiClientService {

	/**
	 * Find the ApiClinet based upon the apiKey
	 * 
	 * @param apiKey
	 * @return
	 * @throws ApiClientNotFoundException
	 */
	ApiClient find(final String apiKey) throws ApiClientNotFoundException;

}
