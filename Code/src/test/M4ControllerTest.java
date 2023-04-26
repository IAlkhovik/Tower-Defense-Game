package test;

import game.controller.Controller;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.base.WindowMatchers;

public class M4ControllerTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
    
    @Test
    public void testEqualDamage() {
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        verifyThat("Monument Health: 100", NodeMatchers.isNotNull());
        clickOn("Start");
        try {
            Thread.sleep(12750);
            verifyThat("Monument Health: 40", NodeMatchers.isNotNull());
            Thread.sleep(3250);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void testHealthDeduction() {
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        verifyThat("Monument Health: 100", NodeMatchers.isNotNull());
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
    public void testEnemiesStartAtBeginning() {
        clickOn("Play");
        write("alyssa");
        clickOn("Continue");
        clickOn("Start");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        boolean fail = true;
        for (Node rect: path.getChildren()) {

            if (fail) {
                if (rect.contains(15, 65)) {
                    fail = false;
                }
            }
        }
        assertEquals(fail, false);
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void testEnemiesMove() {
        clickOn("Play");
        write("alyssa");
        clickOn("Continue");
        clickOn("Start");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        boolean fail = true;
        for (Node rect: path.getChildren()) {

            if (fail) {
                if (rect.contains(15, 65)) {
                    try {
                        Thread.sleep(750);
                        rect.contains(65, 65);
                        fail = false;
                    } catch (InterruptedException e) {
                        System.out.println("Wait Error");
                    }
                }
            }
        }
        assertEquals(fail, false);
        try {
            Thread.sleep(15250);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void testGameOver() {
        clickOn("Play");
        write("Kathy");
        clickOn("Continue");
        clickOn("Start");
        try {
            Thread.sleep(14500);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
        verifyThat("Game Over", NodeMatchers.isNotNull());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }

    @Test
    public void testNoGameOver() {
        clickOn("Play");
        write("Kathy");
        clickOn("Continue");
        targetWindow(0);
        clickOn("Start");
        try {
            Thread.sleep(12250);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
        verifyThat(targetWindow(), WindowMatchers.isShowing());
        try {
            Thread.sleep(3750);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }
    
    @Test
    public void testRestart() {
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        clickOn("Start");
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
        clickOn("Restart");
        verifyThat("Knights of Scheller", NodeMatchers.isNotNull());
    }
    
    @Test
    public void testExit() {
        targetWindow(0);
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        clickOn("Start");
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
        clickOn("Exit");
        verifyThat(targetWindow(), WindowMatchers.isNotShowing());
    }
    
    @Test
    public void startButtonTest() {
        clickOn("Play");
        write("Ethan");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        int count = 0;
        clickOn("Start");
        for (Node rect: path.getChildren()) {
            if (rect.contains(25, 75)) {
                count++;
            }
        }
        assertEquals(6, count);
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }
    
    @Test
    public void noEnemiesUntilStartTest() {
        clickOn("Play");
        write("Ethan");
        clickOn("Continue");
        BorderPane root = (BorderPane) rootNode(window(0));
        Pane path = (Pane) root.getCenter();
        int count = 0;
        for (Node rect: path.getChildren()) {
            if (rect.contains(25, 75)) {
                count++;
            }
        }
        assertEquals(1, count);
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            System.out.println("Wait Error");
        }
    }
}
