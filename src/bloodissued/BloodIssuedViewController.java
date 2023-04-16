package bloodissued;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import bloodcollection.CollectionBean;
import donormaster.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BloodIssuedViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    Connection con;
    PreparedStatement pst;
    ResultSet table;
    @FXML
    private DatePicker dateBox;

    @FXML
    private ComboBox<String> comboBg;
    
    @FXML
    private TableView<IssuedBean> tblgrid;

    @FXML
    private TextField txtBg;

    @FXML
    void doFetch(ActionEvent event) {
    	addCols();
    	tblgrid.setItems(getSelObjects());
    }
    
    
    ObservableList<IssuedBean> getSelObjects() {
    	//PreparedStatement pst;
    	ObservableList<IssuedBean> ary=FXCollections.observableArrayList();
    	String bgStr=comboBg.getEditor().getText();
    	LocalDate dt=dateBox.getValue();
    	String date1=String.valueOf(dt);
		try {
    		pst=con.prepareStatement("select*from issued where bgroup="+"'"+bgStr+"' and doi="+"'"+date1+"'");
    		table=pst.executeQuery();
    		while(table.next()) {
    			String name=table.getString("nname");
    			String mob=table.getString("mobile");
    			String hos=table.getString("hospital");
    			String bgroup=table.getString("bgroup");
    			String rsn=table.getString("reason");
    			String date=table.getString("doi");
    			IssuedBean obj=new IssuedBean(name,mob,hos,bgroup,rsn,date);
    			ary.add(obj);
    			
    			System.out.println(name+" "+mob+" "+" "+bgroup+" "+date);
    			
    		}
    	}
    	catch(SQLException exp) {
    		System.out.println(exp);
    	}
		return ary;
    }
    
    void addCols() {
    	TableColumn<IssuedBean, String>name=new TableColumn<IssuedBean,String>("name kuch");
    	name.setCellValueFactory(new PropertyValueFactory<>("nname"));
    	name.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String>mob=new TableColumn<IssuedBean,String>("mobile kuch");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mob.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String>hosp=new TableColumn<IssuedBean,String>("hosp kuch");
    	hosp.setCellValueFactory(new PropertyValueFactory<>("hospital"));
    	hosp.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String>bgroup=new TableColumn<IssuedBean,String>("bgroup kuch");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));
    	bgroup.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String>rsn=new TableColumn<IssuedBean,String>("reason kuch");
    	rsn.setCellValueFactory(new PropertyValueFactory<>("reason"));
    	rsn.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String>date=new TableColumn<IssuedBean,String>("date kuch");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	date.setMinWidth(100);
    	
//    	TableColumn<CollectionBean, LocalDate>date=new TableColumn<CollectionBean,LocalDate>("date kuch");
//    	date.setCellValueFactory(new PropertyValueFactory<>("dateofdonation"));
//    	date.setMinWidth(100);
    	
    	tblgrid.getColumns().clear();
    	tblgrid.getColumns().addAll(name,mob,hosp,bgroup,rsn,date);
    }

    void doFillBg() {
    	try {
			pst=con.prepareStatement("select * from issued");
			table=pst.executeQuery();
			while(table.next()) {
				Bg.add(table.getString("bgroup"));
			}
		}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    ArrayList<String>Bg=new ArrayList<String>();
    @FXML
    void initialize() {
        con=DatabaseConnection.doConnect();
        doFillBg();
        comboBg.getItems().setAll(Bg);
    }

}
