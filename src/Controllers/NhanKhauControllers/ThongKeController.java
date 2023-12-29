package Controllers.NhanKhauControllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.Model;
import Models.NhanKhau;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ThongKeController implements Initializable {

    @FXML
    private TableColumn<NhanKhau, String> diaChiHienNayCol;

    @FXML
    private TextField fromNamText;

    @FXML
    private TextField fromTuoiText;

    @FXML
    private TableColumn<NhanKhau, String> gioiTinhCol;

    @FXML
    private MenuButton gtMenu;

    @FXML
    private Button hienThiButton;

    @FXML
    private HBox hoKhauButton;

    @FXML
    private TableColumn<NhanKhau, String> hoVaTenCol;

    @FXML
    private ImageView homeButton;

    @FXML
    private TableColumn<NhanKhau, Integer> idCol;

    @FXML
    private MenuItem namMenu;

    @FXML
    private TableColumn<NhanKhau, String> ngaySinhCol;
    
    @FXML
    private TableView<NhanKhau> nhanKhauTable;

    @FXML
    private HBox nhanKhauButton;

    @FXML
    private MenuItem nuMenu;

    @FXML
    private HBox thongKeButton;

    @FXML
    private TextField toNamText;

    @FXML
    private TextField toTuoiText;
    
    public void setAction() {
    	namMenu.setOnAction(e->{
    		gtMenu.setText(namMenu.getText());
    	});
    	nuMenu.setOnAction(e->{
    		gtMenu.setText(nuMenu.getText());
    	});
    }
    
    public void loadData() {
    	ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    	String query = "SELECT *\r\n"
    			+ "FROM NhanKhau\r\n"
    			+ "WHERE YEAR(CURDATE()) - YEAR(ngaySinh) BETWEEN ? AND ?\r\n"
    			+ "AND YEAR(ngaySinh) BETWEEN ? AND ?\r\n"
    			+ "AND gioiTinh = ?";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(fromTuoiText.getText()));
			preparedStatement.setInt(2, Integer.parseInt(toTuoiText.getText()));
			preparedStatement.setInt(3, Integer.parseInt(fromNamText.getText()));
			preparedStatement.setInt(4, Integer.parseInt(toNamText.getText()));
			if(gtMenu.getText().equals("Nam")) {
				preparedStatement.setBoolean(5, true);
			}else if(gtMenu.getText().equals("Nữ")) {
				preparedStatement.setBoolean(5, false);
			}
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				NhanKhau nhanKhau = new NhanKhau();
				nhanKhau.setId(resultSet.getInt("ID"));
				nhanKhau.setHoVaTen(resultSet.getString("hoVaTen"));
				nhanKhau.setNgaySinh(resultSet.getString("ngaySinh"));
				nhanKhau.setGioiTinh(resultSet.getBoolean("gioiTinh"));
				nhanKhau.setDiaChiHienTai(resultSet.getString("diaChiHienTai"));
				nhanKhauList.add(nhanKhau);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	nhanKhauTable.setItems(nhanKhauList);
    	idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
    	hoVaTenCol.setCellValueFactory(new PropertyValueFactory<>("hoVaTen"));
    	ngaySinhCol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
    	gioiTinhCol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
    	gioiTinhCol.setCellValueFactory(cellData -> {
    	    SimpleStringProperty property = new SimpleStringProperty();
    	    boolean gioiTinh = cellData.getValue().getGioiTinh(); // Lấy giá trị boolean từ đối tượng PhuongTien (giả sử PhuongTien có phương thức getGioiTinh() trả về boolean)
    	    if (gioiTinh) {
    	        property.setValue("Nam");
    	    } else {
    	        property.setValue("Nữ");
    	    }
    	    return property;
    	});
    	diaChiHienNayCol.setCellValueFactory(new PropertyValueFactory<>("diaChiHienTai"));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setAction();
		
		hienThiButton.setOnAction(e->{
			loadData();
		});
		
		homeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)homeButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showHome();
		});
		
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
	}

}
