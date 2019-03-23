package pl.exercise;
import java.util.Random;
import static java.lang.Math.*;


public class SudokuBoard {

    private static final int N = 9;
    private Point[][] board=new Point[N][N];
    Random rand  = new Random();
    boolean czyJestElementLosowy = false;

    public SudokuBoard() {
        //generuje puste sudoku
        for (int i=0; i < N; i++) {
            for (int j=0; j < N; j++) {
                this.board[i][j]= new Point(i, j, 0, false);
            }
        }
        // generowanie jednego losowego stałego punktu na planszy
        Point punkt = new Point(abs(rand.nextInt(9)), abs(rand.nextInt(9)), abs(rand.nextInt(9) + 1), true);
        board[punkt.getX()][punkt.getY()]=punkt;
    }

    private boolean checkArea(int x, int y, int value) {
        for (int i=0; i < N; i++) {
            if (value == board[x][i].getValue() || value == board[i][y].getValue()) {
                return false;
            }
        }
        int r = x / 3;
        int c = y / 3;
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                if (value == board[r * 3 + i][c * 3 + j].getValue()) {
                    return false;
                }
            }
        }
        return true;
    }
    // zwraca null jak nie ma juz punktu z wartoscia 0
    private Point whereIsZero() {
        Point punkt = null;
        for (int z = 0; z < 9; z++) {
            for (int g = 0; g < 9; g++) {
                if (board[z][g].getValue() == 0) {
                    punkt=new Point(z, g, 0, false);
                }
            }
        }
        return punkt;
    }

    public boolean fillBoard() {
        if (!czyJestElementLosowy) {
            wypelnijJedenElementLosowo();
        }
        Point point = whereIsZero();
        if (point==null) {
            czyJestElementLosowy=false;
            return true;
        }

        for (int k = 1; k <= N; k++) {
            if (checkArea(point.getX(), point.getY(), k)) {
                board[point.getX()][point.getY()].setValue(k);
                if (fillBoard()) {
                    return true;
                }
            } else {
                board[point.getX()][point.getY()].setValue(0);
            }
        }
        return false;

    }
    
    private void wypelnijJedenElementLosowo() {
        Point point = whereIsZero();

        while (!czyJestElementLosowy) {
            int k=rand.nextInt(9) + 1;
            if (checkArea(point.getX(), point.getY(), k)) {
                board[point.getX()][point.getY()].setValue(k);
                czyJestElementLosowy=true;
            }
        }
    }

    // nie usuwa stalych point!
    public void clearBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!board[i][j].isConstPoint()) {
                    board[i][j].setValue(0);
                }
            }
        }
    }
    public void displayBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j].getValue());
            }
            System.out.println();
        }
        System.out.println();
    }
}

