import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EliminarDetalle extends JFrame {

    private JTextField txtIdDetalle;

    public EliminarDetalle() {
        // Configuración de la ventana
        setTitle("Eliminar Detalle de Pedido");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Etiqueta para Id Detalle
        add(new JLabel("Id Detalle:"));
        txtIdDetalle = new JTextField();
        add(txtIdDetalle);

        // Botón para eliminar detalle de pedido
        JButton btnEliminar = new JButton("Eliminar Detalle");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDetallePedido();
            }
        });
        add(btnEliminar);

        // Botón de regresar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana actual
            }
        });
        add(btnRegresar);
    }

    // Método para eliminar detalle de pedido
    private void eliminarDetallePedido() {
        String idDetalle = txtIdDetalle.getText();
        
        if (idDetalle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el Id Detalle", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "{CALL EliminarDetallePedido(?)}";
            statement = connection.prepareCall(query);

            // Establecer el parámetro
            statement.setString(1, idDetalle);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Detalle de pedido eliminado exitosamente.");

            // Limpiar los campos
            txtIdDetalle.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el detalle de pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            EliminarDetalle ventana = new EliminarDetalle();
            ventana.setVisible(true);
        });
    }
}
