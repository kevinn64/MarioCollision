import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
    //Member variables   kate View.java > /dev/null 2>&1
    Model model;
    static Image[] mario_images = null;
    Image background;
    
    //View constructor
    View(Controller c, Model m)
    {
       m.load("map.json"); //LOADS BRICKS AUTOMATICALLY
        if(mario_images == null) //EXAMPLE OF 'LAZYLOADING'
        {
            mario_images = new Image[5];
            model = m;
            try
            {
                mario_images[0] = ImageIO.read(new File("mario1.png"));
                mario_images[1] = ImageIO.read(new File("mario2.png"));
                mario_images[2] = ImageIO.read(new File("mario3.png"));
                mario_images[3] = ImageIO.read(new File("mario4.png"));
                mario_images[4] = ImageIO.read(new File("mario5.png"));
                background = ImageIO.read(new File("background.png"));
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(1);
            }
            }

    }

    //Sets color of program, and and draws pictures
    public void paintComponent(Graphics g)
    {       
        //Clear the screen
        g.setColor(new Color(128, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        //draw background image
        g.drawImage(background,-model.scrollPos/2,0,null);
        
        //Draw the ground
        g.setColor(new Color(15, 200, 64));
        g.drawLine(0, 595, 9000, 595);
        g.fillRect(0, 595, 9000, 595);
        
        //Draws brickz
        for(int i = 0; i < model.bricks.size(); i++)
        {
	        Brick b = model.bricks.get(i);
	        g.drawRect(b.x - model.scrollPos, b.y, b.w, b.h); 
	        g.setColor(new Color(0, 0, 0)); //SET BRICKS TO BLACK
	        g.fillRect(b.x - model.scrollPos, b.y, b.w, b.h);
        }
            //Draw Mario
            int marioFrame = (Math.abs(model.mario.x) / 20) % 5; //20 slows down how fast he runs, 5 is the total # of frames we have to work with
            
            g.drawImage(mario_images[marioFrame], model.mario.x - model.scrollPos, model.mario.y, null);

    }

}
