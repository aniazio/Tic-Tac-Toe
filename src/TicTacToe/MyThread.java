package TicTacToe;

public class MyThread implements Runnable {

    Thread trd;
    int playerStrategy;
    static Strategies strategies = new Strategies();

    MyThread(String name, int st) {
        trd = new Thread(this,name);
        playerStrategy = st;
    }

    public void run() {
        char signOfPlayer = trd.getName().charAt(6);
        while(strategies.duringGame) {
            switch (playerStrategy) {
                case TicTacToeBoard.PLAYER_STRATEGY:
                    strategies.playPlayersMove(signOfPlayer);
                    break;
                case TicTacToeBoard.RANDOM_STRATEGY:
                    strategies.playRandomMove(signOfPlayer);
                    break;
                case TicTacToeBoard.GOOD_STRATEGY:
                    strategies.playGoodMove(signOfPlayer);
                    break;
                case TicTacToeBoard.BEST_STRATEGY:
                    strategies.playBestMove(signOfPlayer);
                    break;
            }
        }
    }
}
