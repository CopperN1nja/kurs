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
import javafx.util.converter.IntegerStringConverter;
import sample.Main;
import models.AccList;
import java.util.ArrayList;

public class AccController {

    private AccList selectedUser;

    public TableView<AccList> userTable;
    public TableColumn<AccList , String> login;
    public TableColumn<AccList , String> password;
    public TableColumn<AccList, Integer> id_user;
    public TableColumn<AccList , Integer> role;
    public TextField login_field , id_field;
    public PasswordField password_field;
    public Button add_user, delete_user, exit_button , show_all_users, search_user;

    public void initialize(){
        userTable.setEditable(true);
        id_user.setCellValueFactory(new PropertyValueFactory<AccList, Integer>("id_user"));
        login.setCellValueFactory(new PropertyValueFactory<AccList, String>("login"));
        password.setCellValueFactory(new PropertyValueFactory<AccList, String>("password"));
        role.setCellValueFactory(new PropertyValueFactory<AccList, Integer>("role"));

        password.setCellFactory(TextFieldTableCell.forTableColumn());
        password.setOnEditCommit(event -> {
            String newPasw = event.getNewValue();
            selectedUser.setPassword(newPasw);
            updateUser("password", newPasw);
        });

        login.setCellFactory(TextFieldTableCell.forTableColumn());
        login.setOnEditCommit(event -> {
            String newLogin = event.getNewValue();
            selectedUser.setLogin(newLogin);
            updateUser("login", newLogin);
        });

        role.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        role.setOnEditCommit(event -> {
            String newRole =  String.valueOf(event.getNewValue());
            selectedUser.setRole(event.getNewValue());
            updateUser("role", newRole);
        });

        userTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccList>() {
            @Override
            public void changed(ObservableValue<? extends AccList> observable, AccList oldValue, AccList newValue) {
                if (newValue != null) {
                    selectedUser = newValue;

                }
            }
        });

        loadData();
    }

    private void loadData() {
        ObservableList<AccList> ars = FXCollections.observableArrayList();
        ArrayList<String> list = new ArrayList<>();
        String[] allUsers = {""};
        list = (ArrayList<String>) Main.getObjectFromServer("showAllAcc");
        for (String tmp : list) {
            allUsers = tmp.split(" ");
            ars.add(new AccList(Integer.parseInt(allUsers[0]),allUsers[1], allUsers[2], Integer.parseInt(allUsers[3])));
        }
        userTable.setItems(ars);
    }

    public void updateUser(String column, String newVariable) {
        Main.sendToServerString("editAcc " + selectedUser.getId_user() + " " + column + " " + newVariable);
    }

    public void addUser(ActionEvent actionEvent) {
        String an = login_field.getText();
        String ap = password_field.getText();
        Main.sendToServerString("registration " + an + " " + ap);
        initialize();
        login_field.setText("");
        password_field.setText("");
    }

    public void deleteUser(ActionEvent actionEvent) {
        String di = id_field.getText();
        Main.sendToServerString("deleteAc " + di);
        id_field.setText("");
        initialize();
    }

    public void search_User(ActionEvent actionEvent) {
        String si = id_field.getText();
        ArrayList<String> list = new ArrayList<>();ObservableList<AccList> ars = FXCollections.observableArrayList();
        list = (ArrayList<String>) Main.getObjectFromServer("srchAc " + si);
        String[] allUsers = {""};
        for (String tmp : list) {
            allUsers = tmp.split(" ");
            ars.add(new AccList(Integer.parseInt(allUsers[0]),allUsers[1], allUsers[2], Integer.parseInt(allUsers[3])));
        }
        userTable.setItems(ars);
        id_field.setText("");
    }

    public void show_all_Users(ActionEvent actionEvent) {
        initialize();
    }

    public void exit_Button(ActionEvent actionEvent) {
        new Main().openNewWindow((Stage) exit_button.getScene().getWindow(), true , "/views/AdminStart.fxml", "Меню Админа", false);
    }
}
