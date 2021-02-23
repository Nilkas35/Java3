/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.DAO;

import java.util.List;
import labb.DataStructures.Message;

/**
 *
 * @author nikla
 */
public interface ChatDAO {
    public void setReciever(String newReciever);
    public String getReceiever();
    public String getChatUser();    
    public void saveChats();
    public Boolean getChatExist(String user);
    public List<Message> getChat(String logname);
    public void StartReader(String logname);
    public void  addMessage(String msg);
}
