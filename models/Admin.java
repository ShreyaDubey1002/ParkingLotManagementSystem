package ParkingLotManagementSystem.models;

import java.util.HashMap;

public class Admin extends AbstractAccount {
    HashMap<String, ParkingFloor> parkingFloors;
    HashMap<String, EntrancePanel> entrancePanels;
    HashMap<String, ExitPanel> exitPanels;

    Admin(HashMap<String, ParkingFloor> parkingFloors, HashMap<String, EntrancePanel> entrancePanels, HashMap<String, ExitPanel> exitPanels) {
        this.parkingFloors = parkingFloors;
        this.entrancePanels = entrancePanels;
        this.exitPanels = exitPanels;
    }

    public void addParkingSpot(ParkingSpot parkingSpot, String floorName) {
        ParkingFloor parkingFloor = parkingFloors.get(floorName);
        parkingFloor.addParkingSpot(parkingSpot);
    }

    public void addParkingFloor(ParkingFloor parkingFloor) {
        parkingFloors.put(parkingFloor.floorName, parkingFloor);
    }

    public void addParkingDisplayBoard(String floorName, ParkingDisplayBoard parkingDisplayBoard) {
        ParkingFloor parkingFloor = parkingFloors.get(floorName);
        parkingFloor.addParkingDisplayBoard(parkingDisplayBoard);
    }

    public void addCustomerInfoPanel(String floorName, CustomerInfoPanel customerInfoPanel) {
        ParkingFloor parkingFloor = parkingFloors.get(floorName);
        parkingFloor.addCustomerInfoPanel(customerInfoPanel);
    }

    public void addEntrancePanel(EntrancePanel entrancePanel) {
        entrancePanels.put(entrancePanel.entrancePanelName, entrancePanel);
    }

    public void addExitPanel(ExitPanel exitPanel) {
        exitPanels.put(exitPanel.exitPanelName, exitPanel);
    }
}
