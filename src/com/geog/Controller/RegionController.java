package com.geog.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.*;
import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.util.ArrayList;

@SessionScoped
@ManagedBean
public class RegionController {
	
	private DAO sqldao;
	ArrayList<Region> regions;
	
	public RegionController() throws Exception{
		super();
		regions = new ArrayList<Region>();
		try{
			sqldao = new DAO();
		} catch(Exception e){
			e.printStackTrace();	
		}
	}
	
	public RegionController(ArrayList<Region> regions){
		super();
		this.regions = regions;
	}

	public ArrayList<Region> getRegions() {
		return regions;
	}

	public void setRegions(ArrayList<Region> regions) {
		this.regions = regions;
	}
	
	public void loadRegions() throws Exception{
		regions.clear();
		if(sqldao != null){
			try{
				regions = sqldao.loadRegion();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	

}
