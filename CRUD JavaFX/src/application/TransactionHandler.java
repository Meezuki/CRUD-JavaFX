package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionHandler {
	
	public static List<TransactionItem> getAllProducts() {
		 List<TransactionItem> transactionList = new ArrayList<>();
		 String query = "SELECT * FROM struk";
		 
		try{
			Connection connection = DatabaseConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(query);
			while(res.next()) {
				transactionList.add(new TransactionItem(
						res.getInt("struk_id"),
						res.getString("kode_sepatu"),
						res.getString("model_sepatu"),
						res.getString("merk_sepatu"),
						res.getString("warna_sepatu"),
						res.getInt("harga_sepatu"),
						res.getInt("kuantitas_sepatu"),
						res.getInt("uang_pembayaran")));
			}
			System.out.println("[+] Transaction List successfully obtained");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionList;
		 
	 } 
	
	public static boolean insertTransaction(Product product, int kuantitas, int uang_pembayaran ) {
		 String query = "INSERT INTO struk (kode_sepatu, model_sepatu,merk_sepatu,warna_sepatu, harga_sepatu,kuantitas_sepatu, uang_pembayaran ) VALUES (?,?,?,?,?,?,?)";
		 
		 try{
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, product.getKode());
				ps.setString(2, product.getModel());
				ps.setString(3, product.getMerk());
				ps.setString(4, product.getWarna());
				ps.setInt(5, Integer.parseInt(product.getHarga()));
				ps.setInt(6, kuantitas);
				ps.setInt(7, uang_pembayaran);
				
				if(ps.executeUpdate()>0) {
					System.out.printf("[+] Successfully inserted transaction [%s, %s, %s, %s, %s, %d, %d]\n",product.getKode(),product.getModel(),product.getMerk(),product.getWarna(),product.getHarga(), kuantitas, uang_pembayaran);
					return true;
				}else {
					System.out.printf("[!] Failed to insert transaction [%s, %s, %s, %s, %s, %d %d]\n",product.getKode(),product.getModel(),product.getMerk(),product.getWarna(),product.getHarga(), kuantitas, uang_pembayaran);
					return false;
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return false;
		 
	 }
	
	// added struk_id to object , no need this no more
//	public static int getTransactionId(String kode) {
//		 String query = "SELECT * FROM struk WHERE kode_sepatu = ?";
//		 
//		 try{
//				Connection connection = DatabaseConnection.getConnection();
//				PreparedStatement ps = connection.prepareStatement(query);
//				ps.setString(1, kode);
//				ResultSet res = ps.executeQuery();
//				 if (res.next()) {
//			            int id = res.getInt("struk_id");
//			            System.out.printf("[+] Transaction ID found: %d%n", id);
//			            return id;
//			        } else {
//			            System.out.println("[!] No transaction found for kode: " + kode);
//			            return 0;
//			        }
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		 return 0;
//		 
//		 
//	 }
	
}
