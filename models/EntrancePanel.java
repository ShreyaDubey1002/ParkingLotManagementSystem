package ParkingLotManagementSystem.models;

public class EntrancePanel {
    public String entrancePanelName;
    static int lastEntrancePanelId = 0;

    public EntrancePanel() {
        this.lastEntrancePanelId = this.lastEntrancePanelId + 1;
        this.entrancePanelName = String.valueOf(this.lastEntrancePanelId);
    }

    double scanTicket(ParkingTicket parkingTicket, long issuedAt) {
        return parkingTicket.scanTicket(issuedAt);
    }

    ParkingTicket printTicket(long issuedAt, ParkingRate parkingRate) {
        ParkingTicket parkingTicket = new ParkingTicket(issuedAt, parkingRate);
        return parkingTicket;
    }
}
