
package Dots;

import javax.swing.JFrame;
import java.awt.*;
//Главная запускаемая часть игры!
public class Game {

    public static void main(String[] args) {

        JFrame window = new JFrame("Гоняем шары))");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setContentPane(new GamePanel());

        window.pack();
        window.setVisible(true);
    }

}