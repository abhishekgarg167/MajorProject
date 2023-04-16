package bloodcollection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import donormaster.DatabaseConnection;
import donorsinfo.DonorBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BloodCollectionTblViewController {

    @FXML
    private ResourceBundle resources;

    Connection con;
    PreparedStatement pst;
    ResultSet table;
    @FXML
    private URL location;

    @FXML
    private TableView<CollectionBean> tblGrid;

    @FXML
    private TextField txtMob;

    @FXML
    void doFindAll(ActionEvent event) {
    	addCols();
    	tblGrid.setItems(getAllObjects());
    }

    @FXML
    void doFindRecent(ActionEvent event) {
    	addCols();
    	tblGrid.setItems(getSelObjects());
    }
    
    
    
    ObservableList<CollectionBean> getSelObjects() {
    	//PreparedStatement pst;
    	ObservableList<CollectionBean> ary=FXCollections.observableArrayList();
    	String mobStr=txtMob.getText();
		try {
    		pst=con.prepareStatement("select*from donations where mobile="+mobStr);
    		table=pst.executeQuery();
    		while(table.next()) {
    			String mob=table.getString("mobile");
    			String bgroup=table.getString("bgroup");
    			String date=table.getString("dateofdonation");
    			CollectionBean obj=new CollectionBean(mob,bgroup,date);
    			ary.add(obj);
    			
    			System.out.println(mob+" "+" "+bgroup+" "+date);
    			
    		}
    	}
    	catch(SQLException exp) {
    		System.out.println(exp);
    	}
		return ary;
    }
    
    
    ObservableList<CollectionBean> getAllObjects() {
    	//PreparedStatement pst;
    	ObservableList<CollectionBean> ary=FXCollections.observableArrayList();
		try {
    		pst=con.prepareStatement("select*from donations");
    		table=pst.executeQuery();
    		while(table.next()) {
    			String mob=table.getString("mobile");
    			String bgroup=table.getString("bgroup");
    			String date=table.getString("dateofdonation");
    			CollectionBean obj=new CollectionBean(mob,bgroup,date);
    			ary.add(obj);
    			
    			System.out.println(mob+" "+bgroup+" "+date);
    			
    		}
    	}
    	catch(SQLException exp) {
    		System.out.println(exp);
    	}
		return ary;
    }
    
    void addCols() {
    	TableColumn<CollectionBean, String>mob=new TableColumn<CollectionBean,String>("mobile kuch");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mob.setMinWidth(100);
    	
    	TableColumn<CollectionBean, String>bgroup=new TableColumn<CollectionBean,String>("bgroup kuch");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));
    	bgroup.setMinWidth(100);
    	
    	TableColumn<CollectionBean, String>date=new TableColumn<CollectionBean,String>("date kuch");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	date.setMinWidth(100);
    	
//    	TableColumn<CollectionBean, LocalDate>date=new TableColumn<CollectionBean,LocalDate>("date kuch");
//    	date.setCellValueFactory(new PropertyValueFactory<>("dateofdonation"));
//    	date.setMinWidth(100);
    	
    	tblGrid.getColumns().clear();
    	tblGrid.getColumns().addAll(mob,bgroup,date);
    }

    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
    }

}
