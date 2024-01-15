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
import Models.QuanHe;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ThemHoKhauController implements Initializable{
	 	@FXML
	    private MenuButton chonMenu;

	    @FXML
	    private TextField chuHoText;

	    @FXML
	    private TextField diaChiText;

	    @FXML
	    private TableColumn<NhanKhau,String> hoVaTenCol;

	    @FXML
	    private Button huyButton;

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
	    private Button suaButton;

	    @FXML
	    private TableView<NhanKhau> tableView;
    
    
    
    
    Integer id = null;
    List<Integer> thanhVienId = new ArrayList<Integer>();
    List<String> quanHe = new ArrayList<String>();
    
    
    public void InsertData() { 
    	try {		    
		    String query1 = "Select * from NhanKhau";
        	Connection connection1 = DBConnect.getConnection();
			PreparedStatement preparedStatement1 = connection1.prepareStatement(query1);
			ResultSet resultSet = preparedStatement1.executeQuery();
			while(resultSet.next()) {
				String nhanKhau = "ID: " + resultSet.getString("Id") + "  Họ Tên: " + resultSet.getString("hoVaTen") + "  Ngày Sinh: " + resultSet.getString("ngaySinh");
				MenuItem menu = new MenuItem(nhanKhau);
				String ngaysinh = resultSet.getString("ngaySinh");
				String socmnd = resultSet.getString("soCMND");
				menu.setUserData(resultSet.getInt("Id"));
				menu.setOnAction(event->{
					String content = menu.getText();
					chuHoText.setText(content);
					ngaySinhChuHoText.setText(ngaysinh);
					soCMNDText.setText(socmnd);
					int idChuHo = (int) menu.getUserData();
					id = idChuHo;
				});
				chonMenu.getItems().add(menu);
			}
		    
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
    }
    
    public void ThemHoKhau() {
        if (maHoKhauText.getText().isEmpty() || maKhuVucText.getText().isEmpty() ||
            diaChiText.getText().isEmpty() || chuHoText.getText().isEmpty() ||
            ngaySinhChuHoText.getText().isEmpty() || soCMNDText.getText().isEmpty()) {

            // Display an alert for the user to fill in all the fields
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Hãy nhập đầy đủ thông tin!");
            alert.showAndWait();
        } else {
            // All fields are filled, proceed with inserting data
            String query = "Insert into hoKhau (ma_ho_khau, ma_khu_vuc, dia_chi) values(?,?,?)";
            String chuHoQuery = "Insert into chuHo (idChuHo, maHoKhau) values(?,?)";
            String quanHeQuery = "Insert into QuanHe(maHoKhau, maNhanKhau, quanHe) values(?,?,?)";
            Connection connection = DBConnect.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, maHoKhauText.getText());
                preparedStatement.setString(2, maKhuVucText.getText());
                preparedStatement.setString(3, diaChiText.getText());
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(chuHoQuery);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, maHoKhauText.getText());
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(quanHeQuery);
                for (int i = 0; i < thanhVienId.size(); i++) {
                    preparedStatement.setInt(2, thanhVienId.get(i));
                    preparedStatement.setString(3, quanHe.get(i));
                    preparedStatement.setString(1, maHoKhauText.getText());
                    preparedStatement.executeUpdate();
                }
                System.out.println("Data inserted successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQLException
            }
        }
    }

    
    
    //bảng thành viên để thêm vào nhân khẩu
    public void loadThanhVienTable() {
        String query = "SELECT * FROM Nhankhau";
        Connection connection = DBConnect.getConnection();
        List<NhanKhau> dataList = new ArrayList<>(); // Danh sách để lưu dữ liệu từ cơ sở dữ liệu

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
            TableColumn<NhanKhau, String> addButtonCol = new TableColumn<>("Hành động");
            TableColumn<NhanKhau, TextField> QuanHeText = new TableColumn<>("Quan hệ với chủ hộ");
            QuanHeText.setCellFactory(param -> new TableCell<>() {
            	private final TextField quanHeText = new TextField();
            	protected void updateItem(TextField item, boolean empty) {
            		super.updateItem(item, empty);
            		if(empty) {
            			setGraphic(null);
            		}else {
            			setGraphic(quanHeText);
            		}
            	}
            });
            
            addButtonCol.setCellFactory(param -> new TableCell<>() {
                private Button addButton = new Button("Thêm");
                {
                    addButton.setOnAction(event -> {
                        Object rowData = getTableView().getItems().get(getIndex());
                        thanhVienId.add(((NhanKhau) rowData).getId()); 
                        quanHe.add(QuanHeText.getText());
                    });
                }

                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(addButton);
                    }
                }
            });
            tableView.getColumns().add(QuanHeText); // Thêm cột addButtonCol vào bảng tableView
            tableView.getColumns().add(addButtonCol);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		loadThanhVienTable();	
		InsertData();
		
		luuButton.setOnAction(e->{
			ThemHoKhau();
		});
		
		huyButton.setOnAction(e->{
			Stage stage = (Stage)huyButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showQLHoKhau();
		});
		
		suaButton.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Views/Fxml/SuaThanhVien.fxml"));
			Parent root;
			try {
				root = loader.load();
				SuaThanhVienController stvc = loader.getController();
				stvc.setMaHoKhau(maHoKhauText.getText());
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}						
		});
		
	}
}
