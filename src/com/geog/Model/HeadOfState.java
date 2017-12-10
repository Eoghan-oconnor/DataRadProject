package com.geog.Model;

import javax.faces.bean.ManagedBean;

//This class creates the Head of States
@ManagedBean
public class HeadOfState {
 
    private String _id;
    private String headOfState;
 
    //noparam constructor
    public HeadOfState() {}
    //getters & setters
    public String getHeadOfState() {
        return headOfState;
    }
 
    public void setHeadOfState(final String headOfState) {
        this.headOfState = headOfState.trim();
    }
 
    public String get_id() {
        return _id;
    }
 
    public void set_id(final String _id) {
        this._id = _id.trim();
    }
}