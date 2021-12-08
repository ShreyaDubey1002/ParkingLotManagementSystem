package ParkingLotManagementSystem.models;

public abstract class AbstractAccount {
    Person person;
    String username;
    String password;
    AccountStatus accountStatus;

    public void resetPassword(String password) {
        this.password = password;
    }
}
