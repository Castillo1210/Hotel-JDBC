package controller;

import dao.ReservasDAO;
import factory.ConnectionFactory;
import modelo.Reservas;

import java.util.List;

public class ReservasController {

    final private ReservasDAO reservasDAO;

    public ReservasController() {
        this.reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(Reservas reservas) {
        reservasDAO.guardar(reservas);
    }

    public List<Reservas> buscarReservas(Long reservaId) {
        return reservasDAO.buscarReservas(reservaId);
    }

    public List<Reservas> listarReservas() {
        return reservasDAO.listarReservas();
    }

    public void eliminarReservas(Long id) {
        reservasDAO.eliminarReservas(id);
    }

    public void modificarReservas(Reservas reservas) {
        reservasDAO.modificarReservas(reservas);
    }
}
