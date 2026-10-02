package org.example.practicasql.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.practicasql.database.DatabaseConnection;
import org.example.practicasql.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class EmpleadoController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtCedula;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtSalario;

    @FXML private ComboBox<String> cmCargo;
    @FXML private ComboBox<String> cmbDepartamento;
    @FXML private ComboBox<String> cmbEstao;

    @FXML private DatePicker dpFecha;

    @FXML private TableView<Empleado> tblEmpleados;

    @FXML private TableColumn<Empleado, Integer> colId;
    @FXML private TableColumn<Empleado, String> colNombres;
    @FXML private TableColumn<Empleado, String> colApellidos;
    @FXML private TableColumn<Empleado, String> colCedula;
    @FXML private TableColumn<Empleado, String> colCorreo;
    @FXML private TableColumn<Empleado, String> colTelefono;
    @FXML private TableColumn<Empleado, Double> colSalario;
    @FXML private TableColumn<Empleado, LocalDate> colFecha;
    @FXML private TableColumn<Empleado, String> colDepartamento;
    @FXML private TableColumn<Empleado, String> colCargo;
    @FXML private TableColumn<Empleado, String> colEstado;

    private final ObservableList<Empleado> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarTabla();
        configurarComboBox();
        cargarEmpleados();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaContratacion"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void configurarComboBox() {
        cmbDepartamento.getItems().clear();
        cmbDepartamento.getItems().addAll("Administracion", "Bodega", "Contabilidad", "Recursos Humanos", "Tecnologia", "Ventas");

        cmCargo.getItems().clear();
        cmCargo.getItems().addAll("Administrador", "Analista", "Contador", "Programador", "Supervisor", "Vendedor");

        cmbEstao.getItems().clear();
        cmbEstao.getItems().addAll("Activo", "Inactivo");
    }

    @FXML
    private void cargarEmpleados() {
        lista.clear();

        String sql = "SELECT * FROM empleado";

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
        ) {

            while (resultSet.next()) {
                // Cada objeto de tipo empleado equivale a una fila de la tabla empleado
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("id"));
                empleado.setNombres(resultSet.getString("nombres"));
                empleado.setApellidos(resultSet.getString("apellidos"));
                empleado.setCedula(resultSet.getString("cedula"));
                empleado.setCorreo(resultSet.getString("correo"));
                empleado.setTelefono(resultSet.getString("telefono"));
                empleado.setSalario(resultSet.getDouble("salario"));
                //empleado.setFecha(resultSet.getDate("fecha de contratacion").toLocalDate());

                lista.add(empleado);
            }

            tblEmpleados.setItems(lista);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void GuardarEmpleados() {
        if (!ValidarCampos()) {
            return;
        }

        String sql = "INSERT INTO empleado (nombres, apellidos, cedula, correo, telefono, cargo, departamento, salario, fecha_contratacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, txtNombre.getText());
            statement.setString(2, txtApellido.getText());
            statement.setString(3, txtCedula.getText());
            statement.setString(4, txtCorreo.getText());
            statement.setString(5, txtTelefono.getText());
            statement.setString(6, cmCargo.getValue());
            statement.setString(7, cmbDepartamento.getValue());
            statement.setDouble(8, Double.parseDouble(txtSalario.getText()));
            statement.setDate(9, Date.valueOf(dpFecha.getValue()));
            statement.setString(10, cmbEstao.getValue());

            statement.executeUpdate();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Registro almacenado",
                    "Empleado Registrado",
                    "El empleado se ha guardado exitosamente."
            );

            LimpiarCampos();
            cargarEmpleados();

        } catch (SQLException ex) {
            ex.printStackTrace();
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error de base de datos",
                    "No se pudo guardar",
                    ex.getMessage()
            );
        }
    }

    @FXML
    private void LimpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCedula.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtSalario.clear();

        cmCargo.setValue(null);
        cmbDepartamento.setValue(null);
        cmbEstao.setValue(null);
        dpFecha.setValue(null);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String encabezado, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean ValidarCampos() {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                txtCedula.getText().isEmpty() || txtCorreo.getText().isEmpty() ||
                txtSalario.getText().isEmpty() || cmCargo.getValue() == null ||
                cmbDepartamento.getValue() == null || dpFecha.getValue() == null ||
                cmbEstao.getValue() == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Faltan datos",
                    "Debe completar los campos obligatorios."
            );
            return false;
        }

        try {
            Double.parseDouble(txtSalario.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Error de formato",
                    "Salario inválido",
                    "El salario debe ser un número."
            );
            return false;
        }

        return true;
    }
}