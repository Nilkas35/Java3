/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author nikla
 */
public class ChatPanel {
    private final JPanel chatPanel = new JPanel();
    private final JTextArea chatText = new JTextArea("");
    private final JPanel msgPanel = new JPanel();
    private final JTextField messageInput = new JTextField();
    private final JButton sendChatButton = new JButton("Send");
    private JLabel chatLabel = new JLabel("Public Chat");
    
    public ChatPanel(){
        chatPanel.setLayout(new BorderLayout());
        msgPanel.setLayout(new BorderLayout());
        
        chatText.setLineWrap(false);
        chatText.setWrapStyleWord(true);
        chatText.setEditable(false);
        sendChatButton.setActionCommand("send");
        
        JScrollPane scroll = new JScrollPane (getChatText(), 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        chatPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        chatLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        chatText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
                
        msgPanel.add(sendChatButton, BorderLayout.EAST);
        msgPanel.add(messageInput, BorderLayout.CENTER);        
   
        chatPanel.add(chatLabel, BorderLayout.NORTH);
        chatPanel.add(msgPanel, BorderLayout.SOUTH);
        chatPanel.add(scroll, BorderLayout.CENTER);
    }

    /**
     * @return the chatPanel
     */
    public JPanel getChatPanel() {
        return chatPanel;
    }

    /**
     * @return the chatText
     */
    public JTextArea getChatText() {
        return chatText;
    }

    /**
     * @return the msgPanel
     */
    public JPanel getMsgPanel() {
        return msgPanel;
    }

    /**
     * @return the messageInput
     */
    public JTextField getMessageInput() {
        return messageInput;
    }

    /**
     * @return the sendChat
     */
    public JButton getSendChatButton() {
        return sendChatButton;
    }

    /**
     * @return the chatLabel
     */
    public JLabel getChatLabel() {
        return chatLabel;
    }
}
