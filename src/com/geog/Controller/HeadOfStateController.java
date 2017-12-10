package com.geog.Controller;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.geog.DAO.*;

import com.geog.Model.Country;
import com.geog.Model.HeadOfState;


@SessionScoped
@ManagedBean
public class HeadOfStateController {
	
	private MongoDao mongodb;
	private DAO sqlDb;
	private List<HeadOfState> headsOfState;
	
	public HeadOfStateController() {
		mongodb = new MongoDao();
		try{
			sqlDb = new DAO();
			
		} catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	
	public List<HeadOfState> getHeadsOfState() {
		return headsOfState;
	}

	public void setHeadsOfState(List<HeadOfState> headsOfState) {
		this.headsOfState = headsOfState;
	}

	public void loadHeadOfState(){
		this.headsOfState = mongodb.getHeadsOfState();
	}
	
	
	
	

}