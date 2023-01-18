package com.example.proiect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

public class UserBookedController extends Main implements Initializable  {

    int RatingInteger;

    @FXML
    ChoiceBox<String> SelectRatingChoiceBox;

    @FXML
    SplitMenuButton ReturnnButton;
    @FXML
    Button RentButton;
    @FXML
    Button ReturnButton;
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

    @FXML
            private Button BookedButton;
    ObservableList<BookSearch> BookSearchObservableList = FXCollections.observableArrayList();
    public void BackButtonOnAction(ActionEvent e) throws IOException {
        SceneController.switchToUser(e);
    }

    public void returnButtonOnAction(ActionEvent e) throws IOException, SQLException {

        System.out.println(RatingInteger);
            if(RatingInteger!=0) {
                System.out.println("User ID = " +USER_ID);

                BookSearch B = CarteTableView.getSelectionModel().getSelectedItem();
                int U = B.getID();

                String query = "UPDATE carti SET BookedBy = " + "null" + " WHERE id=" + Integer.toString(U);

                DatabaseConnection connectNow1 = new DatabaseConnection();
                Connection con = connectNow1.getConnection();
                Statement statement = con.createStatement();
                statement.executeUpdate(query);

                String query3 = "select Num from carti where id="+Integer.toString(U);

                Statement statement3 = con.createStatement();
                //statement3.executeQuery(query3);
                ResultSet resultSet= statement3.executeQuery(query3);
                int bookednumber;
                resultSet.next();
                bookednumber = resultSet.getInt(1);

                double ratingNew=bookednumber;
                //System.out.println(bookednumber);
                String query5 = "select rating from carti where id= "+ Integer.toString(U);
                Statement statement5 = con.createStatement();
                ResultSet resultSetR = statement5.executeQuery(query5);
                resultSetR.next();

                ratingNew=resultSetR.getDouble(1);
                System.out.println(ratingNew + " * " + bookednumber + "=" + ratingNew*bookednumber) ;

                ratingNew=ratingNew*bookednumber;
                ratingNew=ratingNew+RatingInteger;
                bookednumber++;
                ratingNew=ratingNew/bookednumber;

                String query4 = "UPDATE carti SET Num = "+ Integer.toString(bookednumber) + " WHERE id=" + Integer.toString(U);

                Statement statement4 = con.createStatement();
                statement4.executeUpdate(query4);

                String query6 = "UPDATE carti SET rating = "+ Double.toString(ratingNew) + " WHERE id=" + Integer.toString(U);

                Statement statement6 = con.createStatement();
                statement6.executeUpdate(query6);
                System.out.println("aici"+ratingNew);


                //bookednumber++;
                String query2 = "UPDATE carti SET returnDate = null " + " WHERE id=" + Integer.toString(U);

                Statement statement2 = con.createStatement();
                statement2.executeUpdate(query2);

                SceneController.switchToBooked(e);
            }

    }

    public void BookedButtonOnAction(ActionEvent e) throws IOException {
        SceneController.switchToBooked(e);

    }

    public void RentButtonOnAction(ActionEvent e) throws IOException, SQLException {
        System.out.println(USER_ID);

        BookSearch B = CarteTableView.getSelectionModel().getSelectedItem();
        int U=B.getID();

        String query = "UPDATE carti SET BookedBy = "+ Integer.toString(USER_ID) +" WHERE id=" + Integer.toString(U);
        DatabaseConnection connectNow1 = new DatabaseConnection();
        Connection con = connectNow1.getConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate(query);


        String query2 = "UPDATE carti SET returnDate = (SELECT DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 10 DAY)) "+" WHERE id=" + Integer.toString(U);
        Statement statement2 = con.createStatement();
        statement2.executeUpdate(query2);

        SceneController.switchToUser(e);

    }



    public void getRating(ActionEvent e){
        RatingInteger = Integer.parseInt(SelectRatingChoiceBox.getValue());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] r ={"1","2","3","4","5"};
        SelectRatingChoiceBox.getItems().addAll(r);
        SelectRatingChoiceBox.setOnAction(this::getRating);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String CarteViewQuery = "select id, title, publisher,authors,year,isbn,language,pages,rating from carti where Bookedby ="+USER_ID;


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
