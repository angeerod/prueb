import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuIngresar extends JFrame {

    public MenuIngresar() {
        // Configuración de la ventana
        setTitle("Menú Agregar");
        setSize(600, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usamos setLayout(null) para usar coordenadas absolutas

        // Crear el JLabel que contendrá la imagen de fondo
        JLabel lblFondo = new JLabel();
        lblFondo.setBounds(0, 0, 600, 400); // Establecer el tamaño y la posición de la imagen de fondo
 
        add(lblFondo); // Añadir el JLabel con la imagen de fondo al JFrame

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea agregar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(150, 20, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Crear los botones y establecer sus coordenadas
        JButton btnInsertarCliente = new JButton("Agregar Cliente");
        btnInsertarCliente.setBounds(200, 80, 200, 40); // Coordenadas para el botón de insertar cliente
        btnInsertarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarCliente();
            }
        });
        add(btnInsertarCliente); // Añadir el botón al JFrame

        JButton btnAgregarColaborador = new JButton("Agregar Colaborador");
        btnAgregarColaborador.setBounds(200, 130, 200, 40); // Coordenadas para el botón de agregar colaborador
        btnAgregarColaborador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarColaborador();
            }
        });
        add(btnAgregarColaborador); // Añadir el botón al JFrame

        JButton btnAgregarCargo = new JButton("Agregar Cargo");
        btnAgregarCargo.setBounds(200, 180, 200, 40); // Coordenadas para el botón de agregar cargo
        btnAgregarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarCargo();
            }
        });
        add(btnAgregarCargo); // Añadir el botón al JFrame

        JButton btnAgregarPedido = new JButton("Agregar Pedido");
        btnAgregarPedido.setBounds(200, 230, 200, 40); // Coordenadas para el botón de agregar pedido
        btnAgregarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarPedido();
            }
        });
        add(btnAgregarPedido); // Añadir el botón al JFrame

        JButton btnAgregarDetallePedido = new JButton("Agregar Detalle de Pedido");
        btnAgregarDetallePedido.setBounds(200, 280, 200, 40); // Coordenadas para el botón de agregar detalle de pedido
        btnAgregarDetallePedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarDetalle();
            }
        });
        add(btnAgregarDetallePedido); // Añadir el botón al JFrame

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
    

    private void abrirVentanaInsertarCliente() {
        InsertarCliente ventanaCliente = new InsertarCliente();
        ventanaCliente.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

     // Método para abrir la ventana InsertarColaborador
     private void abrirVentanaInsertarColaborador() {
        InsertarColaborador ventanaColaborador = new InsertarColaborador();
        ventanaColaborador.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

       // Método para abrir la ventana InsertarCargo
       private void abrirVentanaInsertarCargo() {
        InsertarCargo ventanaCargo = new InsertarCargo();
        ventanaCargo.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

      // Método para abrir la ventana InsertarPedido
      private void abrirVentanaInsertarPedido() {
        InsertarPedido ventanaPedido = new InsertarPedido();
        ventanaPedido.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }
    
    // Método para abrir la ventana InsertarDetallePedido
    private void abrirVentanaInsertarDetalle() {
        InsertarDetalle ventanaDetalle = new InsertarDetalle();
        ventanaDetalle.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuIngresar menuIngresar = new MenuIngresar();
            menuIngresar.setVisible(true);
        });
    }
}
