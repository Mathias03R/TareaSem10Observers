package gestiondeempleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GestionEmpleadosVista extends JFrame {
    private List<Empleado> programadores;
    private List<Empleado> diseñadores;
    private List<Empleado> empleados;

    private JTextField txtNombre;
    private JTextField txtPuesto;
    private JTextField txtVacaciones;
    private JComboBox<String> comboEmpleados;
    
    private Observer supervisorProgramadores1 = new Supervisor("Lucas Garcia");
    private Observer supervisorProgramadores2 = new Supervisor("Maria Lopez");
        
    private Observer supervisorDiseñadores1 = new Supervisor("Jose Diaz");
    private Observer supervisorDiseñadores2 = new Supervisor("Manuel Sanchez");

    public GestionEmpleadosVista() {
        programadores = new ArrayList<>();
        diseñadores = new ArrayList<>();
        empleados = new ArrayList<>();
        comboEmpleados = new JComboBox<>();

        setTitle("Gestión de Empleados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Campos de entrada
        txtNombre = new JTextField();
        txtPuesto = new JTextField();
        txtVacaciones = new JTextField();
        comboEmpleados = new JComboBox<>();

        JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
        JButton btnCambiarPuesto = new JButton("Cambiar Puesto");
        JButton btnProgramarVacaciones = new JButton("Programar Vacaciones");

        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Puesto:"));
        add(txtPuesto);
        add(new JLabel("Vacaciones:"));
        add(txtVacaciones);
        add(new JLabel("Empleados:"));
        add(comboEmpleados);
        add(btnAgregarEmpleado);
        add(btnCambiarPuesto);
        add(btnProgramarVacaciones);

        comboEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = (String) comboEmpleados.getSelectedItem();
                for (Empleado emp : empleados) {
                    if (emp.getNombre().equals(nombre)) {
                        txtNombre.setText(emp.getNombre());
                        txtPuesto.setText(emp.getPuesto());
                        if (emp.getVacacionesProgramadas() != null){
                            txtVacaciones.setText(emp.getVacacionesProgramadas());
                        }
                        break;
                    }
                }
            }
        });
        
        btnAgregarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEmpleado();
            }
        });

        btnCambiarPuesto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPuesto();
            }
        });

        btnProgramarVacaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                programarVacaciones();
            }
        });
    }

    private void agregarEmpleado() {
        String nombre = txtNombre.getText();
        String puesto = txtPuesto.getText();

        Empleado empleado = new Empleado(nombre, puesto);
        if ("Programador".equals(puesto) || "programador".equals(puesto)){
            programadores.add(empleado);
            empleados.add(empleado);
            
            empleado.register(supervisorProgramadores1);
            empleado.register(supervisorProgramadores2);
            
            supervisorProgramadores1.setSubject(empleado);
            supervisorProgramadores2.setSubject(empleado);
            
        } else if ("Diseñador".equals(puesto) || "diseñador".equals(puesto)){
            diseñadores.add(empleado);
            empleados.add(empleado);
            
            empleado.register(supervisorDiseñadores1);
            empleado.register(supervisorDiseñadores2);
            
            supervisorDiseñadores1.setSubject(empleado);
            supervisorDiseñadores2.setSubject(empleado);
        }
        comboEmpleados.addItem(nombre);
        empleado.nuevoEmpleado(empleado);
        limpiarCampos();
    }

    private void cambiarPuesto() {
        String nombre = (String) comboEmpleados.getSelectedItem();
        String nuevoPuesto = txtPuesto.getText();

        for (Empleado emp : empleados) {
            if (emp.getNombre().equals(nombre)) {
                emp.changePuesto(nuevoPuesto);
                break;
            }
        }

        limpiarCampos();
    }

    private void programarVacaciones() {
        String nombre = (String) comboEmpleados.getSelectedItem();
        String vacaciones = txtVacaciones.getText();

        for (Empleado emp : empleados) {
            if (emp.getNombre().equals(nombre)) {
                emp.setVacaciones(vacaciones);
                break;
            }
        }

        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPuesto.setText("");
        txtVacaciones.setText("");
    }
}
