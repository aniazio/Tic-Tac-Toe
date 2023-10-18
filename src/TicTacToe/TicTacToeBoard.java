package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeBoard {

    static final int PLAYER_STRATEGY = 1;
    static final int RANDOM_STRATEGY = 2;
    static final int GOOD_STRATEGY = 3;
    static final int BEST_STRATEGY = 4;
    static final int POLISH = 1;
    static final int ENGLISH = 2;

    protected static char[] board;
    protected static boolean XsMove;
    protected static int strategyOfX, strategyOfO;
    protected static Writings writer;

    TicTacToeBoard() {
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = (char) (i+49);
        }

        Random rr = new Random();
        XsMove = rr.nextBoolean();
    }

    protected static void initialize() {
        new TicTacToeBoard();
        chooseLanguage();
        printBoard();
        writer.description();
        try{
            Thread.sleep(2000);
        } catch(InterruptedException exc) {
            System.out.println("Metoda initialize została przerwana.");
        }

        chooseStrategy('X');
        chooseStrategy('O');

        writer.whoStarts(XsMove);
    }

    private static void chooseLanguage() {
        boolean correctAnswer =false;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nChoose language by writing appropriate number.\n" +
                "Wybierz język wciskając odpowiedni przycisk.\n" +
                "Polish/Polski: " + POLISH + ".\n" +
                "English/Angielski: " + ENGLISH + ".");

        while(!correctAnswer){
            int whichLanguage = scan.nextInt();
            if(whichLanguage == POLISH || whichLanguage == ENGLISH) correctAnswer = true;
            else System.out.println("Wrong value, try again.\n" +
                    "Wpisałeś złą wartość, spróbuj jeszcze raz.");
            if(correctAnswer) {
                if (whichLanguage == POLISH) writer = new PolishWritings();
                else writer = new EnglishWritings();
            }
        }
    }

    protected static void printBoard() {
        System.out.println("\t \t|\t \t|\t \t\n\t" + board[0] + "\t|\t" + board[1] + "\t|\t" + board[2] + "\t\n\t \t|\t \t|\t \t");
        System.out.println("  ---------------------");
        System.out.println("\t \t|\t \t|\t \t\n\t" + board[3] + "\t|\t" + board[4] + "\t|\t" + board[5] + "\t\n\t \t|\t \t|\t \t");
        System.out.println("  ---------------------");
        System.out.println("\t \t|\t \t|\t \t\n\t" + board[6] + "\t|\t" + board[7] + "\t|\t" + board[8] + "\t\n\t \t|\t \t|\t \t");
    }

    private static void chooseStrategy(char player) {
        boolean correctAnswer =false;
        Scanner scan = new Scanner(System.in);

        writer.strategies(player);

        while(!correctAnswer){
            int whichStrategy = scan.nextInt();
            if(whichStrategy == PLAYER_STRATEGY || whichStrategy == RANDOM_STRATEGY ||
                    whichStrategy == GOOD_STRATEGY || whichStrategy == BEST_STRATEGY) correctAnswer = true;
            else writer.wrongValue();
            if(correctAnswer) {
                if(player == 'X') strategyOfX = whichStrategy;
                else strategyOfO = whichStrategy;
            }
        }
    }
}