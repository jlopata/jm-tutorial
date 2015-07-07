package com.acme.craft.fixme.commons.string;

import org.apache.commons.lang3.StringUtils;

public class NameValidator {

	public boolean valid(String name) {
		if (StringUtils.isNotBlank(name)) {
			return true;
		}
		return false;
	}

	public boolean isJohn(String name) {
		String johnName = "John";
		return johnName.equals(name);
	}

	public String validationMessage(String firstName, String lastName, String nick) {
		return String.format("Provided name is not valid: First name: %s lastname: %s nick: %s", firstName, lastName,
				nick);
	}

}
