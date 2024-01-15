package Controllers.HoKhauControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.Model;
import Models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SuaHoKhauController implements Initializable {

    @FXML
    private TextField chuHoText;

    @FXML
    private TextField diaChiText;

    @FXML
    private TableColumn<NhanKhau, String> hoVaTenCol;

    @FXML
    private Button luuButton;

    @FXML
    private TextField maHoKhauText;

    @FXML
    private TextField maKhuVucText;

    @FXML
    private TextField ngaySinhChuHoText;

    @FXML
    private TableColumn<NhanKhau, String> ngaySinhCol;

    @FXML
    private TextField soCMNDText;

    @FXML
    private TableView<NhanKhau> tableView;

    @FXML
    private Button themThanhVienButton;
    
    @FXML
    private TableColumn<NhanKhau, String> hanhDongCol;

    @FXML
    private TableColumn<NhanKhau, Text> quanHeCol;
    
    private String maHoKhau;
    public void setData(String string) {
    	this.maHoKhau = string;
    	this.maHoKhauText.setText(string);
    	String query = "select * from HoKhau, ChuHo, NhanKhau where ChuHo.maHoKhau = HoKhau.ma_ho_khau and ChuHo.IdChuHo = NhanKhau.id and ma_ho_khau = ?";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, string);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				maKhuVucText.setText(resultSet.getString("ma_Khu_Vuc"));
				diaChiText.setText(resultSet.getString("dia_Chi"));
				ngaySinhChuHoText.setText(resultSet.getString("ngaySinh"));
				soCMNDText.setText(resultSet.getString("soCMND"));
				chuHoText.setText(resultSet.getString("hoVaTen"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void loadThanhVienTable() {
        String query = "SELECT * FROM Nhankhau, Quanhe, HoKhau Where nhanKhau.id = quanHe.maNhanKhau and hoKhau.ma_ho_khau = QuanHe.maHoKhau and maHoKhau = ?";
        Connection connection = DBConnect.getConnection();
        List<NhanKhau> dataList = new ArrayList<>(); // Danh sách để lưu dữ liệu từ cơ sở dữ liệu

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maHoKhau);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                NhanKhau person = new NhanKhau();
                person.setHoVaTen(resultSet.getString("hoVaTen")); // Thay "hoVaTenColumn" bằng tên cột chứa họ và tên trong cơ sở dữ liệu
                person.setNgaySinh(resultSet.getString("ngaySinh")); // Thay "ngaySinhColumn" bằng tên cột chứa ngày sinh trong cơ sở dữ liệu
                person.setId(resultSet.getInt("ID"));
                
                dataList.add(person); // Thêm đối tượng Person vào danh sách
            }

            ObservableList<NhanKhau> observableList = FXCollections.observableArrayList(dataList);
            tableView.setItems(observableList);
            hoVaTenCol.setCellValueFactory(new PropertyValueFactory<>("hoVaTen"));
            ngaySinhCol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));

            // Thêm cột themButtonCol vào bảng và thiết lập nút "Thêm" cho từng hàng
            TableColumn<NhanKhau, String> addButtonCol = new TableColumn<>("Thêm");
            TableColumn<NhanKhau, Text> QuanHeText = new TableColumn<>("Quan hệ với chủ hộ");
            quanHeCol.setCellFactory(param -> new TableCell<>() {
                private final Text quanHeText = new Text();

                protected void updateItem(Text item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        NhanKhau rowData = getTableView().getItems().get(getIndex());
                        String quanHeValue = getQuanHeFromDatabase(rowData.getId());
                        quanHeText.setText(quanHeValue);
                        setGraphic(quanHeText);
                    }
                }

                // Example method to retrieve quanHe from the database based on the NhanKhau ID
                private String getQuanHeFromDatabase(int nhanKhauId) {
                    // Your database query logic here
                    // Replace this example query with the actual query to get quanHe from the database
                    String query = "SELECT quanHe FROM QuanHe WHERE maNhanKhau = ?";
                    try (Connection connection = DBConnect.getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setInt(1, nhanKhauId);
                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            if (resultSet.next()) {
                                return resultSet.getString("quanHe");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return ""; // Return empty string if there's an issue or no data found
                }
            });
            
            hanhDongCol.setCellFactory(param -> new TableCell<>() {
                private Button delButton = new Button("Xóa");

                {
                    delButton.setOnAction(event -> {
                        String query = "Delete From QuanHe where quanhe.maNhanKhau = ?";
                        Connection connection = DBConnect.getConnection();
                        try {
							PreparedStatement preparedStatement = connection.prepareStatement(query);
							NhanKhau rowData = getTableView().getItems().get(getIndex());
							preparedStatement.setInt(1, rowData.getId());
							preparedStatement.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
                        loadThanhVienTable();
                    });
                }

                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(delButton);
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showThemThanhVien() {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Views/Fxml/ThemThanhVien.fxml"));
    	try {
    	    Parent root = loader.load();
    	    ThemThanhVienController controller = loader.getController();
    	    controller.setData(maHoKhau);
    	    controller.loadData();
    	    Scene scene = new Scene(root);
    	    Stage stage = new Stage();
    	    stage.setScene(scene);
    	    stage.show();
    	    Stage stage1 = (Stage)themThanhVienButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage1);
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		themThanhVienButton.setOnAction(e->{
			showThemThanhVien();
		});
		luuButton.setOnAction(e->{
			Stage stage = (Stage)luuButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showQLHoKhau();
		});
	}

}
