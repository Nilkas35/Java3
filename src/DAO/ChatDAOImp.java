/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStructures.Friend;
import DataStructures.Logs;
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
    private String chattingWith = "";
    private TreeMap<String, List<Logs>> loadedChats;
    
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
        System.out.println("Saveing chats to files");
        loadedChats = chatReader.getLoadedChats();
        System.out.println(chatReader.getLoadedChats());
        for(Map.Entry<String, List<Logs>> entry:loadedChats.entrySet()){
            System.out.println("itterate though the treemap");
            String key = entry.getKey();
            List<Logs> b = entry.getValue();
            File file = new File(System.getProperty("user.dir") + "\\logs\\" + key + ".log");
            try {    
                FileWriter fileWriter = new FileWriter(file, false);
                
                for(int i = 0; i < b.size(); i++){
                String tagCheck = b.get(i).getTag();
                if(tagCheck != null) {
                    System.out.println("<" + b.get(i).getUser() + "[" + b.get(i).getTag() + "]>" + b.get(i).getMsg() + "\n");
                    fileWriter.write("<" + b.get(i).getUser() + "[" + b.get(i).getTag() + "]>" + b.get(i).getMsg() + "\n");
                    }
                else if (tagCheck == null){
                    System.out.println("<" + b.get(i).getUser() + ">" + b.get(i).getMsg() + "\n");
                    fileWriter.write("<" + b.get(i).getUser() + ">" + b.get(i).getMsg() + "\n");
                    }
                else {
                    System.out.println("<" + b.get(i).getUser() + ">" + b.get(i).getMsg() + "\n");
                    fileWriter.write("<" + b.get(i).getUser() + ">" + b.get(i).getMsg() + "\n");
                    }
                }
                System.out.println("---------------------------------------------");
                
            } catch (IOException ex) {
                Logger.getLogger(ChatDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
