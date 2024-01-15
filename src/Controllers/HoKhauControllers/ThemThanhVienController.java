package Controllers.HoKhauControllers;

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
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ThemThanhVienController implements Initializable {

    @FXML
    private TableColumn<NhanKhau, String> hoVaTenCol;

    @FXML
    private TableColumn<NhanKhau, String> ngaySinhCol;

    @FXML
    private TableView<NhanKhau> tableView;

    @FXML
    private Button themButton;

    private String maHoKhhau;
    Integer id = null;
    List<Integer> thanhVienId = new ArrayList<>();
    List<String> quanHe = new ArrayList<>();

    public void setData(String maHoKhau) {
        this.maHoKhhau = maHoKhau;
    }

    public void loadData() {
        ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
        String query = "SELECT * FROM NhanKhau";
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NhanKhau member = new NhanKhau();
                member.setId(resultSet.getInt("id"));  // Thêm lấy ID
                member.setHoVaTen(resultSet.getString("hovaten"));
                member.setNgaySinh(resultSet.getString("ngaysinh"));
                nhanKhauList.add(member);
            }
            tableView.setItems(nhanKhauList);
            hoVaTenCol.setCellValueFactory(new PropertyValueFactory<>("hoVaTen"));
            ngaySinhCol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
            TableColumn<NhanKhau, String> addButtonCol = new TableColumn<>("Hành động");
            TableColumn<NhanKhau, String> quanHeTextCol = new TableColumn<>("Quan hệ với chủ hộ");

            // Trong TableCell cho quanHeTextCol
            quanHeTextCol.setCellFactory(param -> new TableCell<>() {
                private final TextField quanHeText = new TextField();

                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(quanHeText);
                    }
                }
            });

            // Trong TableCell cho addButtonCol
            addButtonCol.setCellFactory(param -> new TableCell<>() {
                private final Button addButton = new Button("Thêm");

                {
                    addButton.setOnAction(event -> {
                        NhanKhau rowData = getTableView().getItems().get(getIndex());
                        thanhVienId.add(rowData.getId());
                        quanHe.add(quanHeTextCol.getText());
                        showAlert("Thêm thành công", "Thông tin đã được thêm vào danh sách.", Alert.AlertType.INFORMATION);
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

            tableView.getColumns().add(quanHeTextCol);
            tableView.getColumns().add(addButtonCol);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void ThemHoKhau() {
        // All fields are filled, proceed with inserting data
        String quanHeQuery = "INSERT INTO QuanHe(maHoKhau, maNhanKhau, quanHe) VALUES (?, ?, ?)";
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(quanHeQuery);
            for (int i = 0; i < thanhVienId.size(); i++) {
                preparedStatement.setString(1, maHoKhhau);
                preparedStatement.setInt(2, thanhVienId.get(i));
                preparedStatement.setString(3, quanHe.get(i));
                preparedStatement.executeUpdate();
            }
            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadData();
        themButton.setOnAction(e -> {
            ThemHoKhau();
            Stage stage = (Stage) themButton.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().showQLHoKhau();
        });
    }
}
