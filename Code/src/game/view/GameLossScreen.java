package game.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameLossScreen {
    private int width;
    private int height;
    private Button restartButton;
    private Button closeButton;

    private GameLossScreen() { }
    public GameLossScreen(int width, int height) {
        this.width = width;
        this.height = height;
        restartButton = new Button("Restart");
        closeButton = new Button("Exit");
    }

    public Scene getScene() {
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setStyle("-fx-font-size: 25px");
        Text consoleText = new Text("Better luck next time!");
        Text goblinsDefeated = new Text("Goblins Defeated: " + FirstGameScreen.enemiesKilled);
        Text towersPlaced = new Text("Towers Placed: " + FirstGameScreen.towersPlaced);
        Text towersUpgraded = new Text("Towers Upgraded: " + FirstGameScreen.towersUpgraded);
        restartButton.setStyle("-fx-font-size: 15px;");
        closeButton.setStyle("-fx-font-size: 15px;");
        HBox hbox = new HBox(restartButton, closeButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-spacing: 15px");
        VBox vbox = new VBox(gameOverLabel, consoleText, goblinsDefeated, towersPlaced, towersUpgraded, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-spacing: 10px");
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public Button getCloseButton() {
        return closeButton;
    }
}
