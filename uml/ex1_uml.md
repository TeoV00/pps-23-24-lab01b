
## UML resulting system

```mermaid
classDiagram
    direction TB

    namespace view {
        class GUI
    }

    namespace logic {
        class Logics {
            <<interface>>
            + hit(int row, int col) boolean
            + hasKnight(int row, int col) boolean
            + hasPawn(int row, int col) boolean
        }
    }

    namespace domain {
        class GameGrid {
            <<interface>>
            + dimensions() Pair~Integer,Integer~
            + isInsideGrid(Pair~Integer,Integer~ position) boolean
            + moveKnight(int row, int col) void
            + hasKnight(int row, int col) boolean
            + hasPawn(int row, int col) boolean
        }

        class Piece {
            <<interface>>
            + isAllowedMove(int row, int col) boolean
            + move(int row, int col) void
            + position() Pair~Integer,Integer~
        }

        class PieceImpl {
 %%           - Pair~Integer,Integer~ position
 %%           - MoveStrategy moveStrategy
        }

        class Knight
        class Pawn

        class MoveStrategy {
            <<interface>>
            + canMove(Pair~Integer,Integer~ currentPos, int row, int col) boolean
        }

        class GridFactory {
            <<interface>>
            + makeGameGridRandomKnightWithoutPawns(int h, int w) GameGrid
            + makeGameGridRandomKnightWithPawns(int h, int w, int pawnsAmount) GameGrid
            + makeGameGridInitialPositionKnightWithPawns(int h, int w, int pawnsAmount, \n Pair~Integer,Integer~ knightPos) GameGrid
        }

        class PiecesFactory {
            + createKnight(Pair<Integer, Integer> position) Knight
            + createPawn(Pair<Integer, Integer> position)
        }
    }


%% Main relationships
    PieceImpl *-- "1" MoveStrategy
    PieceImpl ..|> Piece
    Knight --|> Piece
    Pawn --|> Piece
    GameGrid *-- "1" Knight
    GUI *-- "1" Logics
    Logics *-- "1" GameGrid
    GameGrid *-- "0..*" Pawn
    GridFactory ..> GameGrid : create
    Logics *--> GridFactory : uses
    GridFactory *--> PiecesFactory : uses
    PiecesFactory ..> Knight : create
    PiecesFactory ..> Pawn : create 
```

## Test Implemented
The following UML shows relationship between test and implemenation classes (fields and methods are omitted). Class `PiecesFactoryImpl` was not developed using TDD approach it hasn't a test class.

```mermaid

classDiagram
    direction BT
    namespace Test {
        class GridFactoryTest
        class GameGridTest
        class LogicsTest
        class PieceTest
        class KnightTest
        class PawnTest
    }

    namespace Main {
        class PiecesFactoryImpl
        class GridFactoryImpl
        class GameGridImpl
        class LogicsImpl
        class PawnImpl
        class PieceImpl
        class KnightImpl   
    }
    

%% Test relationships
    KnightTest --|> PieceTest
    PawnTest --|> PieceTest
    PieceTest ..|> PieceImpl : test
    KnightTest ..|> KnightImpl : test
    PawnTest ..|> PawnImpl : test
    LogicsTest ..|> LogicsImpl : test
    GameGridTest ..|> GameGridImpl : test
    GridFactoryTest ..|> GridFactoryImpl : test
```