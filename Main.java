import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Main{
     private static final String DB_URL="jdbc:mysql://localhost:3306/mec";
     private static final String USER="root";
     private static final String PASS="Arunkumar@2005";

     public static void main(String[] args){
          String insertSQL="INSERT INTO employee(name,salary)VALUES(?,?)";
          String SelectSQL="SELECT id,name,salary FROM employee";

          try(
               Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
               PreparedStatement insertStmt=conn.prepareStatement(insertSQL)
          ){
               System.out.println("---Executing Insert operation---");

               insertStmt.setString(1,"John Doe");
               insertStmt.setDouble(2,50000.00);
               int rowsAffected=insertStmt.executeUpdate();
               System.out.println(rowsAffected + "rows inserted Scccessfully");
               
               insertStmt.setString(1,"Jane Smith");
               insertStmt.setDouble(2,65000.00);
               int rowsAffeched= insertStmt.executeUpdate();
               System.out.println(rowsAffected + "rows inserted successfully");
              
               try(
                    PreparedStatement selectStmt =conn.prepareStatement(SelectSQL);
                    ResultSet rs=selectStmt.executeQuery();
               ){
                    System.out.println("Employee Data:");
                    System.out.println("---------------");

                    while(rs.next()){
                         int id=rs.getInt("id");
                         String name=rs.getString("name");
                         double salary=rs.getDouble("salary");

                         System.out.printf("ID;%d,Name:%s,salary:$%2f%n",id,name,salary);
                    }
                    
                    }

          }catch(SQLException e){
               System.out.println("sql Exception occured:"+ e.getMessage());
          }
          
     }
}
