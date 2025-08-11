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
			System.out.println("[+] Product List successfully got");
			
			
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
				ps.setString(1, merk + "%");// prefix match to ensure no dupe ids
				ResultSet res = ps.executeQuery();
				while(res.next()) {
					count+=1;
				}
				
				System.out.printf("[+] Code Generated: %s",firstLetter + String.format("%003d", count));
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (firstLetter.toString() + String.format("%03d", count));
	 }
	 
	 
}
