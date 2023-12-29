package Models;

public class HoKhau {
	private String maHoKhau;
	private String maKhuVuc;
	private String diaChi;
	
	
	public HoKhau(String maHoKhau, String maKhuVuc, String diaChi) {
        this.maHoKhau = maHoKhau;
        this.maKhuVuc = maKhuVuc;
        this.diaChi = diaChi;
    }

	public HoKhau() {
		// TODO Auto-generated constructor stub
	}

	// Getter và setter cho maHoKhau
    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    // Getter và setter cho maKhuVuc
    public String getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(String maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    // Getter và setter cho diaChi
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
