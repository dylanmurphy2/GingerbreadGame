
//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BasicGameApp implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;
    public Image GingerbreadMan;
    public Image mouthPic;
    public Image mouthLeftPic;
    public Image backgroundPic;
    public GingerbreadMan ginger;
    public mouthPic mouth;
    public boolean didCrash = false;
    public SoundFile DooohSound;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();
        //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();


        //variable and objects
        //create (construct) the objects needed for the game and load up
        canvas.addKeyListener(this);

        DooohSound = new SoundFile("DooohSound.wav");
        GingerbreadMan = Toolkit.getDefaultToolkit().getImage("GingerbreadMan.png"); //load the picture
        ginger = new GingerbreadMan(200, 300, 4, 4, GingerbreadMan);
        mouthPic = Toolkit.getDefaultToolkit().getImage("Mouth.png");
        mouth = new mouthPic(400, 300, 3, -4, mouthPic);
        mouthLeftPic = Toolkit.getDefaultToolkit().getImage("Mouth copy.png");
        mouth = new mouthPic(250, 250, 5, 5, mouthPic);
        backgroundPic = Toolkit.getDefaultToolkit().getImage("GingerbreadHouse.png");
        DooohSound = new SoundFile("DooohSound.wav");
    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            crash();
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
            //new Thread(run).start();
        }
    }

    public void moveThings() {
        //calls the move( ) code in the objects
        ginger.move();
        ginger.bounce();
        mouth.move();
    }

    public void crash() {
        if (ginger.rec.intersects(mouth.rec) && didCrash == false) {
            didCrash = true;
            System.out.println("Crash");
            ginger.dx = -ginger.dx;
            mouth.dx = -mouth.dx;
            DooohSound.play();

            if (ginger.width > ginger.minWidth) {
                ginger.width = ginger.width / 2;
                ginger.height = ginger.height / 2;

            }

        }
        if (!ginger.rec.intersects(mouth.rec)) {
            didCrash = false;
        }
    }

    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 65) { //D
            mouth.right = true;
        }
        if (keyCode == 83) { //S
            mouth.down = true;
        }
        if (keyCode == 68) {
            mouth.left = true;
        }
        if (keyCode == 87) {
            mouth.up = true;
        }
    }

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 65) {
            mouth.right = false;
        }
        if (keyCode == 83) {
            mouth.down = false;
        }
        if (keyCode == 68) {
            mouth.left = false;
        }
        if (keyCode == 87) {
            mouth.up = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
    }
        //Pauses or sleeps the computer for the amount specified in milliseconds


        //Graphics setup method
        public void setUpGraphics () {
            frame = new JFrame("BasicGameApp");   //Create the program window or frame.  Names it.
            panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
            panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
            panel.setLayout(null);   //set the layout

            // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
            // and trap input events (Mouse and Keyboard events)
            canvas = new Canvas();
            canvas.setBounds(0, 0, WIDTH, HEIGHT);
            canvas.setIgnoreRepaint(true);

            panel.add(canvas);  // adds the canvas to the panel.

            // frame operations
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
            frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
            frame.setResizable(false);   //makes it so the frame cannot be resized
            frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

            // sets up things so the screen displays images nicely.
            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
            canvas.requestFocus();
            System.out.println("DONE graphic setup");

        }

        //Paints things on the screen using bufferStrategy
        public void render () {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);

            g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);

            if (mouth.dx > 0) {
                g.drawImage(mouthPic, mouth.xpos, mouth.ypos, mouth.width, mouth.height, null);
            } else {
                g.drawImage(mouthLeftPic, mouth.xpos, mouth.ypos, mouth.width, mouth.height, null);
            }
            g.drawRect(ginger.rec.x, ginger.rec.y, ginger.rec.width, ginger.rec.height);
            //draw the image of the astronaut
            g.drawImage(GingerbreadMan, ginger.xpos, ginger.ypos, ginger.width, ginger.height, null);
            g.drawRect(mouth.rec.x, mouth.rec.y, mouth.rec.width, mouth.rec.height);

//        g.drawImage(mouthPic, mouth.xpos, mouth.ypos, mouth.width, mouth.height, null);

            g.dispose();
            bufferStrategy.show();

        }

    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }
}
    //new Thread(run).start();