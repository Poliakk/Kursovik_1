import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        try {
            int c = Integer.parseInt(reader.nextLine());
            return c;
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Unacceptable input.");
            return -1;
        }
    }

    private void printClubOptions() {
        System.out.println("\nWhat club do you prefer?");
        System.out.print("1) Club Mercury\n");
        System.out.print("2) Club Neptune\n");
        System.out.print("3) Club Jupiter\n");
        System.out.print("4) Multi Clubs\n");
    }

    public int getChoice() {
        System.out.println("\nWELCOME TO OZONE FITNESS CENTER");
        System.out.println("================================");
        System.out.print("1) Add Member\n2) Remove Member\n3) Display Member Information\n");
        System.out.print("Please select an option (or Enter -1 to quit): ");
        return getIntInput();
    }

    public String addMembers(LinkedList<Member> m) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal = clubID -> switch (clubID) {
            case 1 -> 900;
            case 2 -> 950;
            case 3 -> 1000;
            case 4 -> 1200;
            default -> -1;
        };

        printClubOptions();
        do {
            System.out.println("Input clubID from 1 to 4, please: ");
            club = getIntInput();
        } while (club < 0 | club > 5);

        if (m.isEmpty()) {
            memberID = 1;
        } else {
            memberID = m.getLast().getMemberID() + 1;
        }

        System.out.println("Input name of new member: ");
        name = reader.next();
        fees = cal.calculateFees(club);

        if (club == 4) {
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            mem = mbr.getMemberType() + ", " + memberID + ", " + name + ", " + fees + ", " +
                    ((MultiClubMember) mbr).getMembershipPoints();

        } else {
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            mem = mbr.getMemberType() + ", " + memberID + ", " + name + ", " + fees + ", " +
                    ((SingleClubMember) mbr).getClub();
        }
        m.add(mbr);
        System.out.println("New member is added:\n" + mbr);
        return mem;
    }

    public void removeMember(LinkedList<Member> m) {
        int memberID;
        do {
            System.out.println("Input memberID to remove: ");
            memberID = getIntInput();
        } while (m.get(memberID) == null);//((memberID < 0) || (memberID > m.size()))
        m.remove(memberID);
    }

    public void printMemberInfo(LinkedList<Member> m) {
        int memberID;
        do {
            System.out.println("Input memberID for info: ");
            memberID = getIntInput() - 1;
        } while (m.get(memberID) == null);//((memberID < 0) || (memberID > m.size()))
        System.out.println("Member info:\n" + m.get(memberID));
    }
}