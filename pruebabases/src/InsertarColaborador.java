import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

public class InsertarColaborador extends JFrame {

    private JTextField txtIdColaborador, txtIdCargo, txtNombre1, txtNombre2, txtApellido1, txtApellido2, txtTelefono;
    private JButton btnRegresar;

    public InsertarColaborador() {
        setTitle("Insertar Colaborador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblIdColaborador = new JLabel("ID Colaborador:");
        lblIdColaborador.setBounds(50, 30, 150, 25);
        add(lblIdColaborador);
        txtIdColaborador = new JTextField();
        txtIdColaborador.setBounds(200, 30, 150, 25);
        add(txtIdColaborador);

        JLabel lblIdCargo = new JLabel("ID Cargo:");
        lblIdCargo.setBounds(50, 70, 150, 25);
        add(lblIdCargo);
        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(200, 70, 150, 25);
        add(txtIdCargo);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(50, 110, 150, 25);
        add(lblNombre1);
        txtNombre1 = new JTextField();
        txtNombre1.setBounds(200, 110, 150, 25);
        add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(50, 150, 150, 25);
        add(lblNombre2);
        txtNombre2 = new JTextField();
        txtNombre2.setBounds(200, 150, 150, 25);
        add(txtNombre2);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setBounds(50, 190, 150, 25);
        add(lblApellido1);
        txtApellido1 = new JTextField();
        txtApellido1.setBounds(200, 190, 150, 25);
        add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setBounds(50, 230, 150, 25);
        add(lblApellido2);
        txtApellido2 = new JTextField();
        txtApellido2.setBounds(200, 230, 150, 25);
        add(txtApellido2);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 270, 150, 25);
        add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(200, 270, 150, 25);
        add(txtTelefono);

        JButton btnInsertar = new JButton("Insertar Colaborador");
        btnInsertar.setBounds(200, 310, 150, 30);
        btnInsertar.addActionListener(e -> insertarColaborador());
        add(btnInsertar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(400, 310, 150, 30);
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }

    private void insertarColaborador() {
        String idColaborador = txtIdColaborador.getText();
        String idCargo = txtIdCargo.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();
        String telefono = txtTelefono.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701")) {
            String query = "{CALL InsertarColaborador(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, idColaborador);
            statement.setString(2, idCargo);
            statement.setString(3, nombre1);
            statement.setString(4, nombre2);
            statement.setString(5, apellido1);
            statement.setString(6, apellido2);
            statement.setString(7, telefono);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Colaborador insertado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el colaborador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdColaborador.setText("");
        txtIdCargo.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtTelefono.setText("");
    }

    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }
}
