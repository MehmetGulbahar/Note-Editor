import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;




public class NoteBook extends JFrame implements ActionListener {
	JTextArea textArea;
	NoteBook notebook;
	ImageIcon image;
	JLabel fontSize;
	JButton colorButton;
	JScrollPane scrollPane;
	JSpinner fontLarge;
	JComboBox changeFont;
	JMenuBar menuBar;
	JMenu file;
	JMenu Editor;
	JPopupMenu popupMenu;
	JMenuItem copy;
	JMenuItem paste;
    JMenuItem cut;
	JMenuItem newPage;
	JMenuItem undo;
	JMenuItem redo;
	UndoManager um;
	Function_Edit Edit;
	JMenuItem backColor;
	JMenuItem open;
	JMenuItem save;
	JMenuItem exit;
    String fileName,fileAdress,cleaver;
	NoteBook() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Text Editor");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NoteBook.class.getResource("/icon/notebook1.png")));
		
	   


	
		Edit = new Function_Edit(this);
		um = new UndoManager();

		// Set Text Area System ------***********
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Bell MT", Font.PLAIN, 20));
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());			
			}
			
		});
		// Set Text Area System ------***********
		
		


		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(780, 495));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		fontSize = new JLabel("Font Size:");

		// Change font large System ------***********
		fontLarge = new JSpinner();
		fontLarge.setPreferredSize(new Dimension(50, 25));
		fontLarge.setValue(18);
		fontLarge.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontLarge.getValue()));
			}
		});
		// Change font large System ------***********

		// Change ColorButton System ------***********
		colorButton = new JButton("Choose Color");
		colorButton.addActionListener(this);
		// Change ColorButton System ------***********

		// Change Font System ------***********
		String[] fontType = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		changeFont = new JComboBox(fontType);
		changeFont.addActionListener(this);
		changeFont.setSelectedItem("Mv Boli");
		// Change Font System ------***********

		// *----------Menu-----------*
		menuBar = new JMenuBar();
        
        
		file = new JMenu("File");
		Editor = new JMenu("Edit");
		open = new JMenuItem("Open File");
		save = new JMenuItem("Save ");
		newPage = new JMenuItem("New Page");
		exit = new JMenuItem("Exit ");
		backColor = new JMenuItem("Choose Back Color");
		undo = new JMenuItem("Undo Typing");
		redo = new JMenuItem("Redo");

		
		menuBar.add(file);
		menuBar.add(Editor);
		file.add(open);
		file.add(save);
		file.add(newPage);
		file.add(exit);
		Editor.add(undo);
		Editor.add(redo);
		Editor.add(backColor);
		
		undo.addActionListener(this);
		undo.setActionCommand("Undo");
		redo.addActionListener(this);
		redo.setActionCommand("Redo");
		open.addActionListener(this);
		save.addActionListener(this);
		newPage.addActionListener(this);
		exit.addActionListener(this);
		backColor.addActionListener(this);
		
		
		// *----------Menu-----------*
		
		// *----------ShortCut-----------*
		file.setMnemonic(KeyEvent.VK_F);
		open.setMnemonic(KeyEvent.VK_O);
		save.setMnemonic(KeyEvent.VK_S);
		exit.setMnemonic(KeyEvent.VK_E);
		undo.setMnemonic(KeyEvent.VK_Z);
		redo.setMnemonic(KeyEvent.VK_Y);
        // *----------ShortCut-----------*
		
		// *----------PopupMenu-----------*
		popupMenu = new JPopupMenu();
		copy = new JMenuItem("Copy"); 
		paste = new JMenuItem("Paste");
		cut = new JMenuItem("Cut");
		
		popupMenu.add(copy);
		popupMenu.add(paste);
		popupMenu.add(cut);

		textArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
		               popupMenu.show(textArea,e.getX(),e.getY());
		            }
			}
		});
		copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
              textArea.copy();			
			}
		});
          paste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
              textArea.paste();			
			}
		});
          cut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
              textArea.cut();			
			}
		});


	    // *----------PopupMenu-----------*
		

		this.add(fontSize);
		this.add(fontLarge);
		this.add(colorButton);
		this.setJMenuBar(menuBar);
		this.add(changeFont);
		this.add(scrollPane);
		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == colorButton) {
			JColorChooser setColor = new JColorChooser();

			Color color = setColor.showDialog(null, "Choose Color", Color.BLACK);

			textArea.setForeground(color);
		}
		if (e.getSource() == changeFont) {
			textArea.setFont(new Font((String) changeFont.getSelectedItem(), Font.BOLD, textArea.getFont().getSize()));

		}
		if (e.getSource() == open) {
		FileDialog fileDialog = new FileDialog(this.notebook,"Open",FileDialog.LOAD);
		fileDialog.setVisible(true);
		
		if(fileDialog.getFile()!= null) {
			fileName = fileDialog.getFile();
			fileAdress = fileDialog.getDirectory();
			this.setTitle(fileName);
			
			}
		
			try {
			BufferedReader bufReader = new BufferedReader(new FileReader(fileAdress + fileName));
			this.textArea.setText("");
			cleaver = null;
			while((cleaver = bufReader.readLine())!= null){
				this.textArea.append(cleaver + "\n");
				}
			   bufReader.close();
			}catch(Exception a) {
				JOptionPane.showMessageDialog(null, "File Not Opened");
				}
		
		}
		if (e.getSource() == save) {
			FileDialog fileDialog = new FileDialog(this.notebook,"Save",FileDialog.SAVE);
			fileDialog.setVisible(true);
			
			if(fileDialog.getFile()!= null) {
				fileName = fileDialog.getFile();
				fileAdress = fileDialog.getDirectory();
				
				}
			
				try {
				FileWriter fileWriter = new FileWriter(fileAdress + fileName);
				fileWriter.write(this.textArea.getText());
				fileWriter.close();
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null, "File Not Opened");
					}
			}
		if(e.getSource() == newPage) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			JOptionPane.showConfirmDialog(null, "Do you want to open NewPage","WARNING", dialogButton);
			if(dialogButton == JOptionPane.YES_OPTION) {
                textArea.setText(null);
            if(dialogButton == JOptionPane.NO_OPTION) {
                  remove(dialogButton);
                }
              }
			
		}
		
		if (e.getSource() == exit) {
           System.exit(0);
		}

		if (e.getSource() == backColor) {
			JColorChooser setColor = new JColorChooser();

			Color color = setColor.showDialog(null, "Choose Color", Color.BLACK);

			textArea.setBackground(color);	
			this.setBackground(color);
			
		
		}if(e.getSource() == undo) {
			Edit.undo();
		}if(e.getSource() == redo) {
			Edit.redo();
		}
		
		}
		

	}

		
	
	
