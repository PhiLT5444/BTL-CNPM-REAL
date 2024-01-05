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
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DangKyController implements Initializable{

    @FXML
    private Button dangKiButton;

    @FXML
    private PasswordField matKhauText;

    @FXML
    private TextField taiKhoanText;
    
    @FXML 
    private Hyperlink quayLaiHL;
    
    public void DangKy() {
        String checkQuery = "SELECT * FROM taiKhoan WHERE tenTaiKhoan = ?";
        String insertQuery = "INSERT INTO taiKhoan (tenTaiKhoan, matKhau) VALUES (?, ?)";
        
        Connection connection = DBConnect.getConnection();
        try {
            // Kiểm tra xem tên đăng nhập đã tồn tại chưa
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, taiKhoanText.getText());
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // Nếu tên đăng nhập đã tồn tại, hiển thị thông báo lỗi
                showAlert(AlertType.ERROR, "Lỗi", null, "Tên đăng nhập đã tồn tại!");
            } else {
                // Nếu tên đăng nhập chưa tồn tại, thêm tài khoản mới
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, taiKhoanText.getText());
                insertStatement.setString(2, matKhauText.getText());
                insertStatement.executeUpdate();

                // Hiển thị thông báo thành công
                showAlert(AlertType.INFORMATION, "Thông báo", null, "Bạn đã đăng kí thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để hiển thị Alert
    private void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
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
