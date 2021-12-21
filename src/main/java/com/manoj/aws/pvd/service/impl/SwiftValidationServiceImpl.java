package com.manoj.aws.pvd.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoj.aws.pvd.exceptions.SwiftValidationTransactionException;
import com.manoj.aws.pvd.model.MBoolean;
import com.manoj.aws.pvd.model.SwiftValidationRequest;
import com.manoj.aws.pvd.model.SwiftValidationResponse;
import com.manoj.aws.pvd.model.SwiftValidationTransaction;
import com.manoj.aws.pvd.service.SwiftValidationFactory;
import com.manoj.aws.pvd.service.SwiftValidationService;
import com.manoj.aws.pvd.service.SwiftValidationTransactionService;
import com.manoj.aws.pvd.service.SwiftValidator;

/**
 * 
 *
 */
@Service
public class SwiftValidationServiceImpl implements SwiftValidationService {

	@Autowired
	private SwiftValidationFactory swiftValidationFactory;

	@Autowired
	private SwiftValidationTransactionService swiftValidationTransactionService;

	@Override
	public SwiftValidationResponse validate(SwiftValidationRequest swiftValidationRequest)
			throws SwiftValidationTransactionException {
		SwiftValidationResponse swiftValidationResponse = new SwiftValidationResponse();

		SwiftValidator swiftValidator = swiftValidationFactory
				.getSwiftValidator(swiftValidationRequest.getSwiftMessageType());

		MBoolean mBoolean = swiftValidator.validate(swiftValidationRequest.getSwiftMessageText());

		SwiftValidationTransaction transaction = new SwiftValidationTransaction();
		transaction.setClientId(swiftValidationRequest.getClientId());
		transaction.setSwiftMessageType(swiftValidationRequest.getSwiftMessageType().toString());
		transaction.setSwiftMessageText(swiftValidationRequest.getSwiftMessageText());
		transaction.setValidationStatus(mBoolean.value());
		transaction.setValidationMessage(mBoolean.getMessage());
		transaction.setValiadtionAt(LocalDateTime.now().toString());

		String transactionId = swiftValidationTransactionService.save(transaction);

		swiftValidationResponse.setTransactionId(transactionId);
		swiftValidationResponse.setValidationStatus(mBoolean.value());
		swiftValidationResponse.setValidationMessage(mBoolean.getMessage());

		return swiftValidationResponse;
	}

}
