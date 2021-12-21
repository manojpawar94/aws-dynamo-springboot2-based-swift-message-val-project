package com.manoj.aws.pvd.service;

import com.manoj.aws.pvd.exceptions.SwiftValidationTransactionException;
import com.manoj.aws.pvd.model.SwiftValidationRequest;
import com.manoj.aws.pvd.model.SwiftValidationResponse;

/**
 * 
 *
 */
public interface SwiftValidationService {

	/**
	 * 
	 * @param swiftValidationRequest
	 * @return
	 * @throws SwiftValidationTransactionException 
	 */
	SwiftValidationResponse validate(SwiftValidationRequest swiftValidationRequest) throws SwiftValidationTransactionException;
}
