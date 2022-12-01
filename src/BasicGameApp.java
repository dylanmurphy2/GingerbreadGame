
//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

// new comment

public class BasicGameApp implements Runnable {

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

    public Image backgroundPic;
    public GingerbreadMan ginger;

    public GingerbreadMan mouth;

    public boolean didCrash = false;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        GingerbreadMan = Toolkit.getDefaultToolkit().getImage("GingerbreadMan.png"); //load the picture
        ginger = new GingerbreadMan("ginger",400,400); //construct the astronaut

        mouthPic = Toolkit.getDefaultToolkit().getImage("Mouth.png");
        mouth = new GingerbreadMan("mouth",400,200);

        backgroundPic = Toolkit.getDefaultToolkit().getImage("GingerbreadHouse.png");

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
//        ginger.move();
        ginger.bounce();
//        mouth.move();
        mouth.bounce();
    }

    public void crash() {
       if (ginger.rec.intersects(mouth.rec) && didCrash == false) {
            didCrash = true;
            System.out.println("Crash");
            ginger.width = ginger.width*2;
            ginger.height = ginger.height*2;

        }
        if (ginger.rec.intersects(mouth.rec)) {
            ginger.dx = -ginger.dx;
            mouth.dx = -mouth.dx;
        }
    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

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
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(backgroundPic, 0,0, WIDTH, HEIGHT, null);

        //draw the image of the astronaut
        g.drawImage(GingerbreadMan, ginger.xpos, ginger.ypos, ginger.width, ginger.height, null);


        g.drawImage(mouthPic, mouth.xpos, mouth.ypos, mouth.width, mouth.height, null);

        g.dispose();
        bufferStrategy.show();


    }
        //new Thread(run).start();
}