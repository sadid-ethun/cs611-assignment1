import java.util.Random;
import java.util.Scanner;

public class SlidingPuzzleGame implements Game {
    private final Scanner in = new Scanner(System.in);

    private int readDim(String prompt, int def, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            if (s.isEmpty())
                return def;
            try {
                int v = Integer.parseInt(s);
                if (v < min || v > max) {
                    System.out.println("Enter a valid integer.");
                } else {
                    return v;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter an integer.");
            }
        }
    }

    @Override
    public void run() {
        System.out.println("--- Sliding Puzzle Game ---");
        System.out.println(
                "Move pieces around until the board is in increasing order from left to right, up to down. Enter a piece number to move it to the empty space.");

        int rows = readDim("How many rows should the board have? (>=2, <=5, default 3): ", 3, 2, 5);
        int cols =
                readDim("How many columns should the board have? (>=2, <=5, default 3): ", 3, 2, 5);

        Random rng = new Random();
        int steps = Math.max(50, 5 * rows * cols);

        Board board = SlidingPuzzleBoard.shuffle(rows, cols, steps, rng);

        while (true) {
            System.out.print(board.render());
            if (board.isSolved()) {
                System.out.println(
                        "Congratulations! You solved it in " + board.getMoves() + " moves.");
                break;
            }

            System.out.print("Which piece number would you like to move?: ");
            String s = in.nextLine().trim().toLowerCase();

            try {
                int piece = Integer.parseInt(s);
                if (!board.move(piece)) {
                    System.out
                            .println("Invalid move. Please pick a piece adjacent to the blank.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a piece number.\n");
            }
        }
    }
}
