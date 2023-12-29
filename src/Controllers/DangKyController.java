package Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class DangKyController implements Initializable{

    @FXML
    private Button dangKiButton;

    @FXML
    private TextField matKhauText;

    @FXML
    private TextField taiKhoanText;
    
    @FXML 
    private Hyperlink quayLaiHL;
    
    public void DangKy() {
    	String query = "Insert into taiKhoan (tenTaiKhoan, matKhau) values (?,?)";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, taiKhoanText.getText());
			preparedStatement.setString(2, matKhauText.getText());		
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Bạn đã đăng kí thành công!");

        alert.showAndWait();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dangKiButton.setOnAction(e->{
			DangKy();
		});
		quayLaiHL.setOnAction(e->{
			Stage stage = (Stage)dangKiButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showDangNhap();
		});
	}

}
