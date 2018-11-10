package com.aggarwal.logic;
import java.util.*;

import com.aggarwal.models.Vehicle;

public class StateManager {

	private Hashtable<Integer, Vehicle> dataBase;
	private Hashtable<String, HashSet<Integer>> modelDataBase; 
	private Hashtable<String, HashSet<Integer>> makeDataBase; 
	private Hashtable<Integer, HashSet<Integer>> yearDataBase;
	public StateManager() {
		dataBase = new Hashtable<>();
		modelDataBase = new Hashtable<>();
		makeDataBase = new Hashtable<>();
		yearDataBase = new Hashtable<>();
	}
	
	public boolean containsVehicle(int id) {
		if(dataBase.containsKey(id)) {
			return true;
		}
		return false;
	}
	
	
	public Vehicle createVehicle(int id, int  year, String make, String model) throws IllegalArgumentException {
		if(dataBase.containsKey(id)) {
			deleteVehicle(id);
		}
		Vehicle newVehicle = new Vehicle(id, year, make, model);
		dataBase.put(id, newVehicle);
		putInDB(id, make, makeDataBase);
		putInDB(id, model, modelDataBase);
		putInDB(id, year, yearDataBase);
		return newVehicle;
		
	}

	public Vehicle deleteVehicle(int id) {
		if(!dataBase.containsKey(id)){
			return null;
		}
		Vehicle vehicle = dataBase.get(id);
		dataBase.remove(id);
		removeFromDB(id, vehicle.getModel(), modelDataBase);
		removeFromDB(id, vehicle.getMake(), makeDataBase);
		removeFromDB(id, vehicle.getYear(), yearDataBase);
		return vehicle;
	}
	
	
	public Vehicle getVehicleById(int id) {
		if(dataBase.containsKey(id)) {
			return dataBase.get(id);
		}
		return null;
	}
	
	public List<Vehicle> getAllVehicles(){
		List<Vehicle> vehicles = new LinkedList<Vehicle>(dataBase.values());
		vehicles.sort((a,b) -> Integer.compare(a.getId(), b.getId())) ;
		return vehicles;
	}
	
	public boolean checkKeyExistence(String parameter, String value) {
		if(parameter.equals("make")) {
			if(makeDataBase.containsKey(value)){
				return true;
			}
		}else if(parameter.equals("model")) {
			if(modelDataBase.containsKey(value)){
				return true;
			}
		}else if(parameter.equals("year")) {
			if(yearDataBase.containsKey(Integer.parseInt(value))){
				return true;
			}
		}
		
		return false;
	}
	
	public List<Vehicle> getFilteredVehicles(String parameter, String value){
		List<Vehicle> vehicles = new LinkedList<Vehicle>();
		if(parameter.equals("make")) {
			for (int id : makeDataBase.get(value)) {
			    vehicles.add(dataBase.get(id));
			}
		}else if(parameter.equals("model")) {
			for (int id : modelDataBase.get(value)) {
			    vehicles.add(dataBase.get(id));
			}
		}else if(parameter.equals("year")) {
			for (int id : yearDataBase.get(Integer.parseInt(value))) {
			    vehicles.add(dataBase.get(id));
			}
		}
		vehicles.sort((a,b) -> Integer.compare(a.getId(), b.getId())) ;
		return vehicles;
	}
	
	public Vehicle updateVehicle(int id, String parameter, String value) {
		if(dataBase.containsKey(id)) {	
			if(parameter.equals("make")) {
				Vehicle vehicle = dataBase.get(id);
				removeFromDB(id, vehicle.getMake(), makeDataBase);
				putInDB(id, value, makeDataBase);
				vehicle.setMake(value);
				return vehicle;
			}else if(parameter.equals("model")) {
				Vehicle vehicle = dataBase.get(id);
				removeFromDB(id, vehicle.getModel(), modelDataBase);
				putInDB(id, value, modelDataBase);
				vehicle.setModel(value);
				return vehicle;
			}else if(parameter.equals("year")) {
				Vehicle vehicle = dataBase.get(id);
				removeFromDB(id, vehicle.getYear(), yearDataBase);
				putInDB(id, Integer.parseInt(value), yearDataBase);
				vehicle.setYear(Integer.parseInt(value));
				return vehicle;
			}
		}
		return null;
	}
	
	
	private void putInDB(int id, String key, Hashtable<String, HashSet<Integer>> table) {
		if(table.containsKey(key)) {
			table.get(key).add(id);
		}else {
			HashSet<Integer> set = new HashSet<>();
			set.add(id);
			table.put(key, set);
		}
	}
	
	private void putInDB(int id, int key, Hashtable<Integer, HashSet<Integer>> table) {
		if(table.containsKey(key)) {
			table.get(key).add(id);
		}else {
			HashSet<Integer> set = new HashSet<>();
			set.add(id);
			table.put(key, set);
		}
	}
	
	
	private void removeFromDB(int id, String key, Hashtable<String, HashSet<Integer>> table) {
		if(table.containsKey(key)) {
			table.get(key).remove(id);
		}
	}
	
	private void removeFromDB(int id, int key, Hashtable<Integer, HashSet<Integer>> table) {
		if(table.containsKey(key)) {
			table.get(key).remove(id);
		}
	}
	
}
