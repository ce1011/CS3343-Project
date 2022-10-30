package main;

public class ExistedUserFoundException extends Exception{
	public ExistedUserFoundException(String username) {
		super("Username: " + username + " already exists");
	}
}
