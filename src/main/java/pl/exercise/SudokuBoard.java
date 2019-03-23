package pl.exercise;
import java.util.Random;

class Point{

    int row = 0;
    int column = 0;
}

public class SudokuBoard {

    private static final int N = 9;
    private int[][] board = new int[N][N];
    Random rand  = new Random();

    public SudokuBoard()
    {
        board[0][0] = rand.nextInt(9)+1;
        board[N/3][N/3] = rand.nextInt(9)+1;
        board[2*N/3][2*N/3] = rand.nextInt(9)+1;
    }

    private boolean add(int row, int column, int value)
    {
        for(int i=0;i<N;i++)
        {
            if( value == board[row][i] || value == board[i][column])
                return false;
        }

        int r = row/3;
        int c = column/3;
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if(value == board[r*3+i][c*3+j])
                    return false;
            }
        }
        return true;
    }

    private boolean isFull()
    {
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
            {
                if(board[i][j] == 0)
                    return false;
            }
        return true;
    }
    private Point whereIsNull()
    {
        Point point = new Point();
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
            {
                if(board[i][j] == 0)
                {
                    point.row = i;
                    point.column = j;
                    return point;
                }
            }
        return null;
    }


    public boolean fillBoard()
    {
        if(isFull())
            return true;

        for(int k=1;k<=N;k++)
        {
            Point point = whereIsNull();
            if(add(point.row, point.column, k))
            {
                board[point.row][point.column] = k;
                if(fillBoard())
                    return true;
                board[point.row][point.column] = 0;
            }
        }
        return false;
    }

    public void displayBoard() {

        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }
}

