import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] arg) {
        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = new LinkedList<>(fh.readFile());
        while (true) {
            int choice = mm.getChoice();
            switch (choice) {
                case 1 -> {
                    mem = mm.addMembers(members);
                    fh.appendFile(mem);
                }
                case 2 -> {
                    mm.removeMember(members);
                    fh.overwriteFile(members);
                }
                case 3 -> mm.printMemberInfo(members);
                case -1 -> {
                    System.out.println("\nApp is closed.");
                    System.exit(0);
                }
                default -> System.out.println("No such command.");
            }
        }
    }
}
