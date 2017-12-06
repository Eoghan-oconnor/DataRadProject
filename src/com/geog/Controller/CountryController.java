package com.geog.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.Country;//import country class
import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.util.ArrayList;

@SessionScoped
@ManagedBean
public class CountryController {
	
	private  DAO sqldao;// dao connection
	ArrayList<Country> countries;

	
	public CountryController() throws Exception {
		super();
		countries = new ArrayList<Country>();
		try{
		sqldao = new DAO();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public CountryController(ArrayList<Country>countries){
		super();
		this.countries = countries;
	}
	
	public ArrayList<Country>getCountries(){
		return countries;
	}
	
	public void setCountries(ArrayList<Country>countries){
		this.countries = countries;
	}
	//load product for listener
	public void loadCountries() throws Exception{
		countries.clear();
		if(sqldao != null){
			try{
				countries = sqldao.loadCountries();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	// add new country
	public String addCountry(Country country) {
		if (sqldao != null) {
			try {
				sqldao.addCountry(country);
				return "index";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Country Code " + country.getCode() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Country " +  country.getCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public String deleteCountry(Country country) {
		if (sqldao != null) {
			try {
				sqldao.deleteCountry(country);
				return "index";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Country ID " + country.getCode() + " not found");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to delete Country " + country.getCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public String updateCountry(Country country) {
		if (sqldao != null) {
			try {
				System.out.println(country);
				sqldao.updateCountry(country);
				return "index";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Country ID " + country.getCode() + " not found");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to update Country " + country.getCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}

}