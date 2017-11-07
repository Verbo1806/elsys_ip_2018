package org.elsys.ip.servlet.service;

import java.util.ArrayList;
import java.util.List;

import org.elsys.ip.servlet.models.User;

public class UserService {
	private static List<User> users = new ArrayList<>();

	public UserService() {
		users.clear();
		users.add(new User(1, "admin", "admin@admin.bg", "admin"));
		users.add(new User(2, "user", "user@user.bg", "user"));
	}

	public List<User> getUsers() {
		return users;
	}

	public User getByName(String name) {
		System.out.println(name);
		if (name != null) {
			return users.stream().filter(u -> name.equals(u.getName())).findFirst().orElse(null);
		} else {
			return null;
		}
	}

	public void remove(String name) {

		User user = getByName(name);

		users.remove(user);
	}
}
