package com.manoj.aws.pvd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manoj.aws.pvd.model.SwiftMessageType;
import com.manoj.aws.pvd.service.SwiftValidationFactory;
import com.manoj.aws.pvd.service.SwiftValidator;

/**
 * 
 *
 */
@Component
public class DMSwiftValidationFactory implements SwiftValidationFactory {

	@Autowired
	private MTSwiftMessageValidator mtSwiftMessageValidator;

	@Override
	public SwiftValidator getSwiftValidator(SwiftMessageType swiftMessageType) {
		return mtSwiftMessageValidator;
	}
}
