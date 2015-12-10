import javax.swing.*;
import java.awt.*;


public class Document extends JFrame {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuCompile;
    private JScrollPane scrollPane;
    private JMenuItem copyMenuItem, pasteMenuItem, saveMenuItem;
    private JToolBar toolBar;
    public Document(){
        super("Document");
        setSize(800,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        toolBar = new JToolBar();

        textArea = new JTextArea();
        textArea.setBackground(Color.gray);
        textArea.setSelectedTextColor(Color.blue);
        


        menuFile = new JMenu("File"); //file menu
        menuEdit = new JMenu("Edit"); //edit menu
        menuCompile = new JMenu("Compile"); //edit menu
        scrollPane = new JScrollPane(textArea);

        menuBar = new JMenuBar();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setJMenuBar(menuBar);

        pane.add(scrollPane,BorderLayout.CENTER);
        pane.add(toolBar,BorderLayout.SOUTH);


        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuCompile);







        //  menuFile.add(saveMenuItem);

        setVisible(true);


    }



}

