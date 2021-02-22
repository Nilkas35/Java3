/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.Windows;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author nikla
 */
public class MenuBar {
    private final JMenuBar menuBar = new JMenuBar();
    private final JCheckBoxMenuItem publicChat = new JCheckBoxMenuItem("Public Chat");
    private final JCheckBoxMenuItem privateChat = new JCheckBoxMenuItem("Private Chat");
    private final JMenuItem exitMenuItem = new JMenuItem("Exit");
    
    public MenuBar(){
        JMenu fileMenu = new JMenu("File");
        JMenu showMenu = new JMenu("Show");

        exitMenuItem.setActionCommand("exit");        
        publicChat.setSelected(true);
        publicChat.setActionCommand("public");
        privateChat.setSelected(false);
        privateChat.setActionCommand("private");

        fileMenu.add(exitMenuItem);
        showMenu.add(publicChat);
        showMenu.add(privateChat);

        menuBar.add(fileMenu);
        menuBar.add(showMenu);
    }

    /**
     * @return the menuBar
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * @return the publicChat
     */
    public JCheckBoxMenuItem getPublicChat() {
        return publicChat;
    }

    /**
     * @return the privateChat
     */
    public JCheckBoxMenuItem getPrivateChat() {
        return privateChat;
    }

    /**
     * @return the exitMenuItem
     */
    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }
}
