package application;

public class TransactionItem {
	private int struk_id;
	private String kode;
    private String model;
    private String merk;
    private String warna;
    private Integer harga;
    private Integer kuantitas;
    private Integer uang_pembayaran;
    
    public TransactionItem(int struk_id, String kode, String model, String merk, String warna, int harga, int kuantitas, int uang_pembayaran) {
    	this.setStruk_id(struk_id);
    	this.kode = kode;
    	this.model = model;
    	this.merk = merk;
    	this.warna = warna;
    	this.harga = harga;
    	this.kuantitas = kuantitas;
    	this.uang_pembayaran = uang_pembayaran;
    }

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMerk() {
		return merk;
	}

	public void setMerk(String merk) {
		this.merk = merk;
	}

	public String getWarna() {
		return warna;
	}

	public void setWarna(String warna) {
		this.warna = warna;
	}

	public Integer getHarga() {
		return harga;
	}

	public void setHarga(Integer harga) {
		this.harga = harga;
	}

	public Integer getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(Integer kuantitas) {
		this.kuantitas = kuantitas;
	}

	public Integer getUang_pembayaran() {
		return uang_pembayaran;
	}

	public void setUang_pembayaran(Integer uang_pembayaran) {
		this.uang_pembayaran = uang_pembayaran;
	}

	public int getStruk_id() {
		return struk_id;
	}

	public void setStruk_id(int struk_id) {
		this.struk_id = struk_id;
	}
    
}
