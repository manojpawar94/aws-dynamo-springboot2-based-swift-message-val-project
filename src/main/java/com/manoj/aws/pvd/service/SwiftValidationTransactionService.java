package com.manoj.aws.pvd.service;

import com.manoj.aws.pvd.exceptions.SwiftValidationTransactionException;
import com.manoj.aws.pvd.model.SwiftValidationTransaction;

/**
 * 
 *
 */
public interface SwiftValidationTransactionService {

	/**
	 * 
	 * @param swiftValidationTransaction
	 * @return
	 * @throws SwiftValidationTransactionException
	 */
	String save(SwiftValidationTransaction swiftValidationTransaction) throws SwiftValidationTransactionException;

	/**
	 * 
	 * @param transactionId
	 * @return
	 */
	SwiftValidationTransaction find(String transactionId);

}
