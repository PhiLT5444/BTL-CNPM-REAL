package Controllers.KhoanThuControllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.KhoanThu;
import Models.Model;
import Models.PhuongTien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PhuongTienController implements Initializable {

    @FXML
    private TableColumn<PhuongTien, String> bienSoXeCol;

    @FXML
    private TextField bienSoXeText;

    @FXML
    private TableColumn<PhuongTien, String> chuSoHuuCol;

    @FXML
    private TextField chuSoHuuText;

    @FXML
    private MenuButton chuXeMenu;

    @FXML
    private TableColumn<PhuongTien, String> chucNangCol;

    @FXML
    private TableColumn<PhuongTien, String> loaiXeCol;

    @FXML
    private MenuButton loaiXeMenu;

    @FXML
    private TextField loaiXeText;

    @FXML
    private TableColumn<PhuongTien, String> nhanHieuCol;

    @FXML
    private TextField nhanHieuText;

    @FXML
    private HBox phuongTienButton;

    @FXML
    private TableView<PhuongTien> phuongTienTable;

    @FXML
    private HBox quanLyButton;

    @FXML
    private Button themButton;

    @FXML
    private HBox thongKeButton;

    @FXML
    private HBox thuTienButton;
    
    @FXML
    private MenuItem otoButton;

    @FXML
    private MenuItem xeMayButton;
    
    @FXML
    private ImageView homeButton;
    
    Integer idChuXe;
    
    public void themPhuongTien() {
    	String query = "Insert Into PhuongTien (bienSoXe, nhanHieu, IdChuSoHuu, loaiXe) values(?,?,?,?)";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bienSoXeText.getText());
			preparedStatement.setString(2, nhanHieuText.getText());
			preparedStatement.setInt(3, idChuXe);
			preparedStatement.setString(4, loaiXeText.getText());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void setAction() {
    	otoButton.setOnAction(e->{
    		loaiXeText.clear();
    		loaiXeText.setText(otoButton.getText());
    	});
    	xeMayButton.setOnAction(e->{
    		loaiXeText.clear();
    		loaiXeText.setText(xeMayButton.getText());
    	});
    	String query = "Select * from nhanKHau";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				MenuItem menu = new MenuItem();
				menu.setText("ID: " + resultSet.getString("Id") + " Họ và tên: " + resultSet.getString("hoVaTen"));
				int id = resultSet.getInt("ID");
				menu.setOnAction(e->{
					chuSoHuuText.setText(menu.getText());
					idChuXe = id;
				});
				chuXeMenu.getItems().add(menu);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   	
    }

    public void loadData() {
        ObservableList<PhuongTien> phuongTienList = FXCollections.observableArrayList();
        String query = "SELECT p.*, n.hoVaTen AS tenChuSoHuu " +
                       "FROM PhuongTien p " +
                       "INNER JOIN Nhankhau n ON p.IdChuSoHuu = n.Id";
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PhuongTien phuongTien = new PhuongTien();
                phuongTien.setBienSoXe(resultSet.getString("bienSoXe"));
                phuongTien.setMaNhanKhau(resultSet.getInt("IdChuSoHuu"));
                phuongTien.setNhanHieu(resultSet.getString("nhanHieu"));
                phuongTien.setLoaiXe(resultSet.getString("loaiXe"));
                phuongTienList.add(phuongTien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        phuongTienTable.setItems(phuongTienList);

        bienSoXeCol.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        nhanHieuCol.setCellValueFactory(new PropertyValueFactory<>("nhanHieu"));
        loaiXeCol.setCellValueFactory(new PropertyValueFactory<>("loaiXe"));
        chuSoHuuCol.setCellFactory(param -> new TableCell<>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    PhuongTien rowData = (PhuongTien) getTableRow().getItem();
                    int maNhanKhau = rowData.getMaNhanKhau();
                    // Truy vấn cơ sở dữ liệu để lấy tên chủ sở hữu từ maNhanKhau
                    String query = "SELECT hoVaTen FROM Nhankhau WHERE Id = ?";
                    Connection connection = DBConnect.getConnection();
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, maNhanKhau);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            setText(resultSet.getString("hoVaTen"));
                        } else {
                            setText("Không tìm thấy");
                        }
                        preparedStatement.close();
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        chucNangCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Xóa");

            {
            	deleteButton.setOnAction(event -> {
            	    PhuongTien phuongTien = getTableView().getItems().get(getIndex());
            	    System.out.println("Thông tin PhuongTien khi nhấp vào nút Xóa: " + phuongTien.getBienSoXe());
            	    
            	    String query = "DELETE FROM PhuongTien WHERE bienSoXe = ?";
            	    Connection connection = DBConnect.getConnection();
            	    
            	    try {
            	        PreparedStatement preparedStatement = connection.prepareStatement(query);
            	        preparedStatement.setString(1, phuongTien.getBienSoXe());
            	        preparedStatement.executeUpdate();
            	        System.out.println("Đã xóa dữ liệu của: " + phuongTien.getBienSoXe());
            	        
            	        loadData(); // Đảm bảo bạn cập nhật lại dữ liệu sau khi xóa
            	    } catch (SQLException e) {
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
                	deleteButton.setStyle("-fx-font-size: 12px; -fx-min-width: 60px; -fx-min-height: 25px;");
                    HBox buttons = new HBox(deleteButton);
                    buttons.setAlignment(Pos.CENTER);
                    setGraphic(buttons);
                }
            }
        });
    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setAction();
		loadData();
		
		homeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)phuongTienButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showHome();
		});
		
		quanLyButton.setOnMouseClicked(e->{
			Stage stage = (Stage)phuongTienButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThu();
		});
		
		themButton.setOnAction(e->{
			themPhuongTien();
			Stage stage = (Stage)phuongTienButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showPhuongTien();		
		});
		
		thongKeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)quanLyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThuThongKe();
		});
		
		thuTienButton.setOnMouseClicked(e->{
			Stage stage = (Stage)quanLyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showThuTien();
		});
		
	}

}
