/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.Readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import DataStructures.Friend;
import java.io.FileWriter;
import java.util.Map;

/**
 *
 * @author nikla
 */
public class FriendReader {
    private static final TreeMap<String,Friend> friendlist = new TreeMap<String,Friend>();
    
    
    public static void FriendReader(){
        
            File file = new File(System.getProperty("user.dir") + "\\logs\\friends.list");
            try {
            FileReader fr = new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)  {
                Friend currentFriend = new Friend(null, null, null, null, null);
                String nick = null;                
                if(line.indexOf('>') != -1){
                    nick = line.substring(1,line.indexOf('>'));
                    currentFriend.setNickname(nick);
                    if(line.indexOf(']') != -1){
                        nick = line.substring(1,line.indexOf('['));
                        currentFriend.setNickname(nick);
                        currentFriend.setTag(line.substring(line.indexOf('[')+1,line.indexOf(']')));
                    }
                }
                line = br.readLine();
                currentFriend.setFullname(line.substring(line.indexOf(']')+1));
                line = br.readLine();
                currentFriend.setIp(line.substring(line.indexOf(']')+1));
                line = br.readLine();
                currentFriend.setImage(line.substring(line.indexOf(']')+1));
                getFriendlist().put(nick, currentFriend);
                }
            fr.close();
            br.close();
            }
            catch(FileNotFoundException e){
            System.out.println(e);
            }
            catch(IOException  e){
            System.out.println(e);
        }
    }
    
    public void saveFriendList(TreeMap<String,Friend> friends) throws IOException{
        File file = new File(System.getProperty("user.dir") + "\\logs\\friends.list");
        FileWriter fileWriter = new FileWriter(file, false);        
        
        for(Map.Entry<String, Friend> entry:friends.entrySet()){
            Friend b=entry.getValue();
            String tagCheck = b.getTag();
            if(tagCheck != null) {
                fileWriter.write("<" + b.getNickname() + "[" + b.getTag() + "]>\n");
            } else if (tagCheck == null){
                fileWriter.write("<" + b.getNickname() + ">\n");
            } else {
                fileWriter.write("<" + b.getNickname() + ">\n");
            }            
            fileWriter.write("[FULLNAME]"+b.getFullname()+"\n");
            fileWriter.write("[LASTIP]"+b.getIp()+"\n");
            fileWriter.write("[IMAGE]"+b.getImage()+"\n");
        }
        fileWriter.close();        
    }

    /**
     * @return the friendlist
     */
    public static TreeMap<String,Friend> getFriendlist() {
        return friendlist;
    }
}
