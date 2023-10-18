package TicTacToe;

public class PolishWritings implements Writings {
    public void description() {
        System.out.println("\nOto plansza do gry w kółko i krzyżyk. Numerami oznaczono kolejne pozycje.\n" +
                "Ruchy gracza X będą zaznaczane krzyżykami, a gracza O - kółkami.\n" +
                "Wybór gracza rozpoczynającego grę dokonuje się losowo.");
    }

    public void whoStarts(boolean XsMove) {
        if(XsMove) System.out.println("\nZaczyna gracz X.");
        else System.out.println("\nZaczyna gracz O.");
    }

    public void wrongValue() {
        System.out.println("Wpisałeś złą wartość, spróbuj jeszcze raz.");
    }

    public void strategies(char player) {
        System.out.println("\nWybierz strategię gracza " + player + ".\n" +
                "Jeśli chcesz sam sterować graczem wpisz " + TicTacToeBoard.PLAYER_STRATEGY + ".\n" +
                "Jeśli chcesz, żeby graczem sterował komputer w sposób losowy, wpisz " + TicTacToeBoard.RANDOM_STRATEGY + ".\n" +
                "Jeśli chcesz, aby graczem sterował komputer, realizując dobrą strategię, wpisz " + TicTacToeBoard.GOOD_STRATEGY + ".\n" +
                "Jeśli chcesz, żeby graczem sterował komputer, realizując najlepszą możliwą strategię, wpisz " + TicTacToeBoard.BEST_STRATEGY + ".");
    }

    public void infoAboutMove(char playerSign, int whereMove) {
        System.out.println("Gracz " + playerSign + " postawił znacznik na polu " + whereMove + ".");
    }

    public void wherePutSign(char playerSign) {
        System.out.println("Ruch gracza " + playerSign + ". W którym miejscu chcesz postawić znak " + playerSign + "?");
    }

    public void endOfTheGame() {
        if(Strategies.draw) System.out.println("Remis!");
        else if(!TicTacToeBoard.XsMove) System.out.println("Grę wygrywa gracz X!");
        else System.out.println("Grę wygrywa gracz O!");
    }

    public void actualBoard() {
        System.out.println("Aktualna plansza:");
    }
}
