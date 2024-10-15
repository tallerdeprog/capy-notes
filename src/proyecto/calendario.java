package proyecto;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import javax.swing.Timer;

public class calendario extends JPanel {

    private JLabel labelHora;

    public calendario() {
        setLayout(new BorderLayout());

        // Panel principal para contener el calendario
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Cargar la imagen personalizada
        ImageIcon imagenPersonalizada = new ImageIcon("assets/imagenes/capibaraFondo1.png");

        // Crear un panel para que la imagen cubra todo el fondo
        JPanel panelConImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar la imagen ajustada al tamaño del panel
                g.drawImage(imagenPersonalizada.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelConImagen.setLayout(new BorderLayout()); // Usar BorderLayout para colocar el calendario encima

        // Crear la etiqueta para la hora
        labelHora = new JLabel();
        labelHora.setFont(new Font("Arial", Font.BOLD, 24));
        labelHora.setForeground(Color.BLACK);  // Cambia el color a blanco para que sea visible sobre la imagen
        labelHora.setHorizontalAlignment(SwingConstants.CENTER);

        // Crear un panel que contendrá tanto la hora como el calendario encima de la imagen
        JPanel panelSobreImagen = new JPanel(new BorderLayout());
        panelSobreImagen.setOpaque(false); // Hacer transparente para ver la imagen debajo

        // Panel para el calendario (7 columnas y 6 filas en total)
        JPanel panelCalendario = new JPanel();
        panelCalendario.setLayout(new GridLayout(6, 7)); // 7 filas y 7 columnas (una para los días de la semana)
        panelCalendario.setOpaque(false); // Transparente para que la imagen de fondo sea visible

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        int diaActual = fechaActual.getDayOfMonth();

        // Días de la semana (empieza en domingo)
        String[] diasSemana = {"D", "L", "M", "M", "J", "V", "S"};
        for (String dia : diasSemana) {
            JLabel labelDia = new JLabel(dia, SwingConstants.CENTER);
            labelDia.setFont(new Font("Arial", Font.BOLD, 16));
            labelDia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelDia.setOpaque(true);
            labelDia.setBackground(new Color(255, 255, 255, 150)); // Fondo semitransparente para las cabeceras
            panelCalendario.add(labelDia);
        }

        // Obtener el mes y el año actuales
        YearMonth mesActual = YearMonth.now();
        int diasEnMes = mesActual.lengthOfMonth();  // Días en el mes actual
        String nombreMes = mesActual.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        int añoActual = mesActual.getYear();

        // Día inicial del mes (en el calendario dominical, 7: Domingo, 1: Lunes)
        int primerDia = mesActual.atDay(1).getDayOfWeek().getValue();  // Lunes = 1, Domingo = 7

        // Ajustar para que el domingo sea el último (convertimos 7 en 0 para que comience en domingo)
        primerDia = primerDia % 7;

        // Añadir espacios vacíos hasta el primer día del mes
        for (int i = 0; i < primerDia; i++) {
            panelCalendario.add(new JLabel(""));  // Celdas vacías
        }

        // Añadir los días del mes y resaltar el día actual
        for (int i = 1; i <= diasEnMes; i++) {
            JLabel labelDiaMes = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            labelDiaMes.setFont(new Font("Arial", Font.PLAIN, 14));
            labelDiaMes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelDiaMes.setOpaque(true);
            labelDiaMes.setBackground(new Color(255, 255, 255, 150)); // Fondo semitransparente

            if (i == diaActual) {
                labelDiaMes.setOpaque(true);
                labelDiaMes.setBackground(Color.YELLOW);  // Resaltar el día actual
                labelDiaMes.setBorder(BorderFactory.createLineBorder(Color.RED, 2));  // Borde rojo
            }

            panelCalendario.add(labelDiaMes);
        }

        // Panel superior con el nombre del mes y año
        JLabel labelMesAno = new JLabel(nombreMes.toUpperCase() + " " + añoActual, SwingConstants.CENTER);
        labelMesAno.setFont(new Font("Arial", Font.BOLD, 24));
        labelMesAno.setForeground(Color.WHITE); // Texto blanco para contraste
        labelMesAno.setOpaque(true);
        labelMesAno.setBackground(new Color(0, 0, 0, 150)); // Fondo semitransparente para el mes/año

        // Añadir el calendario con margen al centro del panel transparente
        panelSobreImagen.add(labelMesAno, BorderLayout.NORTH);
        panelSobreImagen.add(panelCalendario, BorderLayout.CENTER);

        // Añadir la hora al panel superior
        panelSobreImagen.add(labelHora, BorderLayout.SOUTH);

        // Añadir el panel con la imagen al panel principal
        panelConImagen.add(panelSobreImagen, BorderLayout.CENTER);

        // Añadir el panel completo al contenedor
        add(panelConImagen, BorderLayout.CENTER);

        // Actualizar la hora inmediatamente antes de iniciar el temporizador
        actualizarHora();

        // Iniciar el temporizador para actualizar la hora cada segundo
        iniciarTemporizador();
    }

    // Método para iniciar el temporizador que actualiza la hora cada segundo
    private void iniciarTemporizador() {
        Timer timer = new Timer(1000, e -> actualizarHora());
        timer.start();
    }

    // Método para obtener y actualizar la hora actual
    private void actualizarHora() {
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        labelHora.setText(horaActual.format(formatoHora));  // Actualizar el JLabel con la hora actual
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendario Personalizado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);  // Ajustar tamaño de la ventana para que todo se vea bien

        // Crear el calendario personalizado
        calendario calendario = new calendario();

        // Añadir el calendario personalizado al frame
        frame.add(calendario);
        frame.setLocationRelativeTo(null);  // Centrar ventana en pantalla
        frame.setVisible(true);

        // Permitir que se ajuste al cambiar el tamaño de la ventana
        frame.setResizable(true);
    }
}
