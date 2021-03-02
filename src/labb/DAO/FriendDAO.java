/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labb.DAO;

import labb.DataStructures.Friend;
import java.util.TreeMap;

/**
 *
 * @author nikla
 */
public interface FriendDAO {

    public void changeFriendInfo(String user, int attribute, String value);
    public TreeMap<String,Friend> getAllFriends();
    public void saveFriendList();
}
