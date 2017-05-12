package org.dontpanic.spanners.springmvc.forms;

import org.hibernate.validator.constraints.*;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String name;
	@NotBlank(message = NOT_BLANK_MESSAGE)
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}