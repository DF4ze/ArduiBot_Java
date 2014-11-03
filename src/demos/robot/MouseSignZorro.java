package demos.robot;

import java.awt.AWTException;
import java.awt.Robot;

/**
 * Déplacer le curseur de la souris sur l'écran
 * Exemple de zorro
 * fobec.com 2010
 */
public class MouseSignZorro {

    public MouseSignZorro() throws AWTException {
        Robot robot = new Robot();
        /**
         * Fixer le delai entre chaque mouvement à 500 ms
         */
        robot.setAutoDelay(50);
        /**
         * Appeler OnIdle après le déplacement de la souris
         */
        robot.setAutoWaitForIdle(false);

        /**
         * Barre du haut
         */
        for (int i = 0; i < 20; i++) {
            robot.mouseMove(300+(20*i), 400);
        }
        /**
         * Diagonale
         */
        for (int i = 0; i < 20; i++) {
            robot.mouseMove(700-(20*i), 400+(20*i));
        }
        /**
         * Barre du bas
         */
        for (int i = 0; i < 20; i++) {
            robot.mouseMove(300+(20*i), 800);
        }

        /**
         * Quitter l'application
         */
        System.exit(0);
    }

    public static void main(String[] args) throws AWTException {
        MouseSignZorro mouseCatchMe = new MouseSignZorro();
    }
}