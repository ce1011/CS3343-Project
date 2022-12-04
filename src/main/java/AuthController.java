public class AuthController {
    private AuthView view;
    private HomeController homeController;
    private AuthService model;
    public AuthController(){
        view = new AuthView(this);
        model = new AuthService();
        homeController = new HomeController();
        model.tempData();
    }



    public void entry(){
        view.entryView();
    }

    public void login(String username, String password){
        try{
            User user = model.login(username, password);
            
            if (user.getRole().getRoleName().equals("Customer")){
                homeController.showCustomerHome();
                view.entryView();
            } else if (user.getRole().getRoleName().equals("Admin")){
                homeController.showAdminHome(user);
                view.entryView();
            }

            

        } catch (WrongPasswordException e){
            view.wrongPassword();
        } catch (UserNotFoundException e){
            view.userNotFound();
        }

    }

    public void register(String username, String password){
        try{
            model.register(username, password, "Customer");
            view.registerSuccess();
        } catch (ExistedUserFoundException e){
            view.existedUser();
        }
    }








}
