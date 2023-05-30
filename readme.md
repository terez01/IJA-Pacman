## IJA2022/23 - Design and implementation of a Pac-Man type application in Java
Authors: Tereza Magerková, Tomáš Husár

After the project is succesfully built, user can choose which map to play by clicking **Maps** button in the top menu of the application and clicking the desired map (1,2,3).

After the map selection (or when the first loaded map was not changed), the game is started by clicking the **Game** button and choosing **Play** mode.

Game is controlled by arrow keys (`up, down, left, right`). The arrows choose the direction of the Pac-Man.
Pac-Man starts with 3 lives, each one depleting with a ghost collision. When Pac-Man reaches 0 lives, the game is lost. 
Game is won by collecting a **key** and stepping on the **treasure** (target) before Pac-Man loses all his lives.
If the game was won, the user can see how many steps it took him in the leaderboard (and he can also try to beat his previous record by playing again).

After the game (succesful or not), user can replay the map by pressing `SPACE` key, 
or view the course of that game by clicking the **Game** button and choosing **Playback** mode.
In Playback mode, user controls the course of the replayed game with 4 buttons on the bottom of the window:

 - Start - sets the view to the first step of the game
 - End - sets the view to the last step of the game
 - Next - sets the view to the next step of the game (if there is one)
 - Previous - sets the view to the previous step of the game (if there is one)

After the Playback, the user can again choose the desired map and play it by changing the mode back to Play.
