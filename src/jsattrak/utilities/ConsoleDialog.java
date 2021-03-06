/*
 * ConsoleDialog.java
 *=====================================================================
 *   This file is part of JSatTrak.
 *
 *   Copyright 2007-2013 Shawn E. Gano
 *   
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *   
 *       http://www.apache.org/licenses/LICENSE-2.0
 *   
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * =====================================================================
 * Created on February 5, 2008, 10:27 AM
 */

package jsattrak.utilities;

import java.awt.Toolkit;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 *
 * @author  sgano
 */
public class ConsoleDialog extends javax.swing.JDialog implements ConsoleListener
{
    
    PipedInputStream piOut;
    PipedInputStream piErr;
    PipedOutputStream poOut;
    PipedOutputStream poErr;
    
    
    /** Creates new form ConsoleDialog */
    public ConsoleDialog(java.awt.Frame parent, boolean modal)  
    {
        super(parent, modal);
        initComponents();
        
        this.setTitle("Log Console");
        
        // Register this frame as Console Listener
        Console.registerOutputListener(this);  
        
        // set icon
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/other/logviewer.png")));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);

        textArea.setColumns(20);
        textArea.setEditable(false);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(textArea);

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(clearButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(clearButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearButtonActionPerformed
    {//GEN-HEADEREND:event_clearButtonActionPerformed
        clearMessages();
    }//GEN-LAST:event_clearButtonActionPerformed
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
    
   
    // Implement the ConsoleListener interface method to log message to Text Area
  public void logMessage(String message) 
  {
        textArea.append(message);

        // Make sure the last line is always visible
        textArea.setCaretPosition(textArea.getDocument().getLength());

        // Keep the text area down to a certain character size
        int idealSize = 25000;
        int maxExcess = 200;
        int excess = textArea.getDocument().getLength() - idealSize;
        if (excess >= maxExcess)
        {
            textArea.replaceRange("", 0, excess);
        }

    } // logMessage
    
  public void clearMessages()
  {
      textArea.setText("");
  }
    
} // ConsoleDialog


