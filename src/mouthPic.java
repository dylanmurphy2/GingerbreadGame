import java.awt.*;

public class mouthPic {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;               //name of the hero
    public int xpos;                  //the x position
    public int ypos;                  //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                 //the width of the hero image
    public int height;                //the height of the hero image
    public boolean isAlive;           //a boolean to denote if the hero is alive or dead
    public Rectangle rec;
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    public Image pic;



    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public mouthPic(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 100;
        height = 100;
        dx = dxParameter;
        dy = dyParameter;
        pic = picParameter;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);

    } // end Astronaut constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() { // move

            if(right){
                xpos = xpos +dx;
                if(xpos>1000-width){
                    xpos = 1000-width;
                }
            }

            if(down){
                ypos = ypos +dy;
                if(ypos>700-height){
                    ypos = 700-height;
                }
            }

            if(left) {
                xpos = xpos - dx;
                if (xpos < 0) {
                    xpos = 0;
                }
            }
            if(up){
                ypos = ypos -dy;
                if(ypos<0){
                    ypos = 0;
                }
        }
        rec = new Rectangle(xpos, ypos, width, height);
    }
}

