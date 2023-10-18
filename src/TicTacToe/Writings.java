package TicTacToe;

interface Writings {

    void description();
    void whoStarts(boolean XsMove);
    void wrongValue();
    void strategies(char player);
    void infoAboutMove(char playerSign, int whereMove);
    void wherePutSign(char playerSign);
    void endOfTheGame();
    void actualBoard();
}
