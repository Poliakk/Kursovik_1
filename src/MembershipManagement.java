import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        int choice;
        try {
            choice = reader.nextInt();
            reader.nextLine();
            if (choice == -1) {
                System.out.println("\nApp is closed.");
                System.exit(0);
            }
            return choice;
        } catch (Exception e) {
            System.out.print("Invalid input. ");
            reader.nextLine();
            return -1;
        }
    }

    private void printClubOptions() {
        System.out.println("\nWhat club do you prefer?");
        System.out.print("1) Club Mercury\n");
        System.out.print("2) Club Neptune\n");
        System.out.print("3) Club Jupiter\n");
        System.out.print("4) Multi Clubs\n\n");
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
            System.out.println("Enter clubID from 1 to 4, please: ");
            club = getIntInput();
        } while (club <= 0 | club >= 5);

        if (m.isEmpty()) {
            memberID = 1;
        } else {
            memberID = m.getLast().getMemberID() + 1;
        }

        System.out.println("Enter name of new member: ");
        name = reader.nextLine();
        fees = cal.calculateFees(club);

        if (club == 4) {
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
        } else {
            mbr = new SingleClubMember('S', memberID, name, fees, club);
        }
        mem = mbr.toString();
        m.add(mbr);
        System.out.printf("<< Welcome new %sClubmember %s. Your memberID='%d'. >>",
                (((mbr.getMemberType()) == 'S') ? ("Single") : ("Multi")), mbr.getName(), mbr.getMemberID());
        System.out.println();
        return mem;
    }

    public void removeMember(LinkedList<Member> m) {
        int i;
        do {
            System.out.println("Please enter memberID to cancel membership: ");
            int memberID = getIntInput();
            for (i = 0; i < m.size(); i++) {
                if (m.get(i).getMemberID() == memberID) {
                    m.remove(i);
                    System.out.printf("<< Membership for memberID='%d' is canceled. >>", memberID);
                    return;
                }
            }
            System.out.println("No member with this ID.");
            System.out.println();
        } while (i == m.size());
    }

    public void printMemberInfo(LinkedList<Member> m) {
        int i;
        do {
            System.out.println("Enter memberID for info: ");
            int memberID = getIntInput();
            for (i = 0; i < m.size(); i++) {
                if (m.get(i).getMemberID() == memberID) {
                    String[] memberInfo = m.get(i).toString().split(", ");
                    System.out.println("<< Member info: >>");
                    System.out.printf("memberType: %sClubMember\n", (memberInfo[0].equals("S")) ? ("Single") : ("Multi"));
                    System.out.printf("memberID: %s\n", memberInfo[1]);
                    System.out.printf("name: %s\n", memberInfo[2]);
                    System.out.printf("fees: %s\n", memberInfo[3]);
                    System.out.print((memberInfo[0].equals("S")) ? ("clubNumber: ") : ("membershipPoints: "));
                    System.out.print(memberInfo[4]);
                    System.out.println("--------------------------");
                    return;
                }
            }
            System.out.println("No member with this ID.\n");
        } while (i == m.size());
    }
}