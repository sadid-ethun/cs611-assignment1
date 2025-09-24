import java.util.Arrays;
import java.util.Random;

public class SlidingPuzzleBoard implements Board {
    private final int rows, cols;
    private final int[] cells;
    private int moves = 0;

    public SlidingPuzzleBoard(int rows, int cols, int[] cells) {
        if (rows < 2 || cols < 2)
            throw new IllegalArgumentException("rows/cols must be >= 2");
        if (cells == null || cells.length != rows * cols)
            throw new IllegalArgumentException("cells length must equal rows*cols");
        this.rows = rows;
        this.cols = cols;
        this.cells = Arrays.copyOf(cells, cells.length);
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public int getMoves() {
        return moves;
    }

    private int indexOf(int v) {
        for (int i = 0; i < cells.length; i++)
            if (cells[i] == v)
                return i;
        return -1;
    }

    @Override
    public boolean isSolved() {
        for (int i = 0; i < cells.length - 1; i++)
            if (cells[i] != i + 1)
                return false;
        return cells[cells.length - 1] == 0;
    }

    public static SlidingPuzzleBoard goal(int r, int c) {
        int[] a = new int[r * c];
        for (int i = 0; i < a.length - 1; i++)
            a[i] = i + 1;
        a[a.length - 1] = 0;
        return new SlidingPuzzleBoard(r, c, a);
    }

    public static SlidingPuzzleBoard shuffle(int r, int c, int steps, Random rng) {
        SlidingPuzzleBoard b = SlidingPuzzleBoard.goal(r, c);
        b.shuffle(rng, steps);
        b.moves = 0;
        return b;
    }

    @Override
    public void shuffle(Random rng, int steps) {
        int blank = indexOf(0);
        int prevBlank = -1;

        for (int i = 0; i < steps; i++) {
            int blankRow = blank / cols;
            int blankCol = blank % cols;

            int[] adjacentIdx = new int[4];
            int c = 0;
            if (blankRow > 0)
                adjacentIdx[c++] = blank - cols;
            if (blankRow < rows - 1)
                adjacentIdx[c++] = blank + cols;
            if (blankCol > 0)
                adjacentIdx[c++] = blank - 1;
            if (blankCol < cols - 1)
                adjacentIdx[c++] = blank + 1;

            int choiceIdx = rng.nextInt(c);
            int pieceIdx = adjacentIdx[choiceIdx];
            int pieceVal = cells[pieceIdx];
            cells[blank] = pieceVal;
            cells[pieceIdx] = 0;

            prevBlank = blank;
            blank = pieceIdx;
        }

        if (isSolved()) {
            shuffle(rng, steps);
        }
    }

    @Override
    public boolean move(int piece) {
        if (piece <= 0 || piece >= rows * cols)
            return false;
        int pieceIdx = indexOf(piece);
        int blankIdx = indexOf(0);
        if (pieceIdx < 0 || blankIdx < 0)
            return false;

        int pieceRow = pieceIdx / cols;
        int pieceCol = pieceIdx % cols;
        int blankRow = blankIdx / cols;
        int blankCol = blankIdx % cols;
        if (Math.abs(pieceRow - blankRow) + Math.abs(pieceCol - blankCol) != 1)
            return false;

        cells[blankIdx] = piece;
        cells[pieceIdx] = 0;
        moves++;
        return true;
    }

    private static String repeat(String s, int k) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < k; i++)
            b.append(s);
        return b.toString();
    }

    @Override
    public String render() {
        StringBuilder out = new StringBuilder();
        out.append("Moves: ").append(moves).append("\n");
        String line = "+" + repeat("---+", cols) + "\n";
        out.append(line);
        for (int r = 0; r < rows; r++) {
            out.append("|");
            for (int c = 0; c < cols; c++) {
                int v = cells[r * cols + c];
                out.append(v == 0 ? "   " : String.format("%2d ", v)).append("|");
            }
            out.append("\n").append(line);
        }
        return out.toString();
    }
}
