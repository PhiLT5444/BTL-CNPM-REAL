package Controllers.KhoanThuControllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.KhoanThu;
import Models.Model;
import Models.ThuTien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KhoanThuThongKeController implements Initializable{
	
	@FXML
    private DatePicker fromDate;

    @FXML
    private Button hienThiButton;

    @FXML
    private ImageView homeButton;

    @FXML
    private TableColumn<KhoanThu, String> maKhoanThuCol;

    @FXML
    private HBox phuongTienButton;

    @FXML
    private HBox quanLyButton;

    @FXML
    private TableColumn<KhoanThu, String> tenKhoanThuCol;

    @FXML
    private HBox thongKeButton;

    @FXML
    private HBox thuTienButton;

    @FXML
    private TableView<KhoanThu> thuTienTable;

    @FXML
    private DatePicker toDate;

    @FXML
    private TextField tongSoPhieuNopText;

    @FXML
    private TableColumn<KhoanThu, String> tongSoTienCol;

    @FXML
    private TextField tongSoTienText;
    
    public void loadData() {
    	ObservableList<KhoanThu> khoanThuList = FXCollections.observableArrayList();
    	String query = "select * from KhoanThu";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				KhoanThu khoanThu = new KhoanThu();
				khoanThu.setMaKhoanThu(resultSet.getString("maKhoanThu"));
				khoanThu.setTenKhoanThu(resultSet.getString("tenKhoanThu"));
				khoanThu.setSoTien(resultSet.getDouble("soTien"));
				khoanThuList.add(khoanThu);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	thuTienTable.setItems(khoanThuList);
    	maKhoanThuCol.setCellValueFactory(new PropertyValueFactory<>("maKhoanThu"));
    	tenKhoanThuCol.setCellValueFactory(new PropertyValueFactory<>("tenKhoanThu"));
    	tongSoTienCol.setCellFactory(param -> new TableCell<>() {
    		@Override
    		protected void updateItem(String item, boolean empty) {
    		    super.updateItem(item, empty);

    		    if (empty || getTableRow() == null) {
    		        setGraphic(null);
    		        return;
    		    }

    		    double tongSoTien = 0;
    		    String query = "SELECT SUM(soTienThu) AS tongSoTienThu "
    		                 + "FROM ThuTien "
    		                 + "WHERE maKhoanThu = ? AND (ngayThu BETWEEN ? AND ?)";
    		    Connection connection = DBConnect.getConnection();

    		    try {
    		        PreparedStatement preparedStatement = connection.prepareStatement(query);
    		        KhoanThu rowData = (KhoanThu) getTableRow().getItem();
    		        preparedStatement.setString(1, rowData.getMaKhoanThu());
    		        preparedStatement.setDate(2, java.sql.Date.valueOf(fromDate.getValue()));
    		        preparedStatement.setDate(3, java.sql.Date.valueOf(toDate.getValue()));
    		        ResultSet resultSet = preparedStatement.executeQuery();
    		        
    		        // Di chuyển con trỏ tới hàng đầu tiên (nếu có)
    		        if (resultSet.next()) {
    		            tongSoTien = resultSet.getDouble("tongSoTienThu");
    		        }
    		    } catch (SQLException e) {
    		        e.printStackTrace();
    		    }

    		    Double tst = tongSoTien;
    		    setText(tst.toString());
    		}
    	});
    }

    public void setData() {
    	String query = "Select * from ThuTien";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			int count = 0;
			double tien = 0;
			while(resultSet.next()) {
				count ++;
				tien += resultSet.getDouble("soTienThu");
			}
			Integer t = count;
			Double t1 = tien;
			tongSoPhieuNopText.setText(t.toString());
			tongSoTienText.setText(t1.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setData();
		hienThiButton.setOnAction(e->{
			loadData();
		});
		
		homeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)homeButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showHome();
		});
		
		quanLyButton.setOnMouseClicked(e->{
			Stage stage = (Stage)quanLyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showQLHoKhau();
		});
		
		thuTienButton.setOnMouseClicked(e->{
			Stage stage = (Stage)quanLyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showThuTien();
		});
		
		phuongTienButton.setOnMouseClicked(e->{
			Stage stage = (Stage)quanLyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showPhuongTien();
		});
	}

}
