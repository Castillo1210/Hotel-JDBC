package controller;

import dao.LoginDAO;
import factory.ConnectionFactory;
import modelo.Usuarios;

public class LoginController {

    private boolean valido = false;
    final private LoginDAO loginDAO;

    public LoginController() {
        this.loginDAO = new LoginDAO(new ConnectionFactory().recuperaConexion());
    }

    public boolean validar(String usuario, String contraseña) {
        for (Usuarios usuarios : loginDAO.validar()) {
            if (usuarios.getUsuario().equals(usuario) && usuarios.getContrasena().equals(contraseña)) {
                this.valido = true;
            }

            if (this.valido) {
                break;
            }
        }
        return this.valido;
    }
}
