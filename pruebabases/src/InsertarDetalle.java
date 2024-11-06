import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

public class InsertarDetalle extends JFrame {

    private JTextField txtIdDetalle, txtNumeroPedido, txtDescripcion, txtPrecio, txtCantidad, txtSubtotal;
    private JButton btnRegresar;

    public InsertarDetalle() {
        setTitle("Insertar Detalle de Pedido");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblIdDetalle = new JLabel("ID Detalle:");
        lblIdDetalle.setBounds(50, 30, 150, 25);
        add(lblIdDetalle);
        txtIdDetalle = new JTextField();
        txtIdDetalle.setBounds(200, 30, 150, 25);
        add(txtIdDetalle);

        JLabel lblNumeroPedido = new JLabel("Número de Pedido:");
        lblNumeroPedido.setBounds(50, 70, 150, 25);
        add(lblNumeroPedido);
        txtNumeroPedido = new JTextField();
        txtNumeroPedido.setBounds(200, 70, 150, 25);
        add(txtNumeroPedido);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 110, 150, 25);
        add(lblDescripcion);
        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(200, 110, 150, 25);
        add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(50, 150, 150, 25);
        add(lblPrecio);
        txtPrecio = new JTextField();
        txtPrecio.setBounds(200, 150, 150, 25);
        add(txtPrecio);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(50, 190, 150, 25);
        add(lblCantidad);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(200, 190, 150, 25);
        add(txtCantidad);

        JLabel lblSubtotal = new JLabel("Subtotal:");
        lblSubtotal.setBounds(50, 230, 150, 25);
        add(lblSubtotal);
        txtSubtotal = new JTextField();
        txtSubtotal.setBounds(200, 230, 150, 25);
        add(txtSubtotal);

        JButton btnInsertar = new JButton("Insertar Detalle");
        btnInsertar.setBounds(200, 270, 150, 30);
        btnInsertar.addActionListener(e -> insertarDetalle());
        add(btnInsertar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(400, 270, 150, 30);
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }

    private void insertarDetalle() {
        String idDetalle = txtIdDetalle.getText();
        String numeroPedido = txtNumeroPedido.getText();
        String descripcion = txtDescripcion.getText();
        int precio = Integer.parseInt(txtPrecio.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());
        int subtotal = Integer.parseInt(txtSubtotal.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701")) {
            String query = "{CALL InsertarDetalle(?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, idDetalle);
            statement.setString(2, numeroPedido);
            statement.setString(3, descripcion);
            statement.setInt(4, precio);
            statement.setInt(5, cantidad);
            statement.setInt(6, subtotal);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Detalle insertado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el detalle: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdDetalle.setText("");
        txtNumeroPedido.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");
    }

    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }
}
