import processing.core.*;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main("App");
    }

    boolean running = true;
    boolean key_Up = false;
    boolean key_Down = false;
    boolean key_Left = false;
    boolean key_Right = false;
    int speed = 5;

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
            movement();
            ellipse(circleX, circleY, 50, 50);
            if (circleX >= 600 || circleX <= 0) {

            }
            fill(210, 180, 140);
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
            if (collisionDetection1()
                    || collisionDetection2() || collisionDetection3() || collisionDetection4()) {
                gameOver();
            }
        }
    }

    public void keyPressed() {
        if (keyCode == RIGHT) {
            key_Right = true;
        }
        if (keyCode == LEFT) {
            key_Left = true;
        }
        if (keyCode == UP) {
            key_Up = true;
        }
        if (keyCode == DOWN) {
            key_Down = true;
        }
        if (keyCode == 32) {
            restart();
        }
    }

    public void keyReleased() {
        if (keyCode == RIGHT) {
            key_Right = false;
        }
        if (keyCode == LEFT) {
            key_Left = false;
        }
        if (keyCode == UP) {
            key_Up = false;
        }
        if (keyCode == DOWN) {
            key_Down = false;
        }
    }

    public void movement() {
        if (key_Left && circleX > 0) {
            circleX -= speed;
        }
        if (key_Right && circleX < 600) {
            circleX += speed;
        }
        if (key_Up && circleY > 0) {
            circleY -= speed;
        }
        if (key_Down && circleY < 800) {
            circleY += speed;
        }
    }

    public boolean collisionDetection1() {

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

    public boolean collisionDetection2() {
        if (circleX > rect2X + 150) {
            return false;
        } else if (circleX + 40 < rect2X) {
            return false;
        } else if (circleY > rect2Y + 50) {
            return false;
        } else if (circleY + 45 < rect2Y) {
            return false;
        } else {
            return true;
        }
    }

    public boolean collisionDetection3() {
        if (circleX > rect3X + 150) {
            return false;
        } else if (circleX + 50 < rect3X) {
            return false;
        } else if (circleY > rect3Y + 50) {
            return false;
        } else if (circleY + 50 < rect3Y) {
            return false;
        } else {
            return true;
        }
    }

    public boolean collisionDetection4() {
        if (circleX > rect4X + 300) {
            return false;
        } else if (circleX + 50 < rect4X) {
            return false;
        } else if (circleY > rect4Y + 50) {
            return false;
        } else if (circleY + 50 < rect3Y) {
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

        fill(139, 0, 0);
        rect(rect1X, rect1Y, 300, 50);
        rect(rect2X, rect2Y, 150, 50);
        rect(rect3X, rect3Y, 150, 50);
        rect(rect4X, rect4Y, 300, 50);
        ellipse(circleX, circleY, 50, 50);

        fill(139, 0, 0);
        textSize(60);
        text("GAME OVER!", 160, 300);

    }

    public void restart() {
        running = true;
    }
}
