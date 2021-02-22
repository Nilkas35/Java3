/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStructures.Friend;
import java.util.TreeMap;

/**
 *
 * @author nikla
 */
public interface FriendDAO {
    
    //functions
    public void changeFriendInfo(String user, int attribute, String value);
    
    //get
    public TreeMap<String,Friend> getAllFriends();
    
    //set
    public void saveFriendList();
}
