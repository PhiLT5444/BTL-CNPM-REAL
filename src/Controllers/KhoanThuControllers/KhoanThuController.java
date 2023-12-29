package Controllers.KhoanThuControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import Models.KhoanThu;
import Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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

public class KhoanThuController implements Initializable{

	@FXML
    private TableColumn<KhoanThu, String> chucNangCol;

    @FXML
    private TableView<KhoanThu> khoanThuTable;

    @FXML
    private TableColumn<KhoanThu, String> maKhoanThuCol;

    @FXML
    private TextField maKhoanThuText;

    @FXML
    private HBox quanLyButton;

    @FXML
    private TextField soTienNoptext;

    @FXML
    private TableColumn<KhoanThu, Double> soTienPhaiNopCol;

    @FXML
    private TableColumn<KhoanThu, String> tenKhoanThuCol;

    @FXML
    private TextField tenKhoanThuText;

    @FXML
    private Button themKhoanThuButton;

    @FXML
    private HBox thongKeButton;

    @FXML
    private HBox thuTienButton;
    
    @FXML
    private HBox phuongTienButton;
    
    @FXML
    private ImageView homeButton;
    
    ObservableList<KhoanThu> khoanThuList = FXCollections.observableArrayList();
    
    public void loadData() {
    	khoanThuList.clear();
    	String query = "select * from KhoanThu";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				KhoanThu khoanThu = new KhoanThu();
				khoanThu.setMaKhoanThu(resultSet.getString("maKhoanThu"));
				khoanThu.setTenKhoanThu(resultSet.getString("tenKhoanThu"));
				khoanThu.setSoTien(resultSet.getDouble("soTien"));
				khoanThuList.add(khoanThu);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	khoanThuTable.setItems(khoanThuList);
    	maKhoanThuCol.setCellValueFactory(new PropertyValueFactory<>("maKhoanThu"));
    	tenKhoanThuCol.setCellValueFactory(new PropertyValueFactory<>("tenKhoanThu"));
    	soTienPhaiNopCol.setCellValueFactory(new PropertyValueFactory<>("soTien"));
    	chucNangCol.setCellFactory(param -> new TableCell<>(){
    		private final Button editButton = new Button("Sửa");
    	    private final Button deleteButton = new Button("Xóa");

    	    {
    	        // Xử lý sự kiện khi nút "Sửa" được nhấn
    	        editButton.setOnAction(event -> {
    	            KhoanThu rowData = getTableView().getItems().get(getIndex());
    	            System.out.println("Sửa thông tin cho: " + rowData.getMaKhoanThu());
    	            FXMLLoader loader = new FXMLLoader();
    	            loader.setLocation(getClass().getResource("/Views/Fxml/SuaKhoanThu.fxml"));
    	            try {
						Parent root = loader.load();
						SuaKhoanThuController sktc = loader.getController();
						sktc.setMaKhoanThu(rowData.getMaKhoanThu());
						Scene scene = new Scene(root);
					    Stage stage = new Stage();
					    stage.setScene(scene);					    
					    stage.show();
					    Stage stage1 = (Stage)editButton.getScene().getWindow();
	    		        Model.getInstance().getViewFactory().closeStage(stage1);
					    
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	            
    	            
    	        });

    	        // Xử lý sự kiện khi nút "Xóa" được nhấn
    	        deleteButton.setOnAction(event -> {
    	        	KhoanThu rowData = getTableView().getItems().get(getIndex());
    	        	System.out.println("Xóa: " + rowData.getTenKhoanThu());
    	        	String delQuery = "DELETE FROM KhoanThu WHERE maKhoanThu = ?";
    	        	Connection connection = DBConnect.getConnection();

    	        	try {
    	        	    PreparedStatement preparedStatement = connection.prepareStatement(delQuery);
    	        	    preparedStatement.setString(1, rowData.getMaKhoanThu());
    	        	    preparedStatement.executeUpdate();
    	        	} catch (SQLException e) {
    	        	    e.printStackTrace();
    	        	}
    	            Stage stage = (Stage)deleteButton.getScene().getWindow();
    		        Model.getInstance().getViewFactory().closeStage(stage);
    		 	    Model.getInstance().getViewFactory().showKhoanThu();
    	        });
    	    }

    	    protected void updateItem(String item, boolean empty) {
    	        super.updateItem(item, empty);
    	        if (empty) {
    	            setGraphic(null);
    	        } else {
    	            HBox buttons = new HBox(editButton, deleteButton);
    	            buttons.setSpacing(5);
    	            buttons.setAlignment(Pos.CENTER);
    	            setGraphic(buttons);
    	        }
    	    }
    	});
    }
    
    public void ThemKhoanThu() {
    	String query = "Insert into KhoanThu (maKhoanThu, tenKhoanThu, soTien) values(?,?,?)";
    	Connection connection = DBConnect.getConnection();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, maKhoanThuText.getText());
			preparedStatement.setString(2, tenKhoanThuText.getText());
			preparedStatement.setDouble(3, Double.parseDouble(soTienNoptext.getText()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadData();
		
		homeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)themKhoanThuButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showHome();
		});
		
		themKhoanThuButton.setOnAction(e->{
			ThemKhoanThu();
			Stage stage = (Stage)themKhoanThuButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThu();
		});
		
		phuongTienButton.setOnMouseClicked(e->{
			Stage stage = (Stage)phuongTienButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showPhuongTien();
		});
		
		thuTienButton.setOnMouseClicked(e->{
			Stage stage = (Stage)thuTienButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showThuTien();
		});
		thongKeButton.setOnMouseClicked(e->{
			Stage stage = (Stage)thuTienButton.getScene().getWindow();
	        Model.getInstance().getViewFactory().closeStage(stage);
	 	    Model.getInstance().getViewFactory().showKhoanThuThongKe();
		});
	}
}
