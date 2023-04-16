package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtPswd;

    @FXML
    private TextField txtUid;

    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    @FXML
    void doLogin(ActionEvent event) {
    	String usid=txtUid.getText();
    	String ps=txtPswd.getText();
    	if(usid.equals("admin") && ps.equals("admin")) {
    		try {
    			Parent part=FXMLLoader.load(getClass().getResource("/controlpanel/PanelView.fxml"));
    			Stage stage=new Stage();
    			Scene scene=new Scene(part);
    			stage.setScene(scene);
    			stage.show();
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//    		showMsg("Invalid username or password");
//    		return;
    	}
    	else if(usid.equals("user") && ps.equals("user")) {
    		try {
    			Parent part=FXMLLoader.load(getClass().getResource("/controlpaneluser/PanelView.fxml"));
    			Stage stage=new Stage();
    			Scene scene=new Scene(part);
    			stage.setScene(scene);
    			stage.show();
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//    		showMsg("Invalid username or password");
//    		return;
    	}
    	else {
    		showMsg("Invalid username or password");
    		return;
    	}
    	
    }
    
    void doFill() {
    	txtUid.setText("admin");
    	txtPswd.setText("admin");
    }
    
    @FXML
    void initialize() {
        doFill();
    }

}
