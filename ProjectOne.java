import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class ProjectOne {
    private final String url = "jdbc:mysql://localhost:3306/project_one";
    private String username = "root";
    private String password = "Tunenpoo1994b@";
    private Connection conn;


    public void createTable(){
        try (Statement statement = conn.createStatement();) {
            // create table sql query
            String createTableQuery = "CREATE TABLE IF NOT EXISTS user ("
                    + "id INT AUTO_INCREMENT," + "name VARCHAR(100)," + "email VARCHAR(100),"
                    + "age INT,"
                    + "location VARCHAR(100),"
                    + "Designation VARCHAR(100),"
                    + "PRIMARY KEY(id))";
            statement.executeUpdate(createTableQuery);

        } catch (SQLException ex) {
            System.out.println("Something went wrong!");
            ex.printStackTrace();
        }

    }

    public void populateTable(){
        String insertQuery = "INSERT INTO user (name, email, age, location, designation) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            try (Scanner scanner = new Scanner(System.in)) {

                    System.out.println("Enter your name");
                    String name = scanner.nextLine();
                    System.out.println("Enter your email");
                    String email = scanner.nextLine();
                    System.out.println("Enter your age");
                    int age = scanner.nextInt();
                    System.out.println("Enter your location");
                    String location = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Enter your designation");
                    String designation = scanner.nextLine();

                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setInt(3, age);
                    preparedStatement.setString(4, location);
                    preparedStatement.setString(5, designation);
                    preparedStatement.executeUpdate();

            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong!");
            ex.printStackTrace();
        }

    }
    public static void main(String[] args) {
        // create an instance of Projectone

        ProjectOne projectOne = new ProjectOne();
        projectOne.connectDb();
        projectOne.createTable();
        projectOne.populateTable();
    }

    // util method to connect to the database
    public void connectDb(){
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            System.out.println("Connected to the database");
        } catch (SQLException ex) {
            System.out.println("Oops! Something went wrong");
            ex.printStackTrace();
        }

    }

}
