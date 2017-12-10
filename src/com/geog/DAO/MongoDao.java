package com.geog.DAO;

import java.util.*;

import org.bson.Document;

import com.geog.Model.*;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDao {

		private  MongoDatabase db;
		private  MongoClient mongoClient;
		
		public MongoDao() {
			
		mongoClient = new MongoClient();
		db = mongoClient.getDatabase("headsofStateDB");
		}
		
		
		public ArrayList<HeadOfState> getHeadsOfState(){
			
			final MongoCollection<Document> headsOfStateCollection  = db.getCollection("headofStateDB");
			final FindIterable<Document> hos = headsOfStateCollection.find();
			final Gson gson = new Gson();
			final ArrayList<HeadOfState> headsOfState = new ArrayList<>();
			for(Document doc : hos){
				HeadOfState h = gson.fromJson(doc.toJson(), HeadOfState.class);
				headsOfState.add(h);
			}
			return headsOfState;
		}
		
		//this method deletes a head of state from the mongo database
	    public String delete(final HeadOfState hos) {
	        //get the collection from the db
	        MongoCollection<Document> headsOfStateCollection = db.getCollection("headofStateDB");
	        //delete the head of state given the id to find it
	        headsOfStateCollection.deleteOne(new Document("_id", hos.get_id()));
	        return "head_of_state";
	    }
	    //Add a head of state to the Database
	    public String add(final HeadOfState hos) {
	        //get the collection from the db
	        MongoCollection<Document> headsOfStateCollection = db.getCollection("headofStateDB");
	        //Create a new doc for new head of state
	        Document doc = new Document();
	        //append it to the doc
	        doc.append("_id", hos.get_id());
	        doc.append("headOfState", hos.getHeadOfState());
	        //insert it into the DB
	        headsOfStateCollection.insertOne(doc);
	        return "head_of_state";
	    }
}