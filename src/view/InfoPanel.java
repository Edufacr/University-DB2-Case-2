package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class InfoPanel extends JPanel{

    private final String editButtonString = "Edit";
    private final String deleteButtonString = "Delete";
    private final String reportButtonString = "Transactions";

    private final int nameLabelIndex = 0;
    private final int amountLabelIndex = 1;
    private final int editButtonIndex = 2;
    private final int deleteButtonIndex = 3;
    private final int reportButtonIndex = 4;

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
        components.add(nameLabelIndex, new JLabel(nameLabelString));
        components.add(amountLabelIndex, new JLabel(amountLabelString));
        components.add(editButtonIndex, new JButton(editButtonString));
        components.add(deleteButtonIndex, new JButton(deleteButtonString));
        components.add(reportButtonIndex, new JButton(reportButtonString));
    }

    private void addComponents(ArrayList<Integer> pPermissionArray){
        for (Integer permissionIndex : pPermissionArray) {
            Component componentToAdd= components.get(permissionIndex);
            add(componentToAdd);
        };
    }
}
