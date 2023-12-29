package Models;

public class ThuTien {
	private String maKhoanThu;
	private Integer IdNguoiNop;
	private Double soTienThu;
	private String ngayThu;
	
	public ThuTien() {
		
	}
	
	public String getMaKhoanThu() {
		return maKhoanThu;
	}
	public void setMaKhoanThu(String maKhoanThu) {
		this.maKhoanThu = maKhoanThu;
	}
	public Integer getIdNguoiNop() {
		return IdNguoiNop;
	}
	public void setIdNguoiNop(Integer idNguoiNop) {
		IdNguoiNop = idNguoiNop;
	}
	public Double getSoTienThu() {
		return soTienThu;
	}
	public void setSoTienThu(Double soTienThu) {
		this.soTienThu = soTienThu;
	}

	public String getNgayThu() {
		return ngayThu;
	}

	public void setNgayThu(String ngayThu) {
		this.ngayThu = ngayThu;
	}
}
