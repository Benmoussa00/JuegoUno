package juego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DBacceso {
	
	private static JPanel contentPane;
	
	private static Connection connection;
	private static String url="jdbc:mysql://juegouno.cpro85x6hwnq.us-east-1.rds.amazonaws.com/JuegoUno";
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
	
	public static boolean insertarUsuario(String correo, String contra, String nombreUser) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(url, user, password);

	        
	        String insertSQL = "INSERT INTO usuario (correo, contrasena, nombre_usuario) VALUES (?, ?, ?)";
	        PreparedStatement inserSQLStatement = connection.prepareStatement(insertSQL);
	        
	        inserSQLStatement.setString(1, correo);
	        inserSQLStatement.setString(2, contra);
	        inserSQLStatement.setString(3, nombreUser);
	        
	        int filasAfectadas = inserSQLStatement.executeUpdate();
	        
	        connection.close();
	        inserSQLStatement.close();
	        
	        return filasAfectadas > 0;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(contentPane,
	                    "El correo electronico ya esta registrado",
	                    "Error de duplicado",
	                    JOptionPane.ERROR_MESSAGE);
	        } else if (e.getErrorCode() == 1406) {
	            if (e.getMessage().contains("nombre_usuario")) {
	            	JOptionPane.showMessageDialog(contentPane,
		                    "El nombre de usuario es demasiado largo (max 50 caracteres)",
		                    "Error nombre de usuario demasiado largo",
		                    JOptionPane.ERROR_MESSAGE);
	            }
	            if (e.getMessage().contains("correo")) {
	            	JOptionPane.showMessageDialog(contentPane,
		                    "El correo es demasiado largo (max 100 caracteres)",
		                    "Error correo demasiado largo",
		                    JOptionPane.ERROR_MESSAGE);
	            }
	            if (e.getMessage().contains("contrasena")) {
	            	JOptionPane.showMessageDialog(contentPane,
		                    "La contraseña es demasiado larga (max 100 caracteres)",
		                    "Error contraseña demasiado larga",
		                    JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	        	JOptionPane.showMessageDialog(contentPane,
						"Error al registrar el usuario. Verifica los datos.",
						"Error de base de datos",
				        JOptionPane.ERROR_MESSAGE);
	        	e.printStackTrace();
	        }
			return false;
		}
	}
	
    public static String obtenerContraseñaPorCorreo(String correo) {
        String query = "SELECT contrasena FROM usuario WHERE correo = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("contrasena");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean actualizarNombreUsuario(String nuevoNombre) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://juegouno.cpro85x6hwnq.us-east-1.rds.amazonaws.com/JuegoUno", "admin", "alumnoalumno");

            String sql = "UPDATE usuario SET nombre_usuario = ? WHERE correo = ?";
            ps = conn.prepareStatement(sql);
            
            
            ps.setString(1, nuevoNombre);
            ps.setString(2, Cache.obtenerDeCache("correo"));
            
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para verificar si un correo existe en la base de datos
    public static boolean correoExiste(String correo) {
        String query = "SELECT COUNT(*) FROM usuario WHERE correo = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean verificarContrasena(String correo, String contrasena) {
        String contrasenaGuardada = obtenerContraseñaPorCorreo(correo);
        return contrasenaGuardada != null && contrasenaGuardada.equals(contrasena);
    }
    
    public static String obtenerNombreUsuario(String correo) {
        String query = "SELECT nombre_usuario FROM usuario WHERE correo = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
