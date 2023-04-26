package game.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WelcomeScreen {
    private int width;
    private int height;
    private Button playButton;
    private Button quitButton;

    private WelcomeScreen() { }
    public WelcomeScreen(int width, int height) {
        this.width = width;
        this.height = height;
        playButton = new Button("Play");
        quitButton = new Button("Quit");
    }
    public Scene getScene() {
        Label label = new Label("Knights of Scheller");
        Label label2 = new Label("Tower Defense");
        label.setStyle("-fx-font-size: 25px");
        label2.setStyle("-fx-font-size: 25px");
        label.getStyleClass().add("statusText");
        //HBox buttons = new HBox(quitButton, playButton);
        //buttons.getStyleClass().add("buttons");
        playButton.setStyle("-fx-font-size: 15px;");
        quitButton.setStyle("-fx-font-size: 15px;");
        VBox vbox = new VBox(label, label2,  playButton, quitButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-spacing: 5px");
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Button getPlayButton() {
        return playButton;
    }
}
