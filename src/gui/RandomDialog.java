package gui;

import command.GameRequestCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Created by Kovvah on 31/12/2017.
 */
public class RandomDialog extends JDialog{
    GameRequestCommand requestCommand;
    private JFormattedTextField width, height, delay, numbersToGenerate;

    public RandomDialog(JFrame parent, String title, boolean modal){
        super(parent, title, modal);
        this.setSize(550, 270);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }

    public GameRequestCommand showRandomDialog(){
        this.setVisible(true);
        return this.requestCommand;
    }

    private void initComponent(){
        NumberFormat integerFieldFormatter;
        integerFieldFormatter = NumberFormat.getIntegerInstance();
        integerFieldFormatter.setMaximumFractionDigits(0);
        integerFieldFormatter.setGroupingUsed(false);

        //Width
        JPanel panWidth = new JPanel();
        panWidth.setBackground(Color.white);
        panWidth.setPreferredSize(new Dimension(220, 60));
        width = new JFormattedTextField(integerFieldFormatter);
        width.setText("200");
        width.setPreferredSize(new Dimension(100, 25));
        width.setColumns(10);
        panWidth.setBorder(BorderFactory.createTitledBorder("Width"));
        panWidth.add(width);

        //Height
        JPanel panHeight = new JPanel();
        panHeight.setBackground(Color.white);
        panHeight.setPreferredSize(new Dimension(220, 60));
        height = new JFormattedTextField(integerFieldFormatter);
        height.setText("200");
        height.setColumns(10);
        height.setPreferredSize(new Dimension(100, 25));
        panHeight.setBorder(BorderFactory.createTitledBorder("Height"));
        panHeight.add(height);

        //Delay
        JPanel panDelay = new JPanel();
        panDelay.setBackground(Color.white);
        panDelay.setPreferredSize(new Dimension(220, 60));
        delay = new JFormattedTextField(integerFieldFormatter);
        delay.setText("500");
        delay.setColumns(10);
        delay.setPreferredSize(new Dimension(100, 25));
        panDelay.setBorder(BorderFactory.createTitledBorder("Delay (ms)"));
        panDelay.add(delay);

        //Numbers to generate
        JPanel panNmbrsToGenerate = new JPanel();
        panNmbrsToGenerate.setBackground(Color.white);
        panNmbrsToGenerate.setPreferredSize(new Dimension(220, 60));
        numbersToGenerate = new JFormattedTextField(integerFieldFormatter);
        numbersToGenerate.setText("15000");
        numbersToGenerate.setColumns(10);
        numbersToGenerate.setPreferredSize(new Dimension(100, 25));
        panNmbrsToGenerate.setBorder(BorderFactory.createTitledBorder("Number of pixels to generate"));
        panNmbrsToGenerate.add(numbersToGenerate);

        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panWidth);
        content.add(panHeight);
        content.add(panDelay);
        content.add(panNmbrsToGenerate);

        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

        okBouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                requestCommand = new GameRequestCommand(Integer.parseInt(width.getText()),
                        Integer.parseInt(height.getText()),Integer.parseInt(delay.getText()),Integer.parseInt(numbersToGenerate.getText()));
                setVisible(false);
            }
        });

        JButton cancelBouton = new JButton("Annuler");
        cancelBouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });

        control.add(okBouton);
        control.add(cancelBouton);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }
}
