import processing.core.*;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main("App");
    }

    boolean running = true;
    int rect1X = 0;
    int rect1Y = 350;
    int rect2X = 450;
    int rect2Y = 350;
    int rect3X = 0;
    int rect3Y = 0;
    int rect4X = 300;
    int rect4Y = 0;

    int circleX = 300;
    int circleY = 700;

    public void settings() {
        size(600, 800);
    }

    public void setup() {
        background(200);

    }

    public void draw() {
        if (running == true) {
            background(200);
            ellipse(circleX, circleY, 50, 50);
            if (circleX >= 600 || circleX <= 0) {

            }
            rect(rect1X, rect1Y, 300, 50);
            rect(rect2X, rect2Y, 150, 50);
            rect(rect3X, rect3Y, 150, 50);
            rect(rect4X, rect4Y, 300, 50);

            rect1Y += 5;
            rect2Y += 5;
            rect3Y += 5;
            rect4Y += 5;

            if (rect1Y == 800) {
                rect1Y = 0;
            }
            if (rect2Y == 800) {
                rect2Y = 0;
            }
            if (rect3Y == 800) {
                rect3Y = 0;
            }
            if (rect4Y == 800) {
                rect4Y = 0;
            }
            if(collisionDetection()){
                gameOver();
            }
        }
    }

    public void keyPressed() {
        if (keyCode == RIGHT) {
            circleX += 10;
        }
        if (keyCode == LEFT && circleX <= 600) {
            circleX -= 10;
        }
        if (keyCode == UP) {
            circleY -= 10;
        }
        if (keyCode == DOWN) {
            circleY += 10;
        }
        if (keyCode == 32) {
            restart();
        }
    }

    public boolean collisionDetection() {

        if (circleX > rect1X + 300) {
            return false;
        } else if (circleX + 50 < rect1X) {
            return false;
        } else if (circleY > rect1Y + 50) {
            return false;
        } else if (circleY + 50 < rect1Y) {
            return false;
        } else {
            return true;
        }

    }

    public void gameOver() {
        running = false;
        background(200);
        rect1X = 0;
        rect1Y = 350;
        rect2X = 450;
        rect2Y = 350;
        rect3X = 0;
        rect3Y = 0;
        rect4X = 300;
        rect4Y = 0;

        circleX = 300;
        circleY = 700;

        rect(rect1X, rect1Y, 300, 50);
        rect(rect2X, rect2Y, 150, 50);
        rect(rect3X, rect3Y, 150, 50);
        rect(rect4X, rect4Y, 300, 50);
        ellipse(circleX, circleY, 50, 50);
        fill(255, 192, 192);

        textSize(60);
        text("GAME OVER!", 175, 400);

    }

    public void restart() {
        running = true;
    }
}
