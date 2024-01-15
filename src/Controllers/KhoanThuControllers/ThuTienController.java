package Controllers.KhoanThuControllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.DbDoc;

import DataBase.DBConnect;
import Models.Model;
import Models.NhanKhau;
import Models.PhuongTien;
import Models.QuanHe;
import Models.ThuTien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ThuTienController implements Initializable {

    @FXML
    private ImageView homeButton;

    @FXML
    private TextField khoanThutext;

    @FXML
    private MenuButton ktMenu;

    @FXML
    private TableColumn<ThuTien, String> maKhoanThuCol;

    @FXML
    private MenuButton nguoiNopMenu;

    @FXML
    private TextField nguoiNopText;

    @FXML
    private HBox phuongTienButton;

    @FXML
    private HBox quanLyButton;

    @FXML
    private TableColumn<ThuTien, Double> soTienThuCol;

    @FXML
    private TextField soTienThutext;

    @FXML
    private TableColumn<ThuTien, String> tenKhoanThuCol;

    @FXML
    private TableColumn<ThuTien, String> tenNguoiNopCol;

    @FXML
    private Button themButton;

    @FXML
    private HBox thongKeButton;

    @FXML
    private HBox thuTienButton;

    @FXML
    private TableView<ThuTien> thuTienTable;

    @FXML
    private TableColumn<ThuTien, String> tinhNangCol;
    
    @FXML
    private TableColumn<ThuTien, String> ngayThuCol;
    
    @FXML
    private DatePicker dateText;
    
    Integer IdNguoiNop;
    String maKhoanThu;
    
    public void loadData() {
    	ObservableList<ThuTien> thuTienList = FXCollections.observableArrayList();
    	String query =  "select * from ThuTien, NhanKhau, KhoanThu where ThuTien.maKhoanThu = KhoanThu.maKHoanThu and ThuTien.IdNguoiNop = NhanKhau.id";
    	Connection connection = DBConnect.getConnection();
    	// Ta cần tạo và điền dữ liệu vào HashMap này từ kết quả truy vấn cơ sở dữ liệu.
    	HashMap<String, String> mapMaTenKhoanThu = new HashMap<>();
    	HashMap<Integer, String> mapTenNguoiNop = new HashMap<>();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ThuTien thuTien = new ThuTien();
				thuTien.setIdNguoiNop(resultSet.getInt("Id"));
				thuTien.setMaKhoanThu(resultSet.getString("maKhoanThu"));
				thuTien.setSoTienThu(resultSet.getDouble("soTienThu"));	
				thuTien.setNgayThu(resultSet.getString("ngayThu"));
				thuTienList.add(thuTien);
				
				String maKhoanThu = resultSet.getString("maKhoanThu");
			    String tenKhoanThu = resultSet.getString("tenKhoanThu"); 
			    mapMaTenKhoanThu.put(maKhoanThu, tenKhoanThu);
			    
			    String tenNguoiNop = resultSet.getString("hoVaTen");
			    Integer idNguoiNop = resultSet.getInt("id");
			    mapTenNguoiNop.put(idNguoiNop, tenNguoiNop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	thuTienTable.setItems(thuTienList);
    	maKhoanThuCol.setCellValueFactory(new PropertyValueFactory<>("maKhoanThu"));
    	soTienThuCol.setCellValueFactory(new PropertyValueFactory<>("soTienThu"));
    	ngayThuCol.setCellValueFactory(new PropertyValueFactory<>("ngayThu"));
    	tenKhoanThuCol.setCellFactory(param -> new TableCell<>(){
    		protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        
		        // Kiểm tra hàng đang xét có dữ liệu không
		        if (empty || getTableRow() == null) {
		            setGraphic(null);
		            return;
		        }
		        ThuTien rowData = (ThuTien) getTableRow().getItem();
		        setText(mapMaTenKhoanThu.get(rowData.getMaKhoanThu()));
		    }
    	});

    	tenNguoiNopCol.setCellFactory(param -> new TableCell<>(){
    		protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        
		        // Kiểm tra hàng đang xét có dữ liệu không
		        if (empty || getTableRow() == null) {
		            setGraphic(null);
		            return;
		        }
		        ThuTien rowData = (ThuTien) getTableRow().getItem();
		        setText(mapTenNguoiNop.get(rowData.getIdNguoiNop()));
		    }
    	});
    	
    	tinhNangCol.setCellFactory(param -> new TableCell<>(){
    		 @Override
    		    protected void updateItem(String item, boolean empty) {
    		        super.updateItem(item, empty);
    		        
    		        // Kiểm tra hàng đang xét có dữ liệu không
    		        if (empty || getTableRow() == null) {
    		            setGraphic(null);
    		            return;
    		        }
    		        
    		        // Tạo nút Sửa
    		        Button editButton = new Button("Sửa");
    		        editButton.setOnAction(event -> {
    		            // Lấy thông tin từ hàng hiện tại
    		            ThuTien thuTien = getTableView().getItems().get(getIndex());
    		            // Thực hiện hành động sửa ở đây
    		            // Ví dụ: Hiển thị thông tin của thuTien để sửa
    		            System.out.println("Đã nhấn nút Sửa cho ID: " + thuTien.getIdNguoiNop());
    		        });

    		        // Tạo nút Xóa
    		        Button deleteButton = new Button("Xóa");
    		        deleteButton.setOnAction(event -> {
    		            // Lấy thông tin từ hàng hiện tại
    		            ThuTien thuTien = getTableView().getItems().get(getIndex());
    		            // Thực hiện hành động xóa ở đây
    		            String query = "delete from ThuTien where maKhoanThu = ? and IdNguoiNop = ?";
    		            Connection connection = DBConnect.getConnection();
    		            try {
    		            	ThuTien rowData = getTableView().getItems().get(getIndex());
							PreparedStatement preparedStatement = connection.prepareStatement(query);
							preparedStatement.setString(1, rowData.getMaKhoanThu());
							preparedStatement.setInt(2, rowData.getIdNguoiNop());
							preparedStatement.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    		            System.out.println("Đã nhấn nút Xóa cho ID: " + thuTien.getIdNguoiNop());
    		        });

    		        // Tạo một HBox để chứa cả hai nút và căn giữa chúng
    		        HBox buttonsBox = new HBox(5); // 5 là khoảng cách giữa các nút
    		        buttonsBox.setAlignment(Pos.CENTER);
    		        buttonsBox.getChildren().addAll(editButton, deleteButton);

    		        // Đặt nội dung của ô trong cột tinhNangCol là HBox chứa hai nút
    		        setGraphic(buttonsBox);
    		    }
    	});  	
    }
    
    public void setAction() {
    	String khoanThuQuery = "select * from KhoanThu";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(khoanThuQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				MenuItem menu = new MenuItem();
				String mkt = resultSet.getString("maKhoanThu");
				menu.setText("Mã khoản thu: " + resultSet.getString("maKHoanThu") + " |Tên khoản thu: " + resultSet.getString("tenKhoanThu"));
				menu.setOnAction(e->{
					khoanThutext.setText(menu.getText());
					maKhoanThu = mkt;
				});
				ktMenu.getItems().add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    	String nhanKhauQuery = "select * from NhanKhau";
    	try {
			PreparedStatement preparedStatement = connection.prepareCall(nhanKhauQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				MenuItem menu = new MenuItem();
				menu.setText("ID: " + resultSet.getInt("ID") + " | Họ và tên: " + resultSet.getString("hoVaTen") + " Số CMND/CCCD: " + resultSet.getString("soCMND"));
				int id = resultSet.getInt("ID");
				menu.setOnAction(e->{
					nguoiNopText.setText(menu.getText());
					IdNguoiNop = id;
				});
				nguoiNopMenu.getItems().add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void themThuTien() {
    	String query = "INSERT INTO ThuTien (idNguoiNop, maKhoanThu, soTienThu, ngayThu) VALUES (?, ?, ?, ?)";
    	Connection connection = DBConnect.getConnection();

    	try {
    	    PreparedStatement preparedStatement = connection.prepareStatement(query);
    	    preparedStatement.setInt(1, IdNguoiNop);
    	    preparedStatement.setString(2, maKhoanThu);
    	    preparedStatement.setDouble(3, Double.parseDouble(soTienThutext.getText()));

    	    LocalDate inputDate = dateText.getValue();
    	    if (inputDate != null) {
    	        java.sql.Date sqlDate = java.sql.Date.valueOf(inputDate);
    	        preparedStatement.setDate(4, sqlDate);
    	    } else {
    	        System.out.println("Ngày không hợp lệ.");
    	        // Xử lý lỗi hoặc thông báo cho người dùng về việc không chọn ngày
    	    }

    	    preparedStatement.executeUpdate();
    	} catch (SQLException | NumberFormatException e) {
    	    e.printStackTrace();
    	}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadData();
		setAction();
		quanLyButton.setOnMouseClicked(e->{
			Stage stage = (Stage)quanLyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThu();
		});
		
		thongKeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)quanLyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThuThongKe();
		});
		
		phuongTienButton.setOnMouseClicked(e->{
			Stage stage = (Stage)quanLyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showPhuongTien();
		});
		
		themButton.setOnAction(e->{
			themThuTien();
			loadData();
		});
	}

}
