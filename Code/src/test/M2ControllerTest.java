package test;

import game.controller.Controller;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.base.WindowMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class M2ControllerTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }

    @Test
    public void testNullNameError() {
        clickOn("Play");
        clickOn("Continue");
        verifyThat("Error", NodeMatchers.isNotNull());
    }
    @Test
    public void testEnterValidName() {
        clickOn("Play");
        write("alyssa");
        clickOn("Continue");
    }
    @Test
    public void testPlay() {
        clickOn("Play");
        verifyThat("Player Configuration", NodeMatchers.isNotNull());
    }
    @Test
    public void testQuit() {
        verifyThat(window(0), WindowMatchers.isShowing());
        targetWindow(0);
        clickOn("Quit");
        verifyThat(targetWindow(), WindowMatchers.isNotShowing());
    }
    @Test
    public void testEasyHPandMoney() {
        clickOn("Play");
        write("ivan");
        clickOn("Continue");
        verifyThat("Money: 100", NodeMatchers.isNotNull());
        verifyThat("Monument Health: 100", NodeMatchers.isNotNull());
    }
    @Test
    public void testHardHPandMoney() {
        clickOn("Play");
        write("ivan");
        clickOn("easy");
        clickOn("hard");
        clickOn("Continue");
        verifyThat("Money: 25", NodeMatchers.isNotNull());
        verifyThat("Monument Health: 25", NodeMatchers.isNotNull());
    }
    @Test
    public void testWhitespaceNameError() {
        clickOn("Play");
        write("   ");
        clickOn("Continue");
        verifyThat("Error", NodeMatchers.isNotNull());
    }
    @Test
    public void testRedundantEasyValues() {
        clickOn("Play");
        write("Ethan");
        clickOn("easy");
        clickOn("easy");
        clickOn("Continue");
        verifyThat("Money: 100", NodeMatchers.isNotNull());
        verifyThat("Monument Health: 100", NodeMatchers.isNotNull());
    }
    @Test
    public void testIntermediateHPandMoney() {
        clickOn("Play");
        write("Anna");
        clickOn("easy");
        clickOn("intermediate");
        clickOn("Continue");
        verifyThat("Money: 50", NodeMatchers.isNotNull());
        verifyThat("Monument Health: 50", NodeMatchers.isNotNull());
    }
    @Test
    public void testFirstGameScreen() {
        clickOn("Play");
        write("Anna");
        clickOn("Continue");
        verifyThat(window(0), WindowMatchers.isShowing());
    }
}
