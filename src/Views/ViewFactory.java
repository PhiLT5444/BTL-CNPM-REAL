package Views;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {
    public void showLogin() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/Fxml/Login.fxml"));
        createStage(loader);
    }
    public void showHome() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/Home.fxml"));
        createStage(loader);
    }
  
    public void showQuanLyNhanKhau() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/QuanLyNhanKhau.fxml"));
        createStage(loader);
    }
    public void showThemNhanKhau() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/ThemNhanKhau.fxml"));
        createStage(loader);
    }
    public void showQLHoKhau() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/QLHoKhau.fxml"));
        createStage(loader);
    }
    public void showThemHoKhau() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/ThemHoKhau.fxml"));
        createStage(loader);
    }
    
    public void showSuaThanhVien() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/SuaThanhVien.fxml"));
        createStage(loader);
    }
    
    public void showKhoanThu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/KhoanThu.fxml"));
        createStage(loader);
    }
    
    public void showSuaKhoanThu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/SuaKhoanThu.fxml"));
        createStage(loader);
    }
    
    public void showPhuongTien() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/PhuongTien.fxml"));
        createStage(loader);
    }
    
    public void showThongKe() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/ThongKeNhanKhau.fxml"));
        createStage(loader);
    }
    
    public void showThuTien() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/ThuTien.fxml"));
        createStage(loader);
    }
    
    public void showKhoanThuThongKe() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/KhoanThuThongKe.fxml"));
        createStage(loader);
    }
    
    public void showDangKy() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/DangKy.fxml"));
        createStage(loader);
    }
    
    public void showDangNhap() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/DangNhap.fxml"));
        createStage(loader);
    }
    
    public void showSuaHoKhau() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/SuaHoKhau.fxml"));
        createStage(loader);
    }
    
    public void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void createFullScreenStage(FXMLLoader loader){
        Scene scene = null;
        try {
            scene = new Scene(loader.load());	
        } catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public void closeStage(Stage stage) {
        stage.close();
    }
}