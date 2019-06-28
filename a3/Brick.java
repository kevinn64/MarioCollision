public class Brick
{
    //Member variablesS
    int x;
    int y;
    int w;
    int h;

    //Constructor
    Brick(int xx, int yy, int ww, int hh)
    {
        x = xx;
        y = yy;
        w = ww;
        h = hh;
    }

    //Unmarshalling constructor
    Brick(Json ob)
    {   
        x = (int)ob.getLong("x");  
        y = (int)ob.getLong("y");        
        w = (int)ob.getLong("w");        
        h = (int)ob.getLong("h");              
    }

    //Marshall brick values
    Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }

}
