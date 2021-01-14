import java.util.*;
/**
 * Write a description of class Enemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy
{
    // instance variables - replace the example below with your own
    private  ArrayList<String> enemies =new ArrayList<String>();
    private int enemieMaximumEnergy = 100;
    private int enemieMaximumDamage = 25;
    
    
    

    /**
     * Constructor for objects of class Enemy
     */
    public Enemy(int enemiesMaximumEnergy, int enemiesMaximumDamage )
    {
        this.enemieMaximumEnergy = enemieMaximumEnergy;
        this.enemieMaximumDamage = enemieMaximumDamage;
        enemies.add("Spider Monster");
        enemies.add("Sea Monster");
        enemies.add("Hell Monster");
        enemies.add("Devil Monster");
       
        
        
        
        
        
        
        
    }
    // @return enemy health
    public int enemyHealth()
    {
        return enemieMaximumEnergy;
    }
    // @return enemy Maximum damage capacity
    public int enemyStrike()
    {
        return enemieMaximumDamage;
    }
    //@ return monster
    
}
