package Controllers.NhanKhauControllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ThemNhanKhauController implements Initializable{

    @FXML
    private TextField danTocField;

    @FXML
    private TextField diaChiHienTaiField;

    @FXML
    private TextField hoVaTenField;

    @FXML
    private MenuItem namItem;

    @FXML
    private DatePicker ngaySinhField;

    @FXML
    private TextField ngheNghiepField;

    @FXML
    private TextField nguyenQuanField;

    @FXML
    private TextField noiLamViecField;

    @FXML
    private TextField noiThuongTruField;

    @FXML
    private MenuItem nuItem;

    @FXML
    private TextField quocTichField;

    @FXML
    private TextField soCMNDField;

    @FXML
    private Button themMoiButton;

    @FXML
    private TextField tonGiaoField;

    @FXML
    private TextField trinhDoHocVanField;
    
    @FXML 
    private MenuButton gioiTinhField;

    @FXML
    private Button troVeButton;
    
    
    
    public String getFormattedDate() {
        LocalDate selectedDate = ngaySinhField.getValue(); // Lấy ngày từ DatePicker

        if (selectedDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng ngày
            String formattedDate = selectedDate.format(formatter); // Chuyển đổi ngày thành chuỗi theo định dạng

            // Sử dụng chuỗi đã định dạng
            System.out.println("Ngày sinh đã định dạng: " + formattedDate);
            return formattedDate;
            // Hoặc bạn có thể làm điều gì đó khác với chuỗi đã định dạng ở đây
        } else {
            System.out.println("Ngày sinh không hợp lệ.");
            return null;
        }
    }
    
    public Boolean getSex() {
    	if(gioiTinhField.getText().equals("Nam")) {
    		return true;
    	}else if(gioiTinhField.getText().equals("Nữ")){
    		return false;
    	}else {
    		return null;
    	}
    	
    }
    
    public void insertDataToDatabase() {
        // Lấy giá trị từ các TextField
        String hoVaTen = hoVaTenField.getText();
        String ngaySinh = getFormattedDate();
        String nguyenQuan = nguyenQuanField.getText();
        String danToc = danTocField.getText();
        String soCMND = soCMNDField.getText();
        String noiThuongTru = noiThuongTruField.getText();
        String trinhDoHocVan = trinhDoHocVanField.getText();
        String ngheNghiep = ngheNghiepField.getText();
        Boolean gioiTinh = getSex();
        String tonGiao = tonGiaoField.getText();
        String quocTich = quocTichField.getText();
        String diaChiHienTai = diaChiHienTaiField.getText();
        String noiLamViec = noiLamViecField.getText();

        try {
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DBConnect.getConnection();

            // Tạo truy vấn INSERT
            String query = "INSERT INTO NhanKhau (hoVaTen, ngaySinh, nguyenQuan, danToc, soCMND, noiThuongTru, trinhDoHocVan, ngheNghiep, gioiTinh, tonGiao, quocTich, diaChiHienTai, noiLamViec) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Tạo prepared statement để tránh lỗi SQL Injection và tăng bảo mật
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, hoVaTen);
            preparedStatement.setString(2, ngaySinh);
            preparedStatement.setString(3, nguyenQuan);
            preparedStatement.setString(4, danToc);
            preparedStatement.setString(5, soCMND);
            preparedStatement.setString(6, noiThuongTru);
            preparedStatement.setString(7, trinhDoHocVan);
            preparedStatement.setString(8, ngheNghiep);
            preparedStatement.setBoolean(9, gioiTinh);
            preparedStatement.setString(10, tonGiao);
            preparedStatement.setString(11, quocTich);
            preparedStatement.setString(12, diaChiHienTai);
            preparedStatement.setString(13, noiLamViec);

            // Thực thi truy vấn INSERT
            preparedStatement.executeUpdate();

            // Đóng kết nối
            conn.close();

            // In thông báo khi thêm dữ liệu thành công
            System.out.println("Thêm dữ liệu thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		troVeButton.setOnAction(e->{
			Stage stage = (Stage)troVeButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showQuanLyNhanKhau();
		});
		themMoiButton.setOnAction(e->{
			insertDataToDatabase();
		});
		nuItem.setOnAction(e->{
			gioiTinhField.setText(nuItem.getText());
		});
		namItem.setOnAction(e->{
			gioiTinhField.setText(namItem.getText());
		});
	}

}
