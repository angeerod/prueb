import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

public class InsertarCargo extends JFrame {

    private JTextField txtIdCargo, txtNombre, txtSalario;
    private JButton btnRegresar;

    public InsertarCargo() {
        setTitle("Insertar Cargo");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblIdCargo = new JLabel("ID Cargo:");
        lblIdCargo.setBounds(50, 50, 150, 25);
        add(lblIdCargo);
        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(200, 50, 150, 25);
        add(txtIdCargo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 100, 150, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(200, 100, 150, 25);
        add(txtNombre);

        JLabel lblSalario = new JLabel("Salario:");
        lblSalario.setBounds(50, 150, 150, 25);
        add(lblSalario);
        txtSalario = new JTextField();
        txtSalario.setBounds(200, 150, 150, 25);
        add(txtSalario);

        JButton btnInsertar = new JButton("Insertar Cargo");
        btnInsertar.setBounds(200, 200, 150, 30);
        btnInsertar.addActionListener(e -> insertarCargo());
        add(btnInsertar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(400, 200, 150, 30);
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }

    private void insertarCargo() {
        String idCargo = txtIdCargo.getText();
        String nombre = txtNombre.getText();
        int salario = Integer.parseInt(txtSalario.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701")) {
            String query = "{CALL InsertarCargo(?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, idCargo);
            statement.setString(2, nombre);
            statement.setInt(3, salario);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Cargo insertado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el cargo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdCargo.setText("");
        txtNombre.setText("");
        txtSalario.setText("");
    }

    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }
}

