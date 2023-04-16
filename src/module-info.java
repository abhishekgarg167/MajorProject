module bloodbankassistant {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens donormaster to javafx.graphics, javafx.fxml;
	opens bloodunit to javafx.graphics, javafx.fxml;
	opens bloodstock to javafx.graphics, javafx.fxml;
	opens login to javafx.graphics, javafx.fxml;
	opens controlpanel to javafx.graphics, javafx.fxml;
	opens issueblood to javafx.graphics, javafx.fxml;
	opens donorsinfo to javafx.graphics, javafx.fxml,javafx.base;
	opens bloodcollection to javafx.graphics, javafx.fxml,javafx.base;
	opens bloodissued to javafx.graphics, javafx.fxml,javafx.base;
	opens controlpaneluser to javafx.graphics, javafx.fxml;
}
