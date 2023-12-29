package Models;

public class NhanKhau {
	private Integer Id;
    private String hoVaTen;
    private String ngaySinh;
    private String nguyenQuan;
    private String danToc;
    private String soCMND;
    private String noiThuongTru;
    private String trinhDoHocVan;
    private String ngheNghiep;
    private Boolean gioiTinh;
    private String tonGiao;
    private String quocTich;
    private String diaChiHienTai;
    private String noiLamViec;

    // Constructor
    public NhanKhau(String hoVaTen, String ngaySinh, String nguyenQuan, String danToc, String soCMND,
                    String noiThuongTru, String trinhDoHocVan, String ngheNghiep, Boolean gioiTinh,
                    String tonGiao, String quocTich, String diaChiHienTai, String noiLamViec) {
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.soCMND = soCMND;
        this.noiThuongTru = noiThuongTru;
        this.trinhDoHocVan = trinhDoHocVan;
        this.ngheNghiep = ngheNghiep;
        this.gioiTinh = gioiTinh;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.diaChiHienTai = diaChiHienTai;
        this.noiLamViec = noiLamViec;
    }

    public NhanKhau() {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getNoiThuongTru() {
        return noiThuongTru;
    }

    public void setNoiThuongTru(String noiThuongTru) {
        this.noiThuongTru = noiThuongTru;
    }

    public String getTrinhDoHocVan() {
        return trinhDoHocVan;
    }

    public void setTrinhDoHocVan(String trinhDoHocVan) {
        this.trinhDoHocVan = trinhDoHocVan;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getDiaChiHienTai() {
        return diaChiHienTai;
    }

    public void setDiaChiHienTai(String diaChiHienTai) {
        this.diaChiHienTai = diaChiHienTai;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
}
