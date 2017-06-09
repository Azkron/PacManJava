Additional Features

- We save the random seed for the phantom movement in the mementos so that after going back to a memento the Phantoms will move the same way unless Pacman eats one of them. This way the PadMan becomes also a "memory" game as if the player remembers the movement of the phantoms he can plan accordingly.

- We added random labyrinth generation. Now the labyrinth will be different every time you play!

- If PacMan or the Phantoms reach the boundary of the labyrinth he will appear on the other side.

- The Phantoms will also change directions on intersections, not just when encountering a wall.


Main Game Loop

In its main() the Controller creates all the objects, sets itself as an observer of ViewFX and sets ViewFX as an observer of GameState.

Then the Controller calls play() which runs an infinite Timeline that regularly calls nextFrame():

{

– At any time ViewFX gathers the key press and notifies ControllerFX.

1 - In nextFrame() ControllerFX processes the input with InpuFX.proccessInput(KeyCode) and calls GameState.updateGameState(Dir). 

2 - In updateGameState() GameState handles the movement and notifies the ViewFX.

3 - ViewFX gets a ArrayList<Type>[][] copy of the labyrinth as a parameter in its update() and draws the game it based on that.

4 - TheController calls Platform.exit() if the conditions for Game Over or Win are met or if the player types “x”.

}