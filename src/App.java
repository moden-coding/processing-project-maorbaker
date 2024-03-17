import processing.core.*;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main("App");
    }

    boolean running = true;
    //boolean for the whole game to make sure that it can stop running the constant draw loop of the game if collision is detected
    //it begins at true
    boolean key_Up = false;
    boolean key_Down = false;
    boolean key_Left = false;
    boolean key_Right = false;
    //these booleans are for the function of the arrow keys and movement of the ball

    int speed = 5; //speed of ball movement left/right/down/up
    int rect1speed = 5; //set initial speed of rectangle 1
    int rect2speed = 5; //set initial speed of rectangle 2
    int rect3speed = 5; //set initial speed of rectangle 3
    int rect4speed = 5; //set initial speed of rectangle 4

    int GAP = 150; // variable for the gap in between each of the rectangles – must be constant

    float rect1width, rect2width, rect3width, rect4width, rect2X, rect4X;
        //these are global variables that are undefined here
        //we just want to define them as variables because the x-values and width of these rectangles will be randomized so the gap can be randomized.
    
    int rect1X = 0; //set the variable for the initial x-value of rectangle 1
    int rect1Y = 350; //set the variable for the initial y-value of rectangle 1

    int rect2Y = 350; //set the variable for the initial y-value of rectangle 2

    int rect3X = 0; //variable for the initial x-value of rectangle 3
    int rect3Y = 0; //variable for the initial y-value of rectangle 3

    int rect4Y = 0; //variable for the initial y-value of rectable 4

    int circleX = 300; //initial x-value of circle
    int circleY = 700; //initial y-value of circle

    int score = 0; //score begins at 0
    boolean gap1alreadycounted = true;
    boolean gap2alreadycounted = true;
    //these booleans keep track of whether or not a gap has been counted for the score
    //it begins at "true"

    public void settings() {
        size(600, 800);
    }

    public void setup() {
        background(200);
        setRect1and2();
        setRect3and4();
    }

    public void setRect1and2() {
        rect1width = random(400);
        rect2width = 600 - rect1width - GAP;
        rect2X = 600 - rect2width;
        gap1alreadycounted = false;
    } 
    //this method controls the set-up of the first and second rectangles (level 1 of rectangles)
    //we set the width of rectangle 1 to be any x-value between 0 and 400
    //we set the width of the second rectangle based on the randomized value of the first rectangle, the gap length, and the limit of x-values from settings (600)
    //finally, we set the first gap already counted, which is a boolean that begins as true, to false, after a gap has been counted to the score – we want the counted boolean to reset so that when the rectangles reappear at the top of the screen, they go back to having not been counted

    public void setRect3and4() {
        rect3width = random(400);
        rect4width = 600 - rect3width - GAP;
        rect4X = 600 - rect4width;
        gap2alreadycounted = false;
    } 
    //this method works the same way as the set-up for rectangles 1 and 2, only this time for rectangles 3 and 4
    //they are two separate methods just for the ease of visualizing both levels of rectangles

    public void draw() {
        if (running == true) { //we set running = true so that the game's loop runs
            background(0, 0, 255); //background color
            movement(); //insert movement method (explained in that method)
            score(); //insert score method (explained in that method)
            fill(255, 192, 203); //color the ellipse
            ellipse(circleX, circleY, 50, 50); //set the coordinates and size of the ellipse

            textSize(30); //size of score tracker
            fill(255, 255, 255); //white color of score tracker
            text("Score: " + score, 475, 50); //set the coordinates of the score tracker

            fill(255, 255, 51); //set color of rectangles
            rect(rect1X, rect1Y, rect1width, 50); 
            rect(rect2X, rect2Y, rect2width, 50);
            rect(rect3X, rect3Y, rect3width, 50);
            rect(rect4X, rect4Y, rect4width, 50);
            //set coordinates and size of rectangles

            rect1Y += rect1speed;
            rect2Y += rect2speed;
            rect3Y += rect3speed;
            rect4Y += rect4speed;
            //set speed of rectangles' movement down the screen

            if (rect1Y == 800 || rect2Y == 800) {
                rect1Y = 0;
                rect2Y = 0;
                setRect1and2();
            } //makes sure that if the rectangles that are constantly moving down the screen reach the bottom of the screen, they re-set at the top of the screen with randomized gaps
                //that is why we re-insert the method for setting rectangle 1 and 2
            if (rect3Y == 800 || rect4Y == 800) {
                rect3Y = 0;
                rect4Y = 0;
                setRect3and4();
            } //works the same way as the if-statement for rectangles 1 and 2, just for rectangles 3 and 4

            if (collisionDetection1()
                    || collisionDetection2() || collisionDetection3() || collisionDetection4()) {
                gameOver();
            } //if statement ensuring that if the methods for collision are detected, the method for game over runs
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
            //these if statements are for when the keys are pressed
            //when the key is pressed, it sets the boolean for the keys to "true" and ensures the ball moves smoothly – moving constantly until signalled to be "false"

        if (keyCode == 32) {
            restart();
        } //if the space bar is clicked, restarts game with the restart method
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
    //this method is for when a key is released
    //if I do not press one of the arrow keys, the boolean for each key is set to false and the ball stops

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
    //the method for movement sets how fast the ball should move right, left, up, and down
    //the if statements also ensure that the ball can never move outside of the bounds of the 800x600 setup
    //if the key is pressed, then the x- and y-coordinates change according to the variable speed which = 5

    public boolean collisionDetection1() {

        if (circleX > rect1X + rect1width) {
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
    //collision detection for rectangle 1 checks if the coordinates of the circle overlap with the any part of rectangle 1
    // it is set as a boolean method where in all cases it is set to true, but if any collision is detected, it is set to false
    //these methods lead to a game over method in the draw method 

    public boolean collisionDetection2() {
        if (circleX > rect2X + rect2width) {
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
    //works same way as collision for rectangle 1, just a different method for rectangle 2

    public boolean collisionDetection3() {
        if (circleX > rect3X + rect3width) {
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
    //works same way as collision for rectangle 1 and 2, just a different method for rectangle 3

    public boolean collisionDetection4() {
        if (circleX > rect4X + rect4width) {
            return false;
        } else if (circleX + 35 < rect4X) {
            return false;
        } else if (circleY > rect4Y + 50) {
            return false;
        } else if (circleY + 50 < rect3Y) {
            return false;
        } else {
            return true;
        }
    } 
    //works same way as collision for rectangle 1, 2, and 3, just a different method for rectangle 4

    public void score() {
        if (circleY < rect1Y && gap1alreadycounted == false) {
            score++;
            gap1alreadycounted = true;
        }
        if (circleY < rect3Y && gap2alreadycounted == false) {
            score++;
            gap2alreadycounted = true;
        }
    } 
    //this method keeps track of score
    //if the y-coordinates of the ball are less than the y-coordinates of rectangle 1 or 3 (I don't need to also say rectangle 2 or 4 because they are the same y-coordinate so that would be redundant)
    //and if the gaps have not been counted - i.e. the boolean = false
    //then score goes up by 1 (score++)
    //and we change the gap back to being counted (gap = true)

    public void gameOver() { //this is the game over method
        running = false; //first and foremost, the overall running boolean must be set to false to stop the game loop

        background(173, 216, 230); 
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
        //these are just a game over screen with variables for the coordinates of the rectangles and the ball

        fill(139, 0, 0); //color of rectangles
        rect(rect1X, rect1Y, 300, 50);
        rect(rect2X, rect2Y, 150, 50);
        rect(rect3X, rect3Y, 150, 50);
        rect(rect4X, rect4Y, 300, 50);
        ellipse(circleX, circleY, 50, 50);
        //set the coordinates of the rectangles and the ball for the game over screen

        fill(139, 0, 0); //red color of "Game over" display
        textSize(60);
        text("GAME OVER!", 155, 250);
        textSize(35);
        text("Press [space] to restart", 145, 500);
        textSize(35);
        text("Your score was: " + score, 182, 450);
        //set the coordinates and text for game over and displays the score
    }

    public void restart() {
        running = true;
        setRect1and2();
        setRect3and4();
        score = 0;
    } //method for restarting the game – resets the settings of the rectangles
    //sets the game boolean to go back to running
    //resets the score to 0
}
