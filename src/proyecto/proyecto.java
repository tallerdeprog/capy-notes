package proyecto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class proyecto {

    private static final ArrayList<String> eventos = new ArrayList<>();
    
    //Almacena la ventana del calendario y agregar eventos
    private static JDialog ventanaCalendario = null;
    private static JDialog ventanaEventoFormulario = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CAP N' CAP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Ajustar el tamaño de la ventana

        //  panel personalizado que soporte la imagen de fondo y capibaras cayendo
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
        btnCalendario.addActionListener(e -> mostrarCalendario(frame));
        buttonPanel.add(btnCalendario);
        
        // Botón 2: Añadir Evento
        JButton btnAgregarEvento = new JButton("Mostrar Evento Calendario");
        btnAgregarEvento.addActionListener(e -> mostrarEventoFormulario(frame));
        buttonPanel.add(btnAgregarEvento);

        // Botón 3: Visualizar Eventos
        JButton btnVisualizarEventos = new JButton("Visualizar Eventos");
        btnVisualizarEventos.addActionListener(e -> visualizarEventos());
        buttonPanel.add(btnVisualizarEventos);

        // Botón 4: Mostrar Imagen
        JButton btnMostrarImagen = new JButton("Mostrar Imagen");
        btnMostrarImagen.addActionListener(e -> mostrarImagen());
        buttonPanel.add(btnMostrarImagen);

        //panel de botones al centro
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
        if (ventanaEventoFormulario == null) {
            ventanaEventoFormulario = new JDialog(parentFrame, "Evento Formulario", true);  
            ventanaEventoFormulario.setSize(400, 400);

            EventoFormulario EventoFormulario = new EventoFormulario();
            ventanaEventoFormulario.add(EventoFormulario);

            ventanaEventoFormulario.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    ventanaEventoFormulario = null;  // 
                }
            });

            ventanaEventoFormulario.setLocationRelativeTo(parentFrame);
            ventanaEventoFormulario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(parentFrame, "El calendario ya está abierto.");
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

// Clase para crear un JPanel con una imagen de fondo y capibaras cayendo
class FondoConImagen extends JPanel {
    private Image imagenFondo;
    private List<Point> posicionesCapibaras;
    private List<Image> imagenesCapibaras;
    private Random random;

/////////////////////////////////////////////////////////////////////////////////////    
    
    
    public FondoConImagen(String rutaImagen) {
        imagenFondo = new ImageIcon(rutaImagen).getImage();
        random = new Random();
        posicionesCapibaras = new ArrayList<>();
        imagenesCapibaras = new ArrayList<>();
        inicializarCapibaras();
        
        // Temporizador para mover los capibaras
        Timer timer = new Timer(50, e -> moverCapibaras());
        timer.start();
    }

    private void inicializarCapibaras() {
        Image imagenCapibara = new ImageIcon("assets/imagenes/capibaraParticula.png").getImage();
        
        for (int i = 0; i < 5; i++) {
            // Asegurarse de que el ancho y alto sean válidos
            int width = getWidth();
            int height = getHeight();
            if (width > 0 && height > 0) {
                posicionesCapibaras.add(new Point(random.nextInt(width), random.nextInt(height)));
                imagenesCapibaras.add(imagenCapibara);
            }
        }
    }

    private void moverCapibaras() {
        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < posicionesCapibaras.size(); i++) {
            Point posicion = posicionesCapibaras.get(i);
            posicion.y += 5;  // Mover los capibaras hacia abajo

            if (posicion.y > height) {
                posicion.y = 0;  // Reiniciar al llegar al fondo
                posicion.x = random.nextInt(width);  // Nueva posición horizontal aleatoria
            }
        }
        repaint();  // Actualizar el dibujo del panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo en todo el panel
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);

        // Dibujar las imágenes de los capibaras
        for (int i = 0; i < posicionesCapibaras.size(); i++) {
            Point posicion = posicionesCapibaras.get(i);
            g.drawImage(imagenesCapibaras.get(i), posicion.x, posicion.y, 50, 50, this);
        }
    }
}
