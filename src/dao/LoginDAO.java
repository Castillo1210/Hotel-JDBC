package dao;

import modelo.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    final private Connection con;

    public LoginDAO(Connection con) {
        this.con = con;
    }

    public List<Usuarios> validar() {
        List<Usuarios> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM hotel_alura.usuarios");
            try (statement){
                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    Usuarios fila = new Usuarios(resultSet.getString("usuario"), resultSet.getString("contrasena"));
                    resultado.add(fila);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

}
