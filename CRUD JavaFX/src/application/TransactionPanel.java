package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TransactionPanel extends BorderPane {
	private TableView<Product> productTable;
    private TableView<TransactionItem> transactionTable;

    private TextField kodeField, modelField, merkField, warnaField, hargaField;
    private TextField kuantitasField, uangPembayaranField;
    private Label totalValueLabel, changeValueLabel;
    
    private ObservableList<TransactionItem> transactionList;
    private ObservableList<Product> productList;
    
    
    
    public TransactionPanel() {
        transactionList = FXCollections.observableArrayList();
        
        // LEFT FORM
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(30));
        formPane.setHgap(10);
        formPane.setVgap(10);

        kodeField = new TextField(); kodeField.setDisable(true);
        modelField = new TextField(); modelField.setDisable(true);
        merkField = new TextField(); merkField.setDisable(true);
        warnaField = new TextField(); warnaField.setDisable(true);
        hargaField = new TextField(); hargaField.setDisable(true);
        kuantitasField = new TextField();
        uangPembayaranField = new TextField();

        
        formPane.add(new Label("Kode:"), 0, 0); formPane.add(kodeField, 1, 0);
        formPane.add(new Label("Model:"), 0, 1); formPane.add(modelField, 1, 1);
        formPane.add(new Label("Merk:"), 0, 2); formPane.add(merkField, 1, 2);
        formPane.add(new Label("Warna:"), 0, 3); formPane.add(warnaField, 1, 3);
        formPane.add(new Label("Harga:"), 0, 4); formPane.add(hargaField, 1, 4);
        formPane.add(new Label("Kuantitas:"), 0, 5); formPane.add(kuantitasField, 1, 5);
        formPane.add(new Label("Pembayaran:"), 0, 6); formPane.add(uangPembayaranField, 1, 6);
        
        
 
        Label totalTextLabel = new Label("Total:");
        Label changeTextLabel = new Label("Change:");
        
        totalValueLabel = new Label("0");
        changeValueLabel = new Label("0");
        
        formPane.add(totalTextLabel, 0, 7);
        formPane.add(totalValueLabel, 1, 7);
        
        formPane.add(changeTextLabel, 0, 8);
        formPane.add(changeValueLabel, 1, 8);

        
        Button completeBtn = new Button("Complete Transaction");
        Button printBtn = new Button("Print Receipt");

        VBox buttonBox = new VBox(10,  completeBtn, printBtn);
        formPane.add(buttonBox, 1, 9);

        
        
        
        // Product Table and Transactions table

        //---------------------------------------- PRODUCT Table----------------------------------------------------
        productTable = new TableView<Product>();
        
        // Table columns
        TableColumn<Product, String> kodeCol = new TableColumn<>("Kode");
        kodeCol.setCellValueFactory(new PropertyValueFactory<>("kode"));

        TableColumn<Product, String> modelCol = new TableColumn<>("Model"); 
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model")); // bound to Product.getModel()

        TableColumn<Product, String> merkCol = new TableColumn<>("Merk");
        merkCol.setCellValueFactory(new PropertyValueFactory<>("merk"));

        TableColumn<Product, String> warnaCol = new TableColumn<>("Warna");
        warnaCol.setCellValueFactory(new PropertyValueFactory<>("warna"));

        TableColumn<Product, String> hargaCol = new TableColumn<>("Harga");
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));

        productTable.getColumns().addAll(kodeCol, modelCol, merkCol, warnaCol, hargaCol);
        
        // loads product table
        loadProductTable();
    
        // on mouse click event
        productTable.setOnMouseClicked(e -> populateForm());
        
        
      //---------------------------------------- Transaction Table----------------------------------------------------
        transactionTable = new TableView<>();
        
        TableColumn<TransactionItem, String> kodeCol2 = new TableColumn<>("Kode");
        kodeCol2.setCellValueFactory(new PropertyValueFactory<>("kode"));

        
        TableColumn<TransactionItem, String> modelCol2 = new TableColumn<>("Model");
        modelCol2.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<TransactionItem, String> merkCol2 = new TableColumn<>("Merk");
        merkCol2.setCellValueFactory(new PropertyValueFactory<>("merk"));
        
        TableColumn<TransactionItem, String> warnaCol2 = new TableColumn<>("Warna");
        warnaCol2.setCellValueFactory(new PropertyValueFactory<>("warna"));
        
        TableColumn<TransactionItem, Integer> kuantitasCol2 = new TableColumn<>("Kuantitas");
        kuantitasCol2.setCellValueFactory(new PropertyValueFactory<>("kuantitas"));

        TableColumn<TransactionItem, Integer> hargaCol2 = new TableColumn<>("Price");
        hargaCol2.setCellValueFactory(new PropertyValueFactory<>("harga"));

        TableColumn<TransactionItem, Integer> uangPembayaranCol2 = new TableColumn<>("Uang Pembayaran");
        uangPembayaranCol2.setCellValueFactory(new PropertyValueFactory<>("uang_pembayaran"));

        transactionTable.getColumns().addAll(kodeCol2, modelCol2, merkCol2, warnaCol2, kuantitasCol2, hargaCol2, uangPembayaranCol2);
        transactionTable.setItems(transactionList);

        // Event Handlers
        
        completeBtn.setOnAction(e -> completeTransaction());
        printBtn.setOnAction(e -> printReceipt());
        
        kuantitasField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalAndChange());
        uangPembayaranField.textProperty().addListener((observable, oldValue, newValue) -> updateTotalAndChange());
        
        // group the tables together
        VBox tableBox = new VBox(10,productTable, transactionTable);
        
        this.setLeft(formPane);
        this.setCenter(tableBox);
    }

    
    private void updateTotalAndChange() {
		try {
			Integer quantity = Integer.parseInt(kuantitasField.getText());
			Integer price = Integer.parseInt(hargaField.getText());
			Integer totalPaid = Integer.parseInt(uangPembayaranField.getText());
			
			
			int total = quantity*price;
			int change = totalPaid - total;
			
			totalValueLabel.setText((String.valueOf(total)));
			changeValueLabel.setText((String.valueOf(change)));
		} catch (Exception e) {
			totalValueLabel.setText("0");
			changeValueLabel.setText("0");
		}
    	
		return;
	}


	private void loadProductTable() {
    	productList = FXCollections.observableArrayList(ProductHandler.getAllProducts());
    	productTable.setItems(productList);
    	System.out.println("[+] Table data refreshed");
    }

	
	private void populateForm() {
    	Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
        	kodeField.setText(selected.getKode());
            modelField.setText(selected.getModel());
            merkField.setText(selected.getMerk());
            warnaField.setText(selected.getWarna());
            hargaField.setText(selected.getHarga());
        }
	}
    
	private Object printReceipt() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object completeTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
