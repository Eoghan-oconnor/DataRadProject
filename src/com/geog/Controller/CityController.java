package com.geog.Controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.*;
import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

@ManagedBean
@SessionScoped
public class CityController {
	
	private DAO sqldao;
	ArrayList<City> cities;
	private City details;
	
	public CityController() throws Exception{
		super();
		cities = new ArrayList<City>();
		try{
			sqldao = new DAO();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public CityController(ArrayList<City> cities){
		super();
		this.cities = cities;
	}

	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}
	
	public void loadCities() throws Exception{
		cities.clear();
		if(sqldao != null){
			try{
				cities = sqldao.loadCity();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public String getDetailsFor(City city) {
		this.details = city;
		return null;
		
	}

	public City getDetails() {
		return details;
	}

	public void setSelected(City details) {
		this.details = details;
	}
	
	//add city
	public String addCity(City city){
		if(sqldao != null){
			try{
				sqldao.addCity(city);
				return "index";
			}catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Country Code " + city.getCode() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert City " +  city.getCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
		}
	}

