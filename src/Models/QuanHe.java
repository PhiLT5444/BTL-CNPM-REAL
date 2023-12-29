package Models;

public class QuanHe {
	private Integer maNhanKhau;
	private String maHoKhau;
	private String quanHeVoiChuHo;
	
	public QuanHe() {
		
	}
	public QuanHe(String maHoKhau, String quanHe, String hoTen, Integer maNhanKhau) {
		this.maHoKhau = maHoKhau;
		this.quanHeVoiChuHo = quanHe;
		this.maNhanKhau = maNhanKhau;
	}
	public String getMaHoKhau() {
		return maHoKhau;
	}
	public void setMaHoKhau(String maHoKhau) {
		this.maHoKhau = maHoKhau;
	}
	public String getQuanHeVoiChuHo() {
		return quanHeVoiChuHo;
	}
	public void setQuanHeVoiChuHo(String quanHeVoiChuHo) {
		this.quanHeVoiChuHo = quanHeVoiChuHo;
	}
	public Integer getMaNhanKhau() {
		return maNhanKhau;
	}
	public void setMaNhanKhau(Integer maNhanKhau) {
		this.maNhanKhau = maNhanKhau;
	}
	
}
