package com.geog.Controller;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	
	 //Add a Head of state to the mongo DB
    public String add(HeadOfState hos) throws SQLException {
        //get the country code
        String countryCode = hos.get_id();
        //list to hold all the countries
        List<Country> countries = new ArrayList<>();
        try {
            sqlDb = new DAO(); //create sql DAO
            countries = sqlDb.loadCountries(); //load all the countries from SQL DB to check before adding HODs
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Error connecting to SQL data base.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "add_head_of_state";
        }
 
        if (!countryCodeExists(countryCode, countries)) {
            FacesMessage message = new FacesMessage("Country: " + countryCode + " does not exist.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "showHeadsOfState";
        }
 
        if (countryAlreadyHasHeadOfState(countryCode, headsOfState)) {
            FacesMessage message = new FacesMessage("Country: " + countryCode + " already has a head of state.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "showHeadsOfState";
        }
 
        // add the head of state if everything is valid across databases
        return mongodb.add(hos);
    }
    //This method deletes a HOD from the mongo DB
    public String delete( HeadOfState hos) {
        return mongodb.delete(hos);
    }
 
    //returns true if a country with the provided country code already exists.
    private boolean countryCodeExists( String countryCode, final List<Country> countries) {
        return countries.stream().anyMatch(country -> country.getCode().equals(countryCode));
    }
    //returns true if the country already has a head of state.
    private boolean countryAlreadyHasHeadOfState( String countryCode,  List<HeadOfState> hos) {
        return hos.stream().anyMatch(h -> h.get_id().equals(countryCode));
    }
}
	
	
	
	

