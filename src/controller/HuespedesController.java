package controller;

import dao.HuespedesDAO;
import factory.ConnectionFactory;
import modelo.Huespedes;

import java.util.List;

public class HuespedesController {

    final private HuespedesDAO huespedesDAO;

    public HuespedesController() {
        this.huespedesDAO = new HuespedesDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(Huespedes huespedes) {
        huespedesDAO.guardar(huespedes);
    }

    public List<Huespedes> buscarHuespedes(String apellido) {
        return huespedesDAO.buscarHuespedes(apellido);
    }

    public void eliminarHuespedes(Long id) {
        huespedesDAO.eliminarHuespedes(id);
    }

    public List<Huespedes> listarHuespedes() {
        return huespedesDAO.listarHuespedes();
    }

    public void modificarHuespedes(Huespedes huespedes) {
        huespedesDAO.modificarHuespedes(huespedes);
    }

}
