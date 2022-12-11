import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File,Edit,Close;
    JMenuItem OpenFile,SaveFile,NewFile,PrintFile;
    JMenuItem Cut,Copy,Paste;
    JMenuItem CloseEditor;
    TextEditor(){
        //creating Frame
        frame = new JFrame("Text Editor");
        frame.setBounds(0,0,1000,800);
        //Initialising the text Area
        jTextArea = new JTextArea("Welcome to editor");

        //creating menu bar
        jMenuBar = new JMenuBar();
        //Creating Menu's
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");

        //Adding Menu in Menu Bar
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);

        //Creating items for File Menu
        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);

        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);

        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);

        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);

        //Creating items for Edit Menu
        Cut = new JMenuItem("Cut");
        Cut.addActionListener(this);

        Copy = new JMenuItem("Copy");
        Copy.addActionListener(this);

        Paste = new JMenuItem("Paste");
        Paste.addActionListener(this);

        //Creating menu items for Close Menu
        CloseEditor = new JMenuItem("Close");
        CloseEditor.addActionListener(this);
        Close.add(CloseEditor);

        //Edit Menu
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        //File Menu
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(NewFile);
        File.add(PrintFile);

        //Adding Menu Bar to frame
        frame.setJMenuBar(jMenuBar);
        frame.add(jTextArea);

        //adding close function
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //make frame visible
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Copy"))
        {
            jTextArea.copy();
        }
        else if (s.equals("Cut"))
        {
            jTextArea.cut();
        }
        else if (s.equals("Paste"))
        {
            jTextArea.paste();
        }
        else if (s.equals("Print"))
        {

            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (s.equals("New"))
        {
            jTextArea.setText("");
        }
        else if (s.equals("Close"))
        {
            frame.setVisible(false);
        }
        else if (s.equals("Open"))
        {
            JFileChooser jFileChooser = new JFileChooser("C:");

            int ans = jFileChooser.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1 = "", s2 = "";

                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1 = bufferedReader.readLine()) != null)
                    {
                        s2 += s1 + "\n";
                    }
                    jTextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s.equals("Save"))
        {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans == jFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;

                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                    writer.write(jTextArea.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
