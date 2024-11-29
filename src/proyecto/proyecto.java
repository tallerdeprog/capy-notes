package proyecto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class proyecto {

    private static final ArrayList<String> eventos = new ArrayList<>();
    
    // Almacena la ventana del calendario y agregar eventos
    private static JDialog ventanaCalendario = null;
    private static JDialog ventanaEventoFormulario = null;
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("CAP N' CAP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Ajustar el tamaño de la ventana

        // Panel personalizado que soporte la imagen de fondo
        JPanel panelFondo = new FondoConImagen("assets/imagenes/capibaraFondo1.png");
        panelFondo.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("CAP N' CAP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);  
        panelFondo.add(titleLabel, BorderLayout.NORTH);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));  // Espacio entre los botones
        buttonPanel.setOpaque(false);  // Transparencia
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        // Botón 1: Calendario
        JButton btnCalendario = new JButton("Mostrar Calendario");
        btnCalendario.addActionListener(a -> mostrarCalendario(frame));
        buttonPanel.add(btnCalendario);
        
        // Botón 2: Añadir Evento
        JButton btnAgregarEvento = new JButton("Mostrar Evento Calendario");
        btnAgregarEvento.addActionListener(b -> mostrarEventoFormulario(frame));
        buttonPanel.add(btnAgregarEvento);

        // Botón 3: Visualizar Eventos
        JButton btnVisualizarEventos = new JButton("Visualizar Eventos");
        btnVisualizarEventos.addActionListener(c -> visualizarEventos());
        buttonPanel.add(btnVisualizarEventos);

        // Botón 4: Mostrar Imagen
        JButton btnMostrarImagen = new JButton("Mostrar Imagen");
        btnMostrarImagen.addActionListener(d -> mostrarImagen());
        buttonPanel.add(btnMostrarImagen);

        // Panel de botones al centro
        panelFondo.add(buttonPanel, BorderLayout.CENTER);

        // Añadir el panel de fondo al frame
        frame.add(panelFondo);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void mostrarCalendario(JFrame parentFrame) {
        if (ventanaCalendario == null) {
            ventanaCalendario = new JDialog(parentFrame, "Calendario", true);  // Ventana modal
            ventanaCalendario.setSize(400, 400);

            calendario calendario = new calendario();
            ventanaCalendario.add(calendario);

            ventanaCalendario.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent a) {
                    ventanaCalendario = null;  
                }
            });

            ventanaCalendario.setLocationRelativeTo(parentFrame);
            ventanaCalendario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(parentFrame, "El calendario ya está abierto.");
        }
    }

    private static void mostrarEventoFormulario(JFrame parentFrame) {
    // Verificar si el JDialog ya está abierto
    if (ventanaEventoFormulario == null || !ventanaEventoFormulario.isVisible()) {
        // Crear el JDialog y configurarlo si no está abierto
        ventanaEventoFormulario = new JDialog(parentFrame, "Formulario de Evento", true);
        ventanaEventoFormulario.setSize(500, 400);

        // Crear el panel del formulario
        EventoFormulario formulario = new EventoFormulario();
        JPanel formularioPanel = formulario.createFormularioPanel();  // Obtener el panel con los componentes
        ventanaEventoFormulario.add(formularioPanel);  // Agregar el panel al JDialog

        // Mostrar el JDialog
        ventanaEventoFormulario.setLocationRelativeTo(parentFrame);  // Centrar sobre la ventana principal
        ventanaEventoFormulario.setVisible(true);
    } else {
        // Mostrar mensaje si ya está abierto
        JOptionPane.showMessageDialog(parentFrame, "El formulario ya está abierto.");
    }
}





    private static void visualizarEventos() {
        if (eventos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay eventos anotados.");
        } else {
            StringBuilder listaEventos = new StringBuilder();
            for (String evento : eventos) {
                listaEventos.append(evento).append("\n");
            }
            JOptionPane.showMessageDialog(null, listaEventos.toString(), "Eventos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void mostrarImagen() {
        ImageIcon imagen = new ImageIcon("assets/imagenes/capibara.jpg");
        JOptionPane.showMessageDialog(null, imagen, "capibara", JOptionPane.INFORMATION_MESSAGE);
    }
}

// Clase para crear un JPanel con una imagen de fondo (sin partículas)
class FondoConImagen extends JPanel {
    private final Image imagenFondo;

    public FondoConImagen(String rutaImagen) {
        imagenFondo = new ImageIcon(rutaImagen).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo en todo el panel
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
}

