package ParkingLotManagementSystem.models;

public class ParkingAttendant extends AbstractAccount {
    void processPayment(ParkingTicket parkingTicket, long paidAt) {
        parkingTicket.processPayment(paidAt);
    }
    double scanTicket(ParkingTicket parkingTicket, long issuedAt) {
        return parkingTicket.scanTicket(issuedAt);
    }
}
