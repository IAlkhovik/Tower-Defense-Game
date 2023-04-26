package game.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerConfigScreen {
    private int width;
    private int height;
    private Label label;
    private TextField name;
    private ComboBox difficultyLevels;
    private Button saveButton;

    private PlayerConfigScreen() { }
    public PlayerConfigScreen(int width, int height) {
        this.width = width;
        this.height = height;
        label = new Label("Player Configuration");
        name = new TextField();
        name.setPromptText("Enter Player Name");
        difficultyLevels = new ComboBox();
        saveButton = new Button("Continue");
    }

    public Scene getScene() {
        label.getStyleClass().add("statusText");
        HBox nameInput = new HBox(new Text("Enter Name Here: "), name);
        nameInput.setAlignment(Pos.CENTER);

        difficultyLevels.getItems().addAll(
                "easy",
                "intermediate",
                "hard");
        difficultyLevels.getSelectionModel().selectFirst();
        HBox levelInput = new HBox(new Text("Select a Difficulty Level Here: "), difficultyLevels);
        levelInput.setAlignment(Pos.CENTER);

        label.setStyle("-fx-font-size: 25px");
        VBox vbox = new VBox(label, nameInput, levelInput, saveButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-spacing: 5px");
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }

    public TextField getName() {
        return name;
    }

    public ComboBox getDiffLevel() {
        return difficultyLevels; }

    public Button getPlayButton() {
        return saveButton;
    }

}
