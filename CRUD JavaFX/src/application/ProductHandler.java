package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductHandler {

	 public static List<Product> getAllProducts() {
		 List<Product> products = new ArrayList<>();
		 String query = "SELECT * FROM sepatu";
		 
		try{
			Connection connection = DatabaseConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(query);
			while(res.next()) {
				products.add(new Product(
						res.getString("kode_sepatu"),
						res.getString("model_sepatu"),
						res.getString("merk_sepatu"),
						res.getString("warna_sepatu"),
						res.getString("harga_sepatu")));
			}
			System.out.println("[+] Product List successfully obtained");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
		 
	 } 
	 
	 public static String generateCode(String merk) {
		 Character firstLetter = Character.toUpperCase(merk.charAt(0));
		 int count = 1;
		 
		 try{
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM sepatu WHERE merk_sepatu LIKE ?");
				ps.setString(1, firstLetter + "%");// prefix match to ensure no duped codes
				ResultSet res = ps.executeQuery();
				while(res.next()) {
					count+=1;
				}
				
				System.out.printf("[+] Code Generated: %s\n",firstLetter + String.format("%03d", count));
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (firstLetter.toString() + String.format("%03d", count));
	 }
	 
	 public static boolean insertProduct(Product product) {
		 String query = "INSERT INTO sepatu (kode_sepatu, model_sepatu,merk_sepatu,warna_sepatu, harga_sepatu) VALUES (?,?,?,?,?)";
		 
		 try{
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, product.getKode());
				ps.setString(2, product.getModel());
				ps.setString(3, product.getMerk());
				ps.setString(4, product.getWarna());
				ps.setString(5, product.getHarga());
				
				if(ps.executeUpdate()>0) {
					System.out.printf("[+] Successfully inserted product [%s, %s, %s, %s, %s]\n",product.getKode(),product.getModel(),product.getMerk(),product.getWarna(),product.getHarga());
					return true;
				}else {
					System.out.printf("[!] Failed to insert product [%s, %s, %s, %s, %s]\n",product.getKode(),product.getModel(),product.getMerk(),product.getWarna(),product.getHarga());
					return false;
				}
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return false;
		 
	 }
	 
	 public static boolean deleteProduct(String kode) {
		 String query = "DELETE FROM sepatu WHERE kode_sepatu = ?";
		 
		 try{
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, kode);
				if(ps.executeUpdate()>0) {
					System.out.printf("[+] Successfully deleted product with code [%s]\n", kode);
					return true;
				}else {
					System.out.printf("[!] Failed to delete product with code [%s]\n",kode);
					return false;
				}
				
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return false;
		 
	 }
	 
	 
}
