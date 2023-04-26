package game.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class Archer extends Tower {
    private int price;
    private int damage;
    //private int rangeRadius;
    //private Range range;
    //private Pane pane;

    public Archer(int x, int y) {
        super(x, y);
        this.setFill(Color.FIREBRICK);
        price = 15;
        damage = 2;
        //rangeRadius = 75;
        //range = new Range(rangeRadius);
        //range.setCenterX(x + 25);
        //range.setCenterY(y + 25);
        //pane = new Pane();
    }

    //public Pane getPane() {
    //  pane.getChildren().add(this);
    // pane.getChildren().add(range);
    // return pane;
    //}

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int dam) {damage = dam;}

    @Override
    public void fire(Pane path) {
        for (Node gob: path.getChildren()) {
            if  (gob instanceof Enemy && gob.getOpacity() > 0) {
                Enemy goblin = (Enemy) gob;
                if (goblin.contains(this.getXPosition() + 125, this.getYPosition() + 25)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() + 125, this.getYPosition() + 75)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() + 125, this.getYPosition() + 125)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() + 75, this.getYPosition() + 125)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() + 25, this.getYPosition() + 125)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() - 25, this.getYPosition() + 125)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() - 75, this.getYPosition() + 125)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() - 75, this.getYPosition() + 75)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() - 75, this.getYPosition() + 25)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() - 75, this.getYPosition() - 25)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() - 75, this.getYPosition() - 75)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() - 25, this.getYPosition() - 75)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() + 25, this.getYPosition() - 75)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() + 75, this.getYPosition() - 75)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() + 125, this.getYPosition() - 75)) {
                    goblin.takeDamage(damage);
                    return;
                } else if (goblin.contains(this.getXPosition() + 125, this.getYPosition() - 25)) {
                    goblin.takeDamage(damage);
                    return;
                }
            }
        }
    }
}
