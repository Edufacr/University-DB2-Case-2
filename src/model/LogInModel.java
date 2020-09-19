package model;

import database.DatabaseConnector;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogInModel implements ILoginModelConstants {

    private DatabaseConnector dbc = null;

    public LogInModel() {
        this.dbc = new DatabaseConnector();
    }

    public ArrayList<Integer> SignIn(String pEmail, String pPassword) {
        ArrayList<Integer> result = new ArrayList<>();
        ResultSet resultSet = null;
        String query = LOGIN_STORED_PROCEDURE_QUERY;
        CallableStatement callableStatement = null;

        try {
            callableStatement = this.dbc.getConnection()
                    .prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            callableStatement.setString(USERNAME_ARGUMENT, pEmail);
            callableStatement.setString(PASSWORD_ARGUMENT, pPassword);
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                result.add(resultSet.getInt(RESULT_COLUMN));
            }

        } catch (SQLException ex) {
            if (ex.getErrorCode() == LOGIN_ERROR_CODE) {
                result = null;
            } else {
                Logger.getLogger(LogInModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LogInModel.class.getName()).log(Level.WARNING, null, ex);
                }
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LogInModel.class.getName()).log(Level.WARNING, null, ex);
                }
            }
        }
        return result;
    }
}
