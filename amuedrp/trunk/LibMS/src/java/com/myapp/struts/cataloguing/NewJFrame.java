/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on May 24, 2011, 4:00:53 PM
 */

package com.myapp.struts.cataloguing;



/**
 *
 * @author khushnood
 */
public class NewJFrame extends javax.swing.JFrame {
    static String button="";
    static String filename="";
    /** Creates new form NewJFrame */
    public NewJFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        jFileChooser1.setName("jFileChooser1"); // NOI18N
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        
            // TODO add your handling code here:
        button = evt.getActionCommand().toString();
        filename = jFileChooser1.getCurrentDirectory().getAbsolutePath().toString().concat("/");

           System.out.println("Action command="+ button +"&&&&&&&&&&&&&&&&&&&&&&& this is the path of the :" + jFileChooser1.getCurrentDirectory().getAbsoluteFile().getPath().toString().concat("/"));
        //System.out.println("&&&&&&&&&&&&&&&&&&&&&&& this is the path of the :" + evt.toString());


    }//GEN-LAST:event_jFileChooser1ActionPerformed
 public String jFileChooser1ActionPerformed1(NewJFrame obj) {

            // TODO add your handling code here:
            
       // System.out.println("&&&&&&&&&&&&&&&&&&&&&&& this is the path of the :" + evt.toString());
            String path="";
           
            path=filename;
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&& this is the path of the user defined :" + filename);
            obj.setVisible(false);
            if(obj.button.equalsIgnoreCase("CancelSelection")){
                obj.setVisible(false);
            }
            
         return path;

    }
    /**
    * @param args the command line arguments
    */
 /*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });

    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables


}
