package controller;

public class LogInController {

    public LogInController(){

    }

    public boolean SignIn(String pEmail, String pPassword){
        if (pEmail == "1") {
            return true;
        }
        return false;
    }
    
}
