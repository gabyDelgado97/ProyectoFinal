package capa_de_Presentacion;

import prueba.Prueba;
import static capa_Logica_Negocios.ObtenerInformacion.NombresTablas;
import static capa_Logica_Negocios.ObtenerInformacion.obtenerSeleccionar;
import static capa_Logica_Negocios.ObtenerInformacion.obtenerUnir;
import capa_de_datos.Tabla_Script;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class EntornoMain extends javax.swing.JFrame {

    private String[] nombreTablas;
    private static int indiceBoton;
    private String Mensaje;
    private String Consola;
    private Prueba pruebaMain;
    private Tabla_Script tablascript;
    private long time_start, time_end;

    public EntornoMain() {
        initComponents();
        this.nombreTablas = NombresTablas();
        this.indiceBoton = 0;
        pruebaMain = new Prueba();
    }

    public static int getIndiceBoton() {
        return indiceBoton;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jTextFieldEjecutar = new javax.swing.JTextField();
        jButtonEjecutar = new javax.swing.JButton();
        jScrollPaneDirectorios = new javax.swing.JScrollPane();
        jPanelDirectorios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSalida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaConsulta = new javax.swing.JTextArea();
        jButtonLimpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        botonCrearRegistro = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        botonModificarRegistro = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        botonEliminarRegistro = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        botonSeleccionarRegistro = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        botonCrearTabla = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        botonModificarTabla = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        botonEliminarTabla = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        botonJoinTabla = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botonCrearConScript = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cpuTime = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        wallTime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema LCE UC");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                mostrarTablaWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Verdana", 1, 38)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText(" Sistema LCE UC");
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 360, 40));

        jTextFieldEjecutar.setFont(new java.awt.Font("Verdana", 1, 19)); // NOI18N
        jTextFieldEjecutar.setForeground(new java.awt.Color(0, 0, 204));
        jTextFieldEjecutar.setToolTipText("");
        jTextFieldEjecutar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Linea de Comandos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        jTextFieldEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEjecutarActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 750, 70));

        jButtonEjecutar.setBackground(new java.awt.Color(153, 0, 2));
        jButtonEjecutar.setFont(new java.awt.Font("Verdana", 0, 19)); // NOI18N
        jButtonEjecutar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aceptarsentencia.png"))); // NOI18N
        jButtonEjecutar.setToolTipText("Ejecutar sentencia");
        jButtonEjecutar.setBorder(null);
        jButtonEjecutar.setBorderPainted(false);
        jButtonEjecutar.setContentAreaFilled(false);
        jButtonEjecutar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEjecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEjecutar.setIconTextGap(-3);
        jButtonEjecutar.setMargin(new java.awt.Insets(5, 15, 5, 15));
        jButtonEjecutar.setMaximumSize(new java.awt.Dimension(51, 51));
        jButtonEjecutar.setMinimumSize(new java.awt.Dimension(51, 51));
        jButtonEjecutar.setPreferredSize(new java.awt.Dimension(51, 51));
        jButtonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 70, 70));

        jScrollPaneDirectorios.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneDirectorios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 2), 2));
        jScrollPaneDirectorios.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPaneDirectorios.setToolTipText("");

        jPanelDirectorios.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDirectorios.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Base de Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanelDirectorios.setForeground(new java.awt.Color(102, 102, 102));
        jPanelDirectorios.setToolTipText("");
        jPanelDirectorios.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPaneDirectorios.setViewportView(jPanelDirectorios);

        getContentPane().add(jScrollPaneDirectorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 310, 390));

        jTextAreaSalida.setEditable(false);
        jTextAreaSalida.setColumns(20);
        jTextAreaSalida.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jTextAreaSalida.setRows(5);
        jTextAreaSalida.setAlignmentX(50.0F);
        jTextAreaSalida.setAlignmentY(50.0F);
        jTextAreaSalida.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        jScrollPane1.setViewportView(jTextAreaSalida);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 550, 100));

        jTextAreaConsulta.setEditable(false);
        jTextAreaConsulta.setColumns(20);
        jTextAreaConsulta.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jTextAreaConsulta.setRows(5);
        jTextAreaConsulta.setAlignmentX(50.0F);
        jTextAreaConsulta.setAlignmentY(50.0F);
        jTextAreaConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        jScrollPane2.setViewportView(jTextAreaConsulta);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 550, 390));

        jButtonLimpiar.setBackground(new java.awt.Color(153, 0, 2));
        jButtonLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancelarsentencia.png"))); // NOI18N
        jButtonLimpiar.setToolTipText("Limpiar");
        jButtonLimpiar.setBorder(null);
        jButtonLimpiar.setContentAreaFilled(false);
        jButtonLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLimpiar.setMargin(new java.awt.Insets(5, 15, 5, 15));
        jButtonLimpiar.setMaximumSize(new java.awt.Dimension(51, 51));
        jButtonLimpiar.setMinimumSize(new java.awt.Dimension(51, 51));
        jButtonLimpiar.setPreferredSize(new java.awt.Dimension(51, 51));
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 60, 70));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        botonCrearRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/creartabla.png"))); // NOI18N
        botonCrearRegistro.setToolTipText("Nuevo Registro en Tabla");
        botonCrearRegistro.setMargin(new java.awt.Insets(5, 5, 5, 5));
        botonCrearRegistro.setMaximumSize(new java.awt.Dimension(65, 65));
        botonCrearRegistro.setMinimumSize(new java.awt.Dimension(65, 65));
        botonCrearRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearRegistroActionPerformed(evt);
            }
        });
        jPanel4.add(botonCrearRegistro);

        jLabel12.setText("Crear");
        jPanel4.add(jLabel12);

        botonModificarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificartabla.png"))); // NOI18N
        botonModificarRegistro.setToolTipText("Modificar Registro");
        botonModificarRegistro.setMargin(new java.awt.Insets(2, 5, 2, 5));
        botonModificarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarRegistroActionPerformed(evt);
            }
        });
        jPanel4.add(botonModificarRegistro);

        jLabel13.setText("Modificar");
        jPanel4.add(jLabel13);

        botonEliminarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borrartabla.png"))); // NOI18N
        botonEliminarRegistro.setToolTipText("Eliminar Registro");
        botonEliminarRegistro.setMargin(new java.awt.Insets(2, 5, 5, 5));
        botonEliminarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarRegistroActionPerformed(evt);
            }
        });
        jPanel4.add(botonEliminarRegistro);

        jLabel14.setText("Eliminar");
        jPanel4.add(jLabel14);

        botonSeleccionarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seleccionarregistro.png"))); // NOI18N
        botonSeleccionarRegistro.setToolTipText("Seleccionar Registros");
        botonSeleccionarRegistro.setMargin(new java.awt.Insets(2, 5, 2, 5));
        botonSeleccionarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSeleccionarRegistroActionPerformed(evt);
            }
        });
        jPanel4.add(botonSeleccionarRegistro);

        jLabel9.setText("Seleccionar");
        jPanel4.add(jLabel9);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 80, 360));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        botonCrearTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/creartabla.png"))); // NOI18N
        botonCrearTabla.setToolTipText("Crear Nueva Tabla");
        botonCrearTabla.setMargin(new java.awt.Insets(5, 5, 5, 5));
        botonCrearTabla.setMaximumSize(new java.awt.Dimension(65, 65));
        botonCrearTabla.setMinimumSize(new java.awt.Dimension(65, 65));
        botonCrearTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearTablaActionPerformed(evt);
            }
        });
        jPanel5.add(botonCrearTabla);

        jLabel15.setText("Crear");
        jPanel5.add(jLabel15);

        botonModificarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificartabla.png"))); // NOI18N
        botonModificarTabla.setToolTipText("Modificar Tabla");
        botonModificarTabla.setMargin(new java.awt.Insets(2, 5, 2, 5));
        botonModificarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarTablaActionPerformed(evt);
            }
        });
        jPanel5.add(botonModificarTabla);

        jLabel16.setText("Modificar");
        jPanel5.add(jLabel16);

        botonEliminarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borrartabla.png"))); // NOI18N
        botonEliminarTabla.setToolTipText("Eliminar Tabla");
        botonEliminarTabla.setMargin(new java.awt.Insets(2, 5, 5, 5));
        botonEliminarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarTablaActionPerformed(evt);
            }
        });
        jPanel5.add(botonEliminarTabla);

        jLabel17.setText("Eliminar");
        jPanel5.add(jLabel17);

        botonJoinTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/jointablas.png"))); // NOI18N
        botonJoinTabla.setToolTipText("Join de Tablas");
        botonJoinTabla.setMargin(new java.awt.Insets(1, 5, 1, 5));
        botonJoinTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJoinTablaActionPerformed(evt);
            }
        });
        jPanel5.add(botonJoinTabla);

        jLabel5.setText("Join");
        jPanel5.add(jLabel5);

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 370));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        botonCrearConScript.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botoncrearconarchivo.png"))); // NOI18N
        botonCrearConScript.setMargin(new java.awt.Insets(2, 5, 2, 5));
        botonCrearConScript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearConScriptActionPerformed(evt);
            }
        });
        jPanel1.add(botonCrearConScript);

        jLabel2.setText("Tabla-Script");
        jPanel1.add(jLabel2);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 620, 80, 100));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CPU TIME:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        cpuTime.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cpuTime.setText("0.00 milisegundos");
        cpuTime.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(cpuTime);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 620, 220, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "WALL TIME:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        wallTime.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        wallTime.setText("0.00 milisegundos");
        jPanel3.add(wallTime);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 670, 220, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1001, 733));
        jLabel1.setMinimumSize(new java.awt.Dimension(1001, 733));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEjecutarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEjecutarActionPerformed

    private void mostrarTablaWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_mostrarTablaWindowOpened
        crearBotones();
    }//GEN-LAST:event_mostrarTablaWindowOpened

    private void jButtonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutarActionPerformed
        if (!"".equals(jTextFieldEjecutar.getText().trim())) {
            time_start = System.currentTimeMillis();
            if (pruebaMain.Ejecutar(jTextFieldEjecutar.getText().trim())) {
                time_end = System.currentTimeMillis();
                jTextAreaSalida.setText("Sentencia ejecutada correctamente!!");
                wallTime.setText(String.valueOf((time_end-time_start))+" milisegundos");
                jTextAreaSalida.setForeground(Color.GREEN);
                this.nombreTablas = NombresTablas();
                jPanelDirectorios.removeAll();
                crearBotones();
                if (obtenerSeleccionar() != null) {
                    if (!obtenerSeleccionar().equals("")) {
                        jTextAreaConsulta.setText(obtenerSeleccionar());
                    }
                }
                if (obtenerUnir() != null) {
                    if (!obtenerUnir().equals("")) {
                        jTextAreaConsulta.setText(obtenerUnir());
                    }
                }
            } else {
                jTextAreaSalida.setText(Prueba.errores);
                jTextAreaSalida.setForeground(Color.RED);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Línea de comando vacía!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonEjecutarActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        jTextAreaConsulta.setText("");
        jTextAreaSalida.setText("");
        jTextFieldEjecutar.setText("");
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void botonCrearTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearTablaActionPerformed
        String sentenciacrearTabla = "CREAR TABLA nombre_tabla CAMPOS campo1,campo2 CLAVE campo1 LONGITUD longCampo1,longCampo2 ENCRIPTADO campo1";
        jTextFieldEjecutar.setText(sentenciacrearTabla);
    }//GEN-LAST:event_botonCrearTablaActionPerformed

    private void botonModificarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarTablaActionPerformed
        String sentenciamodificarTabla = "MODIFICAR TABLA nombre_tabla CAMPO nombre_campo POR nombre_campo_nuevo";
        jTextFieldEjecutar.setText(sentenciamodificarTabla);
    }//GEN-LAST:event_botonModificarTablaActionPerformed

    private void botonEliminarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarTablaActionPerformed
        String sentenciaeliminarTabla = "ELIMINAR TABLA nombre_tabla";
        jTextFieldEjecutar.setText(sentenciaeliminarTabla);
    }//GEN-LAST:event_botonEliminarTablaActionPerformed

    private void botonJoinTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJoinTablaActionPerformed
        String sentenciaJoinTablas = "UNIR nombre_tabla1, nombre_tabla2 POR nombre_campo = ”Algo” ORDENADO asc/desc VER número_registros";
        jTextFieldEjecutar.setText(sentenciaJoinTablas);
    }//GEN-LAST:event_botonJoinTablaActionPerformed

    private void botonCrearRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearRegistroActionPerformed
        String sentenciaCrearRegistro = "INSERTAR EN nombre_tabla VALORES vCampo1,vCampo2,vCampoN";
        jTextFieldEjecutar.setText(sentenciaCrearRegistro);
    }//GEN-LAST:event_botonCrearRegistroActionPerformed

    private void botonModificarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarRegistroActionPerformed
        String sentenciaModificarRegistro = "ACTUALIZAR REGISTRO nombre_tabla CLAVE valorCampoClave CAMPO campo POR valor_campo_nuevo";
        jTextFieldEjecutar.setText(sentenciaModificarRegistro);
    }//GEN-LAST:event_botonModificarRegistroActionPerformed

    private void botonEliminarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarRegistroActionPerformed
        String sentenciaEliminarRegistro = "BORRAR REGISTRO nombre_tabla CLAVE valorCampoClave";
        jTextFieldEjecutar.setText(sentenciaEliminarRegistro);
    }//GEN-LAST:event_botonEliminarRegistroActionPerformed

    private void botonSeleccionarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSeleccionarRegistroActionPerformed
        String sentenciaSeleccionarRegistro = "SELECCIONAR DE nombre_tabla DONDE nombre_campo ORDENADO asc/desc VER número_registros";
        jTextFieldEjecutar.setText(sentenciaSeleccionarRegistro);
    }//GEN-LAST:event_botonSeleccionarRegistroActionPerformed

    private void botonCrearConScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearConScriptActionPerformed
        String sentenciaSeleccionarRegistro = "Crear tabla a partir de un Script";
        jTextFieldEjecutar.setText(sentenciaSeleccionarRegistro);
        tablascript = new Tabla_Script();
        String input = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo: ");
        String tabla = JOptionPane.showInputDialog(null, "Ahora ingrese el nombre de la tabla a crear: ");
        try {
            time_start = System.currentTimeMillis();
            tablascript.crearConScript(input,tabla);
            time_end = System.currentTimeMillis();
            wallTime.setText(String.valueOf((time_end-time_start))+" milisegundos");
        } catch (IOException ex) {
            Logger.getLogger(EntornoMain.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "¡El nombre del archivo es incorrecto!");
        }
    }//GEN-LAST:event_botonCrearConScriptActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntornoMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntornoMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntornoMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntornoMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntornoMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrearConScript;
    private javax.swing.JButton botonCrearRegistro;
    private javax.swing.JButton botonCrearTabla;
    private javax.swing.JButton botonEliminarRegistro;
    private javax.swing.JButton botonEliminarTabla;
    private javax.swing.JButton botonJoinTabla;
    private javax.swing.JButton botonModificarRegistro;
    private javax.swing.JButton botonModificarTabla;
    private javax.swing.JButton botonSeleccionarRegistro;
    private javax.swing.JLabel cpuTime;
    private javax.swing.JButton jButtonEjecutar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelDirectorios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneDirectorios;
    private javax.swing.JTextArea jTextAreaConsulta;
    private javax.swing.JTextArea jTextAreaSalida;
    private javax.swing.JTextField jTextFieldEjecutar;
    private javax.swing.JLabel wallTime;
    // End of variables declaration//GEN-END:variables

    private void crearBotones() {
        JButton botonBD = new JButton("MetaDataBD");
        botonBD.setBackground(java.awt.Color.darkGray);
        botonBD.setForeground(Color.white);
        botonBD.setFont(new Font("Verdana", 3, 20));
        jPanelDirectorios.add(botonBD);
        //this.botones.add(botonBD);
        botonBD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File objetofile = new File("ArchivoMetaBD/MetaBD.CSV");
                    Desktop.getDesktop().open(objetofile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo encontrar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        indiceBoton++;
        jPanelDirectorios.updateUI();
        if (this.nombreTablas != null) {
            for (int i = 0; i < this.nombreTablas.length; i++) {
                JButton boton = new JButton(this.nombreTablas[i]);
                boton.setBackground(java.awt.Color.darkGray);
                boton.setForeground(Color.white);
                boton.setFont(new Font("Verdana", 2, 20));
                jPanelDirectorios.add(boton);
                //this.botones.add(boton);
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AbrirTabla(boton.getText());
                    }
                });
                indiceBoton++;
                jPanelDirectorios.updateUI();
            }
        }
    }

    private void AbrirTabla(String nombre) {
        try {
            File objetofile = new File("Tablas/" + nombre);
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
