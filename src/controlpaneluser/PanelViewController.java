package controlpaneluser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PanelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void openReg(ActionEvent event) {
    	try {
			Parent part=FXMLLoader.load(getClass().getResource("/donormaster/DonorMasterView.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(part);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void openIssue(ActionEvent event) {
//    	try {
//			Parent part=FXMLLoader.load(getClass().getResource("/issueblood/IssueBloodView.fxml"));
//			Stage stage=new Stage();
//			Scene scene=new Scene(part);
//			stage.setScene(scene);
//			stage.show();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

    @FXML
    void openDonate(ActionEvent event) {
    	try {
			Parent part=FXMLLoader.load(getClass().getResource("/bloodunit/BloodCollectionView.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(part);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void openStock(ActionEvent event) {
//    	try {
//			Parent part=FXMLLoader.load(getClass().getResource("/bloodstock/BloodStockView.fxml"));
//			Stage stage=new Stage();
//			Scene scene=new Scene(part);
//			stage.setScene(scene);
//			stage.show();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    @FXML
    void openDonors(ActionEvent event) {
//    	try {
//			Parent part=FXMLLoader.load(getClass().getResource("/donorsinfo/DonorsInfoView.fxml"));
//			Stage stage=new Stage();
//			Scene scene=new Scene(part);
//			stage.setScene(scene);
//			stage.show();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    @FXML
    void openHistoryDonated(ActionEvent event) {
    	try {
			Parent part=FXMLLoader.load(getClass().getResource("/bloodcollection/BloodCollectionTblView.fxml"));
			Stage stage=new Stage();
			Scene scene=new Scene(part);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void openHistoryIssued(ActionEvent event) {
//    	try {
//			Parent part=FXMLLoader.load(getClass().getResource("/bloodissued/BloodIssuedView.fxml"));
//			Stage stage=new Stage();
//			Scene scene=new Scene(part);
//			stage.setScene(scene);
//			stage.show();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    @FXML
    void initialize() {

    }

}
