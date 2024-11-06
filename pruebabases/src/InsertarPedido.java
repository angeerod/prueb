import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertarPedido extends JFrame {

    // Campos de texto para ingresar los detalles del pedido
    private JTextField txtNumeroPedido, txtIdColaborador, txtCedula, txtMontoTotal, txtFecha;
    private JButton btnRegresar;

    public InsertarPedido() {
        // Configuración de la ventana
        setTitle("Insertar Pedido");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usar coordenadas absolutas para cada componente

        // Crear y posicionar etiquetas y campos de texto
        JLabel lblNumeroPedido = new JLabel("Número de Pedido:");
        lblNumeroPedido.setBounds(50, 30, 150, 25);
        add(lblNumeroPedido);
        txtNumeroPedido = new JTextField();
        txtNumeroPedido.setBounds(200, 30, 150, 25);
        add(txtNumeroPedido);

        JLabel lblIdColaborador = new JLabel("ID Colaborador:");
        lblIdColaborador.setBounds(50, 70, 150, 25);
        add(lblIdColaborador);
        txtIdColaborador = new JTextField();
        txtIdColaborador.setBounds(200, 70, 150, 25);
        add(txtIdColaborador);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(50, 110, 150, 25);
        add(lblCedula);
        txtCedula = new JTextField();
        txtCedula.setBounds(200, 110, 150, 25);
        add(txtCedula);

        JLabel lblMontoTotal = new JLabel("Monto Total:");
        lblMontoTotal.setBounds(50, 150, 150, 25);
        add(lblMontoTotal);
        txtMontoTotal = new JTextField();
        txtMontoTotal.setBounds(200, 150, 150, 25);
        add(txtMontoTotal);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(50, 190, 150, 25);
        add(lblFecha);
        txtFecha = new JTextField();
        txtFecha.setBounds(200, 190, 150, 25);
        add(txtFecha);

        // Botón para insertar el pedido
        JButton btnInsertar = new JButton("Insertar Pedido");
        btnInsertar.setBounds(200, 230, 150, 30);
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarPedido();
            }
        });
        add(btnInsertar);

        // Botón de regresar al menú
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(200, 280, 150, 30);
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenu();
            }
        });
        add(btnRegresar);
    }

    // Método para insertar el pedido llamando al procedimiento almacenado
    private void insertarPedido() {
        String numeroPedido = txtNumeroPedido.getText();
        String idColaborador = txtIdColaborador.getText();
        String cedula = txtCedula.getText();
        int montoTotal = Integer.parseInt(txtMontoTotal.getText());
        String fecha = txtFecha.getText();

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "{CALL InsertarPedido(?, ?, ?, ?, ?)}";
            statement = connection.prepareCall(query);

            // Establecer los parámetros
            statement.setString(1, numeroPedido);
            statement.setString(2, idColaborador);
            statement.setString(3, cedula);
            statement.setInt(4, montoTotal);
            statement.setString(5, fecha);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Pedido insertado exitosamente.");

            // Limpiar los campos después de la inserción
            txtNumeroPedido.setText("");
            txtIdColaborador.setText("");
            txtCedula.setText("");
            txtMontoTotal.setText("");
            txtFecha.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar los recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para regresar al menú principal
    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose(); // Cierra la ventana actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InsertarPedido app = new InsertarPedido();
            app.setVisible(true);
        });
    }
}
