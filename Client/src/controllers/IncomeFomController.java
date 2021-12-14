package controllers;

import javafx.event.ActionEvent;

import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;


public class IncomeFomController {
    public TextField firm_name, firm_unp, business_branch, income;
    public Label info_field;
    public Button back_butt;

    public void send_income_Form(ActionEvent actionEvent) {
        String ns = firm_name.getText();
        String us = firm_unp.getText();
        String bs = business_branch.getText();
        String is = income.getText();
        Main.sendToServerString("createBusinessman " + ns + " " + us + " " + bs + " " + is);
        info_field.setText("Отчет отправлен");
        firm_unp.setText("");
        income.setText("");
        firm_name.setText("");
        business_branch.setText("");
    }

    public void back_Buttt(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) back_butt.getScene().getWindow(), true , "/views/Authorization.fxml", "Авторизация", false);
    }

    public void sendRecalculate_Butt(ActionEvent actionEvent) {
        String us = firm_unp.getText();
        String si = income.getText();
        Main.sendToServerString("sendRecalculate " + us + " " + si);
        firm_unp.setText("");
        income.setText("");
        info_field.setText("Отчет отправлен");
    }
}
