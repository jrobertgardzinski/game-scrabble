# game-scrabble
Flagship project of my GitHub account. Implementation of one of my favourite games.

# Documentation

## Web project plan draft
[TODO]

## Game mechanics plan draft

Module reponsible of game mechanics in other hand is designed using UML diagrams, especially Use Case, Sequence and Activity, because in my opinion those diagrams fits best to describe this part of system. Plan based on Scrabble rules found on the internet on pages written down in [game rules](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/game%20rules.md) file. 

Below you can see Use Case diagram giving general idea. Starting analyzes on the Player actor you'll see he's got basically 3 options: play tiles, skip turn or challange a word. Play tiles action is most complicated, because each game move requires placing tiles on game board, drawing tiles to replace the tiles played and calculate score, but it's no trivial action in Scrabble! It is not restricted to create only one word in one move. Player can create 2 words just by putting one tile on game board in some cases. Another possible move is to challenge move made by player. Based on rules described on https://howdoyouplayit.com/scrabble-rules-play-scrabble/ : "If the challenged word is not legal, those tiles return to the player’s hand and that player loses the turn. If a word is challenged and it is legal, the challenger loses his or her next turn.". The last move player can do is to skip the turn and optionally redraw some tiles.

[Show full size](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Use%20case.jpg)
![UML-Use case](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Use%20case.jpg)

More technical details can be found below. Describing each of diagram would be time consuming. I put some notes to clarify some concepts.

[Show full size](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Sequence.jpg)
![UML-Sequence](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Sequence.jpg)

[Show full size](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Sequence-turn%20loop.jpg)
![UML-Sequence-turn loop](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Sequence-turn%20loop.jpg)

[Show full size](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Activity-flushPreviouslyPlayedTiles().jpg)
![UML-Activity-flushPreviouslyPlayedTiles()](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Activity-flushPreviouslyPlayedTiles().jpg)

[Show full size](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Activity-flushPreviouslyPlayedTilesAndUpdateGameBoard().jpg)
![UML-Activity-flushPreviouslyPlayedTilesAndUpdateGameBoard()](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Activity-flushPreviouslyPlayedTilesAndUpdateGameBoard().jpg)

[Show full size](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Activity-combining%20words%20algorithm.jpg)
![UML-Activity-combining words algorithm](https://raw.githubusercontent.com/jrobertgardzinski/game-scrabble/documentation/doc/game%20mechanics/UML/Activity-combining%20words%20algorithm.jpg)
