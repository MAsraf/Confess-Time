package com.example.demo11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

public class searchPostController implements Initializable{

    double x, y;

    @FXML
    private ImageView home;

    private Scene scene;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<ConfessionPost> tableview;
    @FXML
    private TableColumn<ConfessionPost, String> ID;
    @FXML
    private TableColumn<ConfessionPost, String> content;
    @FXML
    private TableColumn<ConfessionPost, String> time;
    @FXML
    private Label lblSelected2;
    @FXML
    private Label lblSelected3;
    @FXML
    private Label lblSelected4;
    @FXML
    private Label contentField;

    //observable list to store data
    private final ObservableList<ConfessionPost> dataList = FXCollections.observableArrayList();


    @Override
        public void initialize(URL url, ResourceBundle rb){

            ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            time.setCellValueFactory(new PropertyValueFactory<>("Time"));
            content.setCellValueFactory(new PropertyValueFactory<>("Content"));

            ConfessionPost p1 = new ConfessionPost("UM0001", "I am so happy", "2022 09 30");
            ConfessionPost p2 =  new ConfessionPost("UM0002", "I am so sad", "2022 03 10");
            ConfessionPost p3 = new ConfessionPost("UM0003", "I am so angry", "2022 06 25");
            ConfessionPost p4 = new ConfessionPost("UM0004", "I am so regret", "2022 12 31");

            dataList.addAll(p1,p2,p3,p4);
            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<ConfessionPost> filteredData = new FilteredList<>(dataList, confessionPost -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate(confessionPost -> {
                    //If filter text is empty, display all post.
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();
                    // Compare ID, Content & Time of every post with filter text.
                    if(confessionPost.getID().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(confessionPost.getContent().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(confessionPost.getTime().toLowerCase().contains(lowerCaseFilter))
                        return true;
                    else
                        return false;
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<ConfessionPost> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tableview.comparatorProperty());
            // 5. Add sorted (and filtered) data to the table.
            tableview.setItems(sortedData);
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
    public void handleSelectIDAction2(ActionEvent event) {
        ConfessionPost post = tableview.getSelectionModel().getSelectedItem();
        lblSelected4.setText(post.getID());
        contentField.setText(post.getContent());
        lblSelected2.setText(post.getID());
        lblSelected3.setText(post.getTime());
    }

}
