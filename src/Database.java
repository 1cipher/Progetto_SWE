
import java.sql.*;

public class Database {
    public Connection c = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    public Database() {

    }

    public void createConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/CalendarApplication",
                            "postgres", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    private void dropConnection() throws  Exception{

        c.close();
    }

    public void addToDB(Calendar calendar, Event e) throws SQLException {

        stmt = c.createStatement();

        String sql = "INSERT INTO CALENDAREVENTS(CALENDAR,EVENT)"+
                "VALUES("+calendar.getId()+","+e.getId()+");";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EVENTS(ID,NAME,DATE,LOCATION,COLOR,DESCRIZIONE)"+
                "VALUES("+e.getId()+","+e.getName()+","+e.getDate()+","+e.getLocation()+","+e.getColor()+","+e.getDescription()+");";
    }

    public boolean checkUserPresence(String acquiredUser,String acquiredPassword) throws SQLException {

        String sql = "SELECT UID " +
                "FROM LOGIN " +
                "WHERE UID = '"+acquiredUser+"' AND PASSWORD ='"+acquiredPassword+"'";

        stmt = c.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        boolean check = result.next();

        return check;
    }

    public void registerNewUser(String username,String password) {

        try {
            stmt = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO LOGIN(UID,PASSWORD)" +
                "VALUES('"+username+"','"+password+"');";

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean isExistingUsername(String username) {

        String sql = "SELECT * " +
                "FROM LOGIN " +
                "WHERE UID = '" + username + "'";

        try {
            stmt = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean check = false;
        try {
            check = resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;

    }

    public void addEventinEvents(String uid,String name,String location,String descr,String colour,java.sql.Date date) {

        try {
            stmt = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO EVENTS(ID,NAME,DATE,LOCATION,COLOR,DESCRIPTION)" +
                "VALUES('"+uid+"','"+name+"',"+date+",'"+location+"','"+colour+"','"+descr+"');"; //TODO: PROBABILMENTE C'è INCOERENZA TRA IL TIPO DATE E QUELLO SU POSTGRESQL

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}


