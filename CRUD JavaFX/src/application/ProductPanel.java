package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class ProductPanel extends BorderPane {
    private TableView<Product> table;
    private TextField modelField;
    private TextField merkField;
    private TextField warnaField;
    private TextField hargaField;
    private Alert a;
    private ObservableList<Product> productList;
    

    public ProductPanel() {
    	
    	
    	
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(30));
        formPane.setHgap(20);
        formPane.setVgap(20);

        // Labels and Text Fields
        Label modelLabel = new Label("Model:");
        modelField = new TextField();

        Label merkLabel = new Label("Merk:");
        merkField = new TextField();

        Label warnaLabel = new Label("Warna:");
        warnaField = new TextField();

        Label hargaLabel = new Label("Harga:");
        hargaField = new TextField();

        Button addButton = new Button("Add");
        Button updButton = new Button("Update");
        Button delButton = new Button("Delete");

        formPane.add(modelLabel, 0, 0);
        formPane.add(modelField, 1, 0);
        formPane.add(merkLabel, 0, 1);
        formPane.add(merkField, 1, 1);
        formPane.add(warnaLabel, 0, 2);
        formPane.add(warnaField, 1, 2);
        formPane.add(hargaLabel, 0, 3);
        formPane.add(hargaField, 1, 3);

        // Buttons
        HBox buttonBox = new HBox(10, addButton, updButton, delButton);
        formPane.add(buttonBox, 1, 4);

        // Table
        table = new TableView<Product>();
        
        // Table columns
        TableColumn<Product, String> kodeCol = new TableColumn<>("Kode");
        kodeCol.setCellValueFactory(new PropertyValueFactory<>("kode"));

        TableColumn<Product, String> modelCol = new TableColumn<>("Model"); // changed from "Jenis"
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model")); // bound to Product.getModel()

        TableColumn<Product, String> merkCol = new TableColumn<>("Merk");
        merkCol.setCellValueFactory(new PropertyValueFactory<>("merk"));

        TableColumn<Product, String> warnaCol = new TableColumn<>("Warna");
        warnaCol.setCellValueFactory(new PropertyValueFactory<>("warna"));

        TableColumn<Product, String> hargaCol = new TableColumn<>("Harga");
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));

        table.getColumns().addAll(kodeCol, modelCol, merkCol, warnaCol, hargaCol);

        
        loadTable(); // load tables with products
        addButton.setOnAction(e -> addProduct());
        delButton.setOnAction(e -> deleteProduct());
        table.setOnMouseClicked(e -> populateForm());
        
        
        this.setLeft(formPane);
        this.setRight(table);
    }
    
    //Print error message
    private void showError(String msg) {
    	a = new Alert(AlertType.ERROR);
		a.setContentText(msg);
		a.show();
    }
    
    private void loadTable() {
    	productList = FXCollections.observableArrayList(ProductHandler.getAllProducts());
    	table.setItems(productList);
    	System.out.println("[+] Table data refreshed");
    }
    
    private void populateForm() {
    	Product selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            modelField.setText(selected.getModel());
            merkField.setText(selected.getMerk());
            warnaField.setText(selected.getWarna());
            hargaField.setText(selected.getHarga());
        }
	}

	private boolean fieldIsEmpty() {
    	if(!modelField.getText().isEmpty() &&!merkField.getText().isEmpty()&&!warnaField.getText().isEmpty()&&!hargaField.getText().isEmpty()) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    private void clearFields() {
        modelField.clear();
        merkField.clear();
        warnaField.clear();
        hargaField.clear();
    }
    
    private void addProduct() {
    	String tempModel = modelField.getText();
    	String tempMerek = merkField.getText();
    	String tempWarna = warnaField.getText();
    	String tempHarga = hargaField.getText();
    	
    	if(!fieldIsEmpty()) {
    		Product product = new Product(ProductHandler.generateCode(tempMerek),tempModel, tempMerek, tempWarna, tempHarga);
    		// add to the table 
    		//table.getItems().add(product);
    		
    		
    		//add to the database and refresh table
    		if(ProductHandler.insertProduct(product)) {
    			loadTable();
    		} else {
    			showError("Operation Failed. Something broke");
    		}
    		
    	} else {
    		showError("All forms must be filled!");
    	}
        
        clearFields();
    }
    private void deleteProduct() {
    	Product selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
        	if(ProductHandler.deleteProduct(selected.getKode())) {
    			loadTable();
    		} else {
    			showError("Operation Failed. Something broke");
    		}
        	
        } else {
        	showError("No product is currently selected!");
        	return;
        }
        loadTable();
    }
    
    
    
    
}
