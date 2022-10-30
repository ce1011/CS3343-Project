

public class WrongPasswordException extends Exception{
	public WrongPasswordException() {
		super("The password is not valid");
	}
}