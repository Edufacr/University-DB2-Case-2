package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class InfoPanel extends JPanel{

    public static final int NAME_LABEL_INDEX = 0;
    public static final int AMOUNT_LABEL_INDEX = 1;
    public static final int EDIT_BUTTON_INDEX = 2;
    public static final int DELETE_BUTTON_INDEX = 3;
    public static final int REPORT_BUTTON_INDEX = 4;

    private final String editButtonString = "Edit";
    private final String deleteButtonString = "Delete";
    private final String reportButtonString = "Transactions";

    private String nameLabelString;
    private String amountLabelString;

    private ArrayList<Component> components;


    public InfoPanel(ArrayList<Integer> pPermissionArray){
        nameLabelString = "Sucursal";
        amountLabelString = "$9999.99";
        createComponents();
        addComponents(pPermissionArray);
    }

    private void createComponents(){
        components = new ArrayList<Component>();
        components.add(NAME_LABEL_INDEX, new JLabel(nameLabelString));
        components.add(AMOUNT_LABEL_INDEX, new JLabel(amountLabelString));
        components.add(EDIT_BUTTON_INDEX, new JButton(editButtonString));
        components.add(DELETE_BUTTON_INDEX, new JButton(deleteButtonString));
        components.add(REPORT_BUTTON_INDEX, new JButton(reportButtonString));
    }

    private void addComponents(ArrayList<Integer> pPermissionArray){
        for (Integer permissionIndex : pPermissionArray) {
            Component componentToAdd= components.get(permissionIndex);
            add(componentToAdd);
        };
    }
}
