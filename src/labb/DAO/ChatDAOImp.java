/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.DAO;

import labb.DataStructures.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import labb.Readers.ChatReader;

/**
 *
 * @author nikla
 */
public class ChatDAOImp implements ChatDAO {
    private ChatReader chatReader = new ChatReader();
    private String chatUser = "EURAKARTE";
    private String chattingWith = "EURAKARTE";
    private TreeMap<String, List<Message>> loadedChats;
    private List<Message> chat;
    
    public ChatDAOImp(){
    }
    
    
    
    @Override
    public void setReciever(String newReciever) {
        setChattingWith(newReciever);
    }

    @Override
    public String getReceiever() {
        return getChattingWith();
    }

    @Override
    public String getChatUser() {
        return chatUser;
    }

    /**
     * @return the chattingWith
     */
    public String getChattingWith() {
        return chattingWith;
    }

    /**
     * @param chattingWith the chattingWith to set
     */
    public void setChattingWith(String chattingWith) {
        this.chattingWith = chattingWith;
    }

    @Override
    public void saveChats() {
        loadedChats = chatReader.getLoadedChats();
        for(Map.Entry<String, List<Message>> entry:loadedChats.entrySet()){
            String key = entry.getKey();
            List<Message> b = entry.getValue();
            File file = new File(System.getProperty("user.dir") + "\\logs\\" + key + ".log");
            try {    
                FileWriter fileWriter = new FileWriter(file, false);
                
                for(int i = 0; i < b.size(); i++){
                String tagCheck = b.get(i).getTag();
                if(tagCheck != null) {
                    fileWriter.write("<" + b.get(i).getAuthor() + "[" + b.get(i).getTag() + "]>" + b.get(i).getMsg() + "\n");
                    }
                else if (tagCheck == null){
                    fileWriter.write("<" + b.get(i).getAuthor() + ">" + b.get(i).getMsg() + "\n");
                    }
                else {
                    fileWriter.write("<" + b.get(i).getAuthor() + ">" + b.get(i).getMsg() + "\n");
                    }
                }
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(ChatDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    @Override
    public Boolean getChatExist(String user){
        return chatReader.chatExists(user);
    }

    @Override
    public List<Message> getChat(String logname) {
        return chatReader.getChat(logname);
    }

    @Override
    public void StartReader(String logname) {
        try {
            chatReader.StartReader(logname);
        } catch (IOException ex) {
            Logger.getLogger(ChatDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addMessage(String msg) {
        Message currentLog = new Message(null, null, null);        
        currentLog.setAuthor(chatUser);
        currentLog.setMsg(msg);
        chatReader.getChat(chattingWith).add(currentLog);
    }
    
}
