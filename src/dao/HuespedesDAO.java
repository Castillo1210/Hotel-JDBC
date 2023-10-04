package dao;

import modelo.Huespedes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedesDAO {

    final private Connection con;

    public HuespedesDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Huespedes huespedes) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO hotel_alura.huespedes (reservas_id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try (preparedStatement) {
                preparedStatement.setLong(1, huespedes.getReservasId());
                preparedStatement.setString(2, huespedes.getNombre());
                preparedStatement.setString(3, huespedes.getApellido());
                preparedStatement.setDate(4, huespedes.getFechaNacimiento());
                preparedStatement.setString(5, huespedes.getNacionalidad());
                preparedStatement.setString(6, huespedes.getTelefono());

                preparedStatement.execute();

                final ResultSet resultSet = preparedStatement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        huespedes.setId(resultSet.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huespedes> buscarHuespedes(String apellido) {
        List<Huespedes> huespedesList = new ArrayList<>();
        try {
            final PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM hotel_alura.huespedes WHERE apellido = ?");
            try (preparedStatement) {
                preparedStatement.setString(1, apellido);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                while (resultSet.next()) {
                    Huespedes fila = new Huespedes(resultSet.getLong("huespedes_id"), resultSet.getLong("reservas_id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getDate("fecha_nacimiento"), resultSet.getString("nacionalidad"), resultSet.getString("telefono"));
                    huespedesList.add(fila);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return huespedesList;
    }

    public void eliminarHuespedes(Long id) {
        try {
            final PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM hotel_alura.huespedes WHERE huespedes_id = ?");
            try (preparedStatement) {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
                preparedStatement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huespedes> listarHuespedes() {
        List<Huespedes> huespedesList = new ArrayList<>();
        try {
            final PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM hotel_alura.huespedes");
            try (preparedStatement) {
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                while (resultSet.next()) {
                    Huespedes fila = new Huespedes(resultSet.getLong("huespedes_id"), resultSet.getLong("reservas_id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getDate("fecha_nacimiento"), resultSet.getString("nacionalidad"), resultSet.getString("telefono"));
                    huespedesList.add(fila);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return huespedesList;
    }

    public void modificarHuespedes(Huespedes huespedes) {
        try {
            final PreparedStatement preparedStatement = con.prepareStatement("UPDATE hotel_alura.huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ? WHERE huespedes_id = ?");
            try (preparedStatement) {
                preparedStatement.setString(1, huespedes.getNombre());
                preparedStatement.setString(2, huespedes.getApellido());
                preparedStatement.setDate(3, huespedes.getFechaNacimiento());
                preparedStatement.setString(4, huespedes.getNacionalidad());
                preparedStatement.setString(5, huespedes.getTelefono());
                preparedStatement.setLong(6, huespedes.getId());

                preparedStatement.execute();

                preparedStatement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
