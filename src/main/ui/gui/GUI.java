package ui.gui;

import model.Campaign;
import model.Character;
import persistence.GuiReader;
import persistence.GuiWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private Campaign campaign;
    private DefaultTableModel tableModel;
    private JTable characterTable;

    // Constructs new GUI
    public GUI() {
        campaign = new Campaign();
        initializeUI();
    }

    @SuppressWarnings("methodlength")
    private void initializeUI() {
        setTitle("Campaign Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table setup
        String[] columnNames = { "Name", "Race", "Class", "Backstory" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // allow editing of cells
            }
        };
        characterTable = new JTable(tableModel);
        characterTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(characterTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Character");
        JButton removeButton = new JButton("Remove Character");
        JButton saveButton = new JButton("Save Campaign");
        JButton loadButton = new JButton("Load Campaign");

        // Action listener for adding a character
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new String[] { "", "", "", "" });
            }
        });

        // Action listener for removing a character
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = characterTable.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        // Action listener for saving campaign data
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Campaign button clicked!"); // ensure button processes click
                campaign = new Campaign();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String name = (String) tableModel.getValueAt(i, 0);
                    String race = (String) tableModel.getValueAt(i, 1);
                    String characterClass = (String) tableModel.getValueAt(i, 2);
                    String backstory = (String) tableModel.getValueAt(i, 3);

                    // make sure character data is being properly read and saved
                    System.out.println("Character " + (i + 1) + ":");
                    System.out.println("  Name: " + name);
                    System.out.println("  Race: " + race);
                    System.out.println("  Class: " + characterClass);
                    System.out.println("  Backstory: " + backstory);

                    campaign.addCharacter(new Character(name, race, characterClass, backstory));
                }
                GuiWriter.saveCampaign(campaign);
            }
        });

        // Action listener for loading campaign data
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Campaign loadedCampaign = GuiReader.loadCampaign();
                if (loadedCampaign != null) {
                    tableModel.setRowCount(0); // Clear the table
                    for (Character character : loadedCampaign.getCharacters()) {
                        tableModel.addRow(new String[] {
                                character.getName(),
                                character.getRace(),
                                character.getCharacterClass(),
                                character.getBackstory()
                        });
                    }
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        add(buttonPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save Campaign");
        JMenuItem loadMenuItem = new JMenuItem("Load Campaign");

        //debugging statement for action listener to detect save button
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Campaign menu item clicked!");
                saveButton.doClick(); // Trigger the save button's action
            }
        });

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Load Campaign menu item clicked!");
                loadButton.doClick(); // Trigger the load button's action
            }
        });

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar); // Add the menu bar to the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
