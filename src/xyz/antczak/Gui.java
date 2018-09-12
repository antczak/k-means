/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.antczak;

import java.io.BufferedReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcin
 */
public class Gui extends javax.swing.JFrame {
    private DataReader reader;
    private Kmeans kmeans;
    public Gui() {
        initComponents();
        resetButtons();
        resultList.setModel(new DefaultListModel());
    }

    public DataReader getReader() {
        return reader;
    }

    public void setReader(DataReader reader) {
        this.reader = reader;
    }

    public Kmeans getKmeans() {
        return kmeans;
    }

    public void setKmeans(Kmeans kmeans) {
        this.kmeans = kmeans;
    }
    
    private void addLine(String text){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                DefaultListModel model = (DefaultListModel) resultList.getModel();
                model.addElement(text);
            }
        });
    }
    
    private void clearLines(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                DefaultListModel model = (DefaultListModel) resultList.getModel();
                model.clear();
            }
        });        
    }
    
    private void setCell(int row, int col, String value){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                dataTable.getModel().setValueAt(value, row, col);
            }
        });
    }
    private String getCell(int row, int col){
        return dataTable.getValueAt(row, col).toString();
    }
    
    private void resetCells(){
        for (int row = 0; row <= 26; row++){
            for(int col = 1; col <= 3; col++){
                setCell(row, col, "0");
            }
        }   
    }
    
    private void resetButtons(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                startButton.setEnabled(true);
                limitField.setEnabled(true);
                methodBox.setEnabled(true);
                dataLoadButton.setEnabled(true);
            }
        });
    }
    
    private void resetProgram(){
        clearLines();
        resetCells();
    }
    
    private DistanceMethod getMethod(int method){
        DistanceMethod distanceMethod = null;
        switch(method){
            case 0:
                distanceMethod = new DistanceMethod()
                {
                    @Override
                    public double calculateDistance(Vector v1, Vector v2) {
                        double sum = 0;
                        for(int i = 0; i <= v1.getSize() - 1; i++){
                            sum += Math.pow(v1.getParam(i) - v2.getParam(i), 2);
                        }
                        return Math.sqrt(sum);
                    }
                };
                break;
            case 1:
                distanceMethod = new DistanceMethod()
                {
                    @Override
                    public double calculateDistance(Vector v1, Vector v2) {
                        double sum = 0;
                        for(int i = 0; i <= v1.getSize() - 1; i++){
                            sum += Math.abs(v1.getParam(i) - v2.getParam(i));
                        }
                        return Math.sqrt(sum);
                    }
                };
                break; 
            case 2:
                distanceMethod = new DistanceMethod()
                {
                    Vector weights = new Vector(weightsField.getText());
                    @Override
                    public double calculateDistance(Vector v1, Vector v2) {
                        double sum = 0;
                        for(int i = 0; i <= v1.getSize() - 1; i++){
                            sum += weights.getParam(i) * Math.pow(v1.getParam(i) - v2.getParam(i), 2);
                        }
                        return Math.sqrt(sum);
                    }
                };
                break; 
            case 3:
                distanceMethod = new DistanceMethod()
                {
                    Vector weights = new Vector(weightsField.getText());
                    @Override
                    public double calculateDistance(Vector v1, Vector v2) {
                        double sum = 0;
                        for(int i = 0; i <= v1.getSize() - 1; i++){
                            sum += weights.getParam(i) * Math.abs(v1.getParam(i) - v2.getParam(i));
                        }
                        return Math.sqrt(sum);
                    }
                };
                break; 
        }
        return distanceMethod;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataLoadButton = new javax.swing.JButton();
        LearningRowsLabel = new javax.swing.JLabel();
        limitField = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        methodLabel = new javax.swing.JLabel();
        methodBox = new javax.swing.JComboBox<>();
        wieghtsLabel = new javax.swing.JLabel();
        weightsField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dataLoadButton.setText("Wczytaj dane");
        dataLoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataLoadButtonActionPerformed(evt);
            }
        });

        LearningRowsLabel.setText("Dane do nauki");

        limitField.setText("16000");

        startButton.setText("Start");
        startButton.setEnabled(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        resultList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(resultList);

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A", "0", "0", "0"},
                {"B", "0", "0", "0"},
                {"C", "0", "0", "0"},
                {"D", "0", "0", "0"},
                {"E", "0", "0", "0"},
                {"F", "0", "0", "0"},
                {"G", "0", "0", "0"},
                {"H", "0", "0", "0"},
                {"I", "0", "0", "0"},
                {"J", "0", "0", "0"},
                {"K", "0", "0", "0"},
                {"L", "0", "0", "0"},
                {"M", "0", "0", "0"},
                {"N", "0", "0", "0"},
                {"O", "0", "0", "0"},
                {"P", "0", "0", "0"},
                {"Q", "0", "0", "0"},
                {"R", "0", "0", "0"},
                {"S", "0", "0", "0"},
                {"T", "0", "0", "0"},
                {"U", "0", "0", "0"},
                {"V", "0", "0", "0"},
                {"W", "0", "0", "0"},
                {"X", "0", "0", "0"},
                {"Y", "0", "0", "0"},
                {"Z", "0", "0", "0"},
                {"Suma", "00", "0", "0"}
            },
            new String [] {
                "Litera", "Poprawnych", "Wszystkich", "% "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(dataTable);

        methodLabel.setText("Funkcja odległości");

        methodBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "odległość euklidesowa", "odległość miejska", "ważona odleglość euklidesowa", "ważona odległość miejska" }));
        methodBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                methodBoxActionPerformed(evt);
            }
        });

        wieghtsLabel.setText("Wagi");

        weightsField.setText("1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1");
        weightsField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dataLoadButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(methodLabel)
                                    .addComponent(LearningRowsLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(limitField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(methodBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(wieghtsLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(weightsField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LearningRowsLabel)
                    .addComponent(limitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(methodLabel)
                    .addComponent(methodBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wieghtsLabel)
                    .addComponent(weightsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataLoadButton)
                    .addComponent(startButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        if (this.getReader() != null){
            resetProgram();
            int limit = Integer.parseInt(limitField.getText());
            int method = methodBox.getSelectedIndex();
            Vector.setDistanceMethod(this.getMethod(method));
            new Thread(new Runnable(){
                @Override
                public void run() {
                    Kmeans kmeans = new Kmeans(26, 16, 15);
                    kmeans.readVectors(reader, limit);
                    kmeans.setListener(new KmeansListener(){
                        @Override
                        public void onClassifyDone(String realTag, String tag) {
                            addLine(realTag+" Rozpoznano jako "+tag);
                        }
                        @Override
                        public void onLearningEnd() {
                            addLine("Zakończono uczenie");
                        }
                    });
                    kmeans.start();
                    addLine("Poprawnie rozpoznanych zostało "+kmeans.getCorrectSum()+"/"+kmeans.getAllSum()+" liter");
                    addLine("Zakończono działanie programu");
                    for(String key: kmeans.getStats().keySet()){
                        Stat s = kmeans.getStats().get(key);
                        int row = Utils.letterToNum(s.getTag().charAt(0));
                        setCell(row, 1, s.getCorrect()+"");
                        setCell(row, 2, s.getAll()+"");
                        setCell(row, 3, Math.round(((double)s.getCorrect()/(double)s.getAll())*100)+"");
                    }
                    setCell(26, 1, kmeans.getCorrectSum()+"");
                    setCell(26, 2, kmeans.getAllSum()+"");
                    setCell(26, 3, Math.round(((double)kmeans.getCorrectSum()/(double)kmeans.getAllSum())*100)+"");
                    resetButtons();
                }
            }).start();
            startButton.setEnabled(false);
            limitField.setEnabled(false);
            methodBox.setEnabled(false);
            dataLoadButton.setEnabled(false);
            addLine("Rozpoczęto uczenie");
            
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void dataLoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataLoadButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                this.setReader(new DataReader(chooser.getSelectedFile()));
                startButton.setEnabled(true);
        }
    }//GEN-LAST:event_dataLoadButtonActionPerformed

    private void methodBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_methodBoxActionPerformed
        int method = methodBox.getSelectedIndex();
        if (method > 1)
            weightsField.setEnabled(true);
        else
            weightsField.setEnabled(false);
    }//GEN-LAST:event_methodBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LearningRowsLabel;
    private javax.swing.JButton dataLoadButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField limitField;
    private javax.swing.JComboBox<String> methodBox;
    private javax.swing.JLabel methodLabel;
    private javax.swing.JList<String> resultList;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField weightsField;
    private javax.swing.JLabel wieghtsLabel;
    // End of variables declaration//GEN-END:variables
}
