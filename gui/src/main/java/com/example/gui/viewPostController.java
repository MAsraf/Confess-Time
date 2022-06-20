package com.example.demo11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class viewPostController implements Initializable {

    double x, y;
    @FXML
    private Button btnSelectID;
    @FXML
    private ImageView home;
    @FXML
    private Label lblSelected1;
    @FXML
    private Label lblSelected2;
    @FXML
    private Label lblSelected3;
    @FXML
    private TableView<ConfessionPost> viewPost;
    @FXML
    private TableColumn<ConfessionPost, String> ID;
    @FXML
    private TableColumn<ConfessionPost, String> content;
    @FXML
    private TableColumn<ConfessionPost, String> time;
    @FXML
    private Label viewPostContentField;
    @FXML
    private Label lblTime;

    private Scene scene;

    ObservableList<ConfessionPost> List = FXCollections.observableArrayList( new ConfessionPost("UM0001", "I am so happy", "20220930"),
            new ConfessionPost("UM0002", "I am so sad", "20220310"),
            new ConfessionPost("UM0003", "I am so angry", "20220625"),
            new ConfessionPost("UM0004", "I am so regret", "20221231"));

    @Override
    public void initialize(URL url, ResourceBundle rb){

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        content.setCellValueFactory(new PropertyValueFactory<>("Content"));

        viewPost.setItems(List);

    }

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

    public void backToHome(MouseEvent event) throws IOException {
        if(event.getSource()==home) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homePage.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void handleSelectIDAction1(ActionEvent event) {
        ConfessionPost post = viewPost.getSelectionModel().getSelectedItem();
        lblSelected1.setText(post.getID());
        lblSelected2.setText(post.getID());
        lblSelected3.setText(post.getTime());
        viewPostContentField.setText(post.getContent());
    }


}
