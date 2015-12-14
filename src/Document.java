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
    private JMenuItem copyMenuItem, pasteMenuItem, saveMenuItem, saveAsMenuItem, compileFileMenuItem, compileSelectedMenuItem,
            runFileMenuItem, runSelectedMenuItem, newFileMenuItem;
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
    private JToolBar toolBar;
    private boolean isSavedToDisk = false;
    ActionMap actionMappingToTextArea;
    JButton open,cut, compile;
    Action openAction,cutAction, compileAction;

    public Document(String documentName) {

        super(documentName);
        this.documentName = documentName;

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        toolBar = new JToolBar();
        toolBar.setBackground(Color.gray);



        textArea = new JTextArea();
        setTextAreaProperties();
        scrollPane = new JScrollPane(textArea);
        pane.add(scrollPane, BorderLayout.CENTER);
        pane.add(toolBar, BorderLayout.NORTH);

        setTitle(documentName);
        setVisible(true);

        setActions();
        initAndSetButtons();
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
        textArea.setWrapStyleWord(true);


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
        compileSelectedMenuItem = new JMenuItem("Compile Selected");
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
        menuJava.add(compileSelectedMenuItem);
        menuJava.add(runFileMenuItem);
        menuJava.add(runSelectedMenuItem);


    }

    private void addActionsToToolbar(){


    }

    private boolean saveAsFile() {
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
        if (newDocumentName == "" || newDocumentName == null ){
            JOptionPane.showMessageDialog(null,"Must Provide Name");
            return;
        }
        new Document(newDocumentName);
    }

    private void setActions() {

        //Initialize the action mapping
        actionMappingToTextArea = textArea.getActionMap();
        openAction = new AbstractAction("Open", null) {
            public void actionPerformed(ActionEvent e) {

                if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                }

            }
        };

       compileAction = new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               compileFile();
           }
       };


        cutAction = actionMappingToTextArea.get(DefaultEditorKit.cutAction);

    }

    private void initAndSetButtons(){
        //Open Button
        open = toolBar.add(openAction);
        open.setText(null);
        open.setIcon(new ImageIcon("openSmall.png"));


        //Cut Button
        cut = toolBar.add(cutAction);
        cut.setText(null);
        cut.setIcon(new ImageIcon("cutSmall.png"));


        //compile button
        compile = toolBar.add(compileAction);
        compile.setText("Compile");
        compile.setIcon(null);

    }

    public String getDocumentName(){
        return this.documentName;
    }

    private void compileFile(){


        Compiler compiler = new Compiler(textArea.getText(),getDocumentName());


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

