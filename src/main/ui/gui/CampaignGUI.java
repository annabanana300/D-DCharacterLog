package ui.gui;

import javax.swing.*;
import javax.swing.table.*;

import model.Campaign;
import model.Character;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

//GUI to set up a new window of app
public class CampaignGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private Campaign campaign;

    // constructs a window with menu bar, frame, and table of characters
    public CampaignGUI() {
        campaign = new Campaign(); // Initialize the campaign object

        frame = new JFrame("Campaign Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        createMenu();

        // Set up table model
        String[] columns = { "Name", "Race", "Class", "Backstory" };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Make the table editable
        table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));

        // Load characters into the table from the campaign
        loadCampaignData();

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    //EFFECTS: creates menu bar
    private void createMenu() {
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu characterMenu = new JMenu("Character");

        JMenuItem addCharacter = new JMenuItem("Add Character");
        JMenuItem removeCharacter = new JMenuItem("Remove Character");
        JMenuItem saveCampaign = new JMenuItem("Save Campaign");
        JMenuItem loadCampaign = new JMenuItem("Load Campaign");

        fileMenu.add(saveCampaign);
        fileMenu.add(loadCampaign);
        characterMenu.add(addCharacter);
        characterMenu.add(removeCharacter);

        menuBar.add(fileMenu);
        menuBar.add(characterMenu);

        frame.setJMenuBar(menuBar);

        menuActions(addCharacter, removeCharacter, saveCampaign, loadCampaign);
    }

    //EFFECTS: makes action listeners for menu
    private void menuActions(JMenuItem addChar, JMenuItem removeChar, JMenuItem saveCampaign, JMenuItem loadCampaign) {
        // Action Listeners
        addChar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCharacter();
            }
        });

        removeChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCharacter();
            }
        });

        saveCampaign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCampaignData();
            }
        });

        loadCampaign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCampaignData();
            }
        });
    }

    // Add a new character (method needs to create a dialog for input)
    private void addCharacter() {
        String name = JOptionPane.showInputDialog("Enter Character Name:");
        String race = JOptionPane.showInputDialog("Enter Character Race:");
        String characterClass = JOptionPane.showInputDialog("Enter Character Class:");
        String backstory = JOptionPane.showInputDialog("Enter Character Backstory:");

        // Create the new character object and add to the campaign
        Character character = new Character(name, race, characterClass, backstory);
        campaign.addCharacter(character);

        // Update the table
        tableModel.addRow(new Object[] { name, race, characterClass, backstory });
    }

    // Remove selected character
    private void removeCharacter() {
        int row = table.getSelectedRow();
        if (row != -1) {
            String name = (String) tableModel.getValueAt(row, 0);
            campaign.removeCharacter(name);
            tableModel.removeRow(row); // Remove from table view
        }
    }

    // EFFECTS: Save the campaign data to a file
    private void saveCampaignData() {
        try {
            campaign.saveCampaignToFile();  // Save to fixed file (campaign.json)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Load campaign data from a file
    private void loadCampaignData() {
        try {
            campaign.loadCampaignFromFile();
            tableModel.setRowCount(0); // Clear current rows
            for (Character character : campaign.getCharacters()) {
                tableModel.addRow(new Object[] { character.getName(), character.getRace(),
                        character.getCharacterClass(), character.getBackstory() });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
