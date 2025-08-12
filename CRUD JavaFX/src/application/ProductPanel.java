package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ProductPanel extends BorderPane {
    private TableView<Product> productTable;
    private TextField modelField;
    private TextField merkField;
    private TextField warnaField;
    private TextField hargaField;
    private Alert a;
    private ObservableList<Product> productList;
    

    public ProductPanel() {
    	
    	// LEFT FORM
    	// Load shoe icon
    	Image shoeIcon = new Image(getClass().getResourceAsStream("/images/shoeicon.png")); 
    	ImageView shoeIconView = new ImageView(shoeIcon);
    	shoeIconView.setFitHeight(32); 
    	shoeIconView.setPreserveRatio(true);

    	// Title Label
    	Label productPanelTitle = new Label("Add / Edit Products");
    	productPanelTitle.getStyleClass().add("product-panel-title");

    	// Put icon and label inside HBox
    	HBox titleBox = new HBox(10, shoeIconView, productPanelTitle);
    	titleBox.setAlignment(Pos.CENTER_LEFT);
    	titleBox.setPadding(new Insets(10));
    	titleBox.setStyle("-fx-background-color: #2c3e50;");

    	// make the form pane
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
        productTable = new TableView<Product>();
        
        // Table columns
        TableColumn<Product, String> kodeCol = new TableColumn<>("Kode");
        kodeCol.setCellValueFactory(new PropertyValueFactory<>("kode")); // calls getKode()

        TableColumn<Product, String> modelCol = new TableColumn<>("Model"); 
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model")); 

        TableColumn<Product, String> merkCol = new TableColumn<>("Merk");
        merkCol.setCellValueFactory(new PropertyValueFactory<>("merk"));

        TableColumn<Product, String> warnaCol = new TableColumn<>("Warna");
        warnaCol.setCellValueFactory(new PropertyValueFactory<>("warna"));

        TableColumn<Product, String> hargaCol = new TableColumn<>("Harga");
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));

        productTable.getColumns().addAll(kodeCol, modelCol, merkCol, warnaCol, hargaCol);

        
        loadProductTable(); // load tables with products
        addButton.setOnAction(e -> addProduct());
        delButton.setOnAction(e -> deleteProduct());
        updButton.setOnAction(e -> updateProduct());
        productTable.setOnMouseClicked(e -> populateForm());
        
        
        VBox leftPanel = new VBox(10, titleBox, formPane);
        
        this.setLeft(leftPanel);
        this.setCenter(productTable);
    }
    
    private void updateProduct() {
    	Product selected = productTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
	    	String tempModel = modelField.getText();
	    	String tempMerek = merkField.getText();
	    	String tempWarna = warnaField.getText();
	    	String tempHarga = hargaField.getText();
	    	
	    	if(!fieldIsEmpty()) {
	    		Product product = new Product(selected.getKode(),tempModel, tempMerek, tempWarna, tempHarga);
	    		//modifies the column in and load table
	    		if(ProductHandler.updateProduct(product)) {
	    			loadProductTable();
	    		} else {
	    			showError("Operation Failed. Something broke");
	    		}
	    		
	    	} else {
	    		showError("All forms must be filled!");
	    	}    
        clearFields();
    	} else {
    		showError("No product is currently selected!");
        	return;
    	}
	}

	//Print error message
    private void showError(String msg) {
    	a = new Alert(AlertType.ERROR);
		a.setContentText(msg);
		a.show();
    }
    
    // Make list, connect to DB, fills the list with data, insert list to table 
    private void loadProductTable() {
    	productList = FXCollections.observableArrayList(ProductHandler.getAllProducts());
    	productTable.setItems(productList);
    	System.out.println("[+] Table data refreshed");
    }
    
    // auto fill text fields after clicking
    private void populateForm() {
    	Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            modelField.setText(selected.getModel());
            merkField.setText(selected.getMerk());
            warnaField.setText(selected.getWarna());
            hargaField.setText(selected.getHarga());
        }
	}

    // returns true if any of the fields is empty, 
	private boolean fieldIsEmpty() {
    	if(!modelField.getText().isEmpty() &&!merkField.getText().isEmpty()&&!warnaField.getText().isEmpty()&&!hargaField.getText().isEmpty()) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
	// clears all text fields
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
    			loadProductTable();
    		} else {
    			showError("Operation Failed.");
    		}
    		
    	} else {
    		showError("All forms must be filled!");
    	}
        
        clearFields();
    }
    private void deleteProduct() {
    	Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
        	if(ProductHandler.deleteProduct(selected.getKode())) {
    			loadProductTable();
    		} else {
    			showError("Operation Failed.");
    		}
        	
        } else {
        	showError("No product is currently selected!");
        	return;
        }
        loadProductTable();
    }
    
    
    
    
}
