package Controllers.KhoanThuControllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SuaKhoanThuController implements Initializable{

    @FXML
    private Button huyButton;

    @FXML
    private TextField maKhoanThuText;

    @FXML
    private TextField soTienNopText;

    @FXML
    private Button suaButton;

    @FXML
    private TextField tenKhoanThuText;
    
    public void setMaKhoanThu(String mkt) {
    	maKhoanThuText.setText(mkt);
    }
    
    
    
    public void Update() {
    	String query = "UPDATE KhoanThu\r\n"
    			+ "SET tenKhoanThu = ?, soTien = ?\r\n"
    			+ "WHERE makhoanThu = ?;";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, tenKhoanThuText.getText());
			preparedStatement.setDouble(2, Double.parseDouble(soTienNopText.getText()));
			preparedStatement.setString(3, maKhoanThuText.getText());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		maKhoanThuText.setText(maKhoanThuText.getText());
		suaButton.setOnAction(e->{
			Update();
			Stage stage = (Stage)suaButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThu();
		});
		huyButton.setOnAction(e->{
			Stage stage = (Stage)huyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThu();
		});
	}

}






























































