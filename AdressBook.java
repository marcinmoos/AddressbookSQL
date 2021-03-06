/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Marcin
 */
public class AdressBook extends javax.swing.JFrame {
    
    List adressBook;
    File file;
    static String filePath = "data.dat";
    String viewFilter = "";
    OracleDataSource dane;
    Polaczenie con;
    final String InsertString = "INSERT INTO ADDRESSBOOK VALUES(?,?,?,?)";
    final String SelectString = "SELECT * FROM ADDRESSBOOK";
    final String DeleteString = "DELETE FROM ADDRESSBOOK WHERE FIRST_NAME = ? AND LAST_NAME = ? AND ADDRESS = ? AND TELEPHONE_NUMBER = ?";
    /**
     * Creates new form AdressBook
     */
    public AdressBook() {
        initComponents();
        file = new File(filePath);
        adressBook = new LinkedList();
    }
    
    void ShowAdressList(List lista, String filtr){
        int i = 0;
        String[] ListaWyświetlana = new String [lista.size()];
        for(Object adress : lista){
            String temp = adress.toString();
            if(temp.contains(filtr))ListaWyświetlana[i++] = temp;
        }
        jList1.setListData(ListaWyświetlana);
    }
    
    List ListToStringList(List lista){
        List ListString = new LinkedList();
        lista.forEach((tempList) -> {
            ListString.add(tempList.toString());
        });
        return ListString;
    }
    
    
    void Insert(List listToInsert){
        try(PreparedStatement insertStatement = con.polaczenie.prepareStatement(InsertString)) {
            ListIterator iterator = listToInsert.listIterator();
            while (iterator.hasNext()) {           
                Person temp = (Person) iterator.next();
                    insertStatement.setString(1, temp.getFirstName());
                    insertStatement.setString(2, temp.getLastName());
                    insertStatement.setString(3, temp.getAdress());
                    insertStatement.setString(4, temp.getTelephoneNumber());
                    insertStatement.executeUpdate();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AdressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void Delete(List listToDelete){
        try(PreparedStatement deleteStatement = con.polaczenie.prepareStatement(DeleteString); ) {
            ListIterator iterator = listToDelete.listIterator();
            while (iterator.hasNext()) {           
                Person temp = (Person) iterator.next();
                    deleteStatement.setString(1, temp.getFirstName());
                    deleteStatement.setString(2, temp.getLastName());
                    deleteStatement.setString(3, temp.getAdress());
                    deleteStatement.setString(4, temp.getTelephoneNumber());
                    deleteStatement.executeUpdate();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AdressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    List Select(){
        List temp = new LinkedList();
        try (ResultSet result = con.polaczenie.createStatement().executeQuery(SelectString)){ 
            while(result.next()){
                temp.add(new Person(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDataSource = new javax.swing.JFrame();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jTextFieldFirstName = new javax.swing.JTextField();
        jTextFieldLastName = new javax.swing.JTextField();
        jTextFieldAdress = new javax.swing.JTextField();
        jTextFieldTelephoneNumber = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldFiltr = new javax.swing.JTextField();
        jSaveInDatabase = new javax.swing.JButton();
        jOpenDatabase = new javax.swing.JButton();
        jOpenFile = new javax.swing.JButton();
        jDatabaseSetting = new javax.swing.JButton();

        jDataSource.setTitle("Dane do bazy");
        jDataSource.setBounds(new java.awt.Rectangle(300, 200, 290, 300));
        jDataSource.setResizable(false);

        jTextField1.setText("addressbook");

        jTextField3.setText("localhost");
        jTextField3.setToolTipText("");

        jTextField4.setText("1521");

        jTextField5.setText("xe");

        jLabel5.setText("Użytkownik");

        jLabel6.setText("Hasło");

        jLabel7.setText("Host");

        jLabel8.setText("Port");

        jLabel9.setText("SID");

        jButton5.setText("Połącz");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDataSourceLayout = new javax.swing.GroupLayout(jDataSource.getContentPane());
        jDataSource.getContentPane().setLayout(jDataSourceLayout);
        jDataSourceLayout.setHorizontalGroup(
            jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDataSourceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jPasswordField1)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addComponent(jTextField5)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jDataSourceLayout.setVerticalGroup(
            jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDataSourceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jDataSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(25, 25, 25))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Książka adresowa");
        setBounds(new java.awt.Rectangle(300, 200, 750, 600));
        setResizable(false);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Dodaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Usuń");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Zapisz do pliku");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Imię");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nazwisko");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Adres");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Numer telefonu");

        jTextFieldFiltr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltrActionPerformed(evt);
            }
        });

        jSaveInDatabase.setText("Zapisz do bazy");
        jSaveInDatabase.setEnabled(false);
        jSaveInDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveInDatabaseActionPerformed(evt);
            }
        });

        jOpenDatabase.setText("Otwórz z bazy danych");
        jOpenDatabase.setEnabled(false);
        jOpenDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenDatabaseActionPerformed(evt);
            }
        });

        jOpenFile.setText("Otwórz z pliku");
        jOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenFileActionPerformed(evt);
            }
        });

        jDatabaseSetting.setText("Ustawienia bazy danych");
        jDatabaseSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDatabaseSettingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldAdress, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldTelephoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jOpenFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jOpenDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDatabaseSetting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSaveInDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTextFieldFiltr, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldFiltr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jSaveInDatabase))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTelephoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(92, 92, 92)
                .addComponent(jOpenFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDatabaseSetting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jOpenDatabase)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try(ObjectOutputStream zapisObiektu = new ObjectOutputStream(new FileOutputStream(file))) {
            zapisObiektu.writeObject(adressBook);
        } catch (IOException ex) {
            Logger.getLogger(AdressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if((jTextFieldFirstName.getText().length() != 0) && (jTextFieldLastName.getText().length() != 0)){
            Person temporary = new Person(jTextFieldFirstName.getText(), jTextFieldLastName.getText(), jTextFieldAdress.getText(), jTextFieldTelephoneNumber.getText());
            jTextFieldLastName.setText("");
            jTextFieldAdress.setText("");
            jTextFieldTelephoneNumber.setText("");
            jTextFieldFirstName.setText("");
            if(!ListToStringList(adressBook).contains(temporary.toString())){
                adressBook.add(temporary);
            } 
            Collections.sort(adressBook);
            ShowAdressList(adressBook, viewFilter);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldFiltrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltrActionPerformed
        viewFilter = jTextFieldFiltr.getText();
        ShowAdressList(adressBook, viewFilter);
    }//GEN-LAST:event_jTextFieldFiltrActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        List tempToRemove = new LinkedList();
        adressBook.stream().filter((adress) -> (jList1.getSelectedValuesList().contains(adress.toString()))).forEachOrdered((adress) -> {
            tempToRemove.add(adress);
        });
        tempToRemove.forEach((adress) -> {
            adressBook.remove(adress);
        });
        ShowAdressList(adressBook, viewFilter);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSaveInDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveInDatabaseActionPerformed
       List inDatabase, copyInProgramList;
       inDatabase = Select();
       copyInProgramList = new LinkedList(adressBook);
       copyInProgramList.removeAll(inDatabase);
       Insert(copyInProgramList);
       inDatabase.removeAll(adressBook);
       Delete(inDatabase);
       ShowAdressList(adressBook, viewFilter);
        
    }//GEN-LAST:event_jSaveInDatabaseActionPerformed

    private void jOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenFileActionPerformed
        if(file.exists()){
            try(ObjectInputStream odczytObiektu = new ObjectInputStream(new FileInputStream(file))) {
            adressBook = (List) odczytObiektu.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Błąd pliku");
            }
        }
        ShowAdressList(adressBook, viewFilter);
    }//GEN-LAST:event_jOpenFileActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            dane = new OracleDataSource();
            dane.setDriverType("thin");
            dane.setUser(jTextField1.getText());
            dane.setPassword(jPasswordField1.getText());
            dane.setServerName(jTextField3.getText());
            dane.setPortNumber(Integer.parseInt(jTextField4.getText()));
            dane.setDatabaseName(jTextField5.getText());

            jDataSource.setVisible(false);

            con = Polaczenie.inst(dane);
        }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(con != null){
            jOpenDatabase.setEnabled(true);
            jSaveInDatabase.setEnabled(true);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jDatabaseSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDatabaseSettingActionPerformed
        jDataSource.setVisible(true);
    }//GEN-LAST:event_jDatabaseSettingActionPerformed

    private void jOpenDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenDatabaseActionPerformed
        adressBook = Select();
        Collections.sort(adressBook);
        ShowAdressList(adressBook, viewFilter);
    }//GEN-LAST:event_jOpenDatabaseActionPerformed

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
            java.util.logging.Logger.getLogger(AdressBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdressBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdressBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdressBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdressBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JFrame jDataSource;
    private javax.swing.JButton jDatabaseSetting;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JButton jOpenDatabase;
    private javax.swing.JButton jOpenFile;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton jSaveInDatabase;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextFieldAdress;
    private javax.swing.JTextField jTextFieldFiltr;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldTelephoneNumber;
    // End of variables declaration//GEN-END:variables
}
