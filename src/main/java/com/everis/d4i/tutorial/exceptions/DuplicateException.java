package com.everis.d4i.tutorial.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.everis.d4i.tutorial.dto.ErrorDto;

public class DuplicateException extends NetflixException {
	private static final long serialVersionUID = -6870732210014274010L;

	public DuplicateException(final String message) {
		super(HttpStatus.CONFLICT.value(), message);
	}

	public DuplicateException(final String message, final ErrorDto data) {
		super(HttpStatus.CONFLICT.value(), message, Arrays.asList(data));
	}
}
