package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Tab pane as the base to switch between pages
			TabPane tabPane = new TabPane();
			
			
			// Product page (tab 1)
			ProductPanel productPanel = new ProductPanel();
			Tab productTab = new Tab("Products", productPanel);
			
			// Transaction page (tab 2)
			TransactionPanel transactionPanel = new TransactionPanel();
			Tab transactionTab = new Tab("Transactions", transactionPanel);
			
			
			// Add all tabs to the tab pane
			tabPane.getTabs().addAll(productTab, transactionTab);
			
			//disable closing
			productTab.setClosable(false);
			transactionTab.setClosable(false);
			
			Scene scene = new Scene(tabPane,900,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
