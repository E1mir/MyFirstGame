package Rally;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class Road extends JPanel implements ActionListener, Runnable {
    //типа едет
    Timer mainTimer = new Timer(20, this);

    //вызываем изображение дороги
    Image img = new ImageIcon("res/doroga.png").getImage();

    Player p = new Player();

    Thread enemiesFactory = new Thread(this);
    Thread audioThread = new Thread(new Audio());
    List<Enemy> enemies = new ArrayList<Enemy>();

    //таймер для обрисовки дороги
    public Road() {
        mainTimer.start();
        enemiesFactory.start();
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    //Добавление соперника
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200, rand.nextInt(600), rand.nextInt(60), this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Обработчик нажатия
    //если нажали
    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);

        }

        //если отпустили
        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }
    }

    //метод рисунка
    public void paint(Graphics g) {
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
        g.drawImage(p.img, p.x, p.y, null);
        double v = (200 / Player.MAX_V) * p.v;
        g.setColor(Color.red);
        Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Скорость: " + v + " км/ч", 100, 30);
        g.drawString("Рекорд: " + p.s / 200 + "км", 700, 30);
        //итерационный цикл для добавления соперников
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                e.move();
                g.drawImage(e.img, e.x, e.y, null);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        crashtest();
        testWin();
    }
    private void crashtest() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())) {
                JOptionPane.showMessageDialog(null, "Вы проиграли!");
                System.exit(1);
            }
        }
    }
    private void testWin() {
        if (p.s > 200000) {
            JOptionPane.showMessageDialog(null, "Ну ты и задроот))");
            System.exit(0);
        }
    }

}
