package juego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAcceso {
    
    private static final String URL = "jdbc:mysql://juegouno.cpro85x6hwnq.us-east-1.rds.amazonaws.com/JuegoUno";
    private static final String USER = "admin";
    private static final String PASSWORD = "alumnoalumno";

    // Método para obtener la contraseña de un usuario a partir del correo
    public static String obtenerContraseñaPorCorreo(String correo) {
        String query = "SELECT contrasena FROM usuario WHERE correo = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("contrasena"); // Retorna la contraseña encontrada
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra, devuelve null
    }

    // Método para actualizar el nombre de usuario en la base de datos
    public static boolean actualizarNombreUsuario(String nuevoNombre) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // Conectar a la base de datos
            conn = DriverManager.getConnection("jdbc:mysql://juegouno.cpro85x6hwnq.us-east-1.rds.amazonaws.com/JuegoUno", "admin", "alumnoalumno");

            // SQL para actualizar el nombre de usuario
            String sql = "UPDATE usuario SET nombre_usuario = ? WHERE correo = ?";
            ps = conn.prepareStatement(sql);
            
            // Establecer los parámetros (el correo lo puedes obtener de algún lugar, como el usuario logueado)
            String correoUsuario = "usuario@example.com"; // Cambia esto por la variable correspondiente
            ps.setString(1, nuevoNombre);
            ps.setString(2, correoUsuario);
            
            // Ejecutar la actualización
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas > 0; // Si se actualizó al menos una fila, se devuelve true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Si ocurre un error, se devuelve false
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
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el correo existe, devuelve true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no existe, retorna false
    }

    // Método para verificar si la contraseña es correcta (al iniciar sesión)
    public static boolean verificarContrasena(String correo, String contrasena) {
        String contrasenaGuardada = obtenerContraseñaPorCorreo(correo);
        return contrasenaGuardada != null && contrasenaGuardada.equals(contrasena);
    }

    // Método para agregar un nuevo usuario
    public static boolean agregarUsuario(String correo, String contrasena, String nombreUsuario) {
        String query = "INSERT INTO usuario (correo, contrasena, nombre_usuario) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            stmt.setString(3, nombreUsuario);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Si se insertó correctamente, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si ocurre un error, retorna false
    }
}
