package grapher.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorSelector implements ActionListener {
    Main main;

    public ColorSelector(Main main) {
        this.main = main;
    }
    //Syntax error, insert "VariableDeclarators" to complete LocalVariableDeclaration
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ColoredFunction coloredFunction = main.grapher.visualList.getSelectedValue();
        if (coloredFunction == null) return;
            switch (e.getActionCommand()) {
                case "Black" :coloredFunction.color = Color.BLACK;break;
                case "Blue" : coloredFunction.color = Color.BLUE; break;
                case "Green" : coloredFunction.color = Color.GREEN; break;
                case "Gray" : coloredFunction.color = Color.GRAY; break;
                case "Red" : coloredFunction.color = Color.RED; break;
                case "Yellow" :coloredFunction.color =  Color.YELLOW; break;
                case "Pink" : coloredFunction.color = Color.PINK; break;
                default: coloredFunction.color = Color.BLACK; break;
            };
        main.grapher.repaint();
    }
    
}
