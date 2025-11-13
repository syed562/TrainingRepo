package booking;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of hotels: ");
        int hotels = sc.nextInt();

        System.out.print("Enter number of rooms per hotel: ");
        int roomsPerHotel = sc.nextInt();

        System.out.print("Enter price per room: ");
        double price = sc.nextDouble();

        Hotel hotel = new Hotel(hotels, roomsPerHotel, price);

        char wantRoom;
        do {
            System.out.print("Do you want to book a room? (y/n): ");
            wantRoom = sc.next().charAt(0);

            if (wantRoom == 'y' || wantRoom == 'Y') {
                hotel.bookRoom();
            }

        } while ((wantRoom == 'y' || wantRoom == 'Y') && hotel.totalRooms > 0);
        hotel.printSummary();
        
}
}