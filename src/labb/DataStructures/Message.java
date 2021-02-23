/*
 * Copyright 2021 nikla.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package labb.DataStructures;

/**
 *
 * @author nikla
 */
public class Message {
    private String author;
    private String tag;
    private String msg;
    
    public Message(String author, String tag, String msg){
        this.author = author;
        this.tag = tag;
        this.msg = msg;
    }

    /**
     * @return the user
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param user the user to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
