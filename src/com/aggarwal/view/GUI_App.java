package com.aggarwal.view;
import javax.swing.*;

import com.aggarwal.logic.StateManager;
import com.aggarwal.models.Vehicle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI_App {
	StateManager vehicles;
	public GUI_App()
	{
		
	vehicles = new StateManager();
	JFrame guiFrame = new JFrame();
	guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	guiFrame.setTitle("The Vehicles Database");
	guiFrame.setSize(700,500);
	guiFrame.setLocationRelativeTo(null);
	
	
	final JPanel homeScreen = new JPanel();
	
	JButton createButton = new JButton("CREATE");
	JButton getButton = new JButton("GET");
	JButton updateButton = new JButton("UPDATE");
	JButton deleteButton = new JButton("DELETE");

	JTextArea titleField = new JTextArea("Welcome to the Vehicles Database!");
	titleField.setEditable(false);
	titleField.setOpaque(false);
	JTextArea dataField = new JTextArea();
	dataField.setEditable(false);
	dataField.setOpaque(false);
	createButton.addActionListener(new ActionListener()
	{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		dataField.setText(null);
		createBox();
	}
	});
	
	getButton.addActionListener(new ActionListener()
	{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		dataField.setText(null);
		getBox(dataField);
	}
	});
	
	
	updateButton.addActionListener(new ActionListener()
	{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		dataField.setText(null);
		updateBox();
	}
	});
	
	
	
	deleteButton.addActionListener(new ActionListener()
	{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		dataField.setText(null);
		deleteBox();
	}
	});
	
	
	homeScreen.add(titleField);
	homeScreen.add(createButton);
	homeScreen.add(getButton);
	homeScreen.add(updateButton);
	homeScreen.add(deleteButton);
	homeScreen.add(dataField);
	guiFrame.add(homeScreen);
	guiFrame.setVisible(true);
	}
	
	
	private void updateBox() {
		
		String[] choices = { "Make", "Model","Year"};
	    String input = (String) JOptionPane.showInputDialog(null, "Update Value of : ",
	        "Modify Vehicle", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	    
	    JTextField field1 = new JTextField();
	    JTextField field2 = new JTextField();
	    
	    if (input!=null) {
			if (input.equals(choices[0])) {
				Object[] message = { "ID:", field1, "New Make:", field2 };
				int option = JOptionPane.showConfirmDialog(null, message, "Modify Vehicle Make : ",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String value1 = field1.getText();
					String value2 = field2.getText();
					try {
						int id = Integer.parseInt(value1);
						if (!vehicles.containsVehicle(id)) {
							JOptionPane.showMessageDialog(null, "Vehicle Does not Exist.");
							return;
						}
						vehicles.updateVehicle(id, "make", value2);
						JOptionPane.showMessageDialog(null, "Make updated.");
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "ID has to be a number.");
					}
				}

			} else if (input.equals(choices[1])) {
				Object[] message = { "ID:", field1, "New Model:", field2 };
				int option = JOptionPane.showConfirmDialog(null, message, "Modify Vehicle Model : ",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String value1 = field1.getText();
					String value2 = field2.getText();
					try {
						int id = Integer.parseInt(value1);
						if (!vehicles.containsVehicle(id)) {
							JOptionPane.showMessageDialog(null, "Vehicle Does not Exist.");
							return;
						}
						vehicles.updateVehicle(id, "model", value2);
						JOptionPane.showMessageDialog(null, "Model updated.");
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "ID has to be a number.");
					}
				}

			} else if (input.equals(choices[2])) {
				Object[] message = { "ID:", field1, "New Year:", field2 };
				int option = JOptionPane.showConfirmDialog(null, message, "Modify Vehicle Year : ",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String value1 = field1.getText();
					String value2 = field2.getText();
					try {
						int id = Integer.parseInt(value1);
						int year = Integer.parseInt(value2);
						if (year < 1950 || year > 2050) {
							JOptionPane.showMessageDialog(null,
									"Vehicle not Updated! Year has to be a number between 1950 and 2050.");
							return;
						}
						if (!vehicles.containsVehicle(id)) {
							JOptionPane.showMessageDialog(null, "Vehicle Does not Exist.");
							return;
						}
						vehicles.updateVehicle(id, "year", value2);
						JOptionPane.showMessageDialog(null, "Year updated.");
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "ID has to be a number.");
					}
				}
			} 
		}
	}
	
	private void deleteBox(){
		JTextField field = new JTextField();
		Object[] message = { "ID:", field };
    	int option = JOptionPane.showConfirmDialog(null, message, "Delete Vehicle by ID : ", JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION)
		{
    		String value = field.getText();
    		try 
 	        {
 	            int id = Integer.parseInt(value);
 	            if(!vehicles.containsVehicle(id)) {
 	            	JOptionPane.showMessageDialog(null, "Vehicle Does not exist already.");
 	            	return;
 	            }
 	            vehicles.deleteVehicle(id);
 	            JOptionPane.showMessageDialog(null, "Vehicle Deleted.");
 	            
 	        }
    		catch (NumberFormatException e)  
 	        { 
 	        	JOptionPane.showMessageDialog(null, "ID has to be a number."); 
 	        }
		}
	}
	
	private void getBox(JTextArea dataField) {
		
		String[] choices = { "ID", "Make", "Model","Year", "Get All Vehicles"};
	    String input = (String) JOptionPane.showInputDialog(null, "Get Vehicles by : ",
	        "Get Vehicles", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	    
	    JTextField field = new JTextField();
	    
	    if (input != null) {
			if (input.equals(choices[0])) {
				Object[] message = { "ID:", field };
				int option = JOptionPane.showConfirmDialog(null, message, "Get Vehicle by ID : ",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String value = field.getText();
					try {
						int id = Integer.parseInt(value);
						if (!vehicles.containsVehicle(id)) {
							JOptionPane.showMessageDialog(null, "Vehicle Does not Exist.");
							return;
						}
						Vehicle v = vehicles.getVehicleById(id);
						dataField.append("ID : " + v.getId() + "\tYear : " + v.getYear() + "\tMake : " + v.getMake()
								+ "\tModel : " + v.getModel() + "\n");

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "ID has to be a number.");
					}
				}

			} else if (input.equals(choices[1])) {
				Object[] message = { "Make:", field };
				int option = JOptionPane.showConfirmDialog(null, message, "Get Vehicles by Make : ",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String value = field.getText();
					if (!vehicles.checkKeyExistence("make", value)) {
						JOptionPane.showMessageDialog(null, "Make Does not Exist.");
						return;
					}
					for (Vehicle v : vehicles.getFilteredVehicles("make", value)) {
						dataField.append("ID : " + v.getId() + "\tYear : " + v.getYear() + "\tMake : " + v.getMake()
								+ "\tModel : " + v.getModel() + "\n");
					}
				}

			} else if (input.equals(choices[2])) {
				Object[] message = { "Model:", field };
				int option = JOptionPane.showConfirmDialog(null, message, "Get Vehicles by Model : ",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String value = field.getText();
					if (!vehicles.checkKeyExistence("model", value)) {
						JOptionPane.showMessageDialog(null, "Model Does not Exist.");
						return;
					}
					for (Vehicle v : vehicles.getFilteredVehicles("model", value)) {
						dataField.append("ID : " + v.getId() + "\tYear : " + v.getYear() + "\tMake : " + v.getMake()
								+ "\tModel : " + v.getModel() + "\n");
					}
				}
			} else if (input.equals(choices[3])) {
				Object[] message = { "Year:", field };
				int option = JOptionPane.showConfirmDialog(null, message, "Get Vehicles by Year : ",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String value = field.getText();
					try {
						Integer.parseInt(value);
						if (!vehicles.checkKeyExistence("year", value)) {
							JOptionPane.showMessageDialog(null, "Year Does not Exist.");
							return;
						}
						for (Vehicle v : vehicles.getFilteredVehicles("year", value)) {
							dataField.append("ID : " + v.getId() + "\tYear : " + v.getYear() + "\tMake : " + v.getMake()
									+ "\tModel : " + v.getModel() + "\n");
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Year has to be a number.");
					}
				}
			} else if (input.equals(choices[4])) {
				for (Vehicle v : vehicles.getAllVehicles()) {
					dataField.append("ID : " + v.getId() + "\tYear : " + v.getYear() + "\tMake : " + v.getMake()
							+ "\tModel : " + v.getModel() + "\n");
				}
			} 
		}
	    
	    
	}
	
	private void createBox() {
		JTextField idField = new JTextField();
		JTextField yearField = new JTextField();
		JTextField makeField = new JTextField();
		JTextField modelField = new JTextField();
		Object[] message = {
		    "ID:", idField,
		    "Year:", yearField,
		    "Make:", makeField,
		    "Model:", modelField,
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Create Vehicle", JOptionPane.OK_CANCEL_OPTION);
		boolean createVehicle = true;
		if (option == JOptionPane.OK_OPTION)
		{
		    String value1 = idField.getText();
		    String value2 = yearField.getText();
		    String value3 = makeField.getText();
		    String value4 = modelField.getText();
		    
		    if(value1.equals("") || value2.equals("") || value3.equals("")|| value4.equals("")) {
		    	JOptionPane.showMessageDialog(null, "Vehicle not Created! Values can't be empty.");
		    	createVehicle = false;
		    	return;
		    }
		    
		    try 
	        {
	            int id = Integer.parseInt(value1);
	            int year = Integer.parseInt(value2);
	            if(year < 1950 || year > 2050) {
	            	JOptionPane.showMessageDialog(null, "Vehicle not Created! Year has to be a number between 1950 and 2050.");
	            	createVehicle = false;
	            }
	            boolean alreadyExists = vehicles.containsVehicle(id);
	            if(alreadyExists) {
	            	int reply = JOptionPane.showConfirmDialog(null, "Vehicle already exists! Overwrite?", "Duplicate!!", JOptionPane.YES_NO_OPTION);
	                if (reply == JOptionPane.YES_OPTION) {
	                  alreadyExists = false;
	                }
	                else {
	                   alreadyExists = true;
	                }
	            }
			    if(createVehicle && !alreadyExists) {
			    	JOptionPane.showMessageDialog(null, "Vehicle Created!");
			    	vehicles.createVehicle(id, year, value3, value4);
			    }else if(alreadyExists) {
			    	JOptionPane.showMessageDialog(null, "Vehicle not Created!");
			    }
	        }  
	        catch (NumberFormatException e)  
	        { 
	        	JOptionPane.showMessageDialog(null, "Vehicle not Created! ID and Year have to be numbers."); 
	        }
		    
		    
		}
	}
}
