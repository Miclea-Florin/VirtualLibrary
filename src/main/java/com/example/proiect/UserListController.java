package com.example.proiect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.action.Action;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class UserListController extends Main implements Initializable  {

    @FXML
    Button DeleteButton;
    @FXML
    Button RentButton;
    @FXML
    Button BackButton;
    @FXML
    private TableView<UserSearch> CarteTableView;
    @FXML
    private TableColumn<UserSearch,String> IDTableColumn;

    @FXML
    private TextField keywordTextField;
    @FXML
    private TableColumn<UserSearch,String> NumeTableColumn;
    @FXML
    private TableColumn<UserSearch,String> PrenumeTableColumn;
    @FXML
    private TableColumn<UserSearch,String> UsernameTableColumn;


    @FXML
            private Button BookedButton;
    @FXML private Button AdminButton;

    ObservableList<UserSearch> BookSearchObservableList = FXCollections.observableArrayList();
    public void BackButtonOnAction(ActionEvent e) throws IOException {
        SceneController.switchToDashboard(e);
    }

    public void BookedButtonOnAction(ActionEvent e) throws IOException {
        SceneController.switchToBooked(e);

    }

    public void DeleteButtonOnAction(ActionEvent e) throws IOException, SQLException {
        System.out.println(USER_ID);

        UserSearch B = CarteTableView.getSelectionModel().getSelectedItem();
        int U=B.getId();
        System.out.println();
        String query = "DELETE FROM normaluseraccounts WHERE idUserAccounts = "+ Integer.toString(U);
        DatabaseConnection connectNow1 = new DatabaseConnection();
        Connection con = connectNow1.getConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        SceneController.switchToUserList(e);

    }

    public void AdminButtonOnAction(ActionEvent e) throws IOException, SQLException {
        System.out.println(USER_ID);
        UserSearch B = CarteTableView.getSelectionModel().getSelectedItem();
        //String CarteViewQuery = "select idUserAccounts, Firstname, Lastname, Username,password from normaluseraccounts";

        int U=B.getId();
        System.out.println();
        String QueryInput = "select idUserAccounts, Firstname, Lastname, Username,password from normaluseraccounts where idUserAccounts = " + Integer.toString(U);

        DatabaseConnection connectNow1 = new DatabaseConnection();
        Connection con = connectNow1.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet queryOutput = statement1.executeQuery(QueryInput);
        queryOutput.next();
        //Integer queryID= queryOutput.getInt("idUserAccounts");
        String queryPrenume= queryOutput.getString("Firstname");
        String queryNume= queryOutput.getString("Lastname");
        String queryUsername= queryOutput.getString("Username");
        String queryPassword= queryOutput.getString("Password");

        String query1 = "INSERT INTO `useraccounts` (`Firstname`, `Lastname`, `Username`, `Password`) VALUES ('" + queryPrenume + "', '" + queryNume + "', '" + queryUsername + "', '" + queryPassword + "');";
        Statement statementInsert = con.createStatement();
        statementInsert.executeUpdate(query1);


        String query = "DELETE FROM normaluseraccounts WHERE idUserAccounts = "+ Integer.toString(U);
        //DatabaseConnection connectNow1 = new DatabaseConnection();
        //Connection con = connectNow1.getConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        SceneController.switchToUserList(e);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String CarteViewQuery = "select idUserAccounts, Firstname, Lastname, Username from normaluseraccounts";


        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(CarteViewQuery);

            while(queryOutput.next()){
                Integer queryID= queryOutput.getInt("idUserAccounts");
                String queryPrenume= queryOutput.getString("Firstname");
                String queryNume= queryOutput.getString("Lastname");
                String queryUsername= queryOutput.getString("Username");



                BookSearchObservableList.add(new UserSearch(queryID,queryPrenume,queryNume,queryUsername));
                //System.out.println(queryNume);
            }

            IDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            NumeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
            PrenumeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
            UsernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));

            //System.out.println(BookSearchObservableList);
            CarteTableView.setItems(BookSearchObservableList);

            FilteredList<UserSearch> filteredData = new FilteredList<>(BookSearchObservableList, b ->true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredData.setPredicate(UserSearch -> {

                    if(newValue.isEmpty() || newValue.isBlank() || newValue== null){
                        return true;
                    }

                    String searchKeyword= newValue.toLowerCase();
                     if (UserSearch.getUsername().toLowerCase().indexOf(searchKeyword) > -1)
                    {return true;}
                    else if (UserSearch.getLastname().toLowerCase().indexOf(searchKeyword) > -1)
                    {return true;}
                    else if (UserSearch.getFirstname().toLowerCase().indexOf(searchKeyword) > -1)
                    {return true;}
                    else
                        return false;

                });

            });

            SortedList<UserSearch> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(CarteTableView.comparatorProperty());
            CarteTableView.setItems(sortedData);

        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}
