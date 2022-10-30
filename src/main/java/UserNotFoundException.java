package main;

public class UserNotFoundException extends Exception{
	public UserNotFoundException(String username) {
		super("Username: " + username + " not found");
	}
}
