import java.sql.*;

public class StudentDb {
    private static final String url= "jdbc:mysql://localhost:3306/mydb";
    private static final String username= "root";
    private static final String password= "sarthak@123";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e){
            System.out.println("Error:"+ e.getMessage());
        }

        try{
            Connection connection= DriverManager.getConnection(url,username,password);

            Statement statement= connection.createStatement();

            String query=String.format("INSERT INTO students(name,age,marks) VALUES('%s',%d,%f)", "Maggie Smith",21,91.5);
            int affected= statement.executeUpdate(query);
            if(affected>0)
                System.out.println("Data Inserted Successfully !!");
            else
                System.out.println("Data was not inserted !!");

            System.out.println();

            String query2= "select * from students";
            ResultSet result= statement.executeQuery(query2);
            while(result.next()){
                int id= result.getInt("Id");
                String name= result.getString("name");
                int age= result.getInt("age");
                double marks= result.getDouble("marks");
                System.out.println("ID: "+id);
                System.out.println("NAME: "+name);
                System.out.println("AGE: "+age);
                System.out.println("MARKS: "+marks);
                System.out.println();
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
