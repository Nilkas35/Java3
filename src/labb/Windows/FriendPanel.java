/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author nikla
 */
public final class FriendPanel {
    private final JPanel friendsPanel = new JPanel();
    private final JLabel friendsLabel = new JLabel("Friendslist");
    private final JButton editButton = new JButton("Edit");
    private DefaultListModel listModel;
    private final JList friendsList;
    
    public FriendPanel (){
        this.listModel = new DefaultListModel();
        friendsPanel.setLayout(new BorderLayout());
        friendsList = new JList(getListModel());

        friendsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        friendsLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        friendsList.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 0, 5)));
        friendsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        editButton.setActionCommand("edit");

        JScrollPane scroll = new JScrollPane (getFriendsList());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        friendsPanel.add(friendsLabel, BorderLayout.NORTH);
        friendsPanel.add(editButton, BorderLayout.SOUTH);
        friendsPanel.add(scroll, BorderLayout.WEST);
    }

    /**
     * @return the friendsPanel
     */
    public JPanel getFriendsPanel() {
        return friendsPanel;
    }

    /**
     * @return the friendsLabel
     */
    public JLabel getFriendsLabel() {
        return friendsLabel;
    }

    /**
     * @return the friendsList
     */
    public JList getFriendsList() {
        return friendsList;
    }

    /**
     * @return the listModel
     */
    public DefaultListModel getListModel() {
        return listModel;
    }

    /**
     * @return the editButton
     */
    public JButton getEditButton() {
        return editButton;
    }
}
