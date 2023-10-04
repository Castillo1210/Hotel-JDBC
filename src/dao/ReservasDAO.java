package dao;

import modelo.Reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasDAO {

    final private Connection con;

    public ReservasDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Reservas reservas) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO hotel_alura.reservas (fecha_entrada, fecha_salida, valor, forma_pago) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try (preparedStatement) {
                preparedStatement.setDate(1, reservas.getFechaEntrada());
                preparedStatement.setDate(2, reservas.getFechaSalida());
                preparedStatement.setDouble(3, reservas.getValor());
                preparedStatement.setString(4, reservas.getFormaPago());

                preparedStatement.execute();

                final ResultSet resultSet = preparedStatement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        reservas.setId(resultSet.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reservas> buscarReservas(Long reservaId) {
        List<Reservas> reservasList = new ArrayList<>();
        try {
            final PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM hotel_alura.reservas WHERE reservas_id = ?");
            try (preparedStatement) {
                preparedStatement.setLong(1, reservaId);

                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                while (resultSet.next()) {
                    Reservas fila = new Reservas(resultSet.getLong("reservas_id"), resultSet.getDate("fecha_entrada"), resultSet.getDate("fecha_salida"), resultSet.getDouble("valor"), resultSet.getString("forma_pago"));
                    reservasList.add(fila);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return reservasList;
    }

    public List<Reservas> listarReservas() {
        List<Reservas> reservasList = new ArrayList<>();
        try {
            final PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM hotel_alura.reservas");
            try (preparedStatement) {
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                while (resultSet.next()) {
                    Reservas fila = new Reservas(resultSet.getLong("reservas_id"), resultSet.getDate("fecha_entrada"), resultSet.getDate("fecha_salida"), resultSet.getDouble("valor"), resultSet.getString("forma_pago"));
                    reservasList.add(fila);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  reservasList;
    }

    public void eliminarReservas(Long id) {
        try {
            final PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM hotel_alura.reservas WHERE reservas_id = ?");
            try (preparedStatement) {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
                preparedStatement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarReservas(Reservas reservas) {
        try {
            final PreparedStatement preparedStatement = con.prepareStatement("UPDATE hotel_alura.reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE reservas_id = ?");
            try (preparedStatement) {
                preparedStatement.setDate(1, reservas.getFechaEntrada());
                preparedStatement.setDate(2, reservas.getFechaSalida());
                preparedStatement.setDouble(3, reservas.getValor());
                preparedStatement.setString(4, reservas.getFormaPago());
                preparedStatement.setLong(5, reservas.getId());

                preparedStatement.execute();

                preparedStatement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
