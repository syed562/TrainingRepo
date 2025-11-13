package booking;
import java.util.Scanner;

class Hotel {
    int totalRooms;
    double pricePerRoom;
    double totalIncome;

    Hotel(int hotels, int roomsPerHotel, double pricePerRoom) {
        this.totalRooms = hotels * roomsPerHotel;
        this.pricePerRoom = pricePerRoom;
        this.totalIncome = 0;
    }

    boolean bookRoom() {
        if (totalRooms > 0) {
            totalRooms--;
            totalIncome += pricePerRoom;
            System.out.println("Room booked. Rooms left: " + totalRooms);
            return true;
        } else {
            System.out.println("No rooms available!");
            return false;
        }
    }

    void printSummary() {
        System.out.println("\nEnd of day summary:");
        System.out.println("Total rooms left: " + totalRooms);
        System.out.println("Total income: ₹" + totalIncome);
}
}