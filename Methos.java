/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class Methos {

    private static final ArrayList<String> __listFiles = new ArrayList<>();
    private final ArrayList<String> __listSeconDir = new ArrayList<>();
    private final ArrayList<String> __listFolders = new ArrayList<>();
    private final ArrayList<String> __listTypeFiles = new ArrayList<>();
    private final ArrayList<String> __listFoldersUsers = new ArrayList<>();
    private final ArrayList<String> __listRootFiles = new ArrayList<>();
    private final ArrayList<String> __auxilir = new ArrayList<>();
//    private final String fuera = "";
//    private final String nuevo = "";

    // Metodo para buscar un archivo con la extencion establecida
    public boolean validatePath(String dir, String _path) {

        boolean bandera = false;

        if (_path == null || _path.equals("") || _path.length() == 0) {

            File file = new File(dir);

            if (file.exists()) {

                bandera = true;

            }

        } else {

            File _auxFile = new File(dir, _path);

            if (_auxFile.exists()) {
                bandera = true;
            }

        }

        return bandera;
    }

    public boolean selectedItem(int item) {

        boolean bandera = false;

        if (item > -1) {
            bandera = true;
        }

        return bandera;
    }

    // Manejo de tiempo para actualizar si hay cambios
//    public void startTime(ArrayList<String> array) {
//        
//        
//        ActionListener go = (ActionEvent ae) -> {
//            
//            check(array);
//            
////            JOptionPane.showMessageDialog(null, modify, "modificado startTime", 1);
//        };
//
//        Timer upDatetime = new Timer(5000, go);
//
//        upDatetime.start();
//        
//    }
    // Controlador de tiempos 
//    public String check(ArrayList<String> array) {
//
//        String _new, output, salida = "";
//
//        output = deviceOutputDevice(array);
//
//        _new = detectdNewDevice(array);
//
//        JOptionPane.showMessageDialog(null, "nuevo : " + _new + "\nFuera : " + output);
//
//        if (!"".equals(output)) {
//            salida = output;
//
//
//        } else if (!"".equals(_new)) {
//            salida = _new;
//
//        }
//
//        return salida;
//    }

    // Deteccion de nuevos dispositivos
    public String detectdNewDevice(ArrayList<String> arrayAnt) {

        String _name = "", nuevo = "";
        boolean bandera, esc = false;

        ArrayList<String> _auxiliar_root = new ArrayList<>();

        File[] list = File.listRoots();

        for (File list1 : list) {
            _auxiliar_root.add(list1.toString());
        }
        
        for (int i = 0; i < _auxiliar_root.size(); i++){
            _name = (String) _auxiliar_root.get(i);
            
            bandera = arrayAnt.contains(_name);
            
            if (!bandera){
                nuevo = (String) _auxiliar_root.get(i);
                System.out.println("Dispositivo Nuevo : " + nuevo);
            }
        }

//        for (int i = 0; i < _auxiliar_root.size(); i++) {
//
//            _name = _auxiliar_root.get(i);
//
//            bandera = arrayAnt.contains(_name);
//
//            if (!bandera) {
//                nuevo = _auxiliar_root.get(i);
//
//            }
//
//        }

        _auxiliar_root.clear();

        return nuevo;

    }

    // Deteccion salida de sipositivos
    public String deviceOutputDevice(ArrayList<String> arrayAnt) {

        boolean bandera;
        String _name = "", fuera = "";
        ArrayList<String> _auxiliar_root = new ArrayList<>();

        File[] list = File.listRoots();

        for (File list1 : list) {
            _auxiliar_root.add(list1.toString());
        }
        
        for (int j = 0; j < arrayAnt.size(); j++) {

            _name = (String) arrayAnt.get(j);

            bandera = _auxiliar_root.contains(_name);

            if (!bandera) {

                fuera = _name;
                
                System.out.println("Dispositivo fuera : " +fuera);
              
            }
        }

//        for (int j = 0; j < arrayAnt.size(); j++) {
//
//            _name = arrayAnt.get(j);
//
//            bandera = _auxiliar_root.contains(_name);
//
//            if (!bandera) {
//
//                fuera = arrayAnt.get(j);
//              
//            }
//        }
        _auxiliar_root.clear();
        
//        JOptionPane.showMessageDialog(null, "Prueba del metodo startTime\n\n\t\t Array actual" + arrayAnt
//                + "\n\n\t\t Dispositivo fuera : " + fuera,
//                "device Output Device", 1);
        return fuera;

//        modify = null;
    }

    public void clearAuxiliar() {
        __auxilir.clear();
    }

    //Metodo para obtener los archivos raiz
    public ArrayList<String> getListRootFiles() {

        File[] list = File.listRoots();

        for (File list1 : list) {
            __listRootFiles.add(list1.toString());

        }

        return __listRootFiles;
    }

    public void clearRootDevice() {
        __listRootFiles.clear();
    }

    //Metodo para Buscar carpetas si existe una carpeta de Users en el disco seleccionado
    public ArrayList<String> ListFoldersUsers(String dir, String _path) {

        File _auxDir = new File(dir, _path);

        JOptionPane.showMessageDialog(null, _auxDir, "ListFoldersUsers", 1);

        getListFoldersUsers(_auxDir);

        return __listFoldersUsers;
    }

    public void clearListFoldersUsers() {
        __listFoldersUsers.clear();
    }

    private void getListFoldersUsers(File _path) {

        File dir[] = _path.listFiles();
        boolean bandera;
        String nameDir;
        int cont = 0;

        if (dir != null) {

            for (File file : dir) {

                if (file.isDirectory()) {

                    nameDir = file.getName();

                    if (!__listFoldersUsers.isEmpty()) {

                        bandera = __listFoldersUsers.contains(nameDir);

                        if (bandera) {

                        } else {
                            __listFoldersUsers.add(file.getName());
                            cont++;
                        }

                    } else {
                        __listFoldersUsers.add(file.getName());
                        cont++;
                    }
                } else if (file.isHidden()) {

                    nameDir = file.getName();

                    if (!__listFoldersUsers.isEmpty()) {

                        bandera = __listFoldersUsers.contains(nameDir);

                        if (bandera) {

                        } else {
                            __listFoldersUsers.add(file.getName());
                            cont++;
                        }

                    } else {
                        __listFoldersUsers.add(file.getName());
                        cont++;
                    }

                }
            }
        }
        System.out.println(cont);

    }

    //Metodo para asigar una lista de extensiones, dependiendo del tipo de archivo
    public ArrayList<String> getListTypeFiles(String item) {

        String typeText[] = {
            "txt", "doc", "docx", "173"
        };

        String[] typeReading = {
            "pdf", "epub", "azw", "ibook"
        };

        String[] typeSpreadSheet = {
            "XLS", "xlsm", "xls", "xlsx"
        };

        String[] typeExeSistem = {
            "exe", "bat", "dll", "sys"
        };

        String[] typeAudio = {
            "mp3", "wav", "wma"
        };

        String[] typeImage = {
            "jpg", "gif", "bmp", "png"
        };

        String[] typeImageDisk = {
            "iso", "mds", "img"
        };

        String[] typeCompressed = {
            "zip", "rar", "tar"
        };

        String[] typeVideo = {
            "avi", "mp4", "mpeg", "mwv"
        };

        switch (item) {
            case "Texto":
                __listTypeFiles.addAll(Arrays.asList(typeText));
                break;
            case "Lectura":
                __listTypeFiles.addAll(Arrays.asList(typeReading));
                break;
            case "Excel":
                __listTypeFiles.addAll(Arrays.asList(typeSpreadSheet));
                break;
            case "Comprimido":
                __listTypeFiles.addAll(Arrays.asList(typeCompressed));
                break;
            case "Imagen":
                __listTypeFiles.addAll(Arrays.asList(typeImage));
                break;
            case "Audio":
                __listTypeFiles.addAll(Arrays.asList(typeAudio));
                break;
            case "Video":
                __listTypeFiles.addAll(Arrays.asList(typeVideo));
                break;
            case "Sistema":
                __listTypeFiles.addAll(Arrays.asList(typeExeSistem));
                break;
            case "Imagen Disco":
                __listTypeFiles.addAll(Arrays.asList(typeImageDisk));
                break;
        }

        return __listTypeFiles;
    }

    public void clearListTypeFiles() {
        __listTypeFiles.clear();
    }

    //Metodo para identificar los directorios en la ruta establecida
    public ArrayList<String> identifyFolders(String dir, String _path) {

        File _auxDisk = new File(dir, _path);

        getDir(_auxDisk);

        return __listFolders;
    }

    //Metodo Para limpiar la lista de las Carpetas encontradas
    public void clearListFolders() {
        __listFolders.clear();
    }

    public ArrayList<String> identifySecondFolders(String dir, String _path) {

        File _auxDir = new File(dir, _path);

        getSecondDir(_auxDir);

        return __listSeconDir;
    }

    public void getSecondDir(File _path) {

        File dir[] = _path.listFiles();
        boolean bandera;
        String nameDir;
        int cont = 0;

        if (dir != null) {

            for (File file : dir) {

                if (file.isDirectory()) {

                    nameDir = file.getName();

                    if (!__listSeconDir.isEmpty()) {

                        bandera = __listSeconDir.contains(nameDir);

                        if (bandera) {

                        } else {
                            __listSeconDir.add(file.getName());
                            cont++;
                        }

                    } else {
                        __listSeconDir.add(file.getName());
                        cont++;
                    }
                }
            }
        }
        System.out.println(cont);

    }

    public void clearSecondDir() {
        __listSeconDir.clear();
    }

    //metodo para identificar las carpetas existentes en un principio
    private void getDir(File _path) {

        File dir[] = _path.listFiles();
        boolean bandera;
        String nameDir;
        int cont = 0;

        if (dir != null) {

            for (File file : dir) {

                if (file.isDirectory()) {

                    nameDir = file.getName();

                    if (!__listFolders.isEmpty()) {

                        bandera = __listFolders.contains(nameDir);

                        if (bandera) {

                        } else {
                            __listFolders.add(file.getName());
                            cont++;
                        }

                    } else {
                        __listFolders.add(file.getName());
                        cont++;
                    }
                }
            }
        }
        System.out.println(cont);

    }

    // Metodo para verificar si esta duplicado un archivo ( EL nombre )
    public ArrayList<String> arrayFilter(ArrayList<String> array) {

        List<String> _auxList;
        _auxList = new ArrayList<>();

        _auxList.addAll(array);

        Set<String> hashSet = new HashSet<>(_auxList);
        _auxList.clear();
        _auxList.addAll(hashSet);

        JOptionPane.showMessageDialog(null, _auxList, "Archivos TXT / arrayFilter", 1);

        return (ArrayList<String>) _auxList;

    }

    // Obtener los archivos encontrados en un array, para mostrarlos
    public ArrayList<String> fileList(String dir, String file, String ext) {

//        File auxiliar = new File(dir, file);
        if (file == null || file.equals("") || file.length() == 0) {

            File _path = new File(dir);

            filesTXT(_path, ext);

        } else {

            File _auxFile = new File(dir, file);

            filesTXT(_auxFile, ext);

        }

//        filesTXT(auxiliar, ext);
        return __listFiles;

    }

    public void clearArrayList() {

        __listFiles.clear();

    }

    //Metodo recurcivo para obtener los archivos
    private static void filesTXT(File _path, String ext_) {

        File files[] = _path.listFiles();
        String salida;
        boolean bandera;

        if (files != null) {

            for (File file : files) {
                // si encuentra una carpeta ingresara.
                if (file.isDirectory()) {
                    // metodo recursivo hasta que ya no encuente directorios
                    filesTXT(file, ext_);
                } else {
                    // El parametro ext_ lo utilizaremos para buscar el archvo 
                    // con esa extencion
                    if (file.getName().endsWith(ext_)) {
                        salida = file.getName();
                        if (!__listFiles.isEmpty()) {
                            bandera = __listFiles.contains(salida);
                            if (bandera) {

                            } else {
                                __listFiles.add(file.getName());
                            }
                        } else {
                            __listFiles.add(file.getName());
                        }
                    }
                }
            }
        }
    }
}
