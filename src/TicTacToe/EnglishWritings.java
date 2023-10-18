package TicTacToe;

public class EnglishWritings implements Writings {
    public void description() {
        System.out.println("\nHere is a board for tic-tac-toe. The numbers are markers for the squares.\n" +
                "Player X's moves will be marked with crosses, and player O's moves will be marked with circles.\n" +
                "The choice of the player who starts the game is made randomly.");
    }

    public void whoStarts(boolean XsMove) {
        if(XsMove) System.out.println("\nPlayer X starts.");
        else System.out.println("\nPlayer O starts.");
    }

    public void wrongValue() {
        System.out.println("Wrong value, try again.");
    }

    public void strategies(char player) {
        System.out.println("\nChoose strategy of player " + player + ".\n" +
                "If you want to make choices yourself, write " + TicTacToeBoard.PLAYER_STRATEGY + ".\n" +
                "To choose random strategy, write " + TicTacToeBoard.RANDOM_STRATEGY + ".\n" +
                "To choose good strategy, write " + TicTacToeBoard.GOOD_STRATEGY + ".\n" +
                "To choose the best strategy, write " + TicTacToeBoard.BEST_STRATEGY + ".");
    }

    public void infoAboutMove(char playerSign, int whereMove) {
        System.out.println("Player " + playerSign + " make a move on square " + whereMove + ".");
    }

    public void wherePutSign(char playerSign) {
        System.out.println("Player " + playerSign + "'s move. Where do you want to place a sign " + playerSign + "?");
    }

    public void endOfTheGame() {
        if(Strategies.draw) System.out.println("Draw!");
        else if(!TicTacToeBoard.XsMove) System.out.println("Player X wins!");
        else System.out.println("Player O wins!");
    }

    public void actualBoard() {
        System.out.println("Actual board:");
    }
}
