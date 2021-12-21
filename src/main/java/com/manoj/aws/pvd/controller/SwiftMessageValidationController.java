package com.manoj.aws.pvd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.aws.pvd.exceptions.SwiftValidationTransactionException;
import com.manoj.aws.pvd.model.SwiftMessageType;
import com.manoj.aws.pvd.model.SwiftValidationRequest;
import com.manoj.aws.pvd.model.SwiftValidationResponse;
import com.manoj.aws.pvd.model.SwiftValidationTransaction;
import com.manoj.aws.pvd.service.SwiftValidationService;
import com.manoj.aws.pvd.service.SwiftValidationTransactionService;

@RestController
public class SwiftMessageValidationController {

	@Autowired
	private SwiftValidationService swiftValidationService;

	@Autowired
	private SwiftValidationTransactionService swiftValidationTransactionService;

	@PostMapping(value = "/swiftValidate", consumes = "text/plain")
	@PreAuthorize("hasAuthority('validation')")
	public SwiftValidationResponse validate(@RequestParam("clientId") String clientId,
			@RequestParam("type") String messageType, @RequestBody String swiftMessage)
			throws SwiftValidationTransactionException {
		SwiftValidationRequest swiftValidationRequest = new SwiftValidationRequest();
		swiftValidationRequest.setClientId(clientId);
		swiftValidationRequest.setSwiftMessageType(SwiftMessageType.valueOf(messageType));
		swiftValidationRequest.setSwiftMessageText(swiftMessage);
		return swiftValidationService.validate(swiftValidationRequest);
	}

	@GetMapping("/swiftValidate")
	@PreAuthorize("hasAuthority('validation')")
	public SwiftValidationTransaction getTransaction(@RequestParam("txnid") String transactionId) {
		return swiftValidationTransactionService.find(transactionId);
	}
}
