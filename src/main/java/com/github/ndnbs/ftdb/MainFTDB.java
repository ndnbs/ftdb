package com.github.ndnbs.ftdb;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.Statement;  

public class MainFTDB { 
   // JDBC driver name and database URL 
   static final String JDBC_DRIVER = "org.h2.Driver";   
   static final String DB_URL      = "jdbc:h2:./support/db/ftdb";  
   
   //  Database credentials 
   static final String USER = "sa"; 
   static final String PASS = ""; 
 
   /**************************************************************************/
   public static void main(String[] args) { 

      String logMsg;

      // Ensure that exactly one(1) mode is passed in of the three(3) allowable modes. 
      // Because of "short-circuit" behavior, the following works out nicely.
      if ((args.length != 1) || (!(args[0].equals("create") || args[0].equals("populate") || args[0].equals("start")))) {

         logMsg = String.format("Valid Mode NOT specified. Valid Modes are: [%s]", "create | populate | start");
         System.out.println(logMsg);
         System.exit(1);
      }

      logMsg = String.format("Mode is: [%s]", args[0]);
      System.out.println(logMsg);

      switch(args[0]) {

        case "create":
          CreateFTDB();
          break;

        case "populate":
         logMsg = String.format("Mode: [%s] not implemented yet.", args[0]);
         System.out.println(logMsg);
          break;

        case "start":
         logMsg = String.format("Mode: [%s] not implemented yet.", args[0]);
         System.out.println(logMsg);
          break;

        default:
         logMsg = String.format("Should never happen, but here for code maintainability");
         System.out.println(logMsg);
         System.exit(1);
      }

      System.exit(0);
   } 
   /**************************************************************************/
   private static void CreateFTDB() { 

      String logMsg;

      logMsg = String.format("In CreateFTDB");
      System.out.println(logMsg);

      Connection conn = null; 
      Statement stmt  = null; 
      try { 
         // STEP 1: Register JDBC driver 
         Class.forName(JDBC_DRIVER); 
             
         //STEP 2: Open a connection 
         System.out.println("Connecting to database..."); 
         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
         
         //STEP 3: Execute a query 
         System.out.println("Creating [overall_status] table in given database..."); 
         stmt = conn.createStatement(); 
         String sql =  "CREATE TABLE overall_status " + 
            "(id INTEGER NOT NULL, " + 
            " l_delta_dt TIMESTAMP NOT NULL, " +  
            " port INTEGER NOT NULL, " +  
            " symb VARCHAR(255) NOT NULL, " +  
            " exch VARCHAR(255) NOT NULL, " +  
            " stat VARCHAR(255) NOT NULL, " +  
            " pvom DECIMAL(20,2) NOT NULL, " +  
            " o_date DATE NOT NULL, " +  
            " o_amt DECIMAL(20,2) NOT NULL, " +  
            " o_pri DECIMAL(20,2) NOT NULL, " +  
            " s_date DATE NOT NULL, " +  
            " s_amt DECIMAL(20,2) NOT NULL, " +  
            " s_pri DECIMAL(20,2) NOT NULL, " +  
            " l_pri DECIMAL(20,2) NOT NULL, " +  
            " l_pri_dt TIMESTAMP NOT NULL, " +  
            " l_pri_mech VARCHAR(255) NOT NULL, " +  
            " r0001_v VARCHAR(255) NOT NULL, " +  
            " r0001_dt TIMESTAMP NOT NULL, " +  
            " r0002_v VARCHAR(255) NOT NULL, " +  
            " r0002_dt TIMESTAMP NOT NULL, " +  
            " PRIMARY KEY ( id ))";  
         stmt.executeUpdate(sql);
         System.out.println("Created [overall_status] table in given database..."); 
         
         // STEP 4: Clean-up environment 
         stmt.close(); 
         conn.close(); 
      } catch(SQLException se) { 
         //Handle errors for JDBC 
         se.printStackTrace(); 
      } catch(Exception e) { 
         //Handle errors for Class.forName 
         e.printStackTrace(); 
      } finally { 
         //finally block used to close resources 
         try{ 
            if(stmt!=null) stmt.close(); 
         } catch(SQLException se2) { 
         } // nothing we can do 
         try { 
            if(conn!=null) conn.close(); 
         } catch(SQLException se){ 
            se.printStackTrace(); 
         } //end finally try 
      } //end try 
      System.out.println("Goodbye!");
   } 
   /**************************************************************************/
}
