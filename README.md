pd3-Stuyrim-JoeyTong-JackyLo
============================

**Note: Java 8 required.**

Final Project

Group Members: Joey Tong, Justin Kim, Jacky Lo (Pd 3),  Sean Yip (Pd 2)

Project Name: The Senior Scrolls I: Stuyrim

Project Description: 2.5D RPG game


  
  TEST CODE IS ALL IN SAMPLE IMPLEMENT YOUR CODE BITS INTO SAMPLE CODE
  
  Textures and images get dumped in images directory 
  
  Feature log: 
  
  6/6/14 
  
  Joey:
  
  incomplete GUI and screen, will complete in near future
  
  Monster and Player classes near completion, awaiting implementation in test code
  
  Inventory code completed, sort code should be working, awaiting creation of GUI that uses Inventory code (renders into a panel) 
  
  Map creation started, tile classes created, awaiting implementation in screen to render map 
  
  **Note for all textures and sprites, create placeholders I (joey) will later replace them with proper images.**
  
  Awaiting proper implementation of drawing player and monsters to screen. 
  
  Sean, you need to create arraylists of Players and monsters and loop paint each one in the list
  
  **6/4/14**
  
  Control fluidity improvied, still not correct. All coordinate logic should be done externally, preferably in still unstarted game engine
  
  **Massive frame jitter problem (white frames) Critically important that this is fixed**
  
  Justin currently recoding GUI to have better structure
  
  Game engine (combat logic etc.) yet to be started. Need to start
  
  
  Implementation of Storyline yet to be started (NPCs, Dialogue) to be added to GUI in "Dialogue.java" JPanel class
  
  MenuPanel "Options" button still dead weight, don't forget to make this actually have function when more features are implemented. Things like window resize may be useful
  
  **CRITICAL Item class and inventory sort makes use of item types "head, body, legs, hands, feet, shield, weapon, consumables etc. all of these item types must be written **
  
  JTextArea "PlayerData" added. In the future will hold all player data, and party member data if that is implemented
  
  got run() method to start the actual game ticks in the Screen class. Game is now refreshing at a rate of around 120 frames per second
  
  
