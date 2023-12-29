package Models;

public class PhuongTien {
	private String bienSoXe;
	private String nhanHieu;
	private String loaiXe;
	private Integer maNhanKhau;
	
	public PhuongTien() {	
	}
	
	public String getBienSoXe() {
		return bienSoXe;
	}
	public void setBienSoXe(String bienSoXe) {
		this.bienSoXe = bienSoXe;
	}
	public String getLoaiXe() {
		return loaiXe;
	}
	public void setLoaiXe(String loaiXe) {
		this.loaiXe = loaiXe;
	}
	public Integer getMaNhanKhau() {
		return maNhanKhau;
	}
	public void setMaNhanKhau(Integer maNhanKhau) {
		this.maNhanKhau = maNhanKhau;
	}

	public String getNhanHieu() {
		return nhanHieu;
	}

	public void setNhanHieu(String nhanHieu) {
		this.nhanHieu = nhanHieu;
	}
}
