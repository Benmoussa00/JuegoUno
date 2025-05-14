package juego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBacceso {
	
	private static Connection connection;
	private static String url="jdbc:mysql://juegouno.cpro85x6hwnq.us-east-1.rds.amazonaws.com";
	private static String user="admin";
	private static String password="alumnoalumno";

			/*
	
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            
            String insertSQL = "INSERT INTO students (cod, nombre) VALUES (1, 'Mohamed')";
            statement.executeUpdate(insertSQL);
            System.out.println("INSERT realizado.");

            
            String updateSQL = "UPDATE students SET nombre = 'Mohamed Benmoussa' WHERE cod = 1";
            statement.executeUpdate(updateSQL);
            System.out.println("UPDATE realizado.");

           
            String deleteSQL = "DELETE FROM students WHERE cod = 1";
            statement.executeUpdate(deleteSQL);
            System.out.println("DELETE realizado.");
            
            
            para mostrar un select
            
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM students");

            System.out.println("Contenido actual de la tabla students:");
            while (rs.next()) {
                System.out.println(rs.getInt("cod") + " - " + rs.getString("nombre"));
            }
            
            connection.close();
            
            */
	
	public static void insertarUsuario(String correo, String contra, String nombreUser) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
	        Statement statement = connection.createStatement();

	        
	        String insertSQL = "INSERT INTO students (cod, nombre) VALUES ('"+correo+"', '"+contra+"', '"+nombreUser+"')";
	        statement.executeUpdate(insertSQL);
	        
	        connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
