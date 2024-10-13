package proyecto;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class calendario extends JPanel {

    public calendario() {
        setLayout(new BorderLayout());

        // Panel principal para contener la imagen, calendario y texto
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Cargar la imagen personalizada
        ImageIcon imagenPersonalizada = new ImageIcon("assets/imagenes/capibaraFondo1.png");
        JLabel labelImagen = new JLabel(imagenPersonalizada);

        // Panel para el calendario y margen
        JPanel panelCalendarioConMargen = new JPanel();
        panelCalendarioConMargen.setLayout(new BorderLayout());
        panelCalendarioConMargen.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));  // Margen a los lados

        // Panel para el calendario (7 columnas y 6 filas en total)
        JPanel panelCalendario = new JPanel();
        panelCalendario.setLayout(new GridLayout(6, 7)); // 7 filas y 7 columnas (una para los días de la semana)

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        int diaActual = fechaActual.getDayOfMonth();

        // Días de la semana (empieza en domingo)
        String[] diasSemana = {"D", "L", "M", "M", "J", "V", "S"};
        for (String dia : diasSemana) {
            JLabel labelDia = new JLabel(dia, SwingConstants.CENTER);
            labelDia.setFont(new Font("Arial", Font.BOLD, 16));
            labelDia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
        panelCalendarioConMargen.add(labelMesAno, BorderLayout.NORTH);

        // Añadir el calendario con margen al centro
        panelCalendarioConMargen.add(panelCalendario, BorderLayout.CENTER);

        // Añadir el calendario a la derecha con margen
        panelPrincipal.add(panelCalendarioConMargen, BorderLayout.EAST);

        // Añadir la imagen a la izquierda
        panelPrincipal.add(labelImagen, BorderLayout.WEST);

        // Texto personalizado en la parte inferior
        //JLabel labelTexto = new JLabel("", SwingConstants.CENTER);
        //labelTexto.setFont(new Font("Arial", Font.BOLD, 24));

        // Añadir el panel principal y el texto al contenedor
        add(panelPrincipal, BorderLayout.CENTER);
        //add(labelTexto, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendario Personalizado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);  // Ajustar tamaño de la ventana para mejor presentación

        // Crear el calendario personalizado
        calendario calendario = new calendario();

        // Añadir el calendario personalizado al frame
        frame.add(calendario);
        frame.setLocationRelativeTo(null);  // Centrar ventana en pantalla
        frame.setVisible(true);
    }
}
