package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AuthorizationController {

    public Button authorization_button, checkin_button , send_form;
    public TextField login_field;
    public PasswordField password_field;
    public Label info_field;


    public void checkin_Button(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) checkin_button.getScene().getWindow(), true , "/views/Registration.fxml", "Регистрация" , false);
    }

    public void authorization_Button(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) authorization_button.getScene().getWindow();
        String login = login_field.getText();
        String password = password_field.getText();

        if (!(login.equals("") || password.equals(""))) {
            String answer = Main.sendToServerString("authorization " + login + " " + password);
            if (answer.equals("Admin")) {
                new Main().openNewWindow(stage, true, "/views/AdminStart.fxml", "Админ", false);
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("logs.txt", true));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date now = new Date();
                String enterDate = simpleDateFormat.format(now);
                String text = "Администратор авторизировался" + login + " " + enterDate + "\r\n";
                bufferedWriter.write(text);
                bufferedWriter.newLine();
                bufferedWriter.close();
            }
            else if (answer.equals("User")) {
                new Main().openNewWindow(stage, true, "/views/UserStart.fxml", "Пользователь", false);
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("logs.txt", true));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date now = new Date();
                String enterDate = simpleDateFormat.format(now);
                String text = "Пользователь авторизировался " + login + " " + enterDate + "\r\n";
                bufferedWriter.write(text);
                bufferedWriter.newLine();
                bufferedWriter.close();
            }
            else {
                login_field.setText("");
                password_field.setText("");
                info_field.setText("Пользователь не найден");
            }
        } else info_field.setText("Присутствует незаполненное поле");
    }





    public void send_Form(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) send_form.getScene().getWindow(), true , "/views/IncomeForm.fxml", "Заполнение справки о доходах" , false);
    }
}
