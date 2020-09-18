package controller;

public class LogInController {

    public LogInController(){

    }

    public boolean SignIn(String pEmail, String pPassword){
        if (pEmail.isEmpty()) {
            return true;
        }
        return false;
    }
    
}
