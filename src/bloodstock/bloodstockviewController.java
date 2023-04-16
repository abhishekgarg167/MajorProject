package bloodstock;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import donormaster.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class bloodstockviewController {

    @FXML
    private ResourceBundle resources;

    Connection con;
    PreparedStatement pst;
    ResultSet table;
    
    @FXML
    private URL location;

    @FXML
    private TextField txtabn;

    @FXML
    private TextField txtabp;

    @FXML
    private TextField txtan;

    @FXML
    private TextField txtap;

    @FXML
    private TextField txtbn;

    @FXML
    private TextField txtbp;

    @FXML
    private TextField txton;

    @FXML
    private TextField txtop;

    ActionEvent event;
    void doSearch(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("select*from total_blood_record");
    		table=pst.executeQuery();
    		while(table.next()) {
    			txtop.setText(table.getString("op"));
    			txton.setText(table.getString("on"));
    			txtap.setText(table.getString("ap"));
    			txtan.setText(table.getString("an"));
    			txtbp.setText(table.getString("bp"));
    			txtbn.setText(table.getString("bn"));
    			txtabp.setText(table.getString("abp"));
    			txtabn.setText(table.getString("abn"));
    		}
    	}
    	catch(SQLException exp) {
    		
    	}
    }
    
    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
    	doSearch(event);
    }

}
