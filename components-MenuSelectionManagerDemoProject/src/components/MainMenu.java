/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

/* MenuDemo.java requires images/middle.gif. */

/*
 * Display a menu bar in the header of the GUI application
 */
public class MainMenu implements ActionListener, ItemListener {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";

    JMenuBar menuBar;
    JMenu menu;
    Font largeFont;

    JMenuItem loadCampaign;
    JMenuItem saveCampaign;
    JMenuItem viewChar;
    JMenuItem addNewChar;

    JMenuItem changeName;
    JMenuItem changeRace;
    JMenuItem changeClass;
    JMenuItem changeBackstory;
    JMenuItem deleteCharacter;

    //Construct a menu bar in GUI application
    public JMenuBar createMenuBar() {
        //make a large font for menu headings
        largeFont = new Font("Calibri", Font.BOLD, 24);
        //Create the menu bar.
        menuBar = new JMenuBar();
        mainMenu();         //load main menu
        characterMenu();    //load character menu
        return menuBar;
    }

    //create menu to display main menu actions
    private void mainMenu() {
        //Build the first menu.
        menu = new JMenu("Main Menu");
        menu.setFont(largeFont);
        menuBar.add(menu);

        //a group of JMenuItems, buttons for main menu actions
        //menu options to load campaign and save campaign
        loadCampaign = new JMenuItem("Load campaign");
        loadCampaign.addActionListener(this);
        menu.add(loadCampaign);

        ImageIcon icon = createImageIcon("images/middle.gif");
        saveCampaign = new JMenuItem("Save Campaign", icon);
        saveCampaign.addActionListener(this);
        menu.add(saveCampaign);

        //a group of menu items to view all characters and add new characters
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        viewChar = new JMenuItem("View all characters");
        group.add(viewChar);
        viewChar.addActionListener(this);
        menu.add(viewChar);

        addNewChar = new JMenuItem("Add new character");
        group.add(addNewChar);
        addNewChar.addActionListener(this);
        menu.add(addNewChar);
    }

    //create menu to display character menu actions
    private void characterMenu() {
        JMenu menu;
        //new menu for character-specific actions
        menu = new JMenu("Character Menu");
        menu.setFont(largeFont);
        //menu.setEnabled(false);
        menuBar.add(menu);

        changeName = new JMenuItem("Change Name");
        changeName.addActionListener(this);
        menu.add(changeName);

        changeRace = new JMenuItem("Change Race");
        changeRace.addActionListener(this);
        menu.add(changeRace);

        changeClass = new JMenuItem("Change Class");
        changeClass.addActionListener(this);
        menu.add(changeClass);

        changeBackstory = new JMenuItem("Change Backstory");
        changeBackstory.addActionListener(this);
        menu.add(changeBackstory);

        menu.addSeparator();

        ImageIcon icon = createImageIcon("images/middle.gif");
        deleteCharacter = new JMenuItem("Delete Character", icon);
        deleteCharacter.addActionListener(this);
        menu.add(deleteCharacter);
    }

    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")";
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainMenu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MainMenu demo = new MainMenu();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(1250, 790);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
