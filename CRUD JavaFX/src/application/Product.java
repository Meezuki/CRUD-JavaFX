package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
	private StringProperty kode;
    private StringProperty model;
    private StringProperty merk;
    private StringProperty warna;
    private StringProperty harga;

    public Product(String kode, String model, String merk, String warna, String harga) {
        this.kode = new SimpleStringProperty(kode);
        this.model = new SimpleStringProperty(model);
        this.merk = new SimpleStringProperty(merk);
        this.warna = new SimpleStringProperty(warna);
        this.harga = new SimpleStringProperty(harga);
    }
 
    // gs kode
    public String getKode() {
        return kode.get();
    }

    public void setKode(String kode) {
        this.kode.set(kode);
    }

    public StringProperty kodeProperty() {
        return kode;
    }

   //gs model
    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public StringProperty modelProperty() {
        return model;
    }

    //gs merk
    public String getMerk() {
        return merk.get();
    }

    public void setMerk(String merk) {
        this.merk.set(merk);
    }

    public StringProperty merkProperty() {
        return merk;
    }

    //gs warna
    public String getWarna() {
        return warna.get();
    }

    public void setWarna(String warna) {
        this.warna.set(warna);
    }

    public StringProperty warnaProperty() {
        return warna;
    }

    //gs harga
    public String getHarga() {
        return harga.get();
    }

    public void setHarga(String harga) {
        this.harga.set(harga);
    }

    public StringProperty hargaProperty() {
        return harga;
    }
    
}
