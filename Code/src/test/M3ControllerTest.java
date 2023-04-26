package test;

import game.controller.Controller;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static junit.framework.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

public class M3ControllerTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void testPlacingAwayFromPath() {
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        clickOn("Tower 1");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 75, boundsInScreen.getMinY() + 325);
        boolean fail = true;
        for (Node rect: path.getChildren()) {
            if (fail) {
                if (rect.contains(50, 300)) {
                    fail = false;
                }
            }
        }
        assertEquals(fail, false);
    }

    @Test
    public void testMoneyDeduction() {
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        verifyThat("Money: 100", NodeMatchers.isNotNull());
        clickOn("Tower 1");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 75, boundsInScreen.getMinY() + 325);
        verifyThat("Money: 90", NodeMatchers.isNotNull());
    }

    @Test
    public void testPlacingTower3TouchingPath() {
        clickOn("Play");
        write("Alyssa");
        clickOn("Continue");
        clickOn("Tower 3");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 125, boundsInScreen.getMinY() + 325);
        boolean fail = true;

        for (Node rect: path.getChildren()) {
            if (fail) {
                if (rect.contains(100, 300)) {
                    fail = false;
                }
            }
        }
        assertEquals(fail, false);
    }

    @Test
    public void testBuyingTowerWithInsufficientMoney() {
        clickOn("Play");
        write("Alyssa");
        clickOn("easy");
        clickOn("hard");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Tower 3");
        clickOn(boundsInScreen.getMinX() + 75, boundsInScreen.getMinY() + 325);
        clickOn("Tower 3");
        clickOn(boundsInScreen.getMinX() + 125, boundsInScreen.getMinY() + 325);
        boolean fail = true;

        for (Node rect: path.getChildren()) {
            if (fail) {
                if (rect.contains(100, 300)) {
                    fail = false;
                }
            }
        }
        assertEquals(fail, true);
    }

    @Test
    public void testPlacingTower1OnPath() {
        clickOn("Play");
        write("Kathy");
        clickOn("Continue");
        clickOn("Tower 1");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 75, boundsInScreen.getMinY() + 75);
        assertEquals(path.getChildren().size(), 7);
    }

    @Test
    public void testPlacingTower2OnPath() {
        clickOn("Play");
        write("Kathy");
        clickOn("Continue");
        clickOn("Tower 2");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 325, boundsInScreen.getMinY() + 125);
        assertEquals(path.getChildren().size(), 7);
    }

    @Test
    public void testPlacingTower1OnExistingTower() {
        clickOn("Play");
        write("Ethan");
        clickOn("Continue");
        clickOn("Tower 2");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 75, boundsInScreen.getMinY() + 325);
        clickOn("Tower 1");
        Bounds boundsInScreen1 = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen1.getMinX() + 75, boundsInScreen1.getMinY() + 325);
        assertEquals(path.getChildren().size(), 8);
    }

    @Test
    public void testPlacingTower3OnExistingTower() {
        clickOn("Play");
        write("Ethan");
        clickOn("Continue");
        clickOn("Tower 2");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 75, boundsInScreen.getMinY() + 325);
        clickOn("Tower 3");
        Bounds boundsInScreen1 = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen1.getMinX() + 75, boundsInScreen1.getMinY() + 325);
        assertEquals(path.getChildren().size(), 8);
    }

    @Test
    public void testPlacingTower3OnPath() {
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        clickOn("Tower 3");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 225, boundsInScreen.getMinY() + 75);
        assertEquals(path.getChildren().size(), 7);
    }

    @Test
    public void testPlacingTower2OnExistingTower() {
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Tower 2");
        clickOn(boundsInScreen.getMinX() + 100, boundsInScreen.getMinY() + 100);
        clickOn("Tower 2");
        clickOn(boundsInScreen.getMinX() + 100, boundsInScreen.getMinY() + 100);
        assertEquals(path.getChildren().size(), 8);
    }
}