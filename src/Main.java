
import javax.swing.*;
import java.sql.*;

public class Main {
    public static Connection c;

    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        initializedDB();

        String name = JOptionPane.showInputDialog("Find a video by name");
        String sql_statement = "select * from video where videoName = ? ;";
        PreparedStatement pps = c.prepareStatement(sql_statement); //SQL injection
        pps.setString(1,name);
        ResultSet rs = pps.executeQuery();
        if(rs.next()){
            int title = Integer.parseInt(rs.getString("videoId"));
            String vname = rs.getString("videoName");
            int price = Integer.parseInt(rs.getString("price"));
            JOptionPane.showMessageDialog(null,new Video(title, vname, price));
        }else{
            JOptionPane.showMessageDialog(null,"video not found.");
        }





        closeDB();
    }
    private static void initializedDB() throws SQLException{
        c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/wilsonvideodb",
                "wilson" ,
                "password");
        if (c != null){
            System.out.println("Connecting to the database");
        }else{
            System.out.println("Cannot connect to database;");
        }
    }

    private static void closeDB() throws SQLException{
        c.close();
    }
}