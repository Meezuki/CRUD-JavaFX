package application;

import java.sql.Connection;
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
}
