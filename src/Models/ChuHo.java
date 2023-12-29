package Models;

public class ChuHo {
	private Integer idChuHo;
	private String maHoKhau;
	
	public ChuHo(Integer idChuHo, String maHoKhau) {
        this.idChuHo = idChuHo;
        this.maHoKhau = maHoKhau;
    }

    // Getter và setter cho idChuHo
    public Integer getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(Integer idChuHo) {
        this.idChuHo = idChuHo;
    }

    // Getter và setter cho maHoKhau
    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }
}
