// package ui.gui;

// import javax.swing.*;
// import javax.swing.border.Border;

// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// //graphic interface for making gui-based app
// public class GUI implements ActionListener {
//     JFrame frame;
//     JButton loadButton;
//     JPanel panel;

//     //EFFECTS: constructs a new GUI
//     public GUI() {
//         frame = new JFrame();

//         loadButton = new JButton("Load File");
//         loadButton.addActionListener(this);
//         JLabel label = new JLabel("Label");

//         panel = new JPanel();
//         panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//         panel.setLayout(new GridLayout(0, 1));
//         panel.add(loadButton);
//         panel.add(label);

//         frame.add(panel, BorderLayout.CENTER);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setTitle("Campaign Character Book");
//         frame.pack();
//         frame.setVisible(true);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
//     }
// }
