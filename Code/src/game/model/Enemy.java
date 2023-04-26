package game.model;

import javafx.scene.shape.Rectangle;

public class Enemy extends Rectangle {
    private int x;
    private int y;
    private int moveCount = 0;
    private int health;
    private int maxHealth;
    private int damage;
    private boolean hasPaid = false;

    public Enemy(int health, int damage) { //starts at beginning of path
        super(15, 65, 20, 20);
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
    }

    public Enemy(int health, int damage, int size) { // can specify size
        super(5, 55, size, size);
        this.health = health;
        this.damage = damage;
    }

    public int getXCoor() {
        return x;
    }

    public int getYCoor() {
        return y;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void takeDamage(int towerDamage) {
        health = health - towerDamage;
        updateOpacity();
    }
    
    private void updateOpacity() {
        if (health <= 0) {
            this.setOpacity(0);
        } else if (((double) health / maxHealth) > 0 && ((double) health / maxHealth) <= 0.4) {
            this.setOpacity(0.4);
        } else if (((double) health / maxHealth) > 0.33 && ((double) health / maxHealth) <= 0.7) {
            this.setOpacity(0.7);
        } else {
            this.setOpacity(1);
        }
    }
    
    public int getHealth() {
        return health;
    }
    
    public void moveRight() {
        this.setX(this.getX() + 50);
    }
    
    public void moveDown() {
        this.setY(this.getY() + 50);
    }
    
    public void moveLeft() {
        this.setX(this.getX() - 50);
    }
    
    public void moveUp() {
        this.setY(this.getY() - 50);
    }
    
    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moves) {moveCount = moves;}
    
    public void incrementMoveCount() {
        moveCount++;
    }
    
    public boolean getPay() {
        return hasPaid;
    }
    
    public void pays() {
        hasPaid = true;
    }
    
    public void move() {
        if (this.getMoveCount() < 6) {
            this.incrementMoveCount();
            this.moveRight();
        } else if (this.getMoveCount() >= 6 && this.getMoveCount() < 9) {
            this.incrementMoveCount();
            this.moveDown();
        } else if (this.getMoveCount() >= 9 && this.getMoveCount() < 12) {
            this.incrementMoveCount();
            this.moveLeft();
        } else if (this.getMoveCount() >= 12 && this.getMoveCount() < 16) {
            this.incrementMoveCount();
            this.moveDown();
        } else if (this.getMoveCount() >= 16 && this.getMoveCount() < 21) {
            this.incrementMoveCount();
            this.moveRight();
        } else if (this.getMoveCount() >= 21 && this.getMoveCount() < 22) {
            this.incrementMoveCount();
            this.moveUp();
        } else if (this.getMoveCount() >= 22 && this.getMoveCount() < 23) {
            this.incrementMoveCount();
            this.moveRight();
        } else if (this.getMoveCount() == 23) {
            this.incrementMoveCount();
            this.moveRight();
        } else {
            this.incrementMoveCount();
            this.moveRight();
        }
    }
}
