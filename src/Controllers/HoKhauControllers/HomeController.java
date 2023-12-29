package Controllers.HoKhauControllers;

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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController implements Initializable{

    @FXML
    private HBox hoKhauButton;

    @FXML
    private HBox nhanKhauButton;

    @FXML
    private Text soHoKhauText;

    @FXML
    private Text soNhanKhauTamVangText;

    @FXML
    private Text soNhanKhauText;

    @FXML
    private Text soNhanKhauThuongTruText;

    @FXML
    private HBox thongKeButton;
    
    @FXML
    private HBox khoanThuButton;
    
    public void setData() {
    	Connection connection = DBConnect.getConnection();
    	String nhanKhauQuery = "select * from nhanKhau";
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(nhanKhauQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			Integer count = 0;
			while(resultSet.next()) {
				count = count + 1;
			}
			soNhanKhauText.setText(count.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String hoKhauQuery = "select * from hokhau";
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(hoKhauQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			Integer count = 0;
			while(resultSet.next()) {
				count = count + 1;
			}
			soHoKhauText.setText(count.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setData();
		hoKhauButton.setOnMouseClicked(e->{
			Stage stage = (Stage)hoKhauButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showQLHoKhau();
		});
		nhanKhauButton.setOnMouseClicked(e->{
			Stage stage = (Stage)nhanKhauButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showQuanLyNhanKhau();
		});
		khoanThuButton.setOnMouseClicked(e->{
			Stage stage = (Stage)nhanKhauButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThu();
		});																						
	}

}
