package Controllers.NhanKhauControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class NhanKhauItemController implements Initializable {

    @FXML
    private Text diaChiText;

    @FXML
    private Text gioiTinhText;

    @FXML
    private Text idText;

    @FXML
    private Text ngaySinhText;
    
    @FXML
    private Text hoTenText;
    
    public void setData(String id, String gioiTinh, String diaChi, String ngaySinh, String hoTen) {
    	idText.setText(id);
    	if(gioiTinh.equals("1")) {
    		gioiTinhText.setText("Nam");
    	}else if(gioiTinh.equals("0")){
    		gioiTinhText.setText("Nữ");
    	}
    	diaChiText.setText(diaChi);
    	ngaySinhText.setText(ngaySinh); 	
    	hoTenText.setText(hoTen);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
