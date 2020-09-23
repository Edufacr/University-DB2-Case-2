package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.LogInModel;
import view.InfoPanel;

public class LogInController {

    HashMap<Integer,Integer> permissionsForView;

    public LogInController(){
        createPermissionHashMap();
    }

    public ArrayList<Integer> SignIn(String pEmail, String pPassword){
        LogInModel model = new LogInModel();
        ArrayList<Integer> permissions = model.SignIn(pEmail, pPassword);
        if (permissions != null) {
            return translatePermissions(permissions);
        }
        return permissions;
    }

    private ArrayList<Integer> translatePermissions(ArrayList<Integer> pOriginalPermissions){
        ArrayList<Integer> translatedPermissions = new ArrayList<Integer>();
        for (Integer permiCode : pOriginalPermissions) {
            translatedPermissions.add(permissionsForView.get(permiCode));
        }
        return translatedPermissions;
    }

    private void createPermissionHashMap(){
        //Conseguiria los permisos de la base de datos 
        permissionsForView = new HashMap<Integer,Integer>();
        permissionsForView.put(0, InfoPanel.NAME_LABEL_INDEX);
        permissionsForView.put(1, InfoPanel.AMOUNT_LABEL_INDEX);
        permissionsForView.put(2, InfoPanel.DELETE_BUTTON_INDEX);
        permissionsForView.put(3, InfoPanel.EDIT_BUTTON_INDEX);
        permissionsForView.put(4, InfoPanel.REPORT_BUTTON_INDEX);
    }
}
