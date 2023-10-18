package TicTacToe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Strategies {

    private char[] board;
    protected static boolean duringGame, win, draw;
    private static int numberOfMovesMade;
    private int whereMove;
    private char playerSign;

    Strategies() {
        board = TicTacToeBoard.board;
        duringGame = true;
        win = false;
        draw = false;
        numberOfMovesMade = 0;
    }

//RANDOM STRATEGY/////////////////////////////////////

    protected synchronized void playRandomMove(char sign) {
        playerSign = sign;
        whereMove = 0;

        boolean itIsMyMove = ((playerSign == 'X') == TicTacToeBoard.XsMove);

        if(itIsMyMove && duringGame) {
            chooseRandomPlace();
            printMoveAndUpdateVariablesAfterMove();
        }

        handleThread(itIsMyMove, sign);
    }

    private void chooseRandomPlace() {
        Random rr = new Random();
        int randomPlace = rr.nextInt(9 - numberOfMovesMade);
        int l=0;
        for(int i = 0; i < 9; i++){
            if(board[i] == i+49) {
                if(l == randomPlace) {
                    board[i] = playerSign;
                    whereMove = i+1;
                }
                l++;
            }
        }
    }

    private void printMoveAndUpdateVariablesAfterMove() {
        sleepAndPrintInfoAboutMove();
        updateVariablesAndPrintBoard();
    }

    private void updateVariablesAndPrintBoard() {
        endGameIfWinOrDraw();
        numberOfMovesMade++;
        TicTacToeBoard.XsMove = !TicTacToeBoard.XsMove;

        TicTacToeBoard.writer.actualBoard();
        TicTacToeBoard.printBoard();
    }

    private void sleepAndPrintInfoAboutMove() {
        try{
            Thread.sleep(3000);
        } catch(InterruptedException exc) {
            System.out.println(exc);
        }
        TicTacToeBoard.writer.infoAboutMove(playerSign, whereMove);
    }

    private void endGameIfWinOrDraw() {
        win = win || (board[0] == board[1] && board[1] == board[2]);
        win = win || (board[3] == board[4] && board[4] == board[5]);
        win = win || (board[6] == board[7] && board[7] == board[8]);
        win = win || (board[0] == board[3] && board[3] == board[6]);
        win = win || (board[1] == board[4] && board[4] == board[7]);
        win = win || (board[2] == board[5] && board[5] == board[8]);
        win = win || (board[0] == board[4] && board[4] == board[8]);
        win = win || (board[2] == board[4] && board[4] == board[6]);

        draw = (board[0] != '1' && board[1] != '2' && board[2] != '3' && board[3] != '4' && board[4] != '5' && board[5] != '6'
                && board[6] != '7' && board[7] != '8' && board[8] != '9' && !win);

        if(win || draw) duringGame = false;
    }

    private void handleThread(boolean itIsMyMove, char sign) {
        notify();

        if(duringGame) {
            try {
                while (!itIsMyMove) {
                    wait();
                    itIsMyMove = ((sign == 'X') == TicTacToeBoard.XsMove);
                }
            } catch (InterruptedException exc) {
                System.out.println("Działanie wątku gracza " + sign + " zostało przerwane.");
            }
        }
    }

    //GOOD STRATEGY/////////////////////////////

    protected synchronized void playGoodMove(char sign) {
        playerSign = sign;
        whereMove = 0;
        boolean itIsMyMove = ((playerSign == 'X') == TicTacToeBoard.XsMove);

        if(itIsMyMove && duringGame) {
            fillTo3IfThereIs2OfMySign();
            blockOpponentIfHeHas2Of3();

            if (whereMove == 0) chooseRandomPlace();
            printMoveAndUpdateVariablesAfterMove();
        }
        handleThread(itIsMyMove, sign);
    }

    private void fillTo3IfThereIs2OfMySign() {
        checkAndFillRows(playerSign);
        checkAndFillCols(playerSign);
        checkAndFillDiagonals(playerSign);
    }

    private void blockOpponentIfHeHas2Of3() {
        char opponentSign;
        if(playerSign == 'X') opponentSign = 'O';
        else opponentSign = 'X';

        checkAndFillRows(opponentSign);
        checkAndFillCols(opponentSign);
        checkAndFillDiagonals(opponentSign);
    }

    private void checkAndFillRows(char Sign2InRow) {
        for (int i = 0; i < 3; i++) {
            // sprawdzanie wierszy
            if (board[3 * i + 0] == Sign2InRow && board[3 * i + 0] == board[3 * i + 1] && board[3 * i + 2] == 3 * i + 2 + 49 && whereMove == 0) {
                board[3 * i + 2] = playerSign;
                whereMove = 3 * i + 3;
            }
            if (board[3 * i + 1] == Sign2InRow && board[3 * i + 1] == board[3 * i + 2] && board[3 * i + 0] == 3 * i + 0 + 49 && whereMove == 0) {
                board[3 * i + 0] = playerSign;
                whereMove = 3 * i + 1;
            }
            if (board[3 * i + 0] == Sign2InRow && board[3 * i + 0] == board[3 * i + 2] && board[3 * i + 1] == 3 * i + 1 + 49 && whereMove == 0) {
                board[3 * i + 1] = playerSign;
                whereMove = 3 * i + 2;
            }
        }
    }

    private void checkAndFillCols(char Sign2InCol) {
        for (int i = 0; i < 3; i++) {
            if (board[i + 0] == Sign2InCol && board[i + 0] == board[i + 3] && board[i + 6] == i + 6 + 49 && whereMove == 0) {
                board[i + 6] = playerSign;
                whereMove = i + 7;
            }
            if (board[i + 3] == Sign2InCol && board[i + 3] == board[i + 6] && board[i + 0] == i + 0 + 49 && whereMove == 0) {
                board[i + 0] = playerSign;
                whereMove = i + 1;
            }
            if (board[i + 0] == Sign2InCol && board[i + 0] == board[i + 6] && board[i + 3] == i + 3 + 49 && whereMove == 0) {
                board[i + 3] = playerSign;
                whereMove = i + 4;
            }
        }
    }

    private void checkAndFillDiagonals(char Sign2InDiagonal) {
        if (board[0] == Sign2InDiagonal && board[0] == board[4] && board[8] == 8 + 49 && whereMove == 0) {
            board[8] = playerSign;
            whereMove = 9;
        }
        if (board[0] == Sign2InDiagonal && board[0] == board[8] && board[4] == 4 + 49 && whereMove == 0) {
            board[4] = playerSign;
            whereMove = 5;
        }
        if (board[8] == Sign2InDiagonal && board[8] == board[4] && board[0] == 0 + 49 && whereMove == 0) {
            board[0] = playerSign;
            whereMove = 1;
        }

        if (board[2] == Sign2InDiagonal && board[2] == board[4] && board[6] == 6 + 49 && whereMove == 0) {
            board[6] = playerSign;
            whereMove = 7;
        }
        if (board[6] == Sign2InDiagonal && board[6] == board[4] && board[2] == 2 + 49 && whereMove == 0) {
            board[2] = playerSign;
            whereMove = 3;
        }
        if (board[2] == Sign2InDiagonal && board[2] == board[6] && board[4] == 4 + 49 && whereMove == 0) {
            board[4] = playerSign;
            whereMove = 5;
        }
    }

    //BEST STRATEGY////////////////////////

    protected synchronized void playBestMove(char sign) {
        playerSign = sign;
        whereMove = 0;
        char f, opponentSign;
        boolean[] RowsColsDiagsStartedByMe = {false, false, false, false, false, false, false, false};
        boolean[] RowsColsDiagsStartedByOpponent = {false, false, false, false, false, false, false, false};
        //in these tables 3 first values are for rows, 3 next are for columns, and 2 last are for diagonals
        //values say if the player has signs in certain line

        if(playerSign=='X') opponentSign='O';
        else opponentSign = 'X';

        boolean itIsMyMove = ((playerSign == 'X') == TicTacToeBoard.XsMove);

        if(itIsMyMove && duringGame) {
            ifPossiblePutInTheMiddle();

            fillTo3IfThereIs2OfMySign();
            blockOpponentIfHeHas2Of3();

            updateTableStatedBy(opponentSign, RowsColsDiagsStartedByOpponent);
            updateTableStatedBy(playerSign, RowsColsDiagsStartedByMe);

            analyzeSpecialStrategyFor(RowsColsDiagsStartedByMe, RowsColsDiagsStartedByOpponent);
            analyzeSpecialStrategyFor(RowsColsDiagsStartedByOpponent, RowsColsDiagsStartedByMe); //to block special strategy of the opponent

            putInCornerVisAVisEmptyCorner();
            putSignInOtherGoodPlace();

            printMoveAndUpdateVariablesAfterMove();
        }
        handleThread(itIsMyMove, sign);
    }

    private void ifPossiblePutInTheMiddle() {
        if(board[4]=='5') {
            board[4] = playerSign;
            whereMove=5;
        }
    }

    private void updateTableStatedBy(char sign, boolean[] table) {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[3*i+j] == sign){
                    table[i]=true;
                    table[3+j]=true;
                }
            }
        }
        if(board[0] == sign || board[4] == sign || board[8] == sign) table[6] = true;
        if(board[2] == sign || board[4] == sign || board[6] == sign) table[7] = true;
    }

    private void analyzeSpecialStrategyFor(boolean[] StartedByPlayer, boolean[] StartedByOpponent) {

        //If player have already put his signs in two lines of a different type (where line's types are column, row and diagonal),
        //and his opponent didn't put his signs in these exact lines, player should put a sign in such a way, that he continue
        //to fill both of these lines in one move.
        //For example when player has signs on squares 1 and 9, then he has started first row and third column. If his opponent
        //didn't start these lines, player should put his sign on square 3, to continue filling first row and third column.
        //This is the case when moves are: X-1, O-5, X-9, O-4, X-3

        for(int i=0;i<3;i++){
            checkRowAndDiagonals(i, StartedByPlayer, StartedByOpponent);
            checkColumnAndDiagonals(i, StartedByPlayer, StartedByOpponent);
        }
        checkRowsAndColumns(StartedByPlayer, StartedByOpponent);
    }

    private void checkRowAndDiagonals(int i, boolean[] StartedByPlayer, boolean[] StartedByOpponent) {
        if(StartedByPlayer[i] && StartedByPlayer[6] &&
                !StartedByOpponent[i] && !StartedByOpponent[6] &&
                whereMove==0 && board[3*i+i]==3*i+i+49) {
            board[3*i+i]=playerSign;
            whereMove=3*i+i+1;
        }
    }

    private void checkColumnAndDiagonals(int i, boolean[] StartedByPlayer, boolean[] StartedByOpponent) {
        if(StartedByPlayer[3+i] && StartedByPlayer[6] &&
                !StartedByOpponent[3+i] && !StartedByOpponent[6] &&
                whereMove==0 && board[3*i+i]==3*i+i+49) {
            board[3*i+i]=playerSign;
            whereMove=3*i+i+1;

            if(StartedByPlayer[3+i] && StartedByPlayer[7] &&
                    !StartedByOpponent[3+i] && !StartedByOpponent[7] &&
                    whereMove==0 && board[3*(2-i)+i]==3*(2-i)+i+49) {
                board[3*(2-i)+i]=playerSign;
                whereMove=3*(2-i)+1+i;
            }
        }
    }

    private void checkRowsAndColumns(boolean[] StartedByPlayer, boolean[] StartedByOpponent) {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(StartedByPlayer[i] && StartedByPlayer[3+j] &&
                        !StartedByOpponent[i] && !StartedByOpponent[3+j] &&
                        whereMove==0 && board[3*i+j]==3*i+j+49) {
                    board[3*i+j]=playerSign;
                    whereMove=3*i+j+1;
                }
                if(StartedByPlayer[i] && StartedByPlayer[7] &&
                        !StartedByOpponent[i] && !StartedByOpponent[7] &&
                        whereMove==0 && board[3*i+2-i]==3*i+2-i+49) {
                    board[3*i+2-i]=playerSign;
                    whereMove=3*i+3-i;
                }
            }
        }
    }

    private void putInCornerVisAVisEmptyCorner() {
        if(whereMove==0 && board[0]=='1' && board[8]=='9') {
            board[0]=playerSign;
            whereMove=1;
        }
        if(whereMove==0 && board[2]=='3' && board[6]=='7') {
            board[2]=playerSign;
            whereMove=3;
        }
        if(whereMove==0 && board[1]=='2' && board[7]=='8') {
            board[1]=playerSign;
            whereMove=2;
        }
        if(whereMove==0 && board[3]=='4' && board[5]=='6') {
            board[3]=playerSign;
            whereMove=4;
        }
    }

    private void putSignInOtherGoodPlace() {
        if(whereMove==0 && board[0]=='1') {
            board[0]=playerSign;
            whereMove=1;
        }
        if(whereMove==0 && board[8]=='9') {
            board[8]=playerSign;
            whereMove=9;
        }
        if(whereMove==0 && board[2]=='3') {
            board[2]=playerSign;
            whereMove=3;
        }
        if(whereMove==0 && board[5]=='6') {
            board[5]=playerSign;
            whereMove=6;
        }
        if(whereMove==0 && board[1]=='2') {
            board[1]=playerSign;
            whereMove=2;
        }
        if(whereMove==0 && board[3]=='4') {
            board[3]=playerSign;
            whereMove=4;
        }
        if(whereMove==0 && board[4]=='6') {
            board[4]=playerSign;
            whereMove=5;
        }
        if(whereMove==0 && board[7]=='8') {
            board[7]=playerSign;
            whereMove=8;
        }
    }

    //PLAYER'S STRATEGY//////////////////////////////////

    protected synchronized void playPlayersMove(char sign) {
        playerSign = sign;
        boolean itIsMyMove = ((playerSign == 'X') == TicTacToeBoard.XsMove);

        if(itIsMyMove && duringGame) {
            readPlayerChoice();
            updateVariablesAndPrintBoard();
        }
        handleThread(itIsMyMove, sign);
    }

    private void readPlayerChoice() {
        Scanner scan = new Scanner(System.in);
        int cc;

        TicTacToeBoard.writer.wherePutSign(playerSign);

        try {
            cc = scan.nextInt();
            boolean correctAnswer = false;

            for (int i = 0; i <= 8; i++)
                if (cc + 48 == board[i]) {
                    board[i] = playerSign;
                    correctAnswer = true;
                }
            if(!correctAnswer) throw new ArrayIndexOutOfBoundsException();
            else numberOfMovesMade++;
        } catch(InputMismatchException | ArrayIndexOutOfBoundsException exc) {
            TicTacToeBoard.writer.wrongValue();
            TicTacToeBoard.XsMove = !TicTacToeBoard.XsMove;       //we don't want to change player, if move is incorrect
        }
    }
}