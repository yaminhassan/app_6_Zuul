import java.util.*;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Derek and Andrei
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    
    private Random rendom= new Random();
    private Player player;
    private Map map;
    Room jungle, theater, pub, lab, office,cinema, gym, library, dungeon;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        
        createRooms();
        play();
    }
    
    public Player choosePlayer(String name)
    {
       player.setName(name);
       return player;
    }
    
    public void createRooms()
    {
         
        

        // create the rooms
        jungle = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        cinema = new Room( "in a Cinema");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin of fice");
        gym = new Room("in a Gym ");
        library = new Room("In the Library");
        dungeon = new Room( "dangerous cave");

        // initialise room exits
        jungle.setExit("east", theater);
        jungle.setExit("south", lab);
        jungle.setExit("west", pub);
        jungle.setExit("north", cinema);
        
        theater.setExit("south", office);
        theater.setExit("north", dungeon);
        theater.setExit("west", jungle);
        
        
        dungeon.setExit("south" , theater);
        dungeon.setExit("west" , cinema);

        

        pub.setExit("east", jungle);

        lab.setExit("north", jungle);
        lab.setExit("east", office);
        lab.setExit("west" , gym);
        gym.setExit("north" , pub);
        pub.setExit("north", library);
        library.setExit("east" , cinema);
        cinema.setExit("east" , dungeon);
        
        

        office.setExit("west", lab);

        currentRoom = jungle; 
        inventory.add(new Item("Rose"));
        currentRoom.playerName();
        
    }

    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
       
        
        while (! finished) 
        {
            Command command = parser.getCommand();
            
            
            finished = processCommand(command);
              
        }
        
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Create all the rooms and link their exits together.
     */
    

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) 
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
            case ITEM:
               printItem();
            case RUN:
                 goRoom(command);
                
        }
        return wantToQuit;
    }
    
    public void printItem()
    {
      String output="";
      for(int i=0; i < inventory.size(); i++)
        {   
          output += inventory.get(i).getDescription();
        }
        System.out.println("\n You are carring: " + output); 
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            
            System.out.println("Go where?");
            parser.getCommand();
            
            
            
            
            
            
            
        }
        
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            
            
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
