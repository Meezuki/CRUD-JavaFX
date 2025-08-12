package application;

public class TransactionItem {
	private String kode;
    private String model;
    private String merk;
    private String warna;
    private Integer harga;
    private Integer kuantitas;
    private Integer uang_pembayaran;
    
    public TransactionItem(String kode, String model, String merk, String warna, Integer harga, Integer kuantitas, Integer uang_pembayaran) {
    	this.setKode(kode);
    	this.setModel(model);
    	this.setMerk(merk);
    	this.setWarna(warna);
    	this.setHarga(harga);
    	this.setKuantitas(kuantitas);
    	this.setUang_pembayaran(uang_pembayaran);
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
    
}
