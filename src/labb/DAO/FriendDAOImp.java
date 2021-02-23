/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.DAO;

import labb.DataStructures.Friend;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import labb.Readers.FriendReader;

/**
 *
 * @author nikla
 */
public class FriendDAOImp implements FriendDAO{
    private final FriendReader friendReader = new FriendReader();
    private static TreeMap<String, Friend> friendlist;
    
    public FriendDAOImp(){
        friendReader.FriendReader();
        friendlist = friendReader.getFriendlist();
    }

    @Override
    public void changeFriendInfo(String user, int attribute, String value) {        
        for(Map.Entry<String, Friend> entry: this.getAllFriends().entrySet()){
            Friend b=entry.getValue();
            if (b.getNickname() == null ? user == null : b.getNickname().equals(user)) {
                if(attribute == 0) {
                    b.setFullname(value);
                }
                else if(attribute == 1) {
                    b.setImage(value);
                }
                return;
            }
        }
    }

    @Override
    public TreeMap<String, Friend> getAllFriends() {
        
        return friendlist;
    }

    @Override
    public void saveFriendList() {
        try {
            friendReader.saveFriendList(friendlist);
        } catch (IOException ex) {
            Logger.getLogger(FriendDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
