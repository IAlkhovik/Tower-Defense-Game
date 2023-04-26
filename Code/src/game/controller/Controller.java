package game.controller;

import game.view.*;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller extends Application {
    private Stage mainWindow;
    private final int width = 500;
    private final int height = 600;
    private PlayerConfigScreen playerConfigScreen;
    private String selectedLevel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Knights of Scheller");
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        WelcomeScreen screen = new WelcomeScreen(width, height);
        Button quitButton = screen.getQuitButton();
        quitButton.setOnAction(e -> mainWindow.close());
        Button playButton = screen.getPlayButton();
        playButton.setOnAction(e -> goToSecondScreen());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToSecondScreen() {
        playerConfigScreen = new PlayerConfigScreen(width, height);

        Button saveButton = playerConfigScreen.getPlayButton();
        saveButton.setOnAction(e -> {
            Alert invalidName = new Alert(Alert.AlertType.ERROR);
            invalidName.setContentText("Please enter a valid name.");
            if (playerConfigScreen.getName().getText() == null
                    || playerConfigScreen.getName().getText().trim().isBlank()
                    || playerConfigScreen.getName().getText().trim().isEmpty()) {
                invalidName.show();
            } else {
                goToFirstGameScreen();
            }
        });

        Scene scene = playerConfigScreen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToFirstGameScreen() {
        selectedLevel = (String) playerConfigScreen.getDiffLevel().getValue();
        FirstGameScreen screen = new FirstGameScreen(width, height, selectedLevel, mainWindow);

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();

        BooleanProperty completedProperty = new SimpleBooleanProperty();
        completedProperty.bind(screen.gameLost());
        completedProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                goToGameLossScreen();
            }
        });
        
        BooleanProperty completedProperty2 = new SimpleBooleanProperty();
        completedProperty2.bind(screen.gameWon());
        completedProperty2.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                goToGameWinScreen();
            }
        });
    }

    private void goToGameLossScreen() {
        GameLossScreen screen = new GameLossScreen(width, height);
        Button restartButton = screen.getRestartButton();
        restartButton.setOnAction(e -> initWelcomeScreen());
        Button closeButton = screen.getCloseButton();
        closeButton.setOnAction(e -> mainWindow.close());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }
    
    private void goToGameWinScreen() {
        GameWinScreen screen = new GameWinScreen(width, height);
        Button restartButton = screen.getRestartButton();
        restartButton.setOnAction(e -> initWelcomeScreen());
        Button closeButton = screen.getCloseButton();
        closeButton.setOnAction(e -> mainWindow.close());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
