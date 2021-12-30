package com.myInstitute.exception;

import java.util.Date;

public class ExceptionResponse {
	private String message;
	private Date date;

	public ExceptionResponse(String message, Date date) {
		this.message = message;
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public Date getDate() {
		return date;
	}
}
