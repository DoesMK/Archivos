/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MessagesInf;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author Admin
 */
public class MainWindow extends JFrame {

    MessagesInf message = new MessagesInf();

    JPanel panel;
    JButton __connect;
    JButton __upDate;
    ArrayList<String> __root_files = new ArrayList<>();
    private final int positionY = 40;

    public MainWindow() throws HeadlessException {

        this.setTitle("Buscar Archivos");
        this.setSize(1200, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        panel();
        buttons();
        txt_box();
        labels();
    }

    private void panel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.black);
        this.getContentPane().add(panel);
    }

    private void buttons() {
        __connect = new JButton("Conectar");

        __connect.setBounds(950, positionY, 60, 20);
        __connect.setBorder(null);
        __connect.setBackground(Color.BLACK);
        __connect.setForeground(Color.white);
        __connect.setFont(new Font("consolas", Font.BOLD, 12));

        __upDate = new JButton("Actualizar");

        __upDate.setBounds(1020, positionY, 75, 20);
        __upDate.setBorder(null);
        __upDate.setBackground(Color.BLACK);
        __upDate.setForeground(Color.white);
        __upDate.setFont(new Font("consolas", Font.BOLD, 12));

        panel.add(__upDate);
        panel.add(__connect);
    }

    private void txt_box() {

        Methos methos = new Methos();

        ArrayList<String> ltsArray = new ArrayList<>();
        ArrayList<String> _auxltsArray = new ArrayList<>();
        ArrayList<String> _listDir = new ArrayList<>();
        ArrayList<String> _listSecDir = new ArrayList<>();
        ArrayList<String> _listTypeFile = new ArrayList<>();
        ArrayList<String> _listFoldersUsers = new ArrayList<>();

        ArrayList<String> __New_Root_Files = new ArrayList<>();
        ArrayList<String> _auxiliar_Root = new ArrayList<>();
        ArrayList<String> __Output_Root_Files = new ArrayList<>();

        /**
         * Para el JTextField (Ruta absoluta)
         */
        JTextField __path__dir = new JTextField();

        __path__dir.setBounds(500, 10, 400, 10);
        __path__dir.setBorder(null);
        __path__dir.setFont(new Font("consolas", Font.BOLD, 10));

        /**
         * Para el comboBox del disco a seleccionar
         */
        /**
         * Para actualizar si hay una actualizacion de unidades
         */
        JComboBox directory = new JComboBox();

        directory.setBounds(60, positionY, 50, 20);
        directory.setFont(new Font("consolas", Font.BOLD, 12));
        directory.setForeground(Color.WHITE);
        directory.setBackground(Color.BLACK);

        // Se va a crear un metodo que se ejecutara cada 5 seg para agregar directorios
        // validando 2 cosas :
        // 1° si se detecta un directorio nuevo lo agrega.
        // 2° si se retira un despositivo lo elimina ( mandar un mensaje de error si esta en uso)
        // 
        //Mandar llamar el Metodo de tiempo y asignar los Dispositivos
        __root_files.addAll(methos.getListRootFiles());

        for (int i = 0; i < __root_files.size(); i++) {

            directory.addItem(__root_files.get(i));
        }

        __root_files.clear();
        methos.clearRootDevice();

        ActionListener _update = (ActionEvent ae) -> {

            for (int i = 0; i < directory.getItemCount(); i++) {
                _auxiliar_Root.add((String) directory.getItemAt(i));
            }
            System.out.println("Lista Auxiliar : " + _auxiliar_Root);

            __Output_Root_Files.add(methos.deviceOutputDevice(_auxiliar_Root));

            System.out.println("Elemento Salida : " + __Output_Root_Files);

            if (!__Output_Root_Files.isEmpty()) {
                if (!__Output_Root_Files.get(0).equals("")) {
                    _auxiliar_Root.clear();
                    directory.removeItem(__Output_Root_Files.get(0));

                    for (int i = 0; i < directory.getItemCount(); i++) {
                        _auxiliar_Root.add((String) directory.getItemAt(i));
                    }
                    System.out.println("Nueva lista : " + _auxiliar_Root);
                }
            }
            System.out.println("Validacion limpiar lista (Aux) 1°: " + _auxiliar_Root);
            __Output_Root_Files.clear();
            System.out.println("Validacion limpiar lista (__Output_Root_Files) : " + __Output_Root_Files);

            __New_Root_Files.add(methos.detectdNewDevice(_auxiliar_Root));

            System.out.println("Elemento Entrada : " + __New_Root_Files);

            if (!__New_Root_Files.isEmpty()) {
                if (!__New_Root_Files.get(0).equals("")) {

                    _auxiliar_Root.clear();

                    directory.addItem((String) __New_Root_Files.get(0));

                    for (int i = 0; i < directory.getItemCount(); i++) {
                        _auxiliar_Root.add((String) directory.getItemAt(i));
                    }
                    System.out.println("Nueva lista (Entrada): " + _auxiliar_Root);
                }
            }

            _auxiliar_Root.clear();
            System.out.println("Validacion limpiar lista (Aux) 2°: " + _auxiliar_Root);
            __New_Root_Files.clear();
            System.out.println("Validacion limpiar lista (__New_Root_Files) : " + __New_Root_Files);
            System.out.println("\n\n\n");

            _auxiliar_Root.clear();
            __New_Root_Files.clear();
            methos.clearAuxiliar();

        };
        __upDate.addActionListener(_update);
//        ActionListener go = (ActionEvent ae) -> {
//
//            JOptionPane.showMessageDialog(null, modify, "modificado", 1);
//            for (int i = 0; i < directory.getItemCount(); i++) {
//                
//                if (modify.equals(directory.getItemAt(i))) {
//                    
//                    directory.removeItemAt(i);
//                    JOptionPane.showMessageDialog(null, modify, "Eliminado", 1);
//                    
//                } else if (modify != directory.getItemAt(i)) {
//                    directory.addItem(modify);
//                    JOptionPane.showMessageDialog(null, modify, "Agregado", 1);
//                }
//            }
//
//        };
//        Timer upDatetime = new Timer(6000, go);
//
//        upDatetime.start();
        /**
         * Para Identificar el usuario (o carpetas el inicio)
         */
        JComboBox users = new JComboBox();

        users.setEnabled(false);
        users.setBounds(120, positionY, 150, 20);
        users.setFont(new Font("consolas", Font.BOLD, 12));
        users.setForeground(Color.WHITE);
        users.setBackground(Color.BLACK);
        ActionListener getList = (ActionEvent ae) -> {

            users.removeAllItems();

            _listSecDir.addAll(methos.identifySecondFolders((String) directory.getSelectedItem(), ""));

            if (_listSecDir.size() > 0) {
                users.setEnabled(true);
                for (int i = 0; i < _listSecDir.size(); i++) {

                    users.addItem(_listSecDir.get(i));

                }
            } else {
                users.removeAllItems();
                users.setEditable(false);
            }

            _listSecDir.clear();
            methos.clearSecondDir();

        };

        directory.addActionListener(getList);

        /**
         * Para obtener las carpetas secundarias
         */
        JComboBox folders = new JComboBox();

        
        folders.setBounds(280, positionY, 150, 20);
        folders.setFont(new Font("consolas", Font.BOLD, 12));
        folders.setForeground(Color.WHITE);
        folders.setBackground(Color.BLACK);
        folders.setEnabled(false);
        
        ActionListener getDir2 = (ActionEvent ae) -> {

            if (methos.selectedItem(users.getSelectedIndex())) {
                
                folders.removeAllItems();
                _listDir.addAll(methos.identifyFolders((String) directory.getSelectedItem(), (String) users.getSelectedItem()));

                if (!_listDir.isEmpty()) {
                    folders.setEnabled(true);
                    for (int i = 0; i < _listDir.size(); i++) {

                        folders.addItem(_listDir.get(i));
                    }
                } else {
                    folders.removeAllItems();
                    folders.setEnabled(false);
                }

            }

//            for (int i = 0; i < _listDir.size(); i++) {
//
//                folders.addItem(_listDir.get(i));
//            }

            __path__dir.setText((String) users.getSelectedItem() + "\\");
            _listDir.clear();
            methos.clearListFolders();

        };

        users.addActionListener(getDir2);

        /**
         * JComboBox, si el usuario selecciona la capeta "Users || users"
         */
        JComboBox foldersUser = new JComboBox();

        foldersUser.addItem("Folders User");
        foldersUser.setVisible(false);
        foldersUser.setBounds(280, positionY + 30, 150, 20);
        foldersUser.setFont(new Font("consolas", Font.BOLD, 12));
        foldersUser.setForeground(Color.WHITE);
        foldersUser.setBackground(Color.BLACK);
        ActionListener getFoldersUser = (ActionEvent ae) -> {

            foldersUser.removeAllItems();

            _listFoldersUsers.addAll(methos.ListFoldersUsers((String) directory.getSelectedItem(), (String) users.getSelectedItem() + "\\" + (String) folders.getSelectedItem()));

            if (_listFoldersUsers.size() > 0) {

                foldersUser.setVisible(true);

                JOptionPane.showMessageDialog(null, _listFoldersUsers);

                for (int i = 0; i < _listFoldersUsers.size(); i++) {

                    foldersUser.addItem(_listFoldersUsers.get(i));

                }

                _listFoldersUsers.clear();
                methos.clearListFoldersUsers();

            } else {
                foldersUser.setVisible(false);
            }
        };

        folders.addActionListener(getFoldersUser);

        /**
         * Para la clasificacion de los tipos de archivos
         */
        String typeFiles[] = {"Texto", "Lectura", "Excel", "Comprimido", "Imagen", "Audio",
            "Video", "Sistema", "Imagen disco"};

        JComboBox listTypeFiles = new JComboBox(typeFiles);

        listTypeFiles.setBounds(550, positionY, 100, 20);
        listTypeFiles.setFont(new Font("consolas", Font.BOLD, 12));
        listTypeFiles.setForeground(Color.WHITE);
        listTypeFiles.setBackground(Color.BLACK);

        /**
         * Para el tipo de archivo (.bat / .xls / .txt)
         */
        JComboBox extension = new JComboBox();

        extension.setBounds(660, positionY, 50, 20);
        extension.setFont(new Font("consolas", Font.BOLD, 12));
        extension.setForeground(Color.WHITE);
        extension.setBackground(Color.BLACK);
        ActionListener addListTypeFiles = (ActionEvent ae) -> {

            extension.removeAllItems();

            _listTypeFile.addAll(methos.getListTypeFiles((String) listTypeFiles.getSelectedItem()));

            if (_listTypeFile.isEmpty()) {

                JOptionPane.showMessageDialog(null, "No se encontra disponible esta opcion : " + listTypeFiles.getSelectedItem());

            } else {

                for (int i = 0; i < _listTypeFile.size(); i++) {

                    extension.addItem(_listTypeFile.get(i));

                }

                _listTypeFile.clear();
                methos.clearListTypeFiles();

            }
            __path__dir.setText((String) users.getSelectedItem() + "\\" + (String) folders.getSelectedItem());
        };

        listTypeFiles.addActionListener(addListTypeFiles);

        /**
         * Para el comboBox de los Archivos TXT
         */
        JComboBox listFilesTXT = new JComboBox();

        listFilesTXT.addItem("Archivos Encontrados");
        listFilesTXT.setBounds(720, positionY, 200, 20);
        listFilesTXT.setFont(new Font("consolas", Font.BOLD, 12));
        listFilesTXT.setForeground(Color.WHITE);
        listFilesTXT.setBackground(Color.BLACK);

        /**
         * Action al tomar la ruta absoluta
         */
        ActionListener run = (ActionEvent ae) -> {

            listFilesTXT.removeAllItems();

            JOptionPane.showMessageDialog(null, directory.getSelectedItem());

            if (directory.getSelectedItem() != null) {

                if (methos.validatePath((String) directory.getSelectedItem(), __path__dir.getText())) {

                    if (extension.getSelectedItem() == null) {
                        message.getMessageWarning(1, (String) extension.getSelectedItem());
                    } else {

                        ltsArray.addAll(methos.fileList((String) directory.getSelectedItem(), __path__dir.getText(), (String) extension.getSelectedItem()));

                        methos.clearArrayList();

                        for (int i = 0; i < ltsArray.size(); i++) {

                            listFilesTXT.addItem(ltsArray.get(i));

                        }
                        // Despues de agregar NOMBRES al COMBOBOX
                        // limpiar los ArrayList para evitar duplicados.

                        ltsArray.clear();
                        _auxltsArray.clear();

                    }

                } else {

                    if (listFilesTXT.getMaximumRowCount() > 0) {

                        listFilesTXT.removeAllItems();

                    }

                    message.getMessageWarning(2, __path__dir.getText());

                }

            } else {

                message.getMessageError(1, "Directorio < " + (String) directory.getSelectedItem() + " >");

            }
        };

        __connect.addActionListener(run);

        panel.add(folders);
        panel.add(users);
        panel.add(foldersUser);
        panel.add(extension);
        panel.add(directory);
        panel.add(listFilesTXT);
        panel.add(listTypeFiles);
        panel.add(__path__dir);
    }

    private void labels() {

        JLabel label_path = new JLabel("Ruta :");

        label_path.setBounds(5, positionY, 45, 20);
        label_path.setOpaque(true);
        label_path.setForeground(Color.WHITE);
        label_path.setBackground(Color.BLACK);
        label_path.setFont(new Font("consolas", Font.BOLD, 12));
        label_path.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        JLabel label_TipyOfFile = new JLabel("Tipo archivo :");

        label_TipyOfFile.setBounds(440, positionY, 100, 20);
        label_TipyOfFile.setOpaque(true);
        label_TipyOfFile.setForeground(Color.WHITE);
        label_TipyOfFile.setBackground(Color.BLACK);
        label_TipyOfFile.setFont(new Font("consolas", Font.BOLD, 12));
        label_TipyOfFile.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        panel.add(label_path);
        panel.add(label_TipyOfFile);
    }



}
