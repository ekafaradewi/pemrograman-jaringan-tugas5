/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tgs5;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Tugas5Controller {
    
    private Tugas5 view;
    private List<Integer> list = new ArrayList<>();

     public Tugas5Controller(Tugas5 view) {
         this.view = view;
         
         this.view.getBtBaca().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 proses(); //To change body of generated methods, choose Tools | Templates.
             }
         });
         this.view.getBtSimpan().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 save(); //To change body of generated methods, choose Tools | Templates.
             }
         });
     }
     
     private void proses(){
    JFileChooser loadFile = view.getLoadFile();
             StyledDocument doc = view.getTxtPane().getStyledDocument();
             if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
                 BufferedReader reader = null;
                 try {
                     int decimal;
                    char ascii;
                    String line = null;
                    
                     int wordCount = 0; //default 0
                    int charCount = 0; //default 0
                    
                    LineNumberReader numberReader = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                    LineNumberInputStream inputstream = new LineNumberInputStream(new FileInputStream(loadFile.getSelectedFile()));
                
                    reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
                    
                    String data = null;
                     doc.insertString(0, "", null);
                     while ((data = reader.readLine()) != null) {
                        doc.insertString(doc.getLength(), data, null);
                        doc.insertString(doc.getLength(), "\n", null);
                    }
                     
                     while ((decimal = inputstream.read()) != -1) {
                        ascii = (char) decimal;
                    }
                     
                     while ((line = numberReader.readLine()) != null) {
                    String[] wordList = line.split("\\s");
                    wordCount += wordList.length;
                    charCount += line.length();
                }
                     
                     JOptionPane.showMessageDialog(view, "File Berhasil Dibaca" + 
                        "\nJumlah Baris     : " + (inputstream.getLineNumber() + 1) +
                        "\nJumlah Kata      : " + (wordCount) +
                        "\nJumlah Karakter   : " + (charCount), "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                     
                 } catch (FileNotFoundException ex) {
                     Logger.getLogger(Tugas5Controller.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException | BadLocationException ex) {
                     Logger.getLogger(Tugas5Controller.class.getName()).log(Level.SEVERE, null, ex);
                 } finally {
                     if (reader != null) {
                         try {
                             reader.close();
                         } catch (IOException ex) {
                             Logger.getLogger(Tugas5Controller.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 }
         }
    }

     
     
    private void save() {
         JFileChooser loadFile = view.getLoadFile();
         if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
             BufferedWriter writer = null;
             try {
                 String contents = view.getTxtPane().getText();
                 if(contents !=null && !contents.isEmpty()){
                     writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                     writer.write(contents);
                 }
             } catch (FileNotFoundException ex) {
                 java.util.logging.Logger.getLogger(Tugas5Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 java.util.logging.Logger.getLogger(Tugas5Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } finally {
                 if (writer != null) {
                     try {
                         writer.flush();
                         writer.close();
                         list.clear();
                     } catch (IOException ex) {
                         java.util.logging.Logger.getLogger(Tugas5Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
}
