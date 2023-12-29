package Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class DangNhapController implements Initializable {

	@FXML 
	private Button dangKiButton1;
	
    @FXML
    private Button dangKiButton;

    @FXML
    private TextField matKhauText;

    @FXML
    private TextField taiKhoanText;
    
    public void DangNhap() {
    	String query = "Select * from TaiKhoan";
        Connection connection = DBConnect.getConnection();
        boolean loginSuccessful = false; // Sử dụng biến để kiểm tra đăng nhập có thành công không
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (taiKhoanText.getText().equals(resultSet.getString("tenTaiKhoan")) && matKhauText.getText().equals(resultSet.getString("matKhau"))) {
                    // Đăng nhập thành công
                    Stage stage = (Stage) dangKiButton.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);
                    Model.getInstance().getViewFactory().showHome();
                    loginSuccessful = true;
                    break; // Thoát khỏi vòng lặp khi đăng nhập thành công
                }
            }
            
            // Kiểm tra nếu đăng nhập không thành công, hiển thị thông báo
            if (!loginSuccessful) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thông báo đăng nhập");
                alert.setHeaderText(null);
                alert.setContentText("Đăng nhập không thành công! Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.");

                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dangKiButton.setOnAction(e->{
			DangNhap();
		});
		
		dangKiButton1.setOnAction(e->{
			Stage stage = (Stage)dangKiButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showDangKy();
		});
	}

}
