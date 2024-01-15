package Controllers.HoKhauControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import Controllers.NhanKhauControllers.NhanKhauItemController;
import DataBase.DBConnect;
import Models.HoKhau;
import Models.Model;
import Models.ThuTien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QLHoKhauController implements Initializable {

	@FXML
    private TableColumn<HoKhau, String> diaChiCol;

    @FXML
    private HBox hoKhauButton;

    @FXML
    private TableView<HoKhau> hoKhauCol;

    @FXML
    private TableColumn<HoKhau, String> hoTenChuHoCol;

    @FXML
    private ImageView homeButton;

    @FXML
    private TableColumn<HoKhau, Integer> idChuHoCol;

    @FXML
    private TableColumn<HoKhau, String> maHoKhauCol;

    @FXML
    private HBox nhanKhauButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button themMoiButton;

    @FXML
    private HBox thongKeButton;

    @FXML
    private TableColumn<HoKhau, String> tinhNangCol;
    
    
    public void Search() {
        String searchText = searchTextField.getText().trim().toLowerCase();

        if (!searchText.isEmpty()) {
        	refresh();
            ObservableList<HoKhau> filteredList = FXCollections.observableArrayList();
            // Lặp qua danh sách hiện tại của TableView và tìm kiếm theo tên hoặc mã hộ khẩu
            for (HoKhau hoKhau : hoKhauCol.getItems()) {
                if (hoKhau.getMaHoKhau().toLowerCase().contains(searchText)) {
                    filteredList.add(hoKhau);
                }
            }
            // Hiển thị danh sách tìm được trong TableView
            hoKhauCol.setItems(filteredList);
        } else {
            // Nếu ô tìm kiếm trống, hiển thị lại toàn bộ dữ liệu
            refresh();
        }
    }
    
    public void refresh() {
    	ObservableList<HoKhau> hoKhauList = FXCollections.observableArrayList();
    	HashMap<String, String> mapNhanKhauTen = new HashMap<String, String>();
    	HashMap<String, Integer> mapNhanKhauId = new HashMap<String, Integer>();
    	String query = "Select * from HoKhau, NhanKhau, ChuHo where HoKhau.ma_ho_khau = ChuHo.maHoKHau and nhankhau.id = chuho.idchuho";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				HoKhau hoKhau = new HoKhau();
				hoKhau.setMaHoKhau(resultSet.getString("ma_ho_khau"));
				hoKhau.setDiaChi(resultSet.getString("dia_chi"));
				mapNhanKhauTen.put(resultSet.getString("maHoKhau"), resultSet.getString("hoVaTen"));
				mapNhanKhauId.put(resultSet.getString("maHoKhau"), resultSet.getInt("Id"));
				hoKhauList.add(hoKhau);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	hoKhauCol.setItems(hoKhauList);
    	idChuHoCol.setCellFactory(param -> new TableCell<>() {
    		protected void updateItem(Integer item, boolean empty) {
		        super.updateItem(item, empty);
		        
		        // Kiểm tra hàng đang xét có dữ liệu không
		        if (empty || getTableRow() == null) {
		            setGraphic(null);
		            return;
		        }
		        HoKhau rowData = (HoKhau) getTableRow().getItem();
		        setText(mapNhanKhauId.get(rowData.getMaHoKhau()).toString());
		    }
    	});
    	maHoKhauCol.setCellValueFactory(new PropertyValueFactory<>("maHoKhau"));
    	hoTenChuHoCol.setCellFactory(param -> new TableCell<>(){
    		protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        
		        // Kiểm tra hàng đang xét có dữ liệu không
		        if (empty || getTableRow() == null) {
		            setGraphic(null);
		            return;
		        }
		        HoKhau rowData = (HoKhau) getTableRow().getItem();
		        setText(mapNhanKhauTen.get(rowData.getMaHoKhau()));
		    }
    	});
    	diaChiCol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    	tinhNangCol.setCellFactory(param -> new TableCell<>(){
    		private final Button editButton = new Button("Sửa/Xem");
    	    private final Button deleteButton = new Button("Xóa");
    	    
    	    {
    	        // Edit Button Action
    	        editButton.setOnAction(event -> {
    	        	HoKhau rowData = getTableView().getItems().get(getIndex());
    	        	FXMLLoader loader = new FXMLLoader();
    	        	loader.setLocation(getClass().getResource("/Views/Fxml/SuaHoKhau.fxml"));
    	        	try {
    	        	    Parent root = loader.load();
    	        	    SuaHoKhauController controller = loader.getController();
    	        	    controller.setData(rowData.getMaHoKhau());
    	        	    controller.loadThanhVienTable();
    	        	    Scene scene = new Scene(root);
    	        	    Stage stage = new Stage();
    	        	    stage.setScene(scene);
    	        	    stage.show();
    	        	    Stage stage1 = (Stage)themMoiButton.getScene().getWindow();
    	    	        Model.getInstance().getViewFactory().closeStage(stage1);
    	        	} catch (IOException e) {
    	        	    e.printStackTrace();
    	        	}
    	        });

    	        // Delete Button Action
    	        deleteButton.setOnAction(event -> {
    	        	HoKhau rowData = getTableView().getItems().get(getIndex());
    	        	String maHoKhau = rowData.getMaHoKhau();

    	        	try (Connection connection = DBConnect.getConnection()) {
    	        	    // Xóa dữ liệu từ bảng ChuHo trước
    	        	    String delChuHoQuery = "DELETE FROM ChuHo WHERE maHoKhau = ?";
    	        	    try (PreparedStatement preparedStatementChuHo = connection.prepareStatement(delChuHoQuery)) {
    	        	        preparedStatementChuHo.setString(1, maHoKhau);
    	        	        preparedStatementChuHo.execute();
    	        	    } catch (SQLException e) {
    	        	        e.printStackTrace();
    	        	        // Xử lý lỗi khi xóa từ bảng ChuHo
    	        	    }
    	        	    
    	        	    String delQuanHe = "DELETE FROM QuanHe WHERE maHoKhau = ?";
    	        	    try (PreparedStatement preparedStatementHoKhau = connection.prepareStatement(delQuanHe)) {
    	        	        preparedStatementHoKhau.setString(1, maHoKhau);
    	        	        preparedStatementHoKhau.execute();
    	        	    } catch (SQLException e) {
    	        	        e.printStackTrace();
    	        	        // Xử lý lỗi khi xóa từ bảng HoKhau
    	        	    }

    	        	    // Sau đó, xóa dữ liệu từ bảng HoKhau
    	        	    String delHoKhauQuery = "DELETE FROM HoKhau WHERE ma_ho_khau = ?";
    	        	    try (PreparedStatement preparedStatementHoKhau = connection.prepareStatement(delHoKhauQuery)) {
    	        	        preparedStatementHoKhau.setString(1, maHoKhau);
    	        	        preparedStatementHoKhau.execute();
    	        	    } catch (SQLException e) {
    	        	        e.printStackTrace();
    	        	        // Xử lý lỗi khi xóa từ bảng HoKhau
    	        	    }
    	        	} catch (SQLException e) {
    	        	    e.printStackTrace();
    	        	    // Xử lý lỗi khi thiết lập kết nối đến cơ sở dữ liệu
    	        	}	
    	        	Stage stage = (Stage)homeButton.getScene().getWindow();
    		        Model.getInstance().getViewFactory().closeStage(stage);
    		 	    Model.getInstance().getViewFactory().showQLHoKhau();
    	        });
    	    }

    	    @Override
    	    protected void updateItem(String item, boolean empty) {
    	        super.updateItem(item, empty);

    	        if (empty) {
    	            setGraphic(null);
    	        } else {
    	            HBox buttons = new HBox(deleteButton, editButton);
    	            buttons.setAlignment(Pos.CENTER);
    	            buttons.setSpacing(5);
    	            setGraphic(buttons);
    	        }
    	    }
    	});
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		refresh();
		
		homeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)homeButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showHome();
		});
		
		themMoiButton.setOnAction(e->{
			Stage stage = (Stage)themMoiButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showThemHoKhau();
		});
		nhanKhauButton.setOnMouseClicked(e->{
			Stage stage = (Stage)themMoiButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showQuanLyNhanKhau();
		});
		thongKeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)themMoiButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showThongKe();
		});
		searchTextField.setOnAction(e->{
			Search();
		});
	}

}
