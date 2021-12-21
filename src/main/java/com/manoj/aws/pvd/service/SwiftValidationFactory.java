package com.manoj.aws.pvd.service;

import com.manoj.aws.pvd.model.SwiftMessageType;

/**
 * 

 *
 */
public interface SwiftValidationFactory {
	/**
	 * 
	 * @param swiftMessageType
	 * @return
	 */
	public SwiftValidator getSwiftValidator(SwiftMessageType swiftMessageType);
}
