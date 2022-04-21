# Brick-Breaker


## Brief description of the game

The aim of Brick Breaker also referred to as Breakout Ball is to destroy all the bricks in the level and keep the ball in play using the paddle. Hitting the bricks can trigger powerups, for example, increasing the size of the paddle.

This project written in Java mainly utilising the awt and swing libraries. javax.swing.Timer evolves the game through time, java.awt.Rectangle is used to handle collision events and java.awt.Graphics is used to paint the graphics to the screen. 

## How to run and edit on your machine using Visual Studio Code

Install Visual Studio Code:

Download from the website and follow the steps in the graphical installer.
Install the Java Development Kit.

(Optional but recommended) Install the extensions pack which contains:
•	Language support for Java by Redhat
•	Intellicode.

Download the files to your computer:

Option 1: Download the  zip file. Click open to open the project folder.

Option 2: Click clone git repository.

Run within Visual Studio Code:

Click on one of the classes (any file with a .java extension) and the click the run java button. This should give the option to run the main function located in Frame.java (make sure your OS allows VSstudio access to run the program).

## How to run using the command line

Check whether you have java installed:

```
java -–version
```

If not then install it:

```
sudo apt-get install openjdk-7-jdk
```

## Main features

There are 8 different levels to play each with a different map. The map can either be straight where bricks are arranged in a grid or staggered where the bricks are arranged in a brick wall.


<p align="center">
<img src="https://github.com/asmithfrommany/Brick-Breaker/blob/main/ImagesForGitHub/straight.png" width="300" alt="straight map"> <img src="https://github.com/asmithfrommany/Brick-Breaker/blob/main/ImagesForGitHub/staggered.png" width="300" alt = "staggered map"> 
</p>

Each brick has can have a different powerup and a different color. The powerups implemented so far are:

* Speed up and slow down the ball
* Increase and decrease paddle size
* Space invaders	(all bricks in the map move down towards the paddle)
* Moving bricks		(bricks move sideways upon collision)
* New ball
* Gain or lose a Life

<p align="center">
<img src="https://github.com/asmithfrommany/Brick-Breaker/blob/main/ImagesForGitHub/multipleballs.gif" width="400" alt="new ball powerup"> 
</p>


The different levels are accessible via the main menu and the user can navigate between levels using a combination of clicking and key presses. The information for each level is stored in the Maps folder and the code is split into 6 different classes:

*  **Navigate:**	Load levels, window to put the game inside and navigate menus
*  **Pieces:**		Contains the different parts of the game e.g. ball and paddle classes
*  **Gameplay:**	Handles collision events and key presses
*  **Design:**		Contains the Map class which holds all information needed to build a level and the MapInfo class which is a data carrying class
*  **Draw:**		All drawing of graphics to the screen
*  **Maths:**		Simple functions for building maps and incrementing values

## Design choices

The classes are arranged in order to make them well encapsulated from one another. This made multiple balls at once easy to implement and meant methods could be easily reused for example, to make a “bot game” in the main menu only one small method followBall() needed to be written and combined with other methods already used for the main game.

<p align="center">
<img src="https://github.com/asmithfrommany/Brick-Breaker/blob/main/ImagesForGitHub/mainmenu.gif" width="400" alt="main menu"> 
</p>

The majority of values are chosen to be integers in large part because many of the painting functions can only paint to within one pixel. To make sure the game keeps its proportions when resizing the game window, changing the court size or changing the sizes of the pieces the delay on the swing timer scales inversely with the court width. The sizes of most game pieces are scaled relative to each other and simple checks are made in cases where rounding may affect the appearance of the map for example the gap between the bricks and the border.

The balls currently in play are in stored in an list (an ArrayList to be exact) and this list is passed as a parameter into various methods e.g. brick.bounceBalls(), paddle.bounceBalls() etc. The bricks are also stored in a list, this makes bricks a little easier to work than using an array but would perhaps be a hinderance when trying to implement more complex powerups which rely on a given brick having information about its neighbours.

## Extension ideas

Some nice extensions to the code would be: 

* Implement more powerups and maybe associate each powerup with a certain colour
* Extend to 2 player ( Court() is easy to resize and it would be easy to change the keys in the key handler)
* Make a clickable level builder where the player can design and save their own level (Build a map as normal, make each brick clickable and have various buttons for designing e.g. fill all, toggle between straight and staggered etc)

