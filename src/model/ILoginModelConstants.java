package model;

public interface ILoginModelConstants {
        String LOGIN_STORED_PROCEDURE_QUERY = "{call spLogin(?,?)}";
        String USERNAME_ARGUMENT = "username";
        String PASSWORD_ARGUMENT = "pass";
        String RESULT_COLUMN = "Codigo";
        int LOGIN_ERROR_CODE = 50410;
}
