package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

public class AdminStart {
    public Button back_butt, tax_list, acc_list, businessman_list;

    public void back_Butt(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) back_butt.getScene().getWindow(), true , "/views/Authorization.fxml", "Авторизация", false);
    }

    public void acc_list_Butt(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) acc_list.getScene().getWindow(), true , "/views/Acc_List.fxml", "Аккаунты", false);
    }

    public void tax_list_Butt(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) tax_list.getScene().getWindow(), true , "/views/TaxsList.fxml", "Налоги", false);
    }

    public void businessman_list_Butt(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) businessman_list.getScene().getWindow(), true , "/views/BusinessmanList.fxml", "Отчетности", false);
    }
}
