//Singleton State Management
class Store{
    private static Store instance = null;

    private User currentUser;
    private Store() {
    }
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


}
