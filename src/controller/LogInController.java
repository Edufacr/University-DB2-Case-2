package controller;

public class LogInController {

    LogInController(){

    }

    public boolean SignIn(String pEmail, String pPassword){
        if (pEmail == "1") {
            return true;
        }
        return false;
    }
    
}
