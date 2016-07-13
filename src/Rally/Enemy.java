package Rally;

import javax.swing.*;
import java.awt.*;


public class Enemy {
    public static final int MAX_TOP = 60;
    public static final int MAX_BOTTOM = 485;
    int x;
    int y;
    int v;
    Image img = new ImageIcon("res/enemy.png").getImage();
    Road road;

    public Rectangle getRect() {
        return new Rectangle(x, y, 138, 62);
    }
    public Enemy(int x, int y, int v, Road road) {
        this.x = x;
        this.y = y;
        this.v = v;
        if (this.y<=MAX_TOP){
            this.y=MAX_TOP;
        }
        if (this.y>=MAX_BOTTOM){
            this.y=MAX_BOTTOM;
        }
        this.road = road;
    }

    public void move() {
        x = x - road.p.v + v;
    }
}
