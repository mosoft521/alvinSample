/**
  * Copyright 2021 bejson.com 
  */
package com.gmail.mosoft521.github.dto;

/**
 * Auto-generated: 2021-11-23 15:16:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Permissions {

    private boolean admin;
    private boolean maintain;
    private boolean push;
    private boolean triage;
    private boolean pull;
    public void setAdmin(boolean admin) {
         this.admin = admin;
     }
     public boolean getAdmin() {
         return admin;
     }

    public void setMaintain(boolean maintain) {
         this.maintain = maintain;
     }
     public boolean getMaintain() {
         return maintain;
     }

    public void setPush(boolean push) {
         this.push = push;
     }
     public boolean getPush() {
         return push;
     }

    public void setTriage(boolean triage) {
         this.triage = triage;
     }
     public boolean getTriage() {
         return triage;
     }

    public void setPull(boolean pull) {
         this.pull = pull;
     }
     public boolean getPull() {
         return pull;
     }

}