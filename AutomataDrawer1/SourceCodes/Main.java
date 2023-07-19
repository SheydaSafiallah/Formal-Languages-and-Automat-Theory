//package SourceCodes;
//
//import UI.Display;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.TransformerException;
//import java.io.IOException;
//
//class Main {
//    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
//        Display.showUI();
//        //Parse Dom
//        Automata automata = DomParser.parse("myxml.xml");
//
//        DomExporter.export("output.xml",automata);
//        //Show the Automata in UI
//
//
//
//
//
//    }
//}


package SourceCodes;

import UI.AutomataDisplayer;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        createWindow();
    }

    ///////////////////OpenFile
    public static void createWindow() {
        JFrame frame = new JFrame("AutomataDrawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(400, 100);
        frame.setMaximumSize(frame.getSize());
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void createUI(final JFrame frame) {
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);
        JButton button = new JButton("OpenFile");
        JButton button1 = new JButton("About");
        JButton button2 = new JButton("Exit");
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    Automata automata = DomParser.parse(file.getAbsolutePath());
                    AutomataDisplayer displayer = new AutomataDisplayer();
                    displayer.display();
                    displayer.show(automata);
                } catch (ParserConfigurationException | SAXException | IOException parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                }

            }
        });
        button1.addActionListener(e -> {
            JFrame jFrame = new JFrame();
            Label about = new Label();
            about.setText("Welcom to automata Drawer. " +
                    "This app can convert xml to automata and "+
                    "convert automata to xml! " +
                    "By: Sheyda Safi Allah & Nazanin Aryan Matin");
            jFrame.add(about);
            jFrame.setSize(500, 500);
            jFrame.setVisible(true);
        });
        button2.addActionListener(e -> frame.dispose());
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}
////////////////OpenFile