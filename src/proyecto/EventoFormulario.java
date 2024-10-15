
package proyecto;

import javax.swing.*;
import java.awt.*;

public class EventoFormulario extends JFrame {

    public EventoFormulario() {
        setTitle("Añadir Evento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para el nombre del evento
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nombre del evento:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(new JTextField(20), gbc);

        // Etiqueta y campos para la fecha
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Fecha:"), gbc);
        JPanel fechaPanel = new JPanel(new FlowLayout());
        fechaPanel.add(new JComboBox<>(new String[]{"Día"}));
        fechaPanel.add(new JComboBox<>(new String[]{"Mes"}));
        fechaPanel.add(new JComboBox<>(new String[]{"Año"}));
        gbc.gridx = 1; gbc.gridy = 1;
        add(fechaPanel, gbc);

        // Etiqueta y campos para la hora
        gbc.gridx = 0; gbc.gridy = 2;
        
        JPanel horaPanel = new JPanel(new FlowLayout());
        ////
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
        ////
        
        
        
        horaPanel.add(new JRadioButton("AM"));
        horaPanel.add(new JRadioButton("PM"));
        gbc.gridx = 1; gbc.gridy = 2;
        add(horaPanel, gbc);
        
        
        
        
        // Etiqueta y área para la descripción
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        add(new JTextArea(3, 20), gbc);

        // Botones OK y Cancelar
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("OK"));
        buttonPanel.add(new JButton("Cancelar"));
        gbc.gridx = 1; gbc.gridy = 4;
        add(buttonPanel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
    }
}