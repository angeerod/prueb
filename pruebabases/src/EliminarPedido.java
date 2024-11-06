import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EliminarPedido extends JFrame {

    private JTextField txtNumeroPedido;

    public EliminarPedido() {
        // Configuración de la ventana
        setTitle("Eliminar Pedido");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);  // Usamos coordenadas absolutas

        // Etiqueta para el campo de Número de Pedido
        JLabel lblNumeroPedido = new JLabel("Numero de Pedido:");
        lblNumeroPedido.setBounds(50, 30, 150, 30);  // Establecer la posición de la etiqueta
        add(lblNumeroPedido);

        // Campo de texto para ingresar el Número de Pedido
        txtNumeroPedido = new JTextField();
        txtNumeroPedido.setBounds(200, 30, 150, 30);  // Establecer la posición del campo de texto
        add(txtNumeroPedido);

        // Botón para eliminar el pedido
        JButton btnEliminar = new JButton("Eliminar Pedido");
        btnEliminar.setBounds(50, 80, 150, 40);  // Establecer la posición del botón
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPedido();
            }
        });
        add(btnEliminar);

        // Botón para regresar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(200, 80, 150, 40);  // Establecer la posición del botón de regresar
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cerrar la ventana actual
            }
        });
        add(btnRegresar);
    }

    // Método para eliminar el pedido
    private void eliminarPedido() {
        String numPedido = txtNumeroPedido.getText();
        
        if (numPedido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el Número de Pedido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "{CALL EliminarPedido(?)}";
            statement = connection.prepareCall(query);

            // Establecer el parámetro
            statement.setString(1, numPedido);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Pedido eliminado exitosamente.");

            // Limpiar los campos
            txtNumeroPedido.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EliminarPedido ventana = new EliminarPedido();
            ventana.setVisible(true);
        });
    }
}
