# CS611-Assignment 1

## Sliding Puzzle

---

-   Name: Sadid Ethun
-   Email: sethun@bu.edu
-   Student ID: U44108760

## Files

---

-   **Board.java**  
    Interface for any board game.

-   **Game.java**  
    Interface for a game with a single `run()` method.

-   **SlidingPuzzleBoard.java**  
    Implementation of `Board` for the sliding puzzle Uses an array to store the board, swaps the selected piece with the blank space, and prints a grid with a move counter.

-   **SlidingPuzzleGame.java**  
    Implementation of `Game`. Prompts for board dimensions, creates a shuffled board and repeatedly asks the user for input until the board is solved.

-   **Main.java**  
    Creates `SlidingPuzzleGame` and calls `run()`.

## Notes

---

-   **Guaranteed solvability:**  
    The shuffle is implemented as a random walk of valid moves from the solved state, so every generated puzzle can be solved.

-   **Dimesnions:**  
    The minimum dimension is 2, since that is the smallest size for a board. THe maximum size is 5, since it would be really hard to solve a puzzle larger than that.

-   **Array representation:**  
    The board uses a single `int[]` instead of `int[][]`. Row and column are computed as  
    `row = index / cols` and `col = index % cols`. This keeps the code fast and simple.

-   **OO design:**

    `Board` and `Game` are interfaces, so new board games or new game loops can be added easily without rewriting existing code. `SlidingPuzzleBoard` encapsulates all rules and states while `SlidingPuzzleGame` only handles user input and the game loop.

## How to compile and run

---

Compile all classes

```bash

javac Board.java Game.java SlidingPuzzleBoard.java SlidingPuzzleGame.java Main.java
```

Run the program

```bash

java Main
```

## Input/Output Example

Output:

```
--- Sliding Puzzle Game ---
Move pieces around until the board is in increasing order from left to right, up to down. Enter a piece number to move it to the empty space.
How many rows should the board have? (>=2, <=5, default 3):
```

Input: `3`

Output:

```
How many columns should the board have? (>=2, <=5, default 3):
```

Input: `3`

Output:

```
Moves: 0
+---+---+---+
| 4 | 1 | 2 |
+---+---+---+
| 5 | 3 | 6 |
+---+---+---+
| | 7 | 8 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `7`

Output:

```
Moves: 1
+---+---+---+
| 4 | 1 | 2 |
+---+---+---+
| 5 | 3 | 6 |
+---+---+---+
| 7 | | 8 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `8`

Output:

```
Moves: 2
+---+---+---+
| 4 | 1 | 2 |
+---+---+---+
| 5 | 3 | 6 |
+---+---+---+
| 7 | 8 | |
+---+---+---+
Which piece number would you like to move?:
```

Input: `6`

Output:

```
Moves: 3
+---+---+---+
| 4 | 1 | 2 |
+---+---+---+
| 5 | 3 | |
+---+---+---+
| 7 | 8 | 6 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `3`

Output:

```
Moves: 4
+---+---+---+
| 4 | 1 | 2 |
+---+---+---+
| 5 | | 3 |
+---+---+---+
| 7 | 8 | 6 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `5`

Output:

```
Moves: 5
+---+---+---+
| 4 | 1 | 2 |
+---+---+---+
| | 5 | 3 |
+---+---+---+
| 7 | 8 | 6 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `4`

Output:

```
Moves: 6
+---+---+---+
| | 1 | 2 |
+---+---+---+
| 4 | 5 | 3 |
+---+---+---+
| 7 | 8 | 6 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `1`

Output:

```
Moves: 7
+---+---+---+
| 1 | | 2 |
+---+---+---+
| 4 | 5 | 3 |
+---+---+---+
| 7 | 8 | 6 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `2`

Output:

```
Moves: 8
+---+---+---+
| 1 | 2 | |
+---+---+---+
| 4 | 5 | 3 |
+---+---+---+
| 7 | 8 | 6 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `3`

Output:

```
Moves: 9
+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | |
+---+---+---+
| 7 | 8 | 6 |
+---+---+---+
Which piece number would you like to move?:
```

Input: `6`

Output:

```
Moves: 10
+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 | 8 | |
+---+---+---+
Congratulations! You solved it in 10 moves.
```
