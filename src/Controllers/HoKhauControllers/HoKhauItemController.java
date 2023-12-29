package Controllers.HoKhauControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class HoKhauItemController implements Initializable{

    @FXML
    private Text diaChiText;

    @FXML
    private Text hoVaTenChuHoText;

    @FXML
    private Text idText;

    @FXML
    private Text maHoKhauText;
    
    public void setData(String diaChi, String hoVaTenChuHo, String id, String maHoKhau) {
        diaChiText.setText(diaChi);
        hoVaTenChuHoText.setText(hoVaTenChuHo);
        idText.setText(id);
        maHoKhauText.setText(maHoKhau);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
