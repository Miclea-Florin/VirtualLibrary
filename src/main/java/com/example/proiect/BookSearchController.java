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
import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class BookSearchController implements Initializable {

    @FXML
    Button BackButton;
    @FXML
    private TableView<BookSearch> CarteTableView;
    @FXML
    private TableColumn<BookSearch,String> CarteIDTableColumn;

    @FXML
    private TextField keywordTextField;
    @FXML
    private TableColumn<BookSearch,String> CarteTitluTableColumn;
    @FXML
    private TableColumn<BookSearch,String> CarteEdituraTableColumn;
    @FXML
    private TableColumn<BookSearch,String> CarteAutoriTableColumn;
    @FXML
    private TableColumn<BookSearch,String> CarteAnTableColumn;
    @FXML
    private TableColumn<BookSearch,String> CarteISBNTableColumn;
    @FXML
    private TableColumn<BookSearch,String> CarteLimbaTableColumn;
    @FXML
    private TableColumn<BookSearch,String> CartePaginiTableColumn;
    @FXML
    private TableColumn<BookSearch,String> CarteRatingTableColumn;

    ObservableList<BookSearch> BookSearchObservableList = FXCollections.observableArrayList();
    public void BackButtonOnAction(ActionEvent e) throws IOException {
        SceneController.switchToLogin(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String CarteViewQuery = "select id, title, publisher,authors,year,isbn,language,pages,rating from carti";


        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(CarteViewQuery);

            while(queryOutput.next()){
                    Integer queryID= queryOutput.getInt("id");
                String queryTitle= queryOutput.getString("title");
                String queryPublisher= queryOutput.getString("publisher");
                String queryAuthors= queryOutput.getString("authors");
                Integer queryYear= queryOutput.getInt("year");
                String queryISBN= queryOutput.getString("ISBN");
                String queryLanguage= queryOutput.getString("language");
                Integer queryPages= queryOutput.getInt("pages");
                Float queryRating= queryOutput.getFloat("rating");


                BookSearchObservableList.add(new BookSearch(queryID,queryTitle,queryPublisher,queryAuthors,queryYear,queryISBN,queryLanguage,queryPages,queryRating));
            }

            CarteIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            CarteTitluTableColumn.setCellValueFactory(new PropertyValueFactory<>("titlu"));
            CarteEdituraTableColumn.setCellValueFactory(new PropertyValueFactory<>("editura"));
            CarteAutoriTableColumn.setCellValueFactory(new PropertyValueFactory<>("autori"));
            CarteAnTableColumn.setCellValueFactory(new PropertyValueFactory<>("an"));
            CarteISBNTableColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            CarteLimbaTableColumn.setCellValueFactory(new PropertyValueFactory<>("limba"));
            CartePaginiTableColumn.setCellValueFactory(new PropertyValueFactory<>("pagini"));
            CarteRatingTableColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));

            //System.out.println(BookSearchObservableList);
            CarteTableView.setItems(BookSearchObservableList);

            FilteredList<BookSearch> filteredData = new FilteredList<>(BookSearchObservableList, b ->true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredData.setPredicate(BookSearch -> {

                    if(newValue.isEmpty() || newValue.isBlank() || newValue== null){
                        return true;
                    }
                    String searchKeyword= newValue.toLowerCase();
                    if(BookSearch.getTitlu().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if (BookSearch.getISBN().toLowerCase().indexOf(searchKeyword) > -1)
                    {return true;}
                    else if (BookSearch.getAutori().toLowerCase().indexOf(searchKeyword) > -1)
                    {return true;}
                    else if (BookSearch.getEditura().toLowerCase().indexOf(searchKeyword) > -1)
                    {return true;}
                    else if (BookSearch.getLimba().toLowerCase().indexOf(searchKeyword) > -1)
                    {return true;}
                    else if (BookSearch.getAn().toString().toLowerCase().indexOf(searchKeyword) > -1)
                    {return true;}
                    else
                        return false;

                });

            });

            SortedList<BookSearch> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(CarteTableView.comparatorProperty());
            CarteTableView.setItems(sortedData);

        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}
