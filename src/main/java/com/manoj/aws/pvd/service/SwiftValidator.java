package com.manoj.aws.pvd.service;

import com.manoj.aws.pvd.model.MBoolean;

/**
 * 
 *
 */
public interface SwiftValidator {

	/**
	 * 
	 * @param swiftMessageText
	 * @return
	 */
	MBoolean validate(String swiftMessageText);
}
