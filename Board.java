public interface Board {
    int getRows();

    int getCols();

    int getMoves();

    boolean isSolved();

    void shuffle(java.util.Random rng, int steps);

    boolean move(int piece);

    String render();
}
