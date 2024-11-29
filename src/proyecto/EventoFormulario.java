package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoFormulario {

    // Método para crear el formulario del evento
    public JPanel createFormularioPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 15);  // Más margen izquierdo y derecho, menos arriba y abajo
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para el nombre del evento
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre del evento:"), gbc);
        JTextField nombreEvento = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(nombreEvento, gbc);

        // Etiqueta y campos para la fecha
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Fecha:"), gbc);
        JPanel fechaPanel = new JPanel(new FlowLayout());

        // Días
        String[] dias = new String[31];
        for (int i = 0; i < 31; i++) {
            dias[i] = String.format("%02d", i + 1); // Días de 01 a 31
        }
        JComboBox<String> comboDías = new JComboBox<>(dias);
        fechaPanel.add(comboDías);

        // Meses
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        JComboBox<String> comboMeses = new JComboBox<>(meses);
        fechaPanel.add(comboMeses);

        // Años
        String[] años = new String[101];
        for (int i = 0; i < 101; i++) {
            años[i] = String.valueOf(2024 + i); // Ejemplo de años entre 2024 y 2124
        }
        JComboBox<String> comboAños = new JComboBox<>(años);
        fechaPanel.add(comboAños);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(fechaPanel, gbc);

        // Etiqueta y campos para la hora
        gbc.gridx = 0; gbc.gridy = 2;
        JPanel horaPanel = new JPanel(new FlowLayout());
        JLabel labelHoras = new JLabel("Hora:");
        horaPanel.add(labelHoras);
        String[] horas = new String[13];
        for (int i = 0; i <= 12; i++) {
            horas[i] = String.format("%02d", i); // Formatea a dos dígitos
        }
        JComboBox<String> comboHoras = new JComboBox<>(horas);
        horaPanel.add(comboHoras);

        // Etiqueta y ComboBox para minutos
        JLabel labelMinutos = new JLabel("Minuto:");
        horaPanel.add(labelMinutos);
        String[] minutos = new String[60];
        for (int i = 0; i < 60; i++) {
            minutos[i] = String.format("%02d", i); // Formatea a dos dígitos
        }
        JComboBox<String> comboMinutos = new JComboBox<>(minutos);
        horaPanel.add(comboMinutos);

        // Botones AM/PM
        ButtonGroup group = new ButtonGroup();
        JRadioButton amButton = new JRadioButton("AM");
        JRadioButton pmButton = new JRadioButton("PM");
        group.add(amButton);
        group.add(pmButton);
        horaPanel.add(amButton);
        horaPanel.add(pmButton);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(horaPanel, gbc);

        // Etiqueta y área para la descripción
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        JTextArea textArea = new JTextArea(5, 20);  // Aumenté el tamaño
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, gbc);

        // Botones OK y Cancelar
        JPanel buttonPanel = new JPanel();
        JButton btnOk = new JButton("OK");
btnOk.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String eventoNombre = nombreEvento.getText();
        String fechaSeleccionada = comboDías.getSelectedItem() + " " + comboMeses.getSelectedItem() + " " + comboAños.getSelectedItem();
        String horaSeleccionada = comboHoras.getSelectedItem() + ":" + comboMinutos.getSelectedItem() + " " + (amButton.isSelected() ? "AM" : "PM");
        String descripcion = textArea.getText();

        if (eventoNombre.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        } else {
            JOptionPane.showMessageDialog(null, "Evento agregado:\n" + eventoNombre + "\n" + fechaSeleccionada + "\n" + horaSeleccionada + "\n" + descripcion);
            // Cerrar el JDialog después de agregar el evento
            Window window = SwingUtilities.getWindowAncestor(panel);  // Obtiene la ventana contenedora
            if (window != null) {
                window.dispose();  // Cierra la ventana
            }
        }
    }
});
        buttonPanel.add(btnOk);

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual (JDialog o JFrame)
                Window window = SwingUtilities.getWindowAncestor(panel);  // Obtiene la ventana contenedora
                if (window != null) {
                    window.dispose();  // Cierra la ventana
                }
            }
        });
        buttonPanel.add(btnCancel);

        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(buttonPanel, gbc);

        return panel; // Devolver el panel
    }
}



