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
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import labb.DataStructures.Logs;

/**
 *
 * @author nikla
 */
public class ChatReader {
    private final String logfolder = System.getProperty("user.dir") + "\\logs\\";
    private final TreeMap<String, List<Logs>> loadedChats = new TreeMap<>();
    
    
    public void ChatReader(String logname) throws IOException {
        logname = CheckFile(logname);
        ReadFile(logname);
    }
    
    private String CheckFile(String logname) {
        File f1 = new File(logfolder + logname + ".log");
        File f2 = new File(logfolder + logname + "AFK.log");
        File f3 = new File(logfolder + logname + "[AFK].log");
        if(f1.exists() && !f1.isDirectory()) { 
            return logname;
        } else if (f2.exists() && !f2.isDirectory()) { 
            return logname + "AFK";
        } else if (f3.exists() && !f3.isDirectory()) { 
            return logname + "[AFK]";
        }
       return "Not Found";
    }
    
    private void ReadFile(String logname) throws IOException{            
        try {
            File logFile = new File(logfolder + logname + ".log");
            FileReader fr = new FileReader(logFile);
            BufferedReader br=new BufferedReader(fr);
            String line;
            List<Logs> chat = new ArrayList<>();
            while((line = br.readLine()) != null)  {
                Logs currentLog = new Logs(null, null, null);
                if(line.indexOf('>') != -1){
                    currentLog.setUser(line.substring(1,line.indexOf('>')));
                    if(line.indexOf(']') != -1){
                        currentLog.setUser(line.substring(1,line.indexOf('[')));
                        currentLog.setTag(line.substring(line.indexOf('[')+1,line.indexOf(']')));
                    }
                }
                currentLog.setMsg(line.substring(line.indexOf('>')+1));
                chat.add(currentLog);
            }
            loadedChats.put(logname, chat);
        }
        catch (FileNotFoundException e){
            System.out.println("Not Found");
        }
    }
    
     /**
     * @return the loadedChats
     */
    public TreeMap<String, List<Logs>> getLoadedChats() {
        System.out.println("chatReader files: " + loadedChats);
        return loadedChats;
    }
    
    public List<Logs> getChat(String logname) {
        return loadedChats.get(logname);
    }
    
    public boolean chatExists(String nickname){
        return getLoadedChats().containsKey(nickname);
    }
}
