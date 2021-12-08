package ParkingLotManagementSystem.Driver;

import ParkingLotManagementSystem.models.*;

import java.util.HashMap;

public class Driver {
    public static void main(String[] args) {
        HashMap<Long, Double> hourlyRate = new HashMap<Long, Double>();
        hourlyRate.put(1L, 2.0);
        hourlyRate.put(2L, 3.0);
        ParkingRate parkingRate = new ParkingRate(hourlyRate);
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance(parkingRate);
        ParkingFloor parkingFloor1 = new ParkingFloor();
        parkingLot.addParkingFloor(parkingFloor1);
        ParkingFloor parkingFloor2 = new ParkingFloor();
        parkingLot.addParkingFloor(parkingFloor2);
        EntrancePanel entrancePanel1 = new EntrancePanel();
        parkingLot.addEntrancePanel(entrancePanel1);
        EntrancePanel entrancePanel2 = new EntrancePanel();
        parkingLot.addEntrancePanel(entrancePanel2);
        ExitPanel exitPanel1 = new ExitPanel(parkingLot.getTickets());
        parkingLot.addExitPanel(exitPanel1);
        ExitPanel exitPanel2 = new ExitPanel(parkingLot.getTickets());
        parkingLot.addExitPanel(exitPanel2);
        ParkingTicket parkingTicket1 = parkingLot.getParkingTicket("1", new Car(), entrancePanel1.entrancePanelName, 1L, parkingRate);
        ParkingTicket parkingTicket2 = parkingLot.getParkingTicket("1", new Van(), entrancePanel2.entrancePanelName, 2L, parkingRate);
        System.out.println("parkingTicket1 : " + parkingTicket1.parkingTicketId);
        System.out.println("parkingTicket2 : " + parkingTicket2.parkingTicketId);

        ParkingTicket parkingTicket3 = parkingLot.getParkingTicket("1", new Van(), entrancePanel2.entrancePanelName, 3L, parkingRate);
        double price = exitPanel1.scanPayment(parkingTicket1, 4L);
        System.out.println("Price paid : " + price);
        //parkingLot.admin.addParkingSpot(new LargeParkingSpot(), "1");
    }
}
