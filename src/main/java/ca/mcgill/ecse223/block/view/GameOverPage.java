package ca.mcgill.ecse223.block.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.application.Block223Application.Pages;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.model.HallOfFameEntry;
import ca.mcgill.ecse223.block.model.Player;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameOverPage implements IPage{

	Stage stage;

	public GameOverPage(Stage stage) {
		this.stage = stage;

	}
	
	public void display() {
	    // Create the login grid pane
        GridPane gridPane = createGridPane();
        
        //Create an HBox to hold the back button at the top of the screen
        HBox hbButtons = new HBox();
        
        // Add the UI components to the grid pane
        try {
			addUIComponents(gridPane);
		} catch (Exception e) {
			//Components.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error loading page:", e.getMessage());
		}
        // Create the scene with gridPane as the root node
        
        //Add Back Button, requires catch for file not found
        try {
        	FileInputStream backImageInput = new FileInputStream("Images/next.png");
        	Image backImage = new Image(backImageInput); 
            ImageView backImageView = new ImageView(backImage); 
            backImageView.setFitHeight(60);
            backImageView.setFitWidth(60);
            
            Button backButton = new Button("", backImageView);
            backButton.setStyle("-fx-base: #92D3FC;");	// Styles the default background color of the button
            hbButtons.getChildren().add(backButton);
            hbButtons.setAlignment(Pos.CENTER_LEFT);
            
            backButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	if(Block223Application.getCurrentUserRole() instanceof Player)
                	{
                		IPage page = Block223Application.getPage(Pages.AvaliableGamesPlayer);
                		page.display();
                	}
                	else {
                		IPage page = Block223Application.getPage(Pages.AvaliableGamesAdmin);
                		page.display();
                	}
                }
            });
            
        } catch (FileNotFoundException e) {
        	System.out.println("File not found");
        }

        BorderPane root = new BorderPane();
	    root.setPadding(new Insets(20)); // space between elements and window border
	    root.setCenter(gridPane);
	    root.setTop(hbButtons);
        
		Scene scene = new Scene(root, Block223Application.APPLICATION_WIDTH, Block223Application.APPLICATION_HEIGHT, Color.WHITE);
		// setting border plane dimensions and specifications
		stage.setTitle("Game Over");
		stage.setScene(scene); 
		stage.show(); 
	}
	
	private GridPane createGridPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);
        
        return gridPane;
    }
	
    private void addUIComponents(GridPane gridPane) throws FileNotFoundException, Exception {
    	
    	ObservableList<String> data = 
    	        FXCollections.observableArrayList();
    	
		List<HallOfFameEntry> entries = Block223Application.getBlock223().getEntries();
		java.util.Collections.reverse(entries);
        
    	for(HallOfFameEntry entry : entries) {
    		data.add(entry.getPlayername() + " - " + entry.getScore());
    	}
    	ListView<String> listView = new ListView<String>(data);
    	gridPane.add(listView, 0, 1);    
    }

}