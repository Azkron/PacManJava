Most of the commits has been done by me (Hugo Barbachano) but this is because Tidiane Toure has used my PC too so he was logged in my account instead of his.


This is the main loop of the game, but we have also added it with all the analysis in AnalysisAndReadMe.pdf

Main Game Loop

In it´s main() the Controller creates all the objects, sets itself as an observer of Input and sets View as an
observer of GameState.

Then the Controller calls Input.getInput() which runs an infinite loop:

    1 – Input checks for input from the Player and notifies it´s observers (the Controller).

    2 - In it´s update() the Controller calls GameState.updateGameState().

    3 - In updateGameState() GameState handles the movement and notifies it´s observers (the View).

    4 - In it´s update() the View gets a copy of the Labyrinth calling GameState.getLabView() and draws it
        based on that.

    5 - The Controller calls System.Exit() if the conditions for Game Over or Win are met or if the player types
        “x”.