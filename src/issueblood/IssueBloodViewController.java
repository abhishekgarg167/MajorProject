package issueblood;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import donormaster.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class IssueBloodViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    Connection con;
    PreparedStatement pst;
    ResultSet table;
    
    @FXML
    private ComboBox<String> comboBg;

    @FXML
    private TextField txtDoi;

    @FXML
    private TextField txtHosp;

    @FXML
    private TextField txtMob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPur;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	String bg=comboBg.getEditor().getText();
    	int val=0;
    	try {
    		pst=con.prepareStatement("insert into issued values(?,?,?,?,?,current_date())");
    		pst.setString(1, txtName.getText());
    		pst.setString(2, txtMob.getText());
    		pst.setString(3, txtHosp.getText());
    		pst.setString(4, comboBg.getEditor().getText());
    		pst.setString(5, txtPur.getText());
//    		pst.setString(1, "a");
//    		pst.setString(2, "1212");
//    		pst.setString(3, "a");
//    		pst.setString(4, comboBg.getEditor().getText());
//    		pst.setString(5, "a");
    		pst.executeUpdate();
    		showMsg("Updated");
    	}
    	catch(SQLException exp) {
    		System.out.println(exp.getMessage());
    	}
    	
    	try {
    		pst=con.prepareStatement("select * from total_blood_record");
    		table=pst.executeQuery();
    		while(table.next()) {
    			val=table.getInt(bg);
    			System.out.println(val);
    		}
    		showMsg("Updated tabel total");
    	}
    	catch(SQLException exp) {
    		showMsg(exp.toString());
    	}
    	
    	if(val==0) {
    		showMsg("no unit available");
    		return;
    	}
    	try {
    		pst=con.prepareStatement("update total_blood_record set "+bg+"="+bg+"-?");
    		System.out.println(String.valueOf(bg+1));
    		pst.setInt(1, 1);
    		pst.executeUpdate();
    		showMsg("Updated tabel total");
    	}
    	catch(SQLException exp) {
    		showMsg(exp.toString());
    	}
    }

    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
    	ArrayList<String>bg=new ArrayList<String>(Arrays.asList("Select","op","on","ap","an","bp","bn","abp","abn"));
    	comboBg.getItems().setAll(bg);
    }

}
