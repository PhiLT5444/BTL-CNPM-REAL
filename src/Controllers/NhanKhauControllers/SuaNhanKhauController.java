package Controllers.NhanKhauControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class SuaNhanKhauController implements Initializable{

    @FXML
    private TextField danTocField;

    @FXML
    private TextField diaChiHienTaiField;

    @FXML
    private MenuButton gioiTinhField;

    @FXML
    private TextField hoVaTenField;

    @FXML
    private MenuItem namItem;

    @FXML
    private TextField ngaySinhField;

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
    private Button troVeButton;
    
    public void setData(String hoTen, String ngaySinh, String nguyenQuan, String danToc, String soCCCD, String noiThuongTru, String trinhDoHocVan, String ngheNghiep, 
    					Boolean gioiTinh, String tonGiao, String quocTich, String diaChiHienTai, String noiLamViec) {
    	this.hoVaTenField.setText(hoTen);
    	this.ngaySinhField.setText(ngaySinh);
    	this.nguyenQuanField.setText(nguyenQuan);
    	this.danTocField.setText(danToc);
    	this.soCMNDField.setText(soCCCD);
    	this.noiThuongTruField.setText(noiThuongTru);
    	this.trinhDoHocVanField.setText(trinhDoHocVan);
    	this.ngheNghiepField.setText(ngheNghiep);
    	if(gioiTinh == true) {
    		this.gioiTinhField.setText("Nam");
    	}else {
    		this.gioiTinhField.setText("Nữ");
    	}
    	this.tonGiaoField.setText(tonGiao);
    	this.quocTichField.setText(quocTich);
    	this.diaChiHienTaiField.setText(diaChiHienTai);
    	this.noiLamViecField.setText(noiLamViec);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
