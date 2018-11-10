package com.aggarwal.logic.test;

import org.junit.Test;
import com.aggarwal.logic.StateManager;
import com.aggarwal.models.Vehicle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;

public class StateManagerTest {
	static String DefaultMake = "make";
	static String DefaultModel = "model";
	static int CorrectYear = 2000;
	static int IncorrectYear = 1940;
	static int CorrectId = 1;
	static int IncorrectId = -1;
	static int FakeId = 200;
	StateManager manager;
	
	@Before
	public void setup() {
		manager = new StateManager();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsOnIllegalId() {
		manager.createVehicle(IncorrectId, CorrectYear, DefaultMake	, DefaultModel);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsOnIllegalYear() {
		manager.createVehicle(CorrectId, IncorrectYear, DefaultMake	, DefaultModel);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsOnIllegalMake() {
		manager.createVehicle(CorrectId, CorrectYear, null, DefaultModel);
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsOnIllegalModel() {
		manager.createVehicle(CorrectId, CorrectYear, DefaultMake, null);
	}
	
	@Test
	public void validCreateVehicle() {
		StateManager manager = new StateManager();
		Vehicle vehicle = manager.createVehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel);
		assertEquals(CorrectId, vehicle.getId());
		assertEquals(CorrectYear, vehicle.getYear());
		assertEquals(DefaultMake, vehicle.getMake());
		assertEquals(DefaultModel, vehicle.getModel());
	}
	
	
	@Test
	public void testContainsVehicle() {
		manager.createVehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel);
		assertTrue(manager.containsVehicle(CorrectId));
		assertFalse(manager.containsVehicle(FakeId));
	}
	
	
	@Test
	public void testDeleteVehicle() {
		assertNull(manager.deleteVehicle(FakeId));
		Vehicle vehicle = manager.createVehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel);
		assertEquals(vehicle.getId(), manager.deleteVehicle(CorrectId).getId());
	}
	
	@Test
	public void testGetVehicleById() {
		assertNull(manager.getVehicleById(FakeId));
		Vehicle vehicle = manager.createVehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel);
		assertEquals(vehicle.getId(), manager.getVehicleById(CorrectId).getId());
	}
	
	@Test
	public void testUpdateVehicleMake() {
		assertNull(manager.updateVehicle(FakeId, "make", DefaultMake));
		manager.createVehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel);
		Vehicle newVehicle = manager.updateVehicle(CorrectId, "make", "newMake");
		assertNotEquals(newVehicle.getMake(), DefaultMake);
	}
	
	@Test
	public void testUpdateVehicleModel() {
		assertNull(manager.updateVehicle(FakeId, "model", DefaultModel));
		manager.createVehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel);
		Vehicle newVehicle = manager.updateVehicle(CorrectId, "model", "newModel");
		assertNotEquals(newVehicle.getModel(), DefaultModel);
	}
	
	@Test
	public void testUpdateVehicleYear() {
		assertNull(manager.updateVehicle(FakeId, "year", Integer.toString(CorrectYear)));
		manager.createVehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel);
		Vehicle newVehicle = manager.updateVehicle(CorrectId, "year", "2020");
		assertNotEquals(newVehicle.getYear(), CorrectYear);
	}
	
	@Test
	public void testGetAllVehicles() {
		assertEquals(0, manager.getAllVehicles().size());
		manager.createVehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel);
		manager.createVehicle(FakeId, CorrectYear, DefaultMake, DefaultModel);
		assertEquals(2, manager.getAllVehicles().size());
		List<Vehicle> input = new LinkedList<>();
		input.add(new Vehicle(CorrectId, CorrectYear, DefaultMake, DefaultModel));
		input.add(new Vehicle(FakeId, CorrectYear, DefaultMake, DefaultModel));
		assertEquals(input, manager.getAllVehicles());
	}
}
