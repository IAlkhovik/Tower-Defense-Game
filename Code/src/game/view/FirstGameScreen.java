package game.view;

import game.model.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class FirstGameScreen {
    private int width;
    private int height;
    
    public static int enemiesKilled = 0;
    public static int towersPlaced = 0;
    public static int towersUpgraded = 0;

    private String level;
    private int money;
    private int monumentHealth;

    private Button tower1Button;
    private Button tower2Button;
    private Button tower3Button;
    private Button startButton;
    private Button upgradeButton;

    private Pane path;

    private Stage mainWindow;

    private SimpleBooleanProperty isLost = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty isWon = new SimpleBooleanProperty(false);
    private boolean started = false;
    
    private Label moneyLabel;
    private Label monumentHealthLabel;
    
    private int roundCount = 1;

    private FirstGameScreen() { }
    public FirstGameScreen(int width, int height, String level, Stage mainWindow) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.mainWindow = mainWindow;
        
        FirstGameScreen.enemiesKilled = 0;
        FirstGameScreen.towersPlaced = 0;
        FirstGameScreen.towersUpgraded = 0;

        tower1Button = new Button("Squire");
        tower2Button = new Button("Archer");
        tower3Button = new Button("Paladin");
        startButton = new Button("Start");
        upgradeButton = new Button("Upgrade");

        tower1Button.setTooltip(new Tooltip(
                "The Squire is a short-range defense that can target the 4 "
                        + "adjacent spaces with their sword. They cost $10 to buy, "
                        + "and have an attack damage of 15 points."
        ));
        tower2Button.setTooltip(new Tooltip(
                "The Archer has a wide range of attack with their trusty bow, "
                        + "able to attack the 16 spaces in a square one space away from them! "
                        + "However, their arrows cannot attack the spaces directly around them. "
                        + "With such a large range, they only have an attack damage of 2 points, "
                        + "and have a price of $15."
        ));
        tower3Button.setTooltip(new Tooltip(
                "The Paladin has a special property. "
                        + "They deal 10 damage to any of the 4 spaces next to them, "
                        + "or even all 4 at once! Thus, "
                        + "they are more expensive (costing $20 to buy)."
        ));
        upgradeButton.setTooltip(new Tooltip(
                "Click on the tower you would like to upgrade. Upgrades cost $20 and "
                        + "double the attack damage of the selected tower."
        ));

        path = createPath();
    }
    public Scene getScene() {
        getStartingMoney();
        moneyLabel = new Label("Money: " + money);
        moneyLabel.setStyle("-fx-font-size: 20px");
        getStartingMonumentHealth();
        monumentHealthLabel = new Label("Monument Health: " + monumentHealth);
        monumentHealthLabel.setStyle("-fx-font-size: 20px");

        StackPane topBar = new StackPane();
        StackPane.setAlignment(moneyLabel, Pos.CENTER_LEFT);
        StackPane.setAlignment(monumentHealthLabel, Pos.CENTER_RIGHT);
        topBar.setStyle("-fx-padding: 5px");
        topBar.getChildren().addAll(moneyLabel, monumentHealthLabel);

        HBox towerMenu = new HBox(
                new Text("Tower Menu: "), tower1Button, tower2Button,
                tower3Button, new Text("                      "), upgradeButton,
                new Text("                      "), startButton);
        towerMenu.setAlignment(Pos.CENTER);
        StackPane menuBar = new StackPane();
        StackPane.setAlignment(towerMenu, Pos.CENTER);
        menuBar.getChildren().addAll(towerMenu);

        BorderPane borderPane = new BorderPane();

        borderPane.setTop(topBar);
        borderPane.setCenter(path);
        borderPane.setBottom(menuBar);
        Scene scene = new Scene(borderPane, width, height);

        tower1Button.setOnAction(initializeTower1Button());
        tower2Button.setOnAction(initializeTower2Button());
        tower3Button.setOnAction(initializeTower3Button());
        startButton.setOnAction(initializeStartButton());
        upgradeButton.setOnAction(initializeUpgradeButton());
        return scene;
    }

    public SimpleBooleanProperty gameLost() {
        return isLost;
    }
    
    public SimpleBooleanProperty gameWon() {
        return isWon;
    }

    private void getStartingMoney() {
        if (level.equals("easy")) {
            money = 75;
        } else if (level.equals("intermediate")) {
            money = 50;
        } else {
            money = 25;
        }
    }

    private void getStartingMonumentHealth() {
        if (level.equals("easy")) {
            monumentHealth = 100;
        } else if (level.equals("intermediate")) {
            monumentHealth = 75;
        } else {
            monumentHealth = 50;
        }
    }

    private Pane createPath() {
        Rectangle v1 = new Rectangle(0, 50, 350, 50);
        v1.setFill(Color.BLUE);
        Rectangle h1 = new Rectangle(300, 100, 50, 100);
        h1.setFill(Color.BLUE);
        Rectangle v2 = new Rectangle(150, 200, 200, 50);
        v2.setFill(Color.BLUE);
        Rectangle h2 = new Rectangle(150, 250, 50, 150);
        h2.setFill(Color.BLUE);
        Rectangle v3 = new Rectangle(150, 400, 300, 50);
        v3.setFill(Color.BLUE);
        Rectangle h3 = new Rectangle(400, 350, 50, 50);
        h3.setFill(Color.BLUE);
        Rectangle end = new Rectangle(450, 350, 50, 50);
        end.setFill(Color.RED);

        Pane pane = new Pane();
        pane.getChildren().addAll(v1, h1, v2, h2, v3, h3, end);
        return pane;
    }

    private void dmgMonumentCheck(Enemy gob, Label monumentHealthLabel) {
        if (gob.contains(475, 375) && gob.getOpacity() > 0) {
            monumentHealth = monumentHealth - gob.getDamage();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                monumentHealthLabel.setText("Monument Health: " + monumentHealth);
            }
        });
    }

    private void checkGameEnd() {
        if (monumentHealth <= 0) {
            isLost.set(true);
        }
        if (roundCount == 4){
            isWon.set(true);
        }
    }
    
    private void getMoney(Enemy gob) {
        if (!gob.getPay() && gob.getOpacity() == 0) {
            money = money + 5;
            enemiesKilled++;
            gob.pays();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                moneyLabel.setText("Money: " + money);
            }
        });
    }
    
    private void endRound(){
        money = money + 50;
        roundCount++;
        started = false;
        startButton.setStyle("-fx-border-width: 0px;");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                moneyLabel.setText("Money: " + money);
                for (Node enem: path.getChildren()){
                    if (enem instanceof Enemy) {
                        Enemy temp = (Enemy) enem;
                        temp.setMoveCount(-100);
                    }
                }
            }
        });
    }
    
    private EventHandler<ActionEvent> initializeStartButton() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (!started) {
                    started = true;
                    startButton.setStyle("-fx-border-width: 1px; -fx-border-color: red");
                    if (roundCount < 4) {
                        for (int i = 0; i < 5 * roundCount; i++) {
                            if (i % 3 == 0) {
                                path.getChildren().add(new SmallGoblin());
                            } else if (i % 3 == 1) {
                                path.getChildren().add(new MediumGoblin());
                            } else {
                                path.getChildren().add(new LargeGoblin());
                            }
                        }
                        if (roundCount > 2) {
                            path.getChildren().add(new BossGoblin());
                        }
                    }
                    final ScheduledExecutorService executorService
                            = Executors.newSingleThreadScheduledExecutor();
                    executorService.scheduleAtFixedRate(new Runnable() {
                        private int dif = 0;
                        @Override
                        public void run() {
                            if (dif == 0) {
                                for (Node a: path.getChildren()){
                                    if (a instanceof Enemy) {
                                        Enemy a1 = (Enemy) a;
                                        if (a1.getMoveCount() < 0) {
                                            dif++;
                                        }
                                    }
                                }
                            }
                            dif++;
                            int count = 0;
                            for (Node gob: path.getChildren()) {
                                if (gob instanceof Enemy) {
                                    Enemy goblin = (Enemy) gob;
                                    goblin.move();
                                    getMoney(goblin);
                                    if (goblin.getMoveCount() == 23) {
                                        dmgMonumentCheck(goblin, monumentHealthLabel);
                                    } else if (goblin.getMoveCount() > 23) {
                                        if (monumentHealth <= 0) {
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    checkGameEnd();
                                                }
                                            });
                                            executorService.shutdown();
                                        }
                                        else {
                                            if (goblin.getMoveCount() >= (5 * roundCount + 23 + 1)) {
                                                endRound();
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        checkGameEnd();
                                                    }
                                                });
                                                executorService.shutdown();
                                            }
                                        }
                                    }
                                    count++;
                                    if (count == dif) {
                                        break;
                                    }
                                } else if (gob instanceof Tower) {
                                    Tower tower = (Tower) gob;
                                    tower.fire(path);
                                }
                            }
                        }
                    }, 500, 500, TimeUnit.MILLISECONDS);
                }
            }
        };
        return action;
    }
    
    private EventHandler<ActionEvent> initializeTower1Button() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (path.getOnMouseClicked() != null) {
                    path.setOnMouseClicked(null);
                    tower1Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                    tower2Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                    tower3Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                } else {
                    tower1Button.setStyle("-fx-border-width: 1px; -fx-border-color: black");
                    path.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent me) {
                            int x = (int) (me.getX() - (me.getX() % 50));
                            int y = (int) (me.getY() - (me.getY() % 50));
                            boolean taken = false;
                            for (Node rect: path.getChildren()) {
                                if (!taken) {
                                    if (rect.contains(x, y)) {
                                        taken = true;
                                    }
                                }
                            }
                            Squire tower = new Squire(x, y);
                            if (!taken && money >= tower.getPrice()) {
                                path.getChildren().add(tower);
                                money = money - tower.getPrice();
                                moneyLabel.setText("Money: " + money);
                                towersPlaced++;
                                path.setOnMouseClicked(null);
                                tower1Button.setStyle(
                                        "-fx-border-width: 0px; -fx-border-color: black");
                            }
                        }
                    });
                }
            }
        };
        return action;
    }
    
    private EventHandler<ActionEvent> initializeTower2Button() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (path.getOnMouseClicked() != null) {
                    path.setOnMouseClicked(null);
                    tower1Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                    tower2Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                    tower3Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                } else {
                    tower2Button.setStyle("-fx-border-width: 1px; -fx-border-color: black");
                    path.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent me) {
                            int x = (int) (me.getX() - (me.getX() % 50));
                            int y = (int) (me.getY() - (me.getY() % 50));
                            boolean taken = false;
                            for (Node rect: path.getChildren()) {
                                if (!taken) {
                                    if (rect.contains(x, y)) {
                                        taken = true;
                                    }
                                }
                            }
                            Archer tower = new Archer(x, y);
                            if (!taken && money >= tower.getPrice()) {
                                path.getChildren().add(tower);
                                money = money - tower.getPrice();
                                moneyLabel.setText("Money: " + money);
                                towersPlaced++;
                                path.setOnMouseClicked(null);
                                tower2Button.setStyle(
                                        "-fx-border-width: 0px; -fx-border-color: black");
                            }
                        }
                    });
                }
            }
        };
        return action;
    }
    
    private EventHandler<ActionEvent> initializeTower3Button() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (path.getOnMouseClicked() != null) {
                    path.setOnMouseClicked(null);
                    tower1Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                    tower2Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                    tower3Button.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                } else {
                    tower3Button.setStyle("-fx-border-width: 1px; -fx-border-color: black");
                    path.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent me) {
                            int x = (int) (me.getX() - (me.getX() % 50));
                            int y = (int) (me.getY() - (me.getY() % 50));
                            boolean taken = false;
                            for (Node rect: path.getChildren()) {
                                if (!taken) {
                                    if (rect.contains(x, y)) {
                                        taken = true;
                                    }
                                }
                            }
                            Paladin tower = new Paladin(x, y);
                            if (!taken && money >= tower.getPrice()) {
                                path.getChildren().add(tower);
                                money = money - tower.getPrice();
                                moneyLabel.setText("Money: " + money);
                                towersPlaced++;
                                path.setOnMouseClicked(null);
                                tower3Button.setStyle(
                                        "-fx-border-width: 0px; -fx-border-color: black");
                            }
                        }
                    });
                }
            }
        };
        return action;
    }

    private EventHandler<ActionEvent> initializeUpgradeButton() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (path.getOnMouseClicked() != null) {
                    path.setOnMouseClicked(null);
                    upgradeButton.setStyle("-fx-border-width: 0px; -fx-border-color: black");
                } else {
                    upgradeButton.setStyle("-fx-border-width: 1px; -fx-border-color: black");
                    path.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent me) {
                            int x = (int) (me.getX() - (me.getX() % 50));
                            int y = (int) (me.getY() - (me.getY() % 50));
                            for (Node rect: path.getChildren()) {
                                if (rect.contains(x, y) && money >= 20) {
                                    if (rect instanceof Squire && !((Squire) rect).isUpgraded()) {
                                        ((Squire) rect).setFill(Color.GREEN.brighter());
                                        ((Squire) rect).setDamage(((Squire) rect).getDamage() * 2);
                                        ((Squire) rect).setUpgraded(true);
                                        money -= 20;
                                        towersUpgraded++;
                                    } else if (rect instanceof Archer && !((Archer) rect).isUpgraded()) {
                                        ((Archer) rect).setFill(Color.FIREBRICK.brighter());
                                        ((Archer) rect).setDamage(((Archer) rect).getDamage() * 2);
                                        ((Archer) rect).setUpgraded(true);
                                        money -= 20;
                                        towersUpgraded++;
                                    } else if (rect instanceof Paladin && !((Paladin) rect).isUpgraded()) {
                                        ((Paladin) rect).setFill(Color.PURPLE.brighter());
                                        ((Paladin) rect).setDamage(((Paladin) rect).getDamage() * 2);
                                        ((Paladin) rect).setUpgraded(true);
                                        money -= 20;
                                        towersUpgraded++;
                                    }
                                    moneyLabel.setText("Money: " + money);
                                    path.setOnMouseClicked(null);
                                    upgradeButton.setStyle(
                                            "-fx-border-width: 0px; -fx-border-color: black");
                                }
                            }
                        }
                    });
                }
            }
        };
        return action;
    }
}
