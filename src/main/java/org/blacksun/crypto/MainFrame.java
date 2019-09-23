package org.blacksun.crypto;

import org.blacksun.crypto.data.DistinctValidator;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    private MainFrame() {
        super("Lab 1");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(toolbar());
        add(new JTable(Tables.EModel));
        add(new JLabel(" "));
        add(new JTable(Tables.PmModel));
        add(new JLabel(" "));
        add(new JTable(Tables.PkModel));
        add(new JLabel(" "));
        add(new JTable(Tables.PemModel));
        add(new JLabel(" "));
        add(new JTable(Tables.PmeDiffModel));
        add(new JLabel(" "));
        add(new JTable(Tables.PekModel));
        add(new JLabel(" "));
        add(new JTable(Tables.PekmModel));
        pack();
    }

    private JPanel toolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton validate = new JButton("Validate");
        validate.addActionListener(e -> {
            try {
                for (int row = 0; row < Tables.KEY_COUNT; row++) {
                    Tables.E.validateRow(row, new DistinctValidator<>());
                }
                JOptionPane.showMessageDialog(this, "Table is valid", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalStateException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(validate);

        JButton calc = new JButton("Recalculate");
        calc.addActionListener(e -> Tables.fill());
        panel.add(calc);

        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog("Choose filename");
            if (filename == null || filename.isEmpty()) {
                filename = "table";
            }
            try {
                Tables.E.save(filename);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(save);

        JButton load = new JButton("Load");
        load.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser(new File("."));
            jFileChooser.showOpenDialog(this);
            File file = jFileChooser.getSelectedFile();
            if (file != null) {
                try {
                    Tables.E.load(file.getAbsolutePath());
                    Tables.EModel.fireTableDataChanged();
                    Tables.fill();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(load);

        return panel;
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
