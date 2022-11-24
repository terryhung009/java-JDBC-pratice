import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        String sql_statement = "select * from Video";
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/wilsonvideodb",
                "wilson" ,
                "password");
        if (c != null){
            System.out.println("Connecting to the database");
        }else{
            System.out.println("Cannot connect to database;");
        }

        PreparedStatement pps = c.prepareStatement(sql_statement);
        ResultSet rs = pps.executeQuery();

        ArrayList<Video>  result = new ArrayList<Video>();
        while(rs.next()){
            Video v = new Video(Integer.parseInt(rs.getString("videoId")) ,
                    rs.getString("videoName"),
                    Integer.parseInt(rs.getString("price"))
                    );
            result.add(v);
        }

        for(Video v : result){
            System.out.println(v.toString());
        }

        c.close();

    }
}