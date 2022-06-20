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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class adminController implements Initializable {

    double x, y;
    @FXML
    private ImageView home;
    @FXML
    private TableColumn<ConfessionPost,String> ID;
    @FXML
    private TableColumn<ConfessionPost,String> content;
    @FXML
    private Label idSelected;
    @FXML
    private Label lblNo;
    @FXML
    private Label lblRemove;
    @FXML
    private Label lblSelected10;
    @FXML
    private Label lblYes;
    @FXML
    private Button remove;
    @FXML
    private Button removePostSelected;
    @FXML
    private Label reviewPostContentField;
    @FXML
    private Button selectID;
    @FXML
    private Button spamCheck;
    @FXML
    private Button submit;
    @FXML
    private TableView<ConfessionPost> tableview;
    @FXML
    private TableColumn<ConfessionPost, String> time;
    @FXML
    private Label lblSelected2;
    @FXML
    private Label lblSelected3;
    @FXML
    private ListView<String> listview;


    private Scene scene;

    ObservableList<ConfessionPost> List = FXCollections.observableArrayList( new ConfessionPost("UM0001", "I am so happy", "20220930"),
            new ConfessionPost("UM0002", "I am so sad", "20220310"),
            new ConfessionPost("UM0003", "I am so angry", "20220625"),
            new ConfessionPost("UM0004", "I am so regret", "20221231"));

    ObservableList<String> postID = FXCollections.observableArrayList("UM0020","UM0034","UM0052","UM0022");

    @Override
    public void initialize(URL url, ResourceBundle rb){

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        content.setCellValueFactory(new PropertyValueFactory<>("Content"));

        tableview.setItems(List);

        listview.getItems().addAll(postID);

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
    public void handleSelectIDAction(ActionEvent event) {
        ConfessionPost post = tableview.getSelectionModel().getSelectedItem();
        idSelected.setText(post.getID());
        lblSelected3.setText(post.getTime());
        reviewPostContentField.setText(post.getContent());
        lblSelected2.setText(post.getID());
    }

    @FXML
    public void btnSpamCheck(ActionEvent event) {

    }

    @FXML
    public void submit(ActionEvent event) {
        ConfessionPost p1 = new ConfessionPost("UM0001", "Me and my friend", "2022 09 30");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Post is submitted");
        alert.setContentText(" Submitted at " + p1.getTime() + "\n"+" Confession post ID:  " + p1.getID() + "\n"+" Please wait for the approval. \n"+ " Your confession will be published soon. ");
        alert.showAndWait();
    }

    @FXML
    public void handleRemoveIDAction(ActionEvent event) {
        lblRemove.setText(listview.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void btnRemove(ActionEvent event) {

    }
}
