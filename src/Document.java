import java.io.*;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Document extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuJava;
    private JScrollPane scrollPane;
    private String documentName;
    private JMenuItem copyMenuItem, pasteMenuItem, saveMenuItem, saveAsMenuItem, compileFileMenuItem, compileSelectedMenuitem,
            runFileMenuItem, runSelectedMenuItem, newFileMenuItem;
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
    private JToolBar toolBar;
    private boolean isSavedToDisk = false;
    Action open;


    public Document(String documentName) {
        super(documentName);

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        toolBar = new JToolBar();
        textArea = new JTextArea();
        setTextAreaProperties();
        scrollPane = new JScrollPane(textArea);
        pane.add(scrollPane, BorderLayout.CENTER);
        pane.add(toolBar, BorderLayout.NORTH);

        setVisible(true);


        setActions();
        addActionsToToolbar();
        initializeTheMenuBar();
        setUpMenus();
        addingTheMenus();
        settingUpTheMenuItems();
        addingTheMenuItems();
        addActionListenersToMenuItems();


    }


    private void addActionListenersToMenuItems() {
        newFileMenuItem.addActionListener(this);
        saveAsMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);


    }

    private void setTextAreaProperties() {

        textArea.setBackground(Color.darkGray);
        textArea.setSelectedTextColor(Color.blue);

        Font font = new Font("Verdana", Font.PLAIN, 16);
        textArea.setFont(font);
        textArea.setForeground(Color.white);

    }

    private void setUpMenus() {
        //setting up  menus
        menuFile = new JMenu("File"); //file menu
        menuEdit = new JMenu("Edit"); //edit menu
        menuJava = new JMenu("Java"); //java menu


    }

    private void addingTheMenus() {
        //adding the menus
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuJava);


    }

    private void settingUpTheMenuItems() {

        newFileMenuItem = new JMenuItem("New File");
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save as");
        pasteMenuItem = new JMenuItem("Paste");
        copyMenuItem = new JMenuItem("Copy");
        compileFileMenuItem = new JMenuItem("Compile File");
        compileSelectedMenuitem = new JMenuItem("Compile Selected");
        runFileMenuItem = new JMenuItem("Run File");
        runSelectedMenuItem = new JMenuItem("Run Selected");


    }

    private void addingTheMenuItems() {

        //adding the menu items
        menuFile.add(newFileMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(saveAsMenuItem);
        menuEdit.add(copyMenuItem);
        menuEdit.add(pasteMenuItem);
        menuJava.add(compileFileMenuItem);
        menuJava.add(compileSelectedMenuitem);
        menuJava.add(runFileMenuItem);
        menuJava.add(runSelectedMenuItem);


    }
    private void addActionsToToolbar(){
        toolBar.add(open);

    }

    private Boolean saveAsFile() {
        String sb = this.textArea.getText();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/home/me/Desktop"));
        int retrieval = chooser.showSaveDialog(null);
        if (retrieval == JFileChooser.APPROVE_OPTION) {
            try (FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
                fw.write(sb.toString());
                isSavedToDisk = true;
                return isSavedToDisk;
            } catch (java.io.IOException test) {
                test.printStackTrace();
                return isSavedToDisk;
            }
        }
        return isSavedToDisk;
    }

    private String getFileName() {
        String documentName = (String) JOptionPane.showInputDialog(null, "Enter the file name:", "New File", JOptionPane.INFORMATION_MESSAGE);
        return documentName;
    }
    private boolean saveFileToDisk(){

        return false;
    }

    private void initializeTheMenuBar() {
        //initializing the menu bar
        menuBar = new JMenuBar();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setJMenuBar(menuBar);
    }

    private void createNewFile() {
        String newDocumentName = getFileName();
        new Document(newDocumentName);
    }

    private void setActions(){
         open = new AbstractAction("Open", null) {
            public void actionPerformed(ActionEvent e) {

                if(dialog.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
                    //
                }

            }
        };
        ActionMap m = textArea.getActionMap();
        Action Cut = m.get(DefaultEditorKit.cutAction);
        toolBar.add(Cut);
    }


    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItemSelected = (JMenuItem) e.getSource();

        if (menuItemSelected == newFileMenuItem) {
            createNewFile();
        } else if (menuItemSelected == saveAsMenuItem) {
            saveAsFile();
        } else if(menuItemSelected == saveMenuItem){
            if (isSavedToDisk){
                saveFileToDisk();
            }else{
                saveAsFile();
            }
        }

    }


}

