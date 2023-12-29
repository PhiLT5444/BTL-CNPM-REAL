package Controllers.HoKhauControllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SuaThanhVienController implements Initializable{

    @FXML
    private TableColumn<NhanKhau, Boolean> gioiTinhCol;

    @FXML
    private TableColumn<NhanKhau, String> hoTenCol;

    @FXML
    private TableColumn<NhanKhau, Integer> idCol;

    @FXML
    private TableView<NhanKhau> nhanKhauTable;

    @FXML
    private TextField quanHeText;

    @FXML
    private TableColumn<NhanKhau, String> soCMNDCol;

    @FXML
    private Button themButton;

    @FXML
    private Button troVeButton;
    
    String maHoKhau = null;
    public void setMaHoKhau(String mhk) {
    	maHoKhau = mhk;
    }
    
    public void setData() {
        String query = "select * from nhankhau";
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList(); // Tạo một danh sách để chứa dữ liệu
            
            while(resultSet.next()) {
                NhanKhau nhanKhau = new NhanKhau(); // Tạo một đối tượng NhanKhau từ dữ liệu trong ResultSet
                nhanKhau.setId(resultSet.getInt("id")); // Thiết lập các thuộc tính của đối tượng NhanKhau từ cột tương ứng trong ResultSet
                nhanKhau.setHoVaTen(resultSet.getString("hoVaTen"));
                nhanKhau.setGioiTinh(resultSet.getBoolean("gioiTinh"));
                nhanKhau.setSoCMND(resultSet.getString("soCMND"));
                
                nhanKhauList.add(nhanKhau); // Thêm đối tượng NhanKhau vào danh sách
            }
            
            // Thiết lập cột của TableView để hiển thị dữ liệu
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            hoTenCol.setCellValueFactory(new PropertyValueFactory<>("hoVaTen"));
            gioiTinhCol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
            soCMNDCol.setCellValueFactory(new PropertyValueFactory<>("soCMND"));
            
            nhanKhauTable.setItems(nhanKhauList); // Đặt danh sách chứa dữ liệu vào TableView
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void add() {
    	nhanKhauTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    	    if (newSelection != null) {
    	        // Lấy dữ liệu của hàng được chọn
    	        int id = newSelection.getId(); // Thay thế 'getId()' bằng phương thức để lấy ID của hàng trong lớp NhanKhau của bạn
    	        String hoTen = newSelection.getHoVaTen(); // Thay thế 'getHoTen()' bằng phương thức để lấy Họ tên của hàng trong lớp NhanKhau của bạn
    	        
    	        String query = "Insert into QuanHe (maNhanKhau, maHoKhau, quanHeVoiChuHo) values(?,?,?)";
    	        Connection connection = DBConnect.getConnection();
    	        try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, maHoKhau);
					preparedStatement.setString(3, quanHeText.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	        
    	        
    	        // In ra thông tin của hàng được chọn
    	        System.out.println("ID: " + id);
    	        System.out.println("Họ và tên: " + hoTen);
    	        // In ra các thông tin khác tương tự...
    	    }
    	});
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setData();
		themButton.setOnAction(e->add());		
	}

}
