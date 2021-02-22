/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.DAO;

import java.util.List;

/**
 *
 * @author nikla
 */
public interface ChatDAO {
    public void setReciever(String newReciever);
    public String getReceiever();
    public String getChatUser();    
    public void saveChats();
    
//    public void  addMessage( Message msg );
//    public List< Message > getMessages();

}
