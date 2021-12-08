package ParkingLotManagementSystem.models;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public Admin admin;
    String parkingLotId;
    static int lastParkingLotId = 0;
    Address address;
    ParkingRate parkingRate;
    HashMap<String, ParkingFloor> parkingFloors;
    HashMap<String, EntrancePanel> entrancePanels;
    HashMap<String, ExitPanel> exitPanels;

    int largeSpotCount;
    int electricSpotCount;
    int handicappedSpotCount;

    int largeMaxSpotCount;
    int electricMaxSpotCount;
    int handicappedMaxSpotCount;

    int floors;
    int largeSlotsPerFloor;
    int handicappedSlotsPerFloor;
    int electricSlotsPerFloor;

    HashMap<String, ParkingTicket> tickets;

    private static ParkingLot parkingLot = null;

    private ParkingLot(ParkingRate parkingRate) {
        this.lastParkingLotId = this.lastParkingLotId + 1;
        this.parkingLotId = String.valueOf(lastParkingLotId);
        this.parkingRate = parkingRate;
        this.largeSpotCount = 0;
        this.electricSpotCount = 0;
        this.handicappedSpotCount = 0;
        this.largeMaxSpotCount = 10;
        this.electricMaxSpotCount = 10;
        this.handicappedMaxSpotCount = 10;
        this.tickets = new HashMap<String, ParkingTicket>();
        this.largeSlotsPerFloor = 2;
        this.handicappedSlotsPerFloor = 3;
        this.electricSlotsPerFloor = 4;
        this.floors = 2;
        this.parkingFloors = new HashMap<String, ParkingFloor>();
        for (int j=0;j<floors;j++) {
            ParkingFloor parkingFloor = new ParkingFloor();
            parkingFloor.addParkingDisplayBoard(new ParkingDisplayBoard());
            System.out.println("Floor : " + parkingFloor.floorName);
            for (int i = 0; i < largeSlotsPerFloor; i++) {
                ParkingSpot largeParkingSpot = new LargeParkingSpot();
                parkingFloor.addParkingSpot(largeParkingSpot);
            }
            for (int i = 0; i < handicappedSlotsPerFloor; i++) {
                ParkingSpot handicappedParkingSpot = new HandicappedParkingSpot();
                parkingFloor.addParkingSpot(handicappedParkingSpot);
            }
            for (int i = 0; i < electricSlotsPerFloor; i++) {
                ParkingSpot electricParkingSpot = new ElectricParkingSpot();
                parkingFloor.addParkingSpot(electricParkingSpot);
            }
            parkingFloors.put(parkingFloor.floorName, parkingFloor);
        }

        this.entrancePanels = new HashMap<String, EntrancePanel>();
        this.exitPanels = new HashMap<String, ExitPanel>();
        this.admin = new Admin(parkingFloors, entrancePanels, exitPanels);
    }
    public synchronized ParkingTicket getParkingTicket(String floorName, Vehicle vehicle, String entrancePanelName, long issuedAt, ParkingRate parkingRate) {
        ParkingTicket ticket = this.entrancePanels.get(entrancePanelName).printTicket(issuedAt, parkingRate);
        tickets.put(ticket.parkingTicketId, ticket);
        ParkingFloor parkingFloor = this.parkingFloors.get(floorName);
        ParkingSpot parkingSpot = getFreeParkingSpot(vehicle, parkingFloor);
        if (parkingSpot == null) {
            return null;
        } else {
            System.out.println("Booked ticket : " + ticket.parkingTicketId);
        }
        parkingFloor.addVehicleToParkingSpot(vehicle, parkingSpot);
        vehicle.assignTicket(ticket);
        return ticket;
    }

    ParkingSpot getFreeParkingSpot(Vehicle vehicle, ParkingFloor parkingFloor) {
        switch (vehicle.vehicleType) {
            case CAR:
            case VAN:
                for (Map.Entry<String, ParkingSpot> val : parkingFloor.largeParkingSpots.entrySet()) {
                    if (val.getValue().parkingSpotType == ParkingSpotType.LARGE && val.getValue().isFree()) {
                        return val.getValue();
                    }
                }
                System.out.println("No free large parking spots");
                break;
            case ELECTRIC:
                for (Map.Entry<String, ParkingSpot> val : parkingFloor.largeParkingSpots.entrySet()) {
                    if (val.getValue().parkingSpotType == ParkingSpotType.ELECTRIC && val.getValue().isFree()) {
                        return val.getValue();
                    }
                }
                System.out.println("No free electric parking spots");
                break;
        }
        return null;
    }

    public HashMap<String, ParkingTicket> getTickets() {
        return this.tickets;
    }

    public static ParkingLot getParkingLotInstance(ParkingRate parkingRate) {
        if (parkingLot == null) {
            parkingLot = new ParkingLot(parkingRate);
        }
        return parkingLot;
    }

    public void addParkingFloor(ParkingFloor parkingFloor) {
        admin.addParkingFloor(parkingFloor);
    }
    public void addEntrancePanel(EntrancePanel entrancePanel) {
        admin.addEntrancePanel(entrancePanel);
    }
    public void addExitPanel(ExitPanel exitPanel) {
        admin.addExitPanel(exitPanel);
    }
}
