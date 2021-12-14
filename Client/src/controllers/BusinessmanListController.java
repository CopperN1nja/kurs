package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import sample.Main;
import models.BusinessmanList;

import java.util.ArrayList;

public class BusinessmanListController {
    public TableView<BusinessmanList> businessmanList;
    public TableColumn<BusinessmanList, Integer> id_businessman, firm_unp, income, status;
    public TableColumn<BusinessmanList, String> firm_name,business_branch;
    public TableColumn<BusinessmanList, Double> tax,tax_sum, profit;
    public TextField id_field;
    public Button back_butt, showNewBus, showStatus2, showForDel, delete, refresh;

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

        loadData();
    }

    private void loadData() {
        ObservableList<BusinessmanList> ars = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        String[] allRes = {""};
        list = (ArrayList<String>) Main.getObjectFromServer("showAllBusinessman");
        for (String tmp : list) {
            allRes = tmp.split(" ");
            ars.add(new BusinessmanList(Integer.parseInt(allRes[0]),allRes[1], Integer.parseInt(allRes[2]), allRes[3], Integer.parseInt(allRes[4]),
            Double.parseDouble(allRes[5]), Double.parseDouble(allRes[6]), Double.parseDouble(allRes[7]), Integer.parseInt(allRes[8])));
        }
        businessmanList.setItems(ars);
    }

    public void back_Butt(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) back_butt.getScene().getWindow(), true, "/views/AdminStart.fxml", "Админ", false);
    }

    public void showNewBus_Butt(ActionEvent actionEvent) {

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

    public void refresh_Butt(ActionEvent actionEvent) {
        initialize();
    }

    public void delete_Butt(ActionEvent actionEvent) {
        String d = id_field.getText();
        Main.sendToServerString("deleteBusinessman " + d);
        id_field.setText("");
        initialize();

    }
}
