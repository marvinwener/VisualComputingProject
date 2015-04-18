package nl.tue.win.vcp.virtualbreitenbergenvironment.gui;

import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.KeyStroke.getKeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import nl.tue.win.vcp.virtualbreitenbergenvironment.io.Serialization;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Movable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.EnvironmentContainer;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.EnvironmentMover;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.GLEventListenerImpl;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.VirtualCamera;

/**
 *
 * @author maikel
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        initActions();
        GLEventListenerImpl listener = new GLEventListenerImpl();
        ec = listener;
        em = listener;
        camera = listener;
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

        listener.setListener(new GLEventListenerImpl.SelectionListener() {

            @Override
            public void selectionChanged(Movable selection) {
                MainFrame.this.selection = selection;
                deleteAction.setEnabled(selection != Movable.NULL);
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
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
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

        jMenu3.setText("File");

        jMenuItem1.setText("New environment");
        jMenu3.add(jMenuItem1);

        jMenuItem5.setText("Load...");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Save...");
        jMenu3.add(jMenuItem6);

        jMenuItem8.setText("Quit");
        jMenu3.add(jMenuItem8);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");

        jMenuItem2.setText("Add vehicle...");
        jMenu4.add(jMenuItem2);

        jMenuItem7.setText("Add light...");
        jMenu4.add(jMenuItem7);

        jMenuItem9.setText("Add heat source...");
        jMenu4.add(jMenuItem9);

        jMenuItem10.setText("Delete selection");
        jMenu4.add(jMenuItem10);

        jMenuBar2.add(jMenu4);

        jMenu6.setText("View");

        jMenuItem11.setText("Reset camera");
        jMenu6.add(jMenuItem11);

        jMenuBar2.add(jMenu6);

        jMenu7.setText("Tools");

        jMenuItem12.setText("Options...");
        jMenu7.add(jMenuItem12);

        jMenuBar2.add(jMenu7);

        jMenu5.setText("Time");
        jMenu5.setEnabled(false);

        jMenuItem3.setText("Pause");
        jMenuItem3.setEnabled(false);
        jMenu5.add(jMenuItem3);

        jMenuItem4.setText("Play");
        jMenuItem4.setEnabled(false);
        jMenu5.add(jMenuItem4);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 152, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        newAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        addVehicleAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        loadAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        saveAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        addLightAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        quitAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        addHeatSourceAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        deleteAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        resetCameraAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.CTRL_MASK));
        showOptionsAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK | java.awt.event.KeyEvent.SHIFT_MASK));

        deleteAction.setEnabled(false);

        jMenuItem1.setAction(newAction);
        jMenuItem2.setAction(addVehicleAction);
        jMenuItem5.setAction(loadAction);
        jMenuItem6.setAction(saveAction);
        jMenuItem7.setAction(addLightAction);
        jMenuItem8.setAction(quitAction);
        jMenuItem9.setAction(addHeatSourceAction);
        jMenuItem10.setAction(deleteAction);
        jMenuItem11.setAction(resetCameraAction);
        jMenuItem12.setAction(showOptionsAction);
    }

    private final Action saveAction = new AbstractAction("Save...") {

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

    private final Action loadAction = new AbstractAction("Load...") {

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

    private final Action addVehicleAction = new AbstractAction("Add vehicle...") {

        @Override
        public void actionPerformed(ActionEvent e) {
            new AddVehicleFrame(ec.getEnvironment()).setVisible(true);
        }
    };

    private final Action addLightAction = new AbstractAction("Add light...") {

        @Override
        public void actionPerformed(ActionEvent e) {
            new AddLightFrame(ec.getEnvironment()).setVisible(true);
        }
    };

    private final Action quitAction = new AbstractAction("Quit") {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            System.exit(0);
        }
    };
    
    private final Action addHeatSourceAction = new AbstractAction("Add heat source...") {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddHeatFrame(ec.getEnvironment()).setVisible(true);
        }
    };

    private final Action deleteAction = new AbstractAction("Delete selection") {

        @Override
        public void actionPerformed(ActionEvent e) {
            ec.getEnvironment().removeObject(selection);
            em.select(Movable.NULL);
        }
    };
    
    private final Action resetCameraAction = new AbstractAction("Reset camera") {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            camera.resetCamera();
        }
    };
    
    private final Action showOptionsAction = new AbstractAction("Options...") {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            OptionsFrame.getInstance().setVisible(true);
        }
    };

    private final static int FPS = 30;
    private final EnvironmentContainer ec;
    private final EnvironmentMover em;
    private final VirtualCamera camera;
    private Movable selection = Movable.NULL;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
