package proyecto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class proyecto {

    private static final ArrayList<String> eventos = new ArrayList<>();
    
    // Variable para almacenar la ventana del calendario
    private static JDialog ventanaCalendario = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CAP N' CAP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Ajustar el tamaño de la ventana

        // Crear un panel personalizado que soporte la imagen de fondo
        JPanel panelFondo = new FondoConImagen("assets/imagenes/capibaraFondo1.png");
        panelFondo.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("CAP N' CAP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);  // Cambia el color del texto a blanco para que contraste con el fondo
        panelFondo.add(titleLabel, BorderLayout.NORTH);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));  // Espacio entre los botones
        buttonPanel.setOpaque(false);  // Hacer el panel de botones transparente para ver la imagen de fondo
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        // Botón 1: Calendario
        JButton btnCalendario = new JButton("Mostrar Calendario");
        btnCalendario.addActionListener(e -> mostrarCalendario(frame));  // Pasamos el frame como referencia
        buttonPanel.add(btnCalendario);
        

        // Botón 2: Añadir Evento
        JButton btnAgregarEvento = new JButton("Añadir Evento");
        btnAgregarEvento.addActionListener(e -> agregarEvento());
        buttonPanel.add(btnAgregarEvento);

        // Botón 3: Visualizar Eventos
        JButton btnVisualizarEventos = new JButton("Visualizar Eventos");
        btnVisualizarEventos.addActionListener(e -> visualizarEventos());
        buttonPanel.add(btnVisualizarEventos);

        // Botón 4: Mostrar Imagen
        JButton btnMostrarImagen = new JButton("Mostrar Imagen");
        btnMostrarImagen.addActionListener(e -> mostrarImagen());
        buttonPanel.add(btnMostrarImagen);

        // Añadir el panel de botones al centro
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
                public void windowClosing(java.awt.event.WindowEvent e) {
                    ventanaCalendario = null;  // Liberar la referencia cuando se cierre
                }
            });

            ventanaCalendario.setLocationRelativeTo(parentFrame);
            ventanaCalendario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(parentFrame, "El calendario ya está abierto.");
        }
    }

    private static void agregarEvento() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField nombre = new JTextField("Nombre del evento");
        JTextField fecha = new JTextField("Fecha (DD/MM/YYYY)");
        JTextField hora = new JTextField("Hora (HH:MM)");
        JTextField descripcion = new JTextField("Descripción");
        
        panel.add(nombre);
        panel.add(fecha);
        panel.add(hora);
        panel.add(descripcion);

        int result = JOptionPane.showConfirmDialog(null, panel, "Añadir Evento", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            eventos.add("Nombre: " + nombre.getText() + ", Fecha: " + fecha.getText() + 
                         ", Hora: " + hora.getText() + ", Descripción: " + descripcion.getText());
            JOptionPane.showMessageDialog(null, "Evento agregado.");
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

// Clase para crear un JPanel con una imagen de fondo
class FondoConImagen extends JPanel {
    private Image imagenFondo;

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
