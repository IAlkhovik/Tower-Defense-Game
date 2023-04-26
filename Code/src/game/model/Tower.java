package game.model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public abstract class Tower extends Rectangle {
    private int xPosition;
    private int yPosition;
    boolean isUpgraded = false;

    public Tower(int x, int y) {
        super(x, y, 50, 50);
        xPosition = x;
        yPosition = y;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setXPosition(int x) {
        xPosition = x;
    }

    public void setYPosition(int y) {
        yPosition = y;
    }

    public abstract void fire(Pane path);
    
    public boolean isUpgraded(){
        return isUpgraded;
    }
    
    public void setUpgraded(boolean bool){
        isUpgraded = bool;
    }
}
