package grapher.ui;

import javax.swing.*;


import java.awt.*;

public class ColoredFunCellRenderer extends JPanel implements ListCellRenderer<ColoredFunction> {
    private JLabel label;
    private JPanel colorSquare;

    public ColoredFunCellRenderer() {
        setLayout(new BorderLayout());
        label = new JLabel();
        colorSquare = new JPanel();
        colorSquare.setPreferredSize(new Dimension(30, 10));

        add(label, BorderLayout.CENTER);
        add(colorSquare, BorderLayout.EAST);
    }

    /*
        On a choisi de faire avec un cell renderer au lieu d'un table renderer car on 
        car on n'avait lu l'énoncé en diagonale et on avait déjà commencés à explorer cette piste, 
        Etant fonctionnelle, nous n'avait pas pris la peine de changer. 
     */
    @Override
    public Component getListCellRendererComponent(
            JList<? extends ColoredFunction> list, ColoredFunction value, int index,
            boolean isSelected, boolean cellHasFocus) {

        label.setText(value.function.toString());
        colorSquare.setBackground(value.getColor());

        if (isSelected) {
            label.setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        } else {
            label.setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        return this;
    }
}


