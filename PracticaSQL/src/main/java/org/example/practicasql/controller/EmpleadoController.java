package org.example.practicasql.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.practicasql.database.DatabaseConnection;
import org.example.practicasql.model.Empleado;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    @FXML private ComboBox<String> cmbEstado;
    @FXML private DatePicker dpFecha;
    @FXML private TableView<Empleado> tblEmpleados;
    @FXML private TableColumn<Empleado, Integer> colId;
    @FXML private TableColumn<Empleado, String> colNombres;
    @FXML private TableColumn<Empleado, String> colApellidos;
    @FXML private TableColumn<Empleado, String> colCedula;
    @FXML private TableColumn<Empleado, String> colCorreo;
    @FXML private TableColumn<Empleado, String> colTelefono;
    @FXML private TableColumn<Empleado, String> colCargo;
    @FXML private TableColumn<Empleado, String> colDepartamento;
    @FXML private TableColumn<Empleado, Double> colSalario;
    @FXML private TableColumn<Empleado, LocalDate> colFecha;
    @FXML private TableColumn<Empleado, String> colEstado;

    private final ObservableList<Empleado> lista = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
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
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaContratacion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void configurarComboBox() {
        cmCargo.getItems().addAll(
                "Administrador", "Analista", "Contador",
                "Programador", "Supervisor", "Vendedor"
        );
        cmbDepartamento.getItems().addAll(
                "Administracion", "Bodega", "Contabilidad",
                "Recursos Humanos", "Tecnologia", "Ventas"
        );
        cmbEstado.getItems().addAll("Activo", "Inactivo");
    }

    @FXML
    private void cargarEmpleados() {
        lista.clear();
        String sql = "SELECT * FROM empleado ORDER BY id";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("id"));
                empleado.setNombres(resultSet.getString("nombres"));
                empleado.setApellidos(resultSet.getString("apellidos"));
                empleado.setCedula(resultSet.getString("cedula"));
                empleado.setCorreo(resultSet.getString("correo"));
                empleado.setTelefono(resultSet.getString("telefono"));
                empleado.setCargo(resultSet.getString("cargo"));
                empleado.setDepartamento(resultSet.getString("departamento"));
                empleado.setSalario(resultSet.getDouble("salario"));
                empleado.setFechaContratacion(resultSet.getDate("fecha_contratacion").toLocalDate());
                empleado.setEstado(resultSet.getString("estado"));
                lista.add(empleado);
            }

            tblEmpleados.setItems(lista);
        } catch (SQLException ex) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error de base de datos",
                    "No se pudieron cargar los empleados",
                    ex.getMessage()
            );
        }
    }

    @FXML
    private void guardarEmpleado() {
        if (!validarCampos()) {
            return;
        }

        String sql = "INSERT INTO empleado "
                + "(nombres, apellidos, cedula, correo, telefono, cargo, "
                + "departamento, salario, fecha_contratacion, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            statement.setString(10, cmbEstado.getValue());
            statement.executeUpdate();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Registro almacenado",
                    "Empleado registrado",
                    "El empleado se ha guardado correctamente"
            );

            limpiarCampos();
            cargarEmpleados();
        } catch (SQLException ex) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error de base de datos",
                    "No se pudo guardar el empleado",
                    ex.getMessage()
            );
        }
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCedula.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtSalario.clear();
        cmCargo.setValue(null);
        cmbDepartamento.setValue(null);
        cmbEstado.setValue(null);
        dpFecha.setValue(null);
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty()
                || txtApellido.getText().isEmpty()
                || txtCedula.getText().isEmpty()
                || cmbDepartamento.getValue() == null
                || txtSalario.getText().isEmpty()
                || dpFecha.getValue() == null
                || cmbEstado.getValue() == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Faltan datos",
                    "Complete los campos obligatorios"
            );
            return false;
        }

        try {
            Double.parseDouble(txtSalario.getText());
        } catch (NumberFormatException ex) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Salario incorrecto",
                    "Dato invalido",
                    "El salario debe ser un numero"
            );
            return false;
        }

        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo,
                               String encabezado, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
