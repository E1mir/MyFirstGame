package Rally;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public static final int MAX_V = 100;
    public static final int MAX_TOP = 60;
    public static final int MAX_BOTTOM = 485;
    Image img_c = new ImageIcon("res/player.png").getImage();
    Image img_l = new ImageIcon("res/Player_Left.png").getImage();
    Image img_r = new ImageIcon("res/Player_Right.png").getImage();
    Image img = img_c;

    public Rectangle getRect() {
        return new Rectangle(x, y, 160, 56);
    }

    // начальная скорость
    int v = 0;
    //ускорение
    int dv = 0;
    //путь
    int s = 0;
    //координаты
    int x = 30;
    int y = 100;
    int dy = 0;
    //слои для вида движения)
    int layer1 = 0;
    int layer2 = 1200;

    public void move() {
        //путь увеличивается
        s += v;
        //ускорение
        v += dv;
        if (v <= 0) {
            v = 0;
        }
        if (v >= MAX_V) {
            v = MAX_V;
        }
        y -= dy;
        if (y <= MAX_TOP) {
            y = MAX_TOP;
        }
        if (y >= MAX_BOTTOM) {
            y = MAX_BOTTOM;
        }
        //Делаем бесконечную дорогу)
        if (layer2 - v <= 0) {
            layer1 = 0;
            layer2 = 1200;
        } else {
            layer1 -= v;
            layer2 -= v;
        }
    }

    //методы для реагирования нажатия
    public void keyPressed(KeyEvent e) {
        //JOptionPane.showMessageDialog(null, "key pressed");
        //реализовываем
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            dv = 3;
        }
        if (key == KeyEvent.VK_LEFT) {
            dv = -3;
        }
        if (key == KeyEvent.VK_UP) {
            img = img_l;
            dy = 10;
        }
        if (key == KeyEvent.VK_DOWN) {
            img = img_r;
            dy = -10;
        }
    }
    public void keyReleased(KeyEvent e) {
        // JOptionPane.showMessageDialog(null, "key released");
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dv = 0;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP) {
            dy = 0;
            img = img_c;
        }

    }

}



