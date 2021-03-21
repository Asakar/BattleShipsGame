# BattleShipsGame

This is a one sided battleship game where you have to sink the applications randomly placed ships. These ships will be randomly placed either horizontally or veritically and will not intersect each other. The MVC design pattern is implemented here. Currently there is no GUI.
Everything related to the game is printed to the terminal.

To run the application:
git clone the project.
Run the main method found in the start/App class. 
If you wish to quit the game early enter quit and the game shall print the board with the ships on it and end the application.
The image below shows the application running.

<img src=/images/FirstScreen.png width=80%>

Enter any position into the terminal such as A5 to place your shot.

<img src=/images/shot.png width=30%>

If the shot was a miss here the board will update the position with the letter O.

<img src=/images/miss.png width=30%>

If the shot was a hit, the application will print which ship was hit and the board will update the position with the letter X. 

<img src=/images/ShipHit.png width=30%>

When a ship is sunk, the application will print which ship has been sunk and the board will update the position with the letter X.

<img src=/images/ShipSunk.png width=30%>

If you wish to give up, enter quit into the console. The application will print the positions of the ships and end the application.

<img src=/images/quit.png width=30%>

