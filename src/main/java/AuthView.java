import java.util.Scanner;

public class AuthView {
    private AuthController controller;
    Scanner sc = new Scanner(System.in);
    public AuthView(AuthController controller){
    	
        this.controller = controller;
    }

    public void entryView(){
        System.out.println("Welcome to shop!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Please enter your choice: ");
        int choice = sc.nextInt();
        
        switch (choice){
            case 1:
                loginView();
                break;
            case 2:
                registerView();
                break;
            case 3:
            	System.out.println("GoodBye");
                break;
            case 4:
            	break;
            default:
                System.out.println("Invalid choice!");
                this.entryView();
        }
    }
    public void loginView(){
        String username, password;
        System.out.println("Login To Shop");
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();

        controller.login(username, password);
    }

    public void registerView(){
        String username, password;
        System.out.println("Register To Shop");
        System.out.println("Username: ");
        username = sc.next();
        System.out.println("Password: ");
        password = sc.next();

        controller.register(username, password);
        //sc.close();
    }

    public void wrongPassword(){
        System.out.println("Wrong Password!");
        this.entryView();
    }

    public void userNotFound(){
        System.out.println("User Not Found!");
        this.entryView();
    }

    public void existedUser(){
        System.out.println("User Existed!");
        this.entryView();
    }

    public void registerSuccess(){
        System.out.println("Register Success!");
        this.loginView();
    }
}