package com.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class loginAndSignUpController {

    double x, y;

    @FXML
    private Label Status;
    @FXML
    private CheckBox checkBox;
    @FXML
    private PasswordField txthidePassword;
    @FXML
    private TextField txtshowPassword;
    @FXML
    private PasswordField showPassword;
    @FXML
    private TextField usernameSignUp;

    String password;
    @FXML
    private TextField username;
    @FXML
    private ImageView backToLogin;

    private Scene scene;

    private Parent root;

    @FXML
    void draged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x= event.getSceneX();
        y= event.getSceneY();
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void min(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void HidePasswordOnAction(KeyEvent event) {
        password=txthidePassword.getText();
        txtshowPassword.setText(password);
    }

    @FXML
    void ShowPasswordOnAction(KeyEvent event) {
        password=txtshowPassword.getText();
        txthidePassword.setText(password);
    }

    public void Login(ActionEvent event) throws IOException{
        if(username.getText().equals("user")&&txthidePassword.getText().equals("pass")||txtshowPassword.getText().equals("pass")){
            Status.setText("Login Success");

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homePage.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }else{
            Status.setText("Login Failed");
        }
    }

    public void Signup(ActionEvent event) throws IOException{
        if(usernameSignUp.getText().equals("user")&&showPassword.getText().equals("pass")){
            Status.setText("Signup Success");

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homePage.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }else{
            Status.setText("Signup Failed");
        }
    }

    public void switchToSignUp(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUp.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(MouseEvent event) throws IOException{
        if(event.getSource()==backToLogin) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void changeVisibility(ActionEvent event){
        if(checkBox.isSelected()){
            txtshowPassword.setText(txthidePassword.getText());
            txtshowPassword.setVisible(true);
            txthidePassword.setVisible(false);
            return;
        }
        txthidePassword.setText(txtshowPassword.getText());
        txthidePassword.setVisible(true);
        txtshowPassword.setVisible(false);
    }
}
