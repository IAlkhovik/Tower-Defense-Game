package game.model;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Range extends Circle {

    public Range(int radius) {
        super(radius);
        this.setFill(null);
        this.setStroke(Color.RED);
        this.setStrokeWidth(2.0);
    }

}
