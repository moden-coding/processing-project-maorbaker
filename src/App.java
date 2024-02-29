import processing.core.*;

public class App extends PApplet {
    public static void main(String[] args)  {
        PApplet.main("App");
    }

int rect1X = 0;
int rect1Y = 250;
int rect2X = 450;
int rect2Y = 250;

int circleX = 300;
int circleY = 700;

public void settings() {
size(600, 800);
}
public void setup() {
background(200);

}
public void draw() {
background(200);
rect(rect1X, rect1Y, 300, 50);
rect(rect2X, rect2Y, 150, 50);
ellipse(circleX, circleY, 50, 50);
if (circleX >= 600 || circleX <= 0) {

}

rect1Y += 5;
rect2Y += 5;

if (rect1Y == 800) {
    rect1Y = 0;
}
if (rect2Y == 800) {
    rect2Y = 0;
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
  
}

}

