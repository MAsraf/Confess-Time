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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class sharePostController implements Initializable {

    double x, y;

    @FXML
    private ImageView home;
    @FXML
    private Button btnAddFile;
    @FXML
    private Button btnAddImage;
    @FXML
    private TextArea taFile;
    @FXML
    private ImageView Image;
    @FXML
    private TextField searchBar;
    @FXML
    private ListView<String> listView;
    @FXML
    private ImageView searchID;
    @FXML
    private Label lblSelected;
    @FXML
    private Button sharePost;

    private Scene scene;

    private Parent root;

    //Search list
    ObservableList<String> replyID = FXCollections.observableArrayList(
            Arrays.asList("UM001","UM002","UM003","UM004","UM005")
    );

    //Create a FileChooser object
    final FileChooser FC = new FileChooser();

    //observable list to store data
    private final ObservableList<ConfessionPost> dataList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){

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

    public void backToHome(MouseEvent event) throws IOException{
        if(event.getSource()==home) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homePage.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void handleBtnAddFile(ActionEvent event) {

        FC.setTitle("My File Chooser");

        //Set the initial directory for the displayed file dialog
        // user.home refers to the path to the user directory
        FC.setInitialDirectory(new File(System.getProperty("user.home")));
        //Get the extension filters used in the displayed file dialog
        FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files","*.*"));
        //Set the selected file or null id no file has been selected
        File file = FC.showOpenDialog(null);
        if(file!=null) {
            // Returns the absolute pathname string of this abstract pathname
            taFile.appendText(file.getAbsolutePath() + "\n");
        }else{
            System.out.println("A file is invalid!");
        }
    }

    @FXML
    void handleBtnAddImage(ActionEvent event) {
        FC.setTitle("My File Chooser");

        //Set the initial directory for the displayed file dialog
        // user.home refers to the path to the user directory
        FC.setInitialDirectory(new File(System.getProperty("user.home")));
        //Get the extension filters used in the displayed file dialog
        FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
        //Set the selected file or null id no file has been selected
        File file = FC.showOpenDialog(null);
        if(file!=null) {
            // Returns the absolute pathname string of this abstract pathname
            //taFile.appendText(file.getAbsolutePath() + "\n");

            //URI that represents this abstract pathname
            Image.setImage(new Image(file.toURI().toString()));
        }else{
            System.out.println("A file is invalid!");
        }
    }

    public void searchClick(MouseEvent event) throws IOException{
        if(event.getSource()==searchID) {
            listView.getItems().clear();
            listView.getItems().addAll(searchList(searchBar.getText(),replyID));
        }
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @FXML
    public void handleSelectIDAction(ActionEvent event) {
        lblSelected.setText(listView.getSelectionModel().getSelectedItem()+" Selected " +
                ", at Index "+listView.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void submit(ActionEvent event) {

        ConfessionPost p1 = new ConfessionPost("UM0001", "I am so happy", "2022 09 30");


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Post is submitted");
        alert.setContentText(" Submitted at " + p1.getTime() + "\n"+" Confession post ID:  " + p1.getID() + "\n"+" Please wait for the approval. \n"+ " Your confession will be published soon. ");
        alert.showAndWait();
    }







}