package test;

import game.controller.Controller;
import game.model.*;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

public class M6ControllerTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void towersUpgradeWhenClicked() {
        clickOn("Play");
        write("Kathy");
        clickOn("Continue");
        clickOn("Squire");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 175);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 225, boundsInScreen.getMinY() + 275);
        for (Node rect: path.getChildren()) {
            if (rect instanceof Squire) {
                assertEquals(((Squire) rect).getFill(), Color.GREEN);
            } else if (rect instanceof Paladin) {
                assertEquals(((Paladin) rect).getFill(), Color.PURPLE);
            }
        }
        clickOn("Upgrade");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 175);
        clickOn("Upgrade");
        clickOn(boundsInScreen.getMinX() + 225, boundsInScreen.getMinY() + 275);
        for (Node rect: path.getChildren()) {
            if (rect instanceof Squire) {
                assertEquals(((Squire) rect).getFill(), Color.GREEN.brighter());
            } else if (rect instanceof Paladin) {
                assertEquals(((Paladin) rect).getFill(), Color.PURPLE.brighter());
            }
        }
    }

    @Test
    public void pathDoesNotUpgradeWhenClicked() {
        clickOn("Play");
        write("Kathy");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        for (int i = 0; i < path.getChildren().size() - 1; i++) {
            assertEquals(((Rectangle) path.getChildren().get(i)).getFill(), Color.BLUE);
        }
        clickOn("Upgrade");
        clickOn(boundsInScreen.getMinX() + 175, boundsInScreen.getMinY() + 400);
        for (int i = 0; i < path.getChildren().size() - 1; i++) {
            assertEquals(((Rectangle) path.getChildren().get(i)).getFill(), Color.BLUE);
        }
    }
    
    @Test
    public void oldWaveGoblinsAreRemoved() {
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 175);
        clickOn("Start");
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
        clickOn("Start");
        int count = 0;
        for (Node gob: path.getChildren()){
            if (gob instanceof Enemy){
                Enemy goblin = (Enemy) gob;
                if (goblin.getMoveCount() < 0){
                    count++;
                }
            }
        }
        assertEquals(5, count);
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
    }
    
    @Test
    public void differentWavesHaveDiffGobNumbers(){
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 175);
        clickOn("Start");
        int count = 0;
        for (Node gob: path.getChildren()){
            if (gob instanceof Enemy){
                Enemy goblin = (Enemy) gob;
                count++;
            }
        }
        assertEquals(5, count);
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
        count = 0;
        clickOn("Start");
        for (Node gob: path.getChildren()){
            if (gob instanceof Enemy){
                Enemy goblin = (Enemy) gob;
                if (goblin.getMoveCount() < 0){
                    count--;
                }
                count++;
            }
        }
        assertEquals(10, count);
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
    }
    
    @Test
    public void bossShowsUp() {
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 200, boundsInScreen.getMinY() + 350);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 250, boundsInScreen.getMinY() + 350);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 300, boundsInScreen.getMinY() + 350);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 350, boundsInScreen.getMinY() + 350);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 175, boundsInScreen.getMinY() + 450);
        clickOn("Start");
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
        int wave1Gobs = 0;
        for (Node x: path.getChildren()){
            if (x instanceof Enemy){
                wave1Gobs++;
            }
        }
        assertEquals(5, wave1Gobs);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 225, boundsInScreen.getMinY() + 450);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 450);
        clickOn("Upgrade");
        clickOn(boundsInScreen.getMinX() + 350, boundsInScreen.getMinY() + 350);
        clickOn("Upgrade");
        clickOn(boundsInScreen.getMinX() + 300, boundsInScreen.getMinY() + 350);
        clickOn("Start");
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
        int wave2Gobs = 0;
        for (Node x: path.getChildren()){
            if (x instanceof Enemy){
                wave2Gobs++;
            }
        }
        wave2Gobs -= wave1Gobs;
        assertEquals(10, wave2Gobs);
        clickOn("Upgrade");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 450);
        clickOn("Start");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
        int wave3Gobs = 0;
        for (Node x: path.getChildren()){
            if (x instanceof Enemy){
                wave3Gobs++;
            }
        }
        wave3Gobs -= wave2Gobs + wave1Gobs;
        assertEquals(16, wave3Gobs);
        try {
            Thread.sleep(20500);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
    }

    @Test
    public void bossDamagesMonument() {
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 200, boundsInScreen.getMinY() + 350);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 250, boundsInScreen.getMinY() + 350);
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 300, boundsInScreen.getMinY() + 350);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 350, boundsInScreen.getMinY() + 350);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 175, boundsInScreen.getMinY() + 450);
        clickOn("Start");
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 225, boundsInScreen.getMinY() + 450);
        clickOn("Squire");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 450);
        clickOn("Start");
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
        verifyThat("Monument Health: 100", NodeMatchers.isNotNull());
        clickOn("Archer");
        clickOn(boundsInScreen.getMinX() + 325, boundsInScreen.getMinY() + 450);
        clickOn("Start");
        try {
            Thread.sleep(21000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
        verifyThat("Monument Health: 0", NodeMatchers.isNotNull());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }
    }
    
    @Test
    public void testUpgradedTowersDoMoreDamage() {
        clickOn("Play");
        write("Alyssa");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        for (Node rect: path.getChildren()) {
            if (rect instanceof game.model.Paladin) {
                assertEquals(((Paladin) rect).getDamage(), 10);
            }
        }

        clickOn("Upgrade");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);

        for (Node rect: path.getChildren()) {
            if (rect instanceof Paladin) {
                assertEquals(((Paladin) rect).getDamage(), 20);
            }
        }
    }

    @Test
    public void testGameLossDefeatedStats() {
        clickOn("Play");
        write("Alyssa");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Start");


        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }

        verifyThat("Goblins Defeated: 0", NodeMatchers.isNotNull());
    }

    @Test
    public void testGameLossTowersStats() {
        clickOn("Play");
        write("Ethan");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Start");


        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }

        verifyThat("Towers Placed: 1", NodeMatchers.isNotNull());
    }

    @Test
    public void testGameLossUpgradeStats() {
        clickOn("Play");
        write("Ethan");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        Bounds boundsInScreen = path.localToScreen(path.getBoundsInLocal());
        clickOn("Paladin");
        clickOn(boundsInScreen.getMinX() + 275, boundsInScreen.getMinY() + 125);
        clickOn("Start");


        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error.");
        }

        verifyThat("Towers Upgraded: 0", NodeMatchers.isNotNull());
    }
}
