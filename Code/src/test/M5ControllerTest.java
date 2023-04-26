package test;

import game.controller.Controller;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

public class M5ControllerTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
    
    @Test
    public void tower3doesDamage() {
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        clickOn("Paladin");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 175);
        clickOn("Start");
        int pathSize = path.getChildren().size();
        assertEquals(14, pathSize);
        try {
            Thread.sleep(12000);
            pathSize = 0;
            for (Node rect: path.getChildren()) {
                if (rect.getOpacity() != 0) {
                    pathSize++;
                }
            }
            Thread.sleep(4000);
            assertEquals(12, pathSize);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void canPlaceTowersMidWave() {
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        clickOn("Start");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        int pathSize = path.getChildren().size();
        assertEquals(12, pathSize);
        try {
            Thread.sleep(4000);
            clickOn("Paladin");
            Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
            clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
            Thread.sleep(500);
            pathSize = path.getChildren().size();
            Thread.sleep(11500);
            assertEquals(13, pathSize);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }
    
    @Test
    public void testDamagedGoblinsDontGiveMoney() {
        clickOn("Play");
        write("Alyssa");
        clickOn("Continue");
        clickOn("Start");

        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());

        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 400, boundsInScreen.getMinY() + 200);
        try {
            Thread.sleep(5000);
            verifyThat("Money: 70", NodeMatchers.isNotNull());

            Thread.sleep(11500);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void testDeadGoblinsGiveMoney() {
        clickOn("Play");
        write("Alyssa");
        clickOn("Continue");
        clickOn("Start");

        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Archer");
        clickOn(boundsInScreen.getMinX() + 100, boundsInScreen.getMinY() + 125);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 100, boundsInScreen.getMinY() + 300);
        verifyThat("Money: 55", NodeMatchers.isNotNull());
        try {
            Thread.sleep(8000);
            verifyThat("Money: 60", NodeMatchers.isNotNull());
            Thread.sleep(8000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void testDamagedGoblinsChangeOpacity() {
        clickOn("Play");
        write("Kathy");
        clickOn("Continue");
        clickOn("Squire");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 250, boundsInScreen.getMinY() + 175);
        clickOn("Start");
        int pathSize = path.getChildren().size();
        assertEquals(13, pathSize);
        try {
            Thread.sleep(12000);
            pathSize = 0;
            for (Node rect: path.getChildren()) {
                if (rect.getOpacity() < 1) {
                    pathSize++;
                }
            }
            assertEquals(2, pathSize);
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void testNonDamagedGoblinsFullOpacity() {
        clickOn("Play");
        write("Kathy");
        clickOn("Continue");
        clickOn("Squire");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 250, boundsInScreen.getMinY() + 175);
        clickOn("Start");
        int pathSize = path.getChildren().size();
        assertEquals(13, pathSize);
        try {
            Thread.sleep(12000);
            pathSize = 0;
            for (Node rect: path.getChildren()) {
                if (rect.getOpacity() == 1) {
                    pathSize++;
                }
            }
            assertEquals(11, pathSize);
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }
    
    @Test
    public void testDeadGoblinsDealNoDamage() {
        clickOn("Play");
        write("ethan");
        clickOn("Continue");
        verifyThat("Monument Health: 100", NodeMatchers.isNotNull());
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 175);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 225, boundsInScreen.getMinY() + 275);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 225, boundsInScreen.getMinY() + 325);
        clickOn("Start");
        try {
            Thread.sleep(12750);
            verifyThat("Monument Health: 100", NodeMatchers.isNotNull());
            Thread.sleep(3250);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }
    
    @Test
    public void testDamagedGoblinsDealDamage() {
        clickOn("Play");
        write("ethan");
        clickOn("Continue");
        verifyThat("Monument Health: 100", NodeMatchers.isNotNull());
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Start");
        try {
            Thread.sleep(11750);
            verifyThat("Monument Health: 80", NodeMatchers.isNotNull());
            Thread.sleep(4250);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }
    
    @Test
    public void testTower1Damage() {
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 100, boundsInScreen.getMinY() + 300);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 175);
        clickOn("Start");
        int numNodes = path.getChildren().size();
        assertEquals(numNodes, 15);
        try {
            Thread.sleep(12000);
            numNodes = 0;
            for (Node node : path.getChildren()) {
                if (node.getOpacity() != 0) {
                    numNodes++;
                }
            }
            assertEquals(13, numNodes);
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void testTower2Damage() {
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Archer");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Archer");
        clickOn(boundsInScreen.getMinX() + 100, boundsInScreen.getMinY() + 300);
        clickOn("Archer");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 175);
        clickOn("Start");
        int numNodes = path.getChildren().size();
        assertEquals(numNodes, 15);
        try {
            Thread.sleep(12000);
            numNodes = 0;
            for (Node node : path.getChildren()) {
                if (node.getOpacity() != 0) {
                    numNodes++;
                }
            }
            assertEquals(14, numNodes);
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }
}
