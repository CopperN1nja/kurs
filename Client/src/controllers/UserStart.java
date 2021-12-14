package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.BusinessBranchList;
import models.BusinessmanList;
import models.NalogiList;
import models.TaxBranchList;
import sample.Main;

import java.util.ArrayList;

public class UserStart {

    public TableView<BusinessmanList> businessmanList;
    public TableColumn<BusinessmanList, Integer> id_businessman, firm_unp, income, status;
    public TableColumn<BusinessmanList, String> firm_name,business_branch;
    public TableColumn<BusinessmanList, Double> tax,tax_sum, profit;

    public TableView<NalogiList> NalogiTable;
    public TableColumn<NalogiList, Integer> id_tax;
    public TableColumn<NalogiList, String> tax_name;
    public TableColumn<NalogiList, Integer> val_id;

    public TableView<BusinessBranchList> business_branchList;
    public TableColumn<BusinessBranchList, Integer> id_branch;
    public TableColumn<BusinessBranchList, String> name;

    public TableView<TaxBranchList> tax_branchList;
    public TableColumn<TaxBranchList, String> name_branch, name_tax;

    public TextField id_businessman_field, info, id_branch_field, id_tax_field,input_field;
    public Button back_butt, calculateBusinesTax, allincome, allprofit, tax_at_branch, sumAllTax, sumNewTax,
                    showFirmProfLower, showBranchOfTax,showAllTaxOfFitrm;

    public void initialize() {
        id_businessman.setCellValueFactory(new PropertyValueFactory<BusinessmanList, Integer>("id_businessman"));
        firm_name.setCellValueFactory(new PropertyValueFactory<BusinessmanList, String>("firm_name"));
        firm_unp.setCellValueFactory(new PropertyValueFactory<BusinessmanList, Integer>("firm_unp"));
        business_branch.setCellValueFactory(new PropertyValueFactory<BusinessmanList, String>("business_branch"));
        income.setCellValueFactory(new PropertyValueFactory<BusinessmanList,Integer>("income"));
        tax.setCellValueFactory(new PropertyValueFactory<BusinessmanList, Double>("tax"));
        tax_sum.setCellValueFactory(new PropertyValueFactory<BusinessmanList, Double>("tax_sum"));
        profit.setCellValueFactory(new PropertyValueFactory<BusinessmanList, Double>("profit"));
        status.setCellValueFactory(new PropertyValueFactory<BusinessmanList, Integer>("status"));

        id_tax.setCellValueFactory(new PropertyValueFactory<NalogiList, Integer>("id_tax"));
        tax_name.setCellValueFactory(new PropertyValueFactory<NalogiList, String>("tax_name"));
        val_id.setCellValueFactory(new PropertyValueFactory<NalogiList, Integer>("val_id"));

        id_branch.setCellValueFactory(new PropertyValueFactory<BusinessBranchList, Integer>("id_branch"));
        name.setCellValueFactory(new PropertyValueFactory<BusinessBranchList,String>("name"));

        name_branch.setCellValueFactory(new PropertyValueFactory<TaxBranchList, String>("name_branch"));
        name_tax.setCellValueFactory(new PropertyValueFactory<TaxBranchList, String>("name_tax"));

        loadData();
    }

    private void loadData() {
        ObservableList<BusinessmanList> ars = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        String[] allRes = {""};
        list = (ArrayList<String>) Main.getObjectFromServer("showNewBusinessman");
        for (String tmp : list) {
            allRes = tmp.split(" ");
            ars.add(new BusinessmanList(Integer.parseInt(allRes[0]),allRes[1], Integer.parseInt(allRes[2]), allRes[3], Integer.parseInt(allRes[4]),
                    Integer.parseInt(allRes[5])));
        }
        businessmanList.setItems(ars);

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
        new Main().openNewWindow((Stage) back_butt.getScene().getWindow(), true , "/views/Authorization.fxml", "Авторизация", false);
    }

    public void calculateBusinesTax_Butt(ActionEvent actionEvent) {
        String c = id_businessman_field.getText();
        Main.sendToServerString("calculateBusinesTax " + c);
        id_businessman_field.setText("");
    }

    public void refresh_Butt(ActionEvent actionEvent) {
        ObservableList<BusinessmanList> ars = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        String[] allRes = {""};
        list = (ArrayList<String>) Main.getObjectFromServer("showNewBusinessman ");
        for (String tmp : list) {
            allRes = tmp.split(" ");
            ars.add(new BusinessmanList(Integer.parseInt(allRes[0]),allRes[1], Integer.parseInt(allRes[2]), allRes[3], Integer.parseInt(allRes[4]),
                    Integer.parseInt(allRes[5])));
        }
        businessmanList.setItems(ars);
    }

    public void showStatus2_Butt(ActionEvent actionEvent) {
        ObservableList<BusinessmanList> ars = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        String[] allRes = {""};
        list = (ArrayList<String>) Main.getObjectFromServer("showBusinessmanForRecalculation");
        for (String tmp : list) {
            allRes = tmp.split(" ");
            ars.add(new BusinessmanList(Integer.parseInt(allRes[0]),allRes[1], Integer.parseInt(allRes[2]), allRes[3], Integer.parseInt(allRes[4]),
                    Double.parseDouble(allRes[5]), Double.parseDouble(allRes[6]), Double.parseDouble(allRes[7]), Integer.parseInt(allRes[8])));
        }
        businessmanList.setItems(ars);
    }

    public void showStatus1_Butt(ActionEvent actionEvent) {
        ObservableList<BusinessmanList> ars = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        String[] allRes = {""};
        list = (ArrayList<String>) Main.getObjectFromServer("showBusinessmanCalculated");
        for (String tmp : list) {
            allRes = tmp.split(" ");
            ars.add(new BusinessmanList(Integer.parseInt(allRes[0]),allRes[1], Integer.parseInt(allRes[2]), allRes[3], Integer.parseInt(allRes[4]),
                    Double.parseDouble(allRes[5]), Double.parseDouble(allRes[6]), Double.parseDouble(allRes[7]), Integer.parseInt(allRes[8])));
        }
        businessmanList.setItems(ars);
    }

    public void delete_Butt(ActionEvent actionEvent) {
        String d = id_businessman_field.getText();
        Main.sendToServerString("deleteBusinessman " + d);
        id_businessman_field.setText("");
        initialize();
    }

    public void recalculateTax_Butt(ActionEvent actionEvent) {
        String c = id_businessman_field.getText();
        Main.sendToServerString("recalculateTax " + c);
        id_businessman_field.setText("");
    }

    public void allprofit_Butt(ActionEvent actionEvent) {
        String s = String.valueOf(Main.getObjectFromServer("allProfit "));
        info.setText(s);
    }

    public void allincome_Butt(ActionEvent actionEvent) {
        String s = String.valueOf(Main.getObjectFromServer("allIncome "));
        info.setText(s);
    }

    public void tax_at_branch_Butt(ActionEvent actionEvent) {
        String a = id_branch_field.getText();
        String s = String.valueOf(Main.getObjectFromServer("taxAtBranch " + a));
        info.setText(s + "  %");
        id_branch_field.setText("");
    }

    public void sumAllTax_Butt(ActionEvent actionEvent) {
        String s = String.valueOf(Main.getObjectFromServer("sumAllTax "));
        info.setText(s);
    }

    public void sumNewTax_Butt(ActionEvent actionEvent) {
        String s = String.valueOf(Main.getObjectFromServer("sumNewTax "));
        info.setText(s);
    }

    public void showBranchOfTax_Butt(ActionEvent actionEvent) {
        String s = id_tax_field.getText();
        ObservableList<BusinessBranchList> ars2 = FXCollections.observableArrayList();
        ArrayList<String> list2 = new ArrayList<>();
        String[] allRes2 = {""};
        list2 = (ArrayList<String>) Main.getObjectFromServer("showBranchOfTax " + s);
        for (String tmp : list2) {
            allRes2 = tmp.split(" ");
            ars2.add(new BusinessBranchList(Integer.parseInt(allRes2[0]),allRes2[1]));
        }
        business_branchList.setItems(ars2);
        id_tax_field.setText("");

    }

    public void showFirmProfHigther_butt(ActionEvent actionEvent) {
        String s = input_field.getText();
        ObservableList<BusinessmanList> ars = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        String[] allRes = {""};
        list = (ArrayList<String>) Main.getObjectFromServer("showFirmProfHigther " + s);
        for (String tmp : list) {
            allRes = tmp.split(" ");
            ars.add(new BusinessmanList(Integer.parseInt(allRes[0]),allRes[1], Integer.parseInt(allRes[2]), allRes[3], Integer.parseInt(allRes[4]),
                    Double.parseDouble(allRes[5]), Double.parseDouble(allRes[6]), Double.parseDouble(allRes[7]), Integer.parseInt(allRes[8])));
        }
        businessmanList.setItems(ars);
    }

    public void showAllTaxOfFitrm_Butt(ActionEvent actionEvent) {
        String s = id_businessman_field.getText();
        ObservableList<NalogiList> ars4 = FXCollections.observableArrayList();
        ArrayList<String> list4 = new ArrayList<>();
        String[] allRes4 = {""};
        list4 = (ArrayList<String>) Main.getObjectFromServer("showAllTaxOfFitrm " + s);
        for (String tmp : list4) {
            allRes4 = tmp.split(" ");
            ars4.add(new NalogiList(Integer.parseInt(allRes4[0]), allRes4[1], Integer.parseInt(allRes4[2])));
        }
        NalogiTable.setItems(ars4);

    }

    public void showFirmProfLower_Butt(ActionEvent actionEvent) {
        String s = input_field.getText();
        ObservableList<BusinessmanList> ars = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        String[] allRes = {""};
        list = (ArrayList<String>) Main.getObjectFromServer("showFirmProfLower " + s);
        for (String tmp : list) {
            allRes = tmp.split(" ");
            ars.add(new BusinessmanList(Integer.parseInt(allRes[0]),allRes[1], Integer.parseInt(allRes[2]), allRes[3], Integer.parseInt(allRes[4]),
                    Double.parseDouble(allRes[5]), Double.parseDouble(allRes[6]), Double.parseDouble(allRes[7]), Integer.parseInt(allRes[8])));
        }
        businessmanList.setItems(ars);
    }

    public void refresh_Tax_Butt(ActionEvent actionEvent) {
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

    public void refresh_Branch_Butt(ActionEvent actionEvent) {
        ObservableList<BusinessBranchList> ars2 = FXCollections.observableArrayList();
        ArrayList<String> list2 = new ArrayList<>();
        String[] allRes2 = {""};
        list2 = (ArrayList<String>) Main.getObjectFromServer("showAllBusinessBranch");
        for (String tmp : list2) {
            allRes2 = tmp.split(" ");
            ars2.add(new BusinessBranchList(Integer.parseInt(allRes2[0]),allRes2[1]));
        }
        business_branchList.setItems(ars2);
    }
}
