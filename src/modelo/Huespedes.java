package modelo;

import java.util.Date;

public class Huespedes {

    private Long id;
    private Long reservasId;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String telefono;

    public Huespedes(Long reservasId, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
        this.reservasId = reservasId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    public Huespedes(Long id, Long reservasId, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
        this.id = id;
        this.reservasId = reservasId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    public Huespedes(Long id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Long reservasId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reservasId = reservasId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getReservasId() {
        return reservasId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public java.sql.Date getFechaNacimiento() {
        return (java.sql.Date) fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }
}
