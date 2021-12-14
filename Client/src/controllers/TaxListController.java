package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import models.*;

import sample.Main;

import java.util.ArrayList;

public class TaxListController {

    private NalogiList selectedTax;

    public TableView<NalogiList> NalogiTable;
    public TableColumn<NalogiList, Integer> id_tax;
    public TableColumn<NalogiList, String> tax_name;
    public TableColumn<NalogiList, Integer> val_id;

    public TableView<BusinessBranchList> business_branchList;
    public TableColumn<BusinessBranchList, Integer> id_branch;
    public TableColumn<BusinessBranchList, String> name;

    public TableView<TaxBranchList> tax_branchList;
    public TableColumn<TaxBranchList, String> name_branch, name_tax;

    public Button back_butt, add_tax, delete_tax, delete_branch, add_branch, add_tax_branch;
    public TextField tax_name_field, tax_value_field, id_tax_field, name_field,id_branch_field;


    public void initialize() {

        NalogiTable.setEditable(true);
        id_tax.setCellValueFactory(new PropertyValueFactory<NalogiList, Integer>("id_tax"));
        tax_name.setCellValueFactory(new PropertyValueFactory<NalogiList, String>("tax_name"));
        val_id.setCellValueFactory(new PropertyValueFactory<NalogiList, Integer>("val_id"));


        id_branch.setCellValueFactory(new PropertyValueFactory<BusinessBranchList, Integer>("id_branch"));
        name.setCellValueFactory(new PropertyValueFactory<BusinessBranchList,String>("name"));

        name_branch.setCellValueFactory(new PropertyValueFactory<TaxBranchList, String>("name_branch"));
        name_tax.setCellValueFactory(new PropertyValueFactory<TaxBranchList, String>("name_tax"));

        val_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        val_id.setOnEditCommit(event -> {
            String newValue =  String.valueOf(event.getNewValue());
            selectedTax.setVal_id(event.getNewValue());
            updateTax("tax_value", newValue);
        });

        NalogiTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<NalogiList>() {
            @Override
            public void changed(ObservableValue<? extends NalogiList> observable, NalogiList oldValue, NalogiList newValue) {
                if (newValue != null) {
                    selectedTax = newValue;

                }
            }
        });
        loadData();
    }

    private void updateTax(String val_id, String newValue) {
        Main.sendToServerString("editTax " + selectedTax.getId_tax() + " " + val_id + " " + newValue);
    }


    private void loadData() {

        ObservableList<BusinessBranchList> ars2 = FXCollections.observableArrayList();
        ArrayList<String> list2 = new ArrayList<>();
        String[] allRes2 = {""};
        list2 = (ArrayList<String>) Main.getObjectFromServer("showAllBusinessBranch");
        for (String tmp : list2) {
            allRes2 = tmp.split(" ");
            ars2.add(new BusinessBranchList(Integer.parseInt(allRes2[0]),allRes2[1]));
        }
        business_branchList.setItems(ars2);

        ObservableList<TaxBranchList> ars3 = FXCollections.observableArrayList();
        ArrayList<String> list3 = new ArrayList<>();
        String[] allRes3 = {""};
        list3 = (ArrayList<String>) Main.getObjectFromServer("showAllTaxBranch");
        for (String tmp : list3) {
            allRes3 = tmp.split(" ");
            ars3.add(new TaxBranchList(allRes3[0], allRes3[1]));
        }
        tax_branchList.setItems(ars3);

        ObservableList<NalogiList> ars4 = FXCollections.observableArrayList();
        ArrayList<String> list4 = new ArrayList<>();
        String[] allRes4 = {""};
        list4 = (ArrayList<String>) Main.getObjectFromServer("showAllTax");
        for (String tmp : list4) {
            allRes4 = tmp.split(" ");
            ars4.add(new NalogiList(Integer.parseInt(allRes4[0]), allRes4[1], Integer.parseInt(allRes4[2])));
        }
        NalogiTable.setItems(ars4);
    }

    public void back_Butt(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) back_butt.getScene().getWindow(), true , "/views/AdminStart.fxml", "Админ", false);
    }

    public void add_tax_Butt(ActionEvent actionEvent) {
        String an = tax_name_field.getText();
        String av = tax_value_field.getText();
        Main.sendToServerString("addTax " + an + " " + av);
        initialize();
        tax_name_field.setText("");
        tax_value_field.setText("");
    }

    public void delete_tax_Butt(ActionEvent actionEvent) {
        String dt = id_tax_field.getText();
        Main.sendToServerString("deleteTax " + dt);
        id_tax_field.setText("");
        initialize();

    }

    public void add_branch_Butt(ActionEvent actionEvent) {
        String ab = name_field.getText();
        Main.sendToServerString("addBuisBranch " + ab);
        name_field.setText("");
        initialize();
    }

    public void delete_branch_Butt(ActionEvent actionEvent) {
        String db = id_branch_field.getText();
        Main.sendToServerString("deleteBuisBranch " + db);
        id_branch_field.setText("");
        initialize();
    }

    public void add_tax_branch_Butt(ActionEvent actionEvent) {
        String b = id_branch_field.getText();
        String t = id_tax_field.getText();
        Main.sendToServerString("addTaxBranch " + b + " " + t);
        id_branch_field.setText("");
        id_tax_field.setText("");
        initialize();
    }

    public void delete_tax_branch_Butt(ActionEvent actionEvent) {
        String db = id_branch_field.getText();
        String dt = id_tax_field.getText();
        Main.sendToServerString("deleteTaxBranch " + db + " " + dt);
        id_branch_field.setText("");
        id_tax_field.setText("");
        initialize();
    }
}
