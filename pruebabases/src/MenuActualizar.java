import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActualizar extends JFrame {

    public MenuActualizar() {
        // Configuración de la ventana
        setTitle("Menú Actualizar");
        setSize(600, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usamos setLayout(null) para usar coordenadas absolutas

        // Crear el JLabel que contendrá la imagen de fondo (opcional)
        JLabel lblFondo = new JLabel();
        lblFondo.setBounds(0, 0, 600, 400); // Establecer el tamaño y la posición de la imagen de fondo
        add(lblFondo); // Añadir el JLabel con la imagen de fondo al JFrame

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea actualizar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(150, 20, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Crear los botones y establecer sus coordenadas para actualizar
        JButton btnActualizarCliente = new JButton("Actualizar Cliente");
        btnActualizarCliente.setBounds(200, 80, 200, 40); // Coordenadas para el botón de actualizar cliente
        add(btnActualizarCliente); // Añadir el botón al JFrame

        JButton btnActualizarColaborador = new JButton("Actualizar Colaborador");
        btnActualizarColaborador.setBounds(200, 130, 200, 40); // Coordenadas para el botón de actualizar colaborador
        add(btnActualizarColaborador); // Añadir el botón al JFrame

        JButton btnActualizarCargo = new JButton("Actualizar Cargo");
        btnActualizarCargo.setBounds(200, 180, 200, 40); // Coordenadas para el botón de actualizar cargo
        add(btnActualizarCargo); // Añadir el botón al JFrame

        JButton btnActualizarPedido = new JButton("Actualizar Pedido");
        btnActualizarPedido.setBounds(200, 230, 200, 40); // Coordenadas para el botón de actualizar pedido
        add(btnActualizarPedido); // Añadir el botón al JFrame

        JButton btnActualizarDetallePedido = new JButton("Actualizar Detalle de Pedido");
        btnActualizarDetallePedido.setBounds(200, 280, 200, 40); // Coordenadas para el botón de actualizar detalle de pedido
        add(btnActualizarDetallePedido); // Añadir el botón al JFrame

            // Crear el botón "Regresar"
    JButton btnRegresar = new JButton("Regresar");
    btnRegresar.setBounds(30, 340, 100, 30); // Establecer las coordenadas para el botón "Regresar"
    btnRegresar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            regresarAlMenuInicial(); // Regresar al menú inicial
        }
    });
    add(btnRegresar); // Añadir el botón al JFrame
}

// Método para regresar al menú inicial
private void regresarAlMenuInicial() {
    MenuInicial menuInicial = new MenuInicial();
    menuInicial.setVisible(true);
    this.dispose(); // Cerrar la ventana actual
}
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuActualizar menuActualizar = new MenuActualizar();
            menuActualizar.setVisible(true);
        });
    }
}
