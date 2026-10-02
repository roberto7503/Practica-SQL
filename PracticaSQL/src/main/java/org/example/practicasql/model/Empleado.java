package org.example.practicasql.model;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class Empleado {
    private int id;
    private String nombres;
    private String apellidos;
    private String cedula;
    private String correo;
    private String  telefono;
    private Double salario;
    private String celular;
    private DatePicker fecha;
    private ComboBox departamento;
    private ComboBox cargo;
    private ComboBox estado;

    public Empleado(int id, String nombres, String apellidos, String cedula, String correo, String telefono, Double salario, String celular, DatePicker fecha, ComboBox departamento, ComboBox cargo, ComboBox estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.salario = salario;
        this.celular = celular;
        this.fecha = fecha;
        this.departamento = departamento;
        this.cargo = cargo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public DatePicker getFecha() {
        return fecha;
    }

    public void setFecha(DatePicker fecha) {
        this.fecha = fecha;
    }

    public ComboBox getDepartamento() {
        return departamento;
    }

    public void setDepartamento(ComboBox departamento) {
        this.departamento = departamento;
    }

    public ComboBox getCargo() {
        return cargo;
    }

    public void setCargo(ComboBox cargo) {
        this.cargo = cargo;
    }

    public ComboBox getEstado() {
        return estado;
    }

    public void setEstado(ComboBox estado) {
        this.estado = estado;
    }
}
