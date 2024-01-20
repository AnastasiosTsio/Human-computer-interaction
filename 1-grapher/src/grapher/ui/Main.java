/* grapher.ui.Main
 * (c) blanch@imag.fr 2021–2024                                            */

package grapher.ui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;

// main that launch a grapher.ui.Grapher

public class Main extends JFrame {
	Grapher grapher;
	DefaultListModel<ColoredFunction> listFuncs;

	public void addFunction(String expression) {
		Function function = FunctionFactory.createFunction(expression);
		grapher.add(function);
	}

	Main(String title, String[] expressions) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		/*
		Component Tree of the Frame

		Main (JFrame)
		├─ JMenuBar
		│  ├─ JMenu [infoMenu]
		│  │  └─ JMenuItem [aboutMenuItem] (placeholder, usage not implemented)
		│  │
		│  ├─ JMenu [expressionMenu]
		│  │  ├─ JMenuItem [addOption]
		│  │  ├─ JMenuItem [removeOption]
		│  │  └─ JMenuItem [modifyOption] 
		│  │
		│  └─ JMenu [colorMenu]
		│     ├─ JMenuItem ("Black")
		│     ├─ ...
		│     └─ JMenuItem ("Pink")
		│
		└─ JSplitPane [splitPane] (Horizontal)
		   ├─ JPanel [listManager]
		   │  ├─ JToolBar [buttonContainer]
		   │  │  ├─ JButton [addButton]
		   │  │  └─ JButton [removeButton]
		   │  │
		   │  └─ JList<ColoredFunction> [funcList]
		   │
		   └─ Grapher [grapher]
		*/

		

		grapher = new Grapher();
		listFuncs = new DefaultListModel<ColoredFunction>();

		// Menu Creation

		// Color Palette
		ColorSelector colorSelector = new ColorSelector(this);
		JMenu colorMenu = new JMenu("Select Color");
		String[] colorNames = { "Black", "Blue", "Green", "Gray", "Red", "Yellow", "Pink"};
		for (int i = 0; i < colorNames.length; i++) {
			JMenuItem colorOption = new JMenuItem(colorNames[i]);
			colorOption.addActionListener(colorSelector);
			colorMenu.add(colorOption);
		}



		// Grapher/Info Menu placeholder (to respect the subject screenshoot subject)
		JMenuBar menuBar = new JMenuBar();
		JMenu infoMenu = new JMenu("Grapher");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		infoMenu.add(aboutMenuItem);

		// Expression Menu
		JMenu expressionMenu = new JMenu("Expression");
		JMenuItem addOption = new JMenuItem("Add...");
		JMenuItem removeOption = new JMenuItem("Remove");
		JMenuItem modifyOption = new JMenuItem("Modify");
		expressionMenu.add(addOption);
		expressionMenu.add(removeOption);
		expressionMenu.add(modifyOption);

		// Set keyboard shortcuts
		addOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		removeOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		modifyOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));


		// Setting up final Menu
		menuBar.add(infoMenu);
		menuBar.add(expressionMenu);
		menuBar.add(colorMenu);
		

		add(menuBar, BorderLayout.NORTH);

		// Overall Frame Creation
		JButton addButton = new JButton("  +  ");
		JButton removeButton = new JButton("  -  ");

		JToolBar buttonContainer = new JToolBar();
		buttonContainer.add(addButton);
		buttonContainer.add(removeButton);

		JList<ColoredFunction> funcList = new JList<ColoredFunction>(listFuncs);
		funcList.setCellRenderer(new ColoredFunCellRenderer());

		JPanel listManager = new JPanel(new BorderLayout());
		listManager.add(buttonContainer, java.awt.BorderLayout.SOUTH);

		listManager.add(funcList, java.awt.BorderLayout.CENTER);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listManager, grapher);
		add(splitPane);

		grapher.updateFunctions(listFuncs, funcList);

		for (String expression : expressions) {
			grapher.add(expression);
			listFuncs.addElement(new ColoredFunction(Color.BLACK, FunctionFactory.createFunction(expression)));
		}

		// Listener Management

		Interaction inter = new Interaction(grapher);
		UIInteractionListener UIinter = new UIInteractionListener(this);
		// Button Listener
		addButton.addActionListener(UIinter);
		removeButton.addActionListener(UIinter);
		// Menu Listener
		addOption.addActionListener(UIinter);
		removeOption.addActionListener(UIinter);
		modifyOption.addActionListener(UIinter);
		// Other interactions Listener
		funcList.addListSelectionListener(UIinter);
		grapher.inter = inter;
		grapher.addMouseListener(inter);
		grapher.addMouseMotionListener(inter);
		grapher.addMouseWheelListener(inter);
		
		pack();

	}

	public static void main(String[] argv) {
		final String[] expressions = argv;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main("grapher", expressions).setVisible(true);
			}
		});
	}
}
