Main Game Loop
In its main() the ControllerFX creates all the objects, sets itself as an observer of ViewFX and sets ViewFX as an
observer of GameState.
Then the ControllerFX calls GameState.updateGameState(Dir) which runs an infinite loop:
{
1 – ViewFX gathers the key press and notifies ControllerFX.
2 - In its update() ControllerFX processes the input with InpuFXt.proccessInput(KeyCode) and calls
GameState.updateGameState(Dir).
3 - In updateGameState() GameState handles the movement and notifies the ViewFX.
4 - ViewFX gets a Type[][] copy of the labyrinth as a parameter in its update() and draws the game
based on that.
5 – The ControllerFX calls Platform.exit() if the conditions for Game Over or Win are met or if the
player types “x”.
}