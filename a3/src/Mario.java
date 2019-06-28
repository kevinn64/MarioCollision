import java.util.Iterator;

class Mario
{
    //Where Mario was
    int prev_x;
    int prev_y;

    int x;
    int y;
    int w = 60;
    int h = 95;
    double vert_vel;
    Model model;
    
   int frames_since_last_on_ground;
    
    Mario(Model m)
    {
       
        model = m;
        int frames_since_last_on_ground = 0;
    
    }
    
    void LastLocation()
    {
        prev_x = x;
        prev_y = y;
    }
    
    boolean doesCollide(int _x, int _y, int _w, int _h)
    {
        if(x + w <= _x)
            return false;
        else if(x >= _x + _w)
            return false;
        else if(y + h <= _y)
            return false;
        else if( y >= _y + _h)
          return false;
        else

            return true;
    }
    
    void getOut(Brick b)
    {
        if(x + w > b.x && prev_x + w <= b.x) //comes in from left side
        {
           x = b.x - w;
        }
        else if(x < b.x + b.w && prev_x >= b.x+b.w) //comes in from right side
        {
            x = b.x + b.w;
        }
        if(y + h > b.y && prev_y + h <= b.y) //comes in from above
        {
            y = b.y - h;
            vert_vel = 0;
           frames_since_last_on_ground = 0; //reset frames so user can jump while on a brick
        }
        else if( y < b.y + b.h && prev_y >= b.y + b.h) //comes in from below
        {
            y = b.y + b.h;
            vert_vel = 0;
        }
    }
    
    void update()
    {
        model.scrollPos = x - 200;
        vert_vel += 3.14159;
        y += vert_vel;
        
        //Stop when Mario hits ground.
        if(y > 500)
        {
            vert_vel = 0.0;
            y = 500;
            frames_since_last_on_ground = 0;

        }
        
        //Is Mario colliding?
        Iterator<Brick> it = model.bricks.iterator();
        while(it.hasNext())
        {
           Brick b = it.next();
       // }
        
        //for(int i = 0; i < model.bricks.size(); i++)  //ITERATOR DOES THE SAME THING AS commented FOR LOOP
        //{
          //  Brick b = model.bricks.get(i);
            if(doesCollide(b.x, b.y, b.w, b.h))
            {
                getOut(b);
                if(doesCollide(b.x,b.y,b.w,b.h))        //self debugging code   //check if it collides again just to be sure
                    System.out.println("something wrong w collision bro");
             //   System.out.println("was at: " + Integer.toString(prev_x));
            }
           // else
                //WHILE NOT COLLIDING CONDITION System.out.println("");
        }
        
        frames_since_last_on_ground++; //increment frames
    }
    
}