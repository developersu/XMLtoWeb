package general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppGui {
    private JTextArea outputTextArea;
    private JTextArea intutTextArea;
    private JButton updateBtn;
    private JPanel myPanel;
    private JCheckBox cCRLF;
    private JCheckBox cSpaces;
    private JCheckBox cPreCode;
    private JRadioButton cPre;
    private JRadioButton cCode;
    private JLabel copylefts;
    private JScrollBar scrollBarOutpVert;

    private String parcer(String text){
        // Deal with spaces at first
        if (cSpaces.isSelected()){
            text = text.replace(" ","&#160;");
            text = text.replace("\t","&#160;&#160;&#160;&#160;"); // deal with tabs
        }

        text = text.replace("<","&lt;");
        text = text.replace(">","&gt;");
        if (cCRLF.isSelected()){
            text = text.replace("\n","<br />\n");
        }

        if (cPreCode.isSelected()){
            if (cPre.isSelected()){
                text = "<pre>\n" + text + "\n</pre>";
            }else if (cCode.isSelected()){
                text = "<code>\n" + text + "\n</code>";
            }
        }
        return text;
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("XMLtoWeb");
        AppGui appGui = new AppGui();
        frame.setContentPane(appGui.myPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1024, 480));
        frame.setVisible(true);
    }

    public AppGui(){

        ButtonGroup btnGrp = new ButtonGroup();
        btnGrp.add(cPre);
        btnGrp.add(cCode);

        cPreCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (cPreCode.isSelected()) {
                    cPre.setEnabled(true);
                    cCode.setEnabled(true);
                }else{
                    cPre.setEnabled(false);
                    cCode.setEnabled(false);
                }
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String textReturned =  parcer(intutTextArea.getText());
                outputTextArea.setText(textReturned);
            }
        });

    }

}
