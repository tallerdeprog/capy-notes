package proyecto;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class proyecto {

    private static final ArrayList<String> eventos = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("CAP N' CAP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("CAP N' CAP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        // Botón 1: Calendario
        JButton btnCalendario = new JButton("Mostrar Calendario");
        btnCalendario.addActionListener(e -> mostrarCalendario());
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

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void mostrarCalendario() {
        // Aquí puedes implementar el calendario
        JOptionPane.showMessageDialog(null, "Función de calendario aún no implementada.");
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
        // Cambia "ruta/a/tu/imagen.jpg" por la ruta real de tu imagen
        ImageIcon imagen = new ImageIcon("ruta/a/tu/imagen.jpg");
        JOptionPane.showMessageDialog(null, imagen, "Imagen", JOptionPane.INFORMATION_MESSAGE);
    }
}
