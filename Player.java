
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private int energy;
    private int score;
    private int damageDone;
    
    

    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        this.name = name;
        energy = 200;
        score = 0;
        damageDone = 50;
        
       
    }
    
    public void setName(String name)
    {
       this.name = name;
    }
    
    public String returnName()
    {
        return name;
    }
    
    public int returnScore()
    {
     return score;
    }
    
    public int returnHealth()
    {
     return energy;   
    }
    
    public int returnDamageDone()
    {
       return damageDone; 
    }
   
}
