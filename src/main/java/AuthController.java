public class AuthController {
    private AuthView view;
    private AuthService model;
    public AuthController(){
        view = new AuthView(this);
        model = new AuthService();
        model.tempData();
    }

    public void enterLoginPage(){
        view.loginView();
    }

    public void entry(){
        view.entryView();
    }

    public void login(String username, String password){
        try{
            model.login(username, password);
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
