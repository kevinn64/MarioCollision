import java.util.ArrayList;

class Model
{
    //Member variables
    int scrollPos;
    ArrayList<Brick> bricks;
    Mario mario;
    	
    //Constructor
    Model()
    {
    //LOAD BRICKS INITIALLY
        mario = new Mario(this);
        bricks = new ArrayList<Brick>();
    }

    public void update() //indirectly calls mario.update from model.update
    {		
        mario.update();
    }
    
    public void LastLocation()
    {
        mario.LastLocation();
    }

    //Add brick method
    void addBrick(int x, int y, int w, int h)
    {
        Brick b = new Brick(x, y, w, h);
        bricks.add(b);
    }

    //Clears arraylist of bricks & unmarshalls the json bricks
    void unmarshall(Json ob)
    {
        bricks.clear();
        Json json_bricks = ob.get("bricks");
        for(int i = 0; i < json_bricks.size(); i++)
        {
            Json j = json_bricks.get(i);
            Brick b = new Brick(j);
            bricks.add(b);
        }
    }           

    //Marshalls brick objects into Json objects into a json brick list
    Json marshall()
    {
        Json ob = Json.newObject();
        Json json_bricks = Json.newList();
        ob.add("bricks", json_bricks);
        for(int i = 0; i < bricks.size(); i++)
        {
            Brick b = bricks.get(i);
            Json j = b.marshall();
            json_bricks.add(j);
        }
        return ob;
    }

    //Save method for map
    void save(String filename)
    {
        Json ob = marshall();
        ob.save(filename);
        System.out.println("MAP SAVED");
    }
    
    //Load method for map   
    void load(String filename)
    {
        Json ob = Json.load("map.json");
        unmarshall(ob);
        System.out.println("MAP LOADED");
    }

}
