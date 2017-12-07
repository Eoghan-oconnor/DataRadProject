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
		db = mongoClient.getDatabase("headOfStateDB");
		}
		
		
		public ArrayList<HeadOfState> getHeadsOfState(){
			
			final MongoCollection<Document> headsOfStateCollection  = db.getCollection("headsOfState");
			final FindIterable<Document> hos = headsOfStateCollection.find();
			final Gson gson = new Gson();
			final ArrayList<HeadOfState> headsOfState = new ArrayList<>();
			for(Document doc : hos){
				HeadOfState h = gson.fromJson(doc.toJson(), HeadOfState.class);
				headsOfState.add(h);
			}
			
			
			
			
			
			return headsOfState;
			
		}
}
