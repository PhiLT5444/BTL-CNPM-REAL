package Models;

public class KhoanThu {
	private String maKhoanThu;
	private String tenKhoanThu;
	private Double soTien;
	
	public KhoanThu() {
		
	}
	
	public KhoanThu(String mkt, String tkt, Double st) {
		this.maKhoanThu = mkt;
		this.tenKhoanThu = tkt;
		this.soTien = st;
	}
	
	public String getMaKhoanThu() {
		return maKhoanThu;
	}
	public void setMaKhoanThu(String maKhoanThu) {
		this.maKhoanThu = maKhoanThu;
	}
	public String getTenKhoanThu() {
		return tenKhoanThu;
	}
	public void setTenKhoanThu(String tenKhoanThu) {
		this.tenKhoanThu = tenKhoanThu;
	}
	public Double getSoTien() {
		return soTien;
	}
	public void setSoTien(Double soTien) {
		this.soTien = soTien;
	}
}
