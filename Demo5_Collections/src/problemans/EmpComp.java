package problemans;

import java.util.*;

public class EmpComp {

    public static void find_person(HashMap<String, List<String>> hm, String cname, String name) {
        if (hm.containsKey(cname)) {
            if (hm.get(cname).contains(name)) {
                System.out.println(name + " is working in " + cname);
            } else {
                System.out.println(name + " is not found in employees list of " + cname);
            }
        } else {
            hm.put(cname, new ArrayList<>(List.of(name)));
            System.out.println(name + " is added to the list");
        }
    }

    public static void main(String[] args) {
        HashMap<String, List<String>> hm = new HashMap<>();

        hm.put("Chubb", new ArrayList<>(List.of("Sabi", "Aash", "Aak")));
        hm.put("Google", new ArrayList<>(List.of("Aarav", "Meera", "Rohan")));
        hm.put("Amazon", new ArrayList<>(List.of("Sabiha", "Vikram", "Tanya")));
        hm.put("Microsoft", new ArrayList<>(List.of("Aakash", "Priya", "Dev")));
        hm.put("Apple", new ArrayList<>(List.of("Nisha", "Rahul", "Manav")));
        hm.put("Netflix", new ArrayList<>(List.of("Karan", "Isha", "Sneha")));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter the person name:");
            String name = sc.next();
            System.out.println("Enter the company name:");
            String cname = sc.next();

            find_person(hm, cname, name);

            System.out.println("Do you want to continue? (yes/no)");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("no")) break;
        }
        sc.close();
    }
}
