import java.util.Scanner;

public class AuthView {
    private AuthController controller;

    public AuthView(AuthController controller){
        this.controller = controller;
    }

    public void entryView(){
        System.out.println("Welcome to shop!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Please enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                loginView();
                break;
            case 2:
                registerView();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
                entryView();
        }
    }
    public void loginView(){
        String username, password;
        System.out.println("Login To Shop");
        System.out.println("Username: ");

        Scanner sc = new Scanner(System.in);
        username = sc.nextLine();
        System.out.println("Password: ");
        password = sc.nextLine();

        controller.login(username, password);
        //sc.close();
    }

    public void registerView(){
        String username, password;
        System.out.println("Register To Shop");
        System.out.println("Username: ");

        Scanner sc = new Scanner(System.in);
        username = sc.nextLine();
        System.out.println("Password: ");
        password = sc.nextLine();

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
