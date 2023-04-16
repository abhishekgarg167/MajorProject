package donormaster;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DonorMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    ResultSet table;
    @FXML
    private ComboBox<String> comboBG;

    @FXML
    private ComboBox<String> comboGender;

    @FXML
    private ImageView imgBox;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtDisease;

    @FXML
    private TextField txtMob;

    @FXML
    private TextField txtName;

    
    Connection con;
    PreparedStatement pst;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    
    @FXML
    void doBrowse(ActionEvent event) {
    	Stage stg=new Stage();
    	FileChooser file = new FileChooser();
    	File f=file.showOpenDialog(stg);
    	picpathstr=f.getPath();
    	//String picpath=f.getPath();
    	Image img = new Image(picpathstr);
        imgBox.setImage(img);
    }

    
    
    @FXML
    void doClear(ActionEvent event) {
    	txtMob.setText("");
    	txtName.setText("");
    	comboGender.getEditor().setText("");
    	txtAddress.setText("");
    	txtCity.setText("");
    	comboBG.getEditor().setText("");
    	txtAge.setText("");
    	txtDisease.setText("");
    }

    @FXML
    void doDelete(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("delete from donors where mobile=?");
    		pst.setString(1, txtMob.getText());
    		pst.executeUpdate();
    		showMsg("Deleted");
    	}
    	catch(SQLException exp) {
    		
    	}
    }

    String picpathstr;
    
    @FXML
    void doRegister(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("insert into donors values(?,?,?,?,?,?,?,?,?,current_date())");
    		pst.setString(1, txtMob.getText());
    		pst.setString(2, String.valueOf(picpathstr));
    		pst.setString(3, txtName.getText());
    		pst.setString(4, comboGender.getEditor().getText());
    		pst.setString(5, txtAddress.getText());
    		pst.setString(6, txtCity.getText());
    		pst.setString(7, comboBG.getEditor().getText());
    		pst.setInt(8, Integer.parseInt(txtAge.getText()));
    		pst.setString(9, txtDisease.getText());
    		pst.executeUpdate();
    		showMsg("Registered");
    	}
    	catch(SQLException exp) {
    		
    	}
    }

    @FXML
    void doSearch(ActionEvent event) {
    	boolean jasoos=false;
    	try {
    		pst=con.prepareStatement("select*from donors where mobile=?");
    		pst.setString(1, txtMob.getText());
    		table=pst.executeQuery();
    		
    		while(table.next()) {
    			jasoos=true;
    			txtName.setText(table.getString("name"));
    			comboGender.getEditor().setText(table.getString("gender"));
    			txtAddress.setText(table.getString("address"));
    			txtCity.setText(table.getString("city"));
    			comboBG.getEditor().setText(table.getString("bgroup"));
    			txtAge.setText(String.valueOf(table.getInt("age")));
    			txtDisease.setText(table.getString("disease"));
    			//txtImgPath.setText(table.getString("picpath"));
    			
    			picpathstr=table.getString("picpath");
    			Image img = new Image(picpathstr);
    			
    	        imgBox.setImage(img);
    		}
    	}
    	catch(SQLException exp) {
    		System.out.println(exp.getMessage());
    	}
    	if(jasoos==false) {
    		showMsg("Invalid Phone Number");
    	}
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	try {
    		//pst=con.prepareStatement("update donors set name=?,gender=?,address=?,city=?,bgroup=?,age=?,disease=? where mobile=?");
    		pst=con.prepareStatement("update donors set name=?,gender=?,address=?,city=?,bgroup=?,age=?,disease=?,picpath=? where mobile=?");
    		pst.setString(1, txtName.getText());
    		pst.setString(2, comboGender.getEditor().getText());
    		pst.setString(3, txtAddress.getText());
    		pst.setString(4, txtCity.getText());
    		pst.setString(5, comboBG.getEditor().getText());
    		pst.setInt(6, Integer.parseInt(txtAge.getText()));
    		pst.setString(7, txtDisease.getText());
    		pst.setString(9, txtMob.getText());
    		pst.setString(8, String.valueOf(picpathstr));
    		pst.executeUpdate();
    		showMsg("Updated");
    	}
    	catch(SQLException exp) {
    		
    	}
    }

    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
    	ArrayList<String>bg=new ArrayList<String>(Arrays.asList("Select","op","on","ap","an","bp","bn","abp","abn"));
    	comboBG.getItems().setAll(bg);
    	ArrayList<String>gender=new ArrayList<String>(Arrays.asList("Select","Male","Female"));
    	comboGender.getItems().setAll(gender);
    }

}
