package Controllers.NhanKhauControllers;

import java.awt.Menu;
import java.awt.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.Model;
import Models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuanLyNhanKhauController implements Initializable{


    @FXML
    private TableColumn<NhanKhau, String> diaChiHienTaiCol;

    @FXML
    private TableColumn<NhanKhau, Boolean> gioiTinhCol;

    @FXML
    private HBox hoKhauButton;

    @FXML
    private TableColumn<NhanKhau, String> hoVaTenCol;

    @FXML
    private ImageView homeButton;

    @FXML
    private TableColumn<NhanKhau, Integer> idCol;

    @FXML
    private MenuButton mucTimKiemMenu;

    @FXML
    private TableColumn<NhanKhau, String> ngaySinhCol;

    @FXML
    private HBox nhanKhauButton;

    @FXML
    private TableView<NhanKhau> nhanKhauTable;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button themMoiButton;

    @FXML
    private HBox thongKeButton;

    @FXML
    private TableColumn<NhanKhau, String> tinhNangCol;
    
    public void search() {
        String searchText = searchTextField.getText().trim().toLowerCase();
        ObservableList<NhanKhau> filteredList = FXCollections.observableArrayList();

        if (!searchText.isEmpty()) {
        	refresh();
            for (NhanKhau nhanKhau : nhanKhauTable.getItems()) {
                if (nhanKhau.getHoVaTen().toLowerCase().contains(searchText) ||
                    String.valueOf(nhanKhau.getId()).contains(searchText)) {
                    filteredList.add(nhanKhau);
                }
            }
        } else {
            // Nếu ô tìm kiếm trống, hiển thị lại toàn bộ dữ liệu
            refresh();
            return;
        }

        // Hiển thị danh sách tìm được trong TableView
        nhanKhauTable.setItems(filteredList);
    }
    
    
    public void refresh() {
    	ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    	String query = "Select * from NhanKhau";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
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
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	hoVaTenCol.setCellValueFactory(new PropertyValueFactory<>("hoVaTen"));
    	ngaySinhCol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
    	gioiTinhCol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
    	gioiTinhCol.setCellFactory(column -> new TableCell<NhanKhau, Boolean>() {
    	    @Override
    	    protected void updateItem(Boolean item, boolean empty) {
    	        super.updateItem(item, empty);
    	        
    	        if (empty || item == null) {
    	            setText(null);
    	        } else {
    	            setText(item ? "Nam" : "Nữ");
    	        }
    	    }
    	});
    	diaChiHienTaiCol.setCellValueFactory(new PropertyValueFactory<>("diaChiHienTai"));
    	tinhNangCol.setCellFactory(column -> new TableCell<NhanKhau, String>() {
    	    private final Button deleteButton = new Button("Xóa");

    	    {
    	        deleteButton.setOnAction(event -> {
    	            NhanKhau selectedNhanKhau = getTableView().getItems().get(getIndex());
    	            System.out.println("Đã xóa dữ liệu của: " + selectedNhanKhau.getId());
    	            String delQuery = "delete from nhankhau where id = " + selectedNhanKhau.getId();
    	            Connection connection = DBConnect.getConnection();
    	            try {
						PreparedStatement preparedStatement = connection.prepareStatement(delQuery);
						preparedStatement.execute();
						refresh();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	            
    	        });
    	    }

    	    @Override
    	    protected void updateItem(String item, boolean empty) {
    	        super.updateItem(item, empty);
    	        if (empty) {
    	            setGraphic(null);
    	        } else {
    	            setGraphic(deleteButton);
    	        }
    	    }
    	});


    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		refresh();
		
		homeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)themMoiButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showHome();
		});
		
		themMoiButton.setOnAction(e->{
			Stage stage = (Stage)themMoiButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showThemNhanKhau();
		});
		
		hoKhauButton.setOnMouseClicked(e->{
			Stage stage = (Stage)hoKhauButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showQLHoKhau();
		});
		
		thongKeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)themMoiButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showThongKe();
		});
		searchTextField.setOnAction(e->{
			search();
		});
	}

}
