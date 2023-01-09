import java.awt.*;

public class GingerbreadMan {

    //VARIABLE DECLARATION SECTION!
    //Here's where you state which variables you are going to use.
    public String name;               //name of the hero
    public int xpos;                  //the x position
    public int ypos;                  //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                 //the width of the hero image
    public int height;                //the height of the hero image
    public int maxWidth = 350;

    public int minWidth = 50;
    public boolean isAlive;           //a boolean to denote if the hero is alive or dead
    public Rectangle rec;
    public Image pic;


    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public GingerbreadMan(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 50;
        height = 50;
        dx = dxParameter;
        dy = dyParameter;
        pic = picParameter;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
    } // end Astronaut constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);

    }
    public void bounce() {
        xpos = xpos +dx;
        ypos = ypos +dy;

        if (xpos >= 1000 - width) {
            xpos = 1000 - width - 1;
            dx = -dx;
        }
        if (xpos <= 0) {

            dx = -dx;
        }
        if (ypos >=700 - height && dy > 0) { // bottom
            dy = -dy;
        }
        if (ypos <= 0 && dy < 0){
            dy = -dy;
        }
        rec = new Rectangle(xpos, ypos, width, height);
    }
}
