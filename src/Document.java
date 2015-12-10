import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Document extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuJava;
    private JScrollPane scrollPane;
    private String documentName ;
    private JMenuItem copyMenuItem, pasteMenuItem, saveMenuItem, saveAsMenuItem, compileFileMenuItem, compileSelectedMenuitem,
            runFileMenuItem, runSelectedMenuItem, newFileMenuItem;

    private JToolBar toolBar;


    public Document(String documentName){
        super(documentName);


        setSize(800,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        toolBar = new JToolBar();
        textArea = new JTextArea();
        setTextAreaProperties();
        scrollPane = new JScrollPane(textArea);
        pane.add(scrollPane,BorderLayout.CENTER);
        pane.add(toolBar,BorderLayout.SOUTH);
        setVisible(true);


        initializeTheMenuBar();
        setUpMenus();
        addingTheMenus();
        settingUpTheMenuItems();
        addingTheMenuItems();
        addActionListenerstoMenuItems();


    }



    private void addActionListenerstoMenuItems(){
        newFileMenuItem.addActionListener(this);


    }

    private void setTextAreaProperties(){

        textArea.setBackground(Color.gray);
        textArea.setSelectedTextColor(Color.blue);

        Font font = new Font("Verdana", Font.PLAIN, 16);
        textArea.setFont(font);
        textArea.setForeground(Color.white);

    }

    private void setUpMenus(){
        //setting up  menus
        menuFile = new JMenu("File"); //file menu
        menuEdit = new JMenu("Edit"); //edit menu
        menuJava = new JMenu("Java"); //java menu


    }

    private void addingTheMenus(){
        //adding the menus
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuJava);


    }

    private void settingUpTheMenuItems(){

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

    private void addingTheMenuItems(){

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

    private void initializeTheMenuBar(){
        //initializing the menu bar
        menuBar = new JMenuBar();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setJMenuBar(menuBar);
    }



    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItemSelected = (JMenuItem) e.getSource();

        if (menuItemSelected == newFileMenuItem){
            System.out.println("Success");
        }

    }


}

