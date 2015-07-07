package com.acme.craft.fixme.dry;

import java.util.HashSet;
import java.util.List;

public class UserService {

	private static final int ADULT_AGE = 18;

	public HashSet doSomethingDifferent(List<User> users) {
		HashSet<String> userNames = new HashSet<String>();
		for (int i = users.size(); i >= 0; i--) {
			if (users.get(i).getAge() > ADULT_AGE) {
				String temp = users.get(i).getFullName();
				userNames.add(temp);
			}
		}
		return userNames;
	}
}
