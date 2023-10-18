package TicTacToe;

public class TicTacToe {

    public static void main(String args[]) {

        TicTacToeBoard.initialize();

        var mtX = new MyThread("gracz X", TicTacToeBoard.strategyOfX);
        var mtO = new MyThread("gracz O", TicTacToeBoard.strategyOfO);

        mtX.trd.start();
        mtO.trd.start();

        try {
            mtX.trd.join();
            mtO.trd.join();
        } catch(InterruptedException exc) {
            System.out.println("Wątek główny został przerwany.");
        }

        TicTacToeBoard.writer.endOfTheGame();
    }
}