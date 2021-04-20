package com.group_twelve.dbconnection;

import java.sql.*;
import org.mockito.*;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */

public class SQLConnection
{
    Statement statement = null;
    Connection connection = null;
    boolean connected = false;
    
    public SQLConnection(){}

    public boolean connect(String url, String username, String password, boolean verbose){
        String connectionUrl = url
                /*+ "databaseName=dbName;"*/
            + "user=" + username + ";"
            + "password=" + password + ";"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=5;";

        connection = null;
        //if(verbose) System.out.println("Parameter set.");
        try{
            connection = DriverManager.getConnection(connectionUrl);
            //if(verbose) System.out.println("INFO: Database connection established.");
        }catch(SQLException e){
            if(verbose){
                //System.out.println("ERROR: Database connection could not be established:\n");
                //e.printStackTrace();
            }
            return false;
        }

        try{
            statement = connection.createStatement();
            //if(verbose) System.out.println("INFO: Query received");
        }catch(SQLException e){
            //System.out.println("ERROR: Query could not be received:\n");
            //e.printStackTrace();
        }
        
        connected = true;
        return true;
    }
    
//    public void printResultSet(ResultSet queryResultSet){
//        try{
//            ResultSetMetaData metaData = queryResultSet.getMetaData();
//            int amountColumns = metaData.getColumnCount();
//            while(queryResultSet.next()){
//                for(int counter = 1; counter <= amountColumns; counter++){
//                    if(counter > 1) System.out.print(", ");
//                    String columnValue = queryResultSet.getString(counter);
//                    System.out.print(columnValue + " " + metaData.getColumnName(counter));
//                }
//                System.out.println("");
//            }
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }

    public ResultSet query(String query){
        if(!connected)
            return null;
        
        try{
            if(query.toUpperCase().contains("SELECT")){
                return statement.executeQuery(query);
            }else if(query.toUpperCase().contains("CREATE") || query.toUpperCase().contains("INSERT")){
                statement.executeUpdate(query);
                final ResultSet resultSet = Mockito.mock(ResultSet.class); // Placeholder so that NULL is reserved for errors
                Mockito.when(resultSet.getString(1)).thenReturn("Success");
                return resultSet;
            }else{
                //System.out.println("No command found, query will be forwarded to database");
                statement.executeUpdate(query);
                final ResultSet resultSet = Mockito.mock(ResultSet.class); // Placeholder so that NULL is reserved for errors
                Mockito.when(resultSet.getString(1)).thenReturn("Success");
                return resultSet;
            }
        }catch(SQLException e){
            //System.out.println("Query could not be executed:\n");
            //e.printStackTrace();
            return null;
        }
        //return null;
    }
}
