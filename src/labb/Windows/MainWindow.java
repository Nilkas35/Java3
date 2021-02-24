/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.Windows;

import labb.DAO.ChatDAO;
import labb.DAO.ChatDAOImp;
import labb.DAO.FriendDAO;
import labb.DAO.FriendDAOImp;
import labb.DataStructures.Friend;
import labb.DataStructures.Message;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author nikla
 */
public class MainWindow extends JFrame implements ActionListener, ListSelectionListener {
    private final JFrame mainFrame;
    
    
    private final MenuBar menuBar = new MenuBar();
    private final FriendPanel friendPanel = new FriendPanel();
    private final ChatPanel chatPanel = new ChatPanel();
    
    private final ChatDAO chatDAO = new ChatDAOImp();
    private final FriendDAO friendDAO = new FriendDAOImp();
        
    private final JCheckBoxMenuItem publicCheckBox = menuBar.getPublicChat();
    private final JCheckBoxMenuItem privateCheckBox = menuBar.getPrivateChat();
    private final JMenuItem exitButton = menuBar.getExitMenuItem();
    private final JButton editButton = friendPanel.getEditButton();
    private final JButton sendButton = chatPanel.getSendChatButton();
    private final JList listEvent = friendPanel.getFriendsList();
        
    private Boolean privateSetting = false;
    
   
    public MainWindow() throws IOException {
        mainFrame = new JFrame("Chat");
        mainFrame.setSize(800,500);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setJMenuBar(menuBar.getMenuBar());
        mainFrame.add(friendPanel.getFriendsPanel(), BorderLayout.EAST);
        mainFrame.add(chatPanel.getChatPanel(), BorderLayout.CENTER);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        publicCheckBox.addActionListener(this);
        privateCheckBox.addActionListener(this);
        exitButton.addActionListener(this);
        editButton.addActionListener(this);        
        sendButton.addActionListener(this);
        listEvent.addListSelectionListener(this);
        
        this.FriendListWriter();
        this.ChatWriter(chatDAO.getChatUser());
        mainFrame.repaint();
        
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Exit();
            }
        });
        
    }
    public void Exit(){
        friendDAO.saveFriendList();
        chatDAO.saveChats();
        System.exit(0);
    };
    
    private void FriendListWriter(){
        friendPanel.getListModel().clear();
        for(Map.Entry<String, Friend> entry:friendDAO.getAllFriends().entrySet()){
            Friend b=entry.getValue();
            if(b.getTag() != null) {
                friendPanel.getListModel().addElement(b.getNickname() + "[" + b.getTag() + "]");
            } else if (b.getTag() == null){
                friendPanel.getListModel().addElement(b.getNickname());
            } else {
                friendPanel.getListModel().addElement(b.getNickname());
            }
        }
    }
    
    
    public void ChatWriter(String nickName) throws IOException{
        List<Message> currentChatText;
        if(chatDAO.getChatExist(nickName)){
            currentChatText = chatDAO.getChat(nickName);
        }
        else {
            chatDAO.StartReader(nickName);
            currentChatText = chatDAO.getChat(nickName);
        }
        chatPanel.getChatText().setText("");
        if(currentChatText != null){
            for(int i = 0; i < currentChatText.size(); i++){
            String tagCheck = currentChatText.get(i).getTag();
                if(tagCheck != null) {
                    chatPanel.getChatText().append("<" + currentChatText.get(i).getAuthor() + "[" + currentChatText.get(i).getTag() + "]>" + currentChatText.get(i).getMsg() + "\n");
                } else if (tagCheck == null){
                    chatPanel.getChatText().append("<" + currentChatText.get(i).getAuthor() + ">" + currentChatText.get(i).getMsg() + "\n");
                } else {
                    chatPanel.getChatText().append("<" + currentChatText.get(i).getAuthor() + ">" + currentChatText.get(i).getMsg() + "\n");
                }
            }
        }
        if(currentChatText == null){
            chatPanel.getChatText().setText("No chatlog found");
            chatPanel.getChatLabel().setText("No chatlog found");
        }
        else if(nickName == chatDAO.getChatUser()){
            chatPanel.getChatLabel().setText("Public Chat");
        }
        else {
            chatPanel.getChatLabel().setText("Private Chat With: " + nickName);
        }
    }
    
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "public"){
            if(publicCheckBox.getState() == true){
                chatPanel.getChatText().setText("");
                if(privateCheckBox.isSelected()){
                    privateCheckBox.setSelected(false);
                    privateSetting = false;
                }
                if(publicCheckBox.isSelected()){
                    try {
                        chatDAO.setReciever(chatDAO.getChatUser());
                        this.ChatWriter(chatDAO.getReceiever());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if(publicCheckBox.getState() == false){
                publicCheckBox.setSelected(true);
            }
        }             
        else if(e.getActionCommand() == "private") {
            if(privateCheckBox.getState() == true){
                chatPanel.getChatText().setText(""); 
                if(publicCheckBox.isSelected()){
                    publicCheckBox.setSelected(false);
                    privateSetting = true;
                }
                if(privateCheckBox.isSelected()){
                    
                    try {
                        this.ChatWriter(chatDAO.getReceiever());
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if(privateCheckBox.getState() == false){
                privateCheckBox.setSelected(true);
                mainFrame.repaint();
            }
        }
        else if(e.getActionCommand() == "exit"){
            this.Exit();
        }
        else if(e.getActionCommand() == "edit"){
            if(listEvent.getSelectedValue() != null){
                String[] options = {"Fullname","Image"};
                int option = JOptionPane.showOptionDialog(null, "What attribute do you want to change?","Change attribute for " + chatDAO.getReceiever(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(option != -1){
                    String Value = JOptionPane.showInputDialog("New value for " + options[option] + " to " + (String) listEvent.getSelectedValue());
                    friendDAO.changeFriendInfo((String) listEvent.getSelectedValue(), option, Value);                }
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "No friend chosen to edit");
            }
        }
        else if(e.getActionCommand() == "send"){
            String msg = chatPanel.getMessageInput().getText();
            chatDAO.addMessage(msg);
            if(msg.length() > 0){
                chatPanel.getChatText().append("<" + chatDAO.getChatUser() + ">" + msg + "\n");
                chatPanel.getMessageInput().setText("");
            }
        }
    }    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(privateSetting == true){
            chatDAO.setReciever(listEvent.getSelectedValue().toString());
            try {
                ChatWriter(chatDAO.getReceiever());
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
