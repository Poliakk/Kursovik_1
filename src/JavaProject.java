import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] arg) {
        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = fh.readFile();
        int choice = 0;
        while (choice != -1) {
            choice = mm.getChoice();
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
                default -> System.out.println("\n<< No such command. >>");
            }
        }
    }
}
