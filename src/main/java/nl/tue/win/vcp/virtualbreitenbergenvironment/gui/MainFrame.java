package nl.tue.win.vcp.virtualbreitenbergenvironment.gui;

import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import nl.tue.win.vcp.virtualbreitenbergenvironment.io.Serialization;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.LightSource;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Movable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.EnvironmentContainer;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.EnvironmentMover;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.GLEventListenerImpl;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 *
 * @author maikel
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        GLEventListenerImpl listener = new GLEventListenerImpl();
        ec = listener;
        em = listener;
        initComponents();
        initActions();
        GLJPanel glPanel = (GLJPanel) jPanel1;
        glPanel.addGLEventListener(listener);
        glPanel.addMouseListener(listener);
        glPanel.addMouseMotionListener(listener);
        glPanel.addMouseWheelListener(listener);
        glPanel.addKeyListener(listener);
        glPanel.setFocusable(true);
        glPanel.requestFocusInWindow();
        // Attach animator to OpenGL panel and begin refresh
        // at the specified number of frames per second.
        final FPSAnimator animator
                = new FPSAnimator((GLJPanel) glPanel, FPS, true);
        animator.setIgnoreExceptions(false);
        animator.setPrintExceptions(true);

        animator.start();

        // Stop animator when window is closed.
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                animator.stop();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        jPanel1 = new GLJPanel(glcapabilities);
        jComboBox1 = new javax.swing.JComboBox();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 807, Short.MAX_VALUE)
        );

        jComboBox1.setModel(comboBox1Model);
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox1MouseEntered(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jMenu3.setText("File");

        jMenuItem1.setText("New environment");
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Add vehicle...");
        jMenu3.add(jMenuItem2);

        jMenuItem7.setText("Add light...");
        jMenu3.add(jMenuItem7);

        jMenuItem5.setText("Load");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Save");
        jMenu3.add(jMenuItem6);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Time");

        jMenuItem3.setText("Pause");
        jMenu5.add(jMenuItem3);

        jMenuItem4.setText("Play");
        jMenu5.add(jMenuItem4);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseEntered
        updateComboBox();
    }//GEN-LAST:event_jComboBox1MouseEntered

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        em.select((Movable) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void updateComboBox() {
        final Movable[] movables = ec.getEnvironment().getMovables();
        Movable selected = (Movable) comboBox1Model.getSelectedItem();
        comboBox1Model.removeAllElements();
        for (Object movable : movables) {
            comboBox1Model.addElement(movable);
        }
        if (Arrays.asList(movables).contains(selected)) comboBox1Model.setSelectedItem(selected);
    }
    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private void initActions() {
        jMenuItem1.setAction(newAction);
        jMenuItem2.setAction(addVehicleAction);
        jMenuItem5.setAction(loadAction);
        jMenuItem6.setAction(saveAction);
        jMenuItem7.setAction(addLightAction);
    }

    private final Action saveAction = new AbstractAction("Save") {

        @Override
        public void actionPerformed(ActionEvent e) {
            Environment environment = ec.getEnvironment();

            JFileChooser fc = new JFileChooser();
            fc.setDialogType(JFileChooser.SAVE_DIALOG);
            final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Braitenberg Environment", "env");
            fc.setFileFilter(filter);
            final int showSaveDialog = fc.showSaveDialog(MainFrame.this);

            File outputFile = fc.getSelectedFile();
            if (fc.getFileFilter().equals(filter)) {
                if (outputFile != null && !outputFile.toString().endsWith(".env")) {
                    outputFile = new File(outputFile.toString() + ".env");
                }
            }
            // TODO: add confirmation
            if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
                try {
                    Serialization.write(environment, outputFile);

                } catch (IOException ex) {
                    System.err.println("While writing: " + ex);
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "There was a problem when writing to the "
                            + "specified file.");
                }
            }

        }
    };

    private final Action loadAction = new AbstractAction("Load") {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            fc.setDialogType(JFileChooser.OPEN_DIALOG);
            final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Braitenberg Environment", "env");
            fc.setFileFilter(filter);
            final int showOpenDialog = fc.showOpenDialog(MainFrame.this);
            final File selectedFile = fc.getSelectedFile();
            if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
                try {
                    ec.setEnvironment((Environment) Serialization.read(selectedFile));
                } catch (IOException | ClassNotFoundException ex) {
                    System.err.println("While reading: " + ex);
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "The selected file could not be read.");
                }
            }

        }
    };

    private final Action newAction = new AbstractAction("New environment") {

        @Override
        public void actionPerformed(ActionEvent e) {
            ec.setEnvironment(new Environment());
        }
    };
    
    private final Action addVehicleAction = new AbstractAction("Add vehicle") {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddVehicleFrame(ec.getEnvironment()).setVisible(true);
        }
    };
    
    private final Action addLightAction = new AbstractAction("Add light") {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddLightFrame(ec.getEnvironment()).setVisible(true);
        }
    };

    private final static int FPS = 30;
    private final EnvironmentContainer ec;
    private final EnvironmentMover em;
    private final DefaultComboBoxModel comboBox1Model = new DefaultComboBoxModel<>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
