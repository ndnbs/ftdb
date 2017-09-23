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
      if ((args.length != 1) || (!(args[0].equals("create") || args[0].equals("output") || 
                                   args[0].equals("populate") || args[0].equals("start")))) 
      {

         logMsg = String.format("Valid Mode NOT specified. Valid Modes are: [%s]", 
                                 "create | output | populate | start");
         System.out.println(logMsg);
         System.exit(1);
      }

      logMsg = String.format("Mode is: [%s]", args[0]);
      System.out.println(logMsg);

      switch(args[0]) {

        case "create":
          CreateFTDB();
          break;

        case "output":
         logMsg = String.format("Mode: [%s] not implemented yet.", args[0]);
         System.out.println(logMsg);
          break;

        case "populate":
          PopulateFTDB();
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
         System.out.println("Creating [os] table in given database..."); 
         stmt = conn.createStatement(); 
         String sql =  "CREATE TABLE os " + 
            "(id IDENTITY, " + 
            " create_dt TIMESTAMP NOT NULL DEFAULT NOW(0), " +  
            " delta_dt TIMESTAMP NOT NULL DEFAULT NOW(0), " +  
            " port INTEGER NOT NULL, " +  
            " symb VARCHAR(255) NOT NULL, " +  
            " exch VARCHAR(255) NOT NULL, " +  
            " status CHAR(1) NOT NULL DEFAULT 'H', " +  
            " pvm DECIMAL(20,2) NOT NULL DEFAULT 0.00, " +  
            " o_date DATE NOT NULL DEFAULT TODAY, " +  
            " o_amt DECIMAL(20,2) NOT NULL, " +  
            " o_pri DECIMAL(20,2) NOT NULL, " +  
            " s_date DATE NOT NULL DEFAULT TODAY, " +  
            " s_amt DECIMAL(20,2) NOT NULL DEFAULT 0.00, " +  
            " s_pri DECIMAL(20,2) NOT NULL DEFAULT 0.00, " +  
            " l_pri DECIMAL(20,2) NOT NULL DEFAULT 0.00, " +  
            " l_pri_dt TIMESTAMP NOT NULL DEFAULT TODAY, " +  
            " l_pri_mech CHAR(6) NOT NULL DEFAULT 'MANUAL', " +  
            " r0001_v CHAR(1) NOT NULL DEFAULT 'N', " +  
            " r0001_dt TIMESTAMP NOT NULL DEFAULT NOW(0), " +  
            " r0002_v CHAR(1) NOT NULL DEFAULT 'N', " +  
            " r0002_dt TIMESTAMP NOT NULL DEFAULT NOW(0)" +  
            ")";  

         stmt.executeUpdate(sql);
         System.out.println("Created [os] table in given database..."); 
         
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
   private static void PopulateFTDB() { 

      String logMsg;
      String sql;

      logMsg = String.format("In PopulateFTDB");
      System.out.println(logMsg);

      Connection conn = null; 
      Statement stmt  = null; 

      try{
         // STEP 1: Register JDBC driver 
         Class.forName(JDBC_DRIVER);  
         
         // STEP 2: Open a connection 
         System.out.println("Connecting to a selected database..."); 
         conn = DriverManager.getConnection(DB_URL,USER,PASS); 
         System.out.println("Connected database successfully..."); 
         
         // STEP 3: Execute a query 
         stmt = conn.createStatement();  

         sql = "INSERT INTO os " + 
                "VALUES (" +
                "default, default, default, 13, 'AMZN', 'NASDAQ', default, default, default, 10.00, 13.13, " +
                "default, default, default, default, default, default, default, default, default, default" +
                ")"; 
         stmt.executeUpdate(sql); 

         sql = "INSERT INTO os " + 
                "VALUES (" +
                "default, default, default, 13, 'SPOK', 'NASDAQ', default, default, default, 8.00, 14.14, " +
                "default, default, default, default, default, default, default, default, default, default" +
                ")"; 
         stmt.executeUpdate(sql); 

         sql = "INSERT INTO os " + 
                "VALUES (" +
                "default, default, default, 13, 'LMT', 'NYSE', default, default, default, 99.00, 15.15, " +
                "default, default, default, default, default, default, default, default, default, default" +
                ")"; 
         stmt.executeUpdate(sql); 

         System.out.println("Inserted records into the table..."); 
         
         // STEP 4: Clean-up environment 
         stmt.close(); 
         conn.close(); 
      } catch(SQLException se) { 
         // Handle errors for JDBC 
         se.printStackTrace(); 
      } catch(Exception e) { 
         // Handle errors for Class.forName 
         e.printStackTrace(); 
      } finally { 
         // finally block used to close resources 
         try {
            if(stmt!=null) stmt.close();  
         } catch(SQLException se2) { 
         } // nothing we can do 
         try { 
            if(conn!=null) conn.close(); 
         } catch(SQLException se) { 
            se.printStackTrace(); 
         } // end finally try 
      } // end try 
      System.out.println("Goodbye!"); 

   }
   /**************************************************************************/
}
