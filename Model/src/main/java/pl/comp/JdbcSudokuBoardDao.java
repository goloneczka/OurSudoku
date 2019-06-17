package pl.comp;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    Connection connection = null;
    private String fileName;
    private int id =1;

    static final String WRITE_OBJECT_SQL = "INSERT INTO SUDOKUBOARDS(MYNAME, object_value) VALUES (?, ?)";

    static final String READ_OBJECT_SQL = "SELECT object_value FROM SUDOKUBOARDS WHERE ID = ?";

    private static Connection getConnection() throws Exception {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        String dbURL = "jdbc:derby:D:\\BranchSudoku\\nextDatabase\\lib\\Sudoku;";

        Connection conn = DriverManager.getConnection(dbURL,"app","12345");

        return conn;
    }

    public JdbcSudokuBoardDao(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws Throwable {
        try {
            connection = getConnection();
        }
        catch (FileNotFoundException e) {
            throw new FileExeption("Nie mozna odczytac").initCause(new FileNotFoundException("Nie mozna polaczyc"));
        }

        PreparedStatement pstmt = connection.prepareStatement(READ_OBJECT_SQL);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        SudokuBoard sudokuBoard;
        Blob bl = rs.getBlob(1);

        try(InputStream is = bl.getBinaryStream();
        ObjectInputStream ois= new ObjectInputStream(is)) {
            sudokuBoard = (SudokuBoard) ois.readObject();
        }

        rs.close();
        pstmt.close();
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws Throwable{

        byte[] data = null;
        try {
            connection = getConnection();
        }
        catch (FileNotFoundException e) {
            throw new FileExeption("Nie mozna zapisac").initCause(new FileNotFoundException("Nie mozna polaczyc"));
        }

        String className = obj.getClass().getName();
        PreparedStatement pstmt = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, className);

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            oos.flush();
            data = baos.toByteArray();
        }


        pstmt.setObject(2, data);
        pstmt.executeUpdate();


        ResultSet rs = pstmt.getGeneratedKeys();
        id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        rs.close();
        pstmt.close();

    }
}

