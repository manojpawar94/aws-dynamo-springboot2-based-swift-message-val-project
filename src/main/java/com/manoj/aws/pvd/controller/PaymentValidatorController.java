package com.manoj.aws.pvd.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.manoj.aws.pvd.model.PaymentRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@EnableWebMvc
@Slf4j
public class PaymentValidatorController {

	private static final Logger log = LoggerFactory.getLogger(PaymentValidatorController.class);

	@RequestMapping(path = "/payment", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('validation')")
	public Map<String, Object> validatePayment(@RequestBody PaymentRequest paymentRequest, Principal principal) {

		Map<String, Object> res = new HashMap<>();

		log.info("Payment validation request from {}. paymentRequest is {}", principal.getName(), paymentRequest);
		String validationResult = "";

		if (paymentRequest.getSourceAccount() == null || paymentRequest.getSourceAccount().length() < 8) {
			validationResult += "Source account is invalid. ";
		}

		if (paymentRequest.getTargetAccount() == null || paymentRequest.getTargetAccount().length() < 8) {
			validationResult += "Target account is invalid. ";
		}

		if (paymentRequest.getAmount() == null || paymentRequest.getAmount() <= 0) {
			validationResult += "Payment amount is invalid. ";
		}

		if (validationResult.isEmpty())
			validationResult = "Valid!";

		res.put("request", paymentRequest);
		res.put("result", validationResult);
		return res;
	}

	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('hello')")
	public Map<String, Object> hello(@RequestParam("name") Optional<String> name, Principal principal) {
		Map<String, Object> res = new HashMap<>();
		res.put("message", "Hello " + name.orElse(principal.getName()));
		return res;

	}

	@RequestMapping(path = "/health", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('health')")
	public Map<String, Object> health(Principal principal) {

		Map<String, Object> res = new HashMap<>();
		res.put("status", "System is up");
		return res;

	}

	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public Map<String, Object> admin(Principal principal, HttpServletRequest request) {

		Map<String, Object> res = new HashMap<>();
		res.put("principal", principal.getName());
		res.put("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return res;

	}

}
