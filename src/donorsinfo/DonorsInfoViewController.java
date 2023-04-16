package donorsinfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import donormaster.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DonorsInfoViewController {

    @FXML
    private ResourceBundle resources;

    Connection con;
    PreparedStatement pst;
    ResultSet table;
    
    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBg;

    @FXML
    private TableView<DonorBean> tblGrid;

    @FXML
    void doFetch(ActionEvent event) {
    	
    	addCols();
    	
    	String bGrp=comboBg.getEditor().getText();
    	tblGrid.setItems(null);
    	
    	tblGrid.setItems(getSelObjects(bGrp));
    }

    
    @FXML
    void doShow(ActionEvent event) {
    	
    	
    	addCols();
    	
//    	tblGrid.getColumns().clear();
//    	tblGrid.getColumns().addAll(mob, name, gender,addr,city,bgroup,dis,age);
    	tblGrid.setItems(null);
    	
    	tblGrid.setItems(getAllObjects());
    	
    }

    ObservableList<DonorBean> ary1;
    ObservableList<DonorBean> getAllObjects() {
    	//PreparedStatement pst;
    	ary1=FXCollections.observableArrayList();
		try {
    		pst=con.prepareStatement("select*from donors");
    		table=pst.executeQuery();
    		while(table.next()) {
    			String mob=table.getString("mobile");
    			String name=table.getString("name");
    			String gender=table.getString("gender");
    			String address=table.getString("address");
    			String city=table.getString("city");
    			String bgroup=table.getString("bgroup");
    			int age=table.getInt("age");
    			String disease=table.getString("disease");
    			DonorBean obj=new DonorBean(mob, name, gender,address,city,bgroup,disease,age);
    			ary1.add(obj);
    			
    			System.out.println(mob+" "+name+" "+gender+" "+address+" "+city+" "+bgroup+" "+disease+" "+age);
    			
    		}
    	}
    	catch(SQLException exp) {
    		System.out.println(exp);
    	}
		return ary1;
    }
    
    ObservableList<DonorBean> getSelObjects(String bGrp) {
    	//PreparedStatement pst;
    	ObservableList<DonorBean> ary=FXCollections.observableArrayList();
		try {
			//System.out.println(bGrp);
			//System.out.println();
    		pst=con.prepareStatement("select * from donors where bgroup="+"'"+bGrp+"'");
    		//pst=con.prepareStatement("select*from donors where bgroup="+bGrp);
    		table=pst.executeQuery();
    		while(table.next()) {
    			String mob=table.getString("mobile");
    			String name=table.getString("name");
    			String gender=table.getString("gender");
    			String address=table.getString("address");
    			String city=table.getString("city");
    			String bgroup=table.getString("bgroup");
    			int age=table.getInt("age");
    			String disease=table.getString("disease");
    			DonorBean obj=new DonorBean(mob, name, gender,address,city,bgroup,disease,age);
    			ary.add(obj);
    			
    			System.out.println(mob+" "+name+" "+gender+" "+address+" "+city+" "+bgroup+" "+disease+" "+age);
    			
    		}
    	}
    	catch(SQLException exp) {
    		System.out.println(exp);
    	}
		return ary;
    }
    
    void addCols() {
    	TableColumn<DonorBean, String>mob=new TableColumn<DonorBean,String>("mobile kuch");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mob.setMinWidth(100);
    	
    	TableColumn<DonorBean, String>name=new TableColumn<DonorBean,String>("name kuch");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<DonorBean, String>gender=new TableColumn<DonorBean,String>("gender kuch");
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    	gender.setMinWidth(100);
    	
    	TableColumn<DonorBean, String>addr=new TableColumn<DonorBean,String>("address kuch");
    	addr.setCellValueFactory(new PropertyValueFactory<>("address"));
    	addr.setMinWidth(100);
    	
    	TableColumn<DonorBean, Integer>age=new TableColumn<DonorBean,Integer>("age kuch");
    	age.setCellValueFactory(new PropertyValueFactory<>("age"));
    	age.setMinWidth(100);
    	
    	TableColumn<DonorBean, String>city=new TableColumn<DonorBean,String>("city kuch");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	city.setMinWidth(100);
    	
    	TableColumn<DonorBean, String>bgroup=new TableColumn<DonorBean,String>("bgroup kuch");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));
    	bgroup.setMinWidth(100);
    	
    	TableColumn<DonorBean, String>dis=new TableColumn<DonorBean,String>("disease kuch");
    	dis.setCellValueFactory(new PropertyValueFactory<>("disease"));
    	dis.setMinWidth(100);
    	
    	tblGrid.getColumns().clear();
    	tblGrid.getColumns().addAll(mob, name, gender,addr,city,bgroup,dis,age);
    }
    
    void doFillBg() {
    	try {
			pst=con.prepareStatement("select * from donors");
			table=pst.executeQuery();
			while(table.next()) {
				Bg.add(table.getString("bgroup"));
			}
		}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    @FXML
    void doExport(ActionEvent event) {
    	try {
    		writeExcel();
    		System.out.println("exported to excel");
    	}
    	catch(Exception exp) {
    		exp.printStackTrace();
    	}
    }
    
    void writeExcel() throws Exception{
    	Writer writer=null;
    	try {
    		File file=new File("Users.csv");
    		writer=new BufferedWriter(new FileWriter(file));
    		String text="mobile,name,gender,address,city,bloodgroup,disease,age\n";
    		writer.write(text);
    		for(DonorBean p:ary1) {
    			text=p.getMobile()+","+p.getName()+","+p.getGender()+","+p.getAddress()+","+p.getCity()+","+p.getBgroup()+","+p.getDisease()+","+p.getAge();
    			writer.write(text);
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	finally{
    		writer.flush();
    		writer.close();
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
