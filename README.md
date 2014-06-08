pd3-Stuyrim-JoeyTong-JackyLo


**Note: Java 8 required.**

Final Project

Group Members: Joey Tong, Justin Kim, Jacky Lo (Pd 3),  Sean Yip (Pd 2)

Project Name: The Senior Scrolls I: Stuyrim

Project Description: 2.5D RPG game


  
  TEST CODE IS ALL IN SAMPLE IMPLEMENT YOUR CODE BITS INTO SAMPLE CODE
  
  Textures and images get dumped in images directory 
  
  Feature log: 
  
  **6/8/14**
  
  So much stuff added I can't even begin to describe it all, but I will (Joey)
  
  Player movement animations fully implemented, only issue is minor frame skips upon first moving in the process of drawing all the animations (Joey)
  
  subsets of animation update:
    
  -added up, down, left right, upanimated, downanimated, leftanimated rightanimated image states for Player (Joey)
  
  -Changed drawn image to an image rather than bufferedimage because of gif compatability issues with bufferedimage (Joey)
      
  -Created animation stops in screen class, sets player to idled directional image when movement input stops (Joey)
      
  -Optimized Animations by writing in conditional within setImage() methods to prevent excessive changes to the image variable (Joey) 
      
        
  Fixed the non transparency issue with inventory panel (Joey)
  
  Fixed various inconsistencies with variables in the Screen class (Joey)
  
  Created character loops that act on a specified character in the characters arraylist rather than a specific character(Joey) 
  
  Fixed various GUI sizing issues GUI looks much cleaner now (Joey) 
  
  Fixed unaccessable components issue in the GamePanel, they were instanced in the constructor (Joey)
  
  Made the PlayerData TextField loop through all players in the character arraylist and display their information real-time (Joey)
  
  Made various control failsafes to prevent bugs when interacting with GUI (Joey)
  
  Made InventoryPanel nullify all active keyboard input before accessing the Inventory to prevent continued movement and animations (Joey) 
  
  **6/7/14**
  
  Implemented monster spawning and made them lose interest to the player after a distance (Jacky)
  
  Created some basic animations and idle sprites for swordsman. (Joey)
  
  Implemented directional movement corresponding to respective sprite image (sprite changes base on direction) (Joey)
  
  **6/6/14**

  Creation of Inventory JPanel (Sean)
  
  Implementation if items into Inventory, etc. (Sean)
    
  Implementation of Inventory panel into Screen, bringing up inventory upon pressing inventory button (Joey)
  
  Creation of updateInventory method to update inventory upon pressing inventory button (Joey)
  

  *inventory sort still needs to be implemented, could be done engine side or panel side*
  
  More updates to Monster, player unit (Jacky)
  
  Implementation of gold to players (Jacky)
  
  Creation of special Attack method, working out attack animations, collision detection etc (Jacky)
  
  Further work on migrating game engine logic to GameEngine (Justin)
  
  
  Creation of PartyPanel and implementation into GamePanel (Joey) 
  
  Edited the Visual look of the UI (Joey)
  
  *inventory sort still needs to be implemented, could be done engine side or panel side*
  
  More updates to Monster, player unit (Jacky)
  
  Creation of rudimentary AI, monsters now follow the player on the screen (Jacky)
  
  Implementation of gold to players (Jacky)
  
  Creation of special Attack method, working out attack animations, collision detection etc (Jacky)
  
  Made characters follow player in Gamepanel (Jacky)
  
  Further work on migrating game engine logic to GameEngine (Justin)
  
  GameEngine now functional.
  
  **6/5/14**
  
  Begin migration of test code into Game Loop class (Justin)
  
  tile map rendering fully implemented (Justin)
  
  Screen flicker failsafe implemented (paint black screen) (Joey)
  
  **6/4/14**

  *Justin is getting runtime errors, we need to figure out why and fix it ASAP*  Fixed
  
  Controls fixed
  
  Screen Frame refresh issue fixed (Joey)
  
  GUI expanded (Joey, Sean)
  
  FPS counter fixed and fully implemented, game now runs at very constant 125 FPS (Joey, Justin)
  
  Screen made into inner class of GamePanel for easy access to GamePanel button input (Joey)
  
  Map rendering almost implemented  (Justin)
  
  Item implementation in players in progress (Jacky)
  
  *Game loop to be started ASAP*
    
  Map creation to be started
  
  Ability trees, ability/item hotbar to be started
  
  GUI skeleton nearly complete, begin testing of all game loop-related code
  
  **6/3/14**
  
  Control fluidity improvied, still not correct. All coordinate logic should be done externally, preferably in still unstarted game engine (Sean)
  
  *Massive frame jitter problem (white frames) Critically important that this is fixed*
  
  Justin currently recoding GUI to have better structure **Aborted** 
  
  Game engine (combat logic etc.) yet to be started. Need to start
  
  
  Implementation of Storyline yet to be started (NPCs, Dialogue) to be added to GUI in "Dialogue.java" JPanel class
  
  MenuPanel "Options" button still dead weight, don't forget to make this actually have function when more features are implemented. Things like window resize may be useful
  
  *CRITICAL Item class and inventory sort makes use of item types "head, body, legs, hands, feet, shield, weapon, consumables etc. all of these item types must be written*
  
  JTextArea "PlayerData" added. In the future will hold all player data, and party member data if that is implemented
  
  got run() method to start the actual game ticks in the Screen class. Game is now refreshing at a rate of around 120 frames per second
  
  currently working on the player classes, specifically the special attacks (Jacky)
  
  Added more variables in the Unit class to help the game engine (hopefully)
  
  Monster classes needed some tweaks, and monsters in the volcano level need coding 
  
  Boss for the volcano level is TBD. Maybe the volcano level will be the last level. 
  
  **6/2/14**
 
  Joey:
  
  incomplete GUI and screen, will complete in near future (Joey)
  
  Monster and Player classes near completion, awaiting implementation in test code (Jacky)
  
  Inventory code completed, sort code should be working, awaiting creation of GUI that uses Inventory code (renders into a panel) (Justin)
  
  Map creation started, tile classes created, awaiting implementation in screen to render map (Justin)
  
  *Note for all textures and sprites, create placeholders I (joey) will later replace them with proper images.*
  
  Awaiting proper implementation of drawing player and monsters to screen. 
  
  Sean, you need to create arraylists of Players and monsters and loop paint each one in the list
  

  
