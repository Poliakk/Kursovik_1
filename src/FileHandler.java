import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class FileHandler {
    public LinkedList<Member> readFile() {
        LinkedList<Member> listOfMembers = new LinkedList<>();
        List<String> lines;
        Member mbr;
        try {
            lines = Files.readAllLines(Path.of("members.csv"));
            lines.stream().forEach(System.out::println);
            System.out.println();
        } catch (IOException e) {
            System.out.println("Unreadable file.");
            return null;
        }

        for (String j : lines) {
            String[] parts = j.split(", ");
            char pMemberType = parts[0].charAt(0);
            int pMemberID = Integer.parseInt((parts[1]));
            String pName = parts[2];
            double pFees = Double.parseDouble((parts[3]));
            int lastPart = Integer.parseInt((parts[4]));
            if (pMemberType == 4) {
                mbr = new MultiClubMember(pMemberType, pMemberID, pName, pFees, lastPart);
            } else {
                mbr = new SingleClubMember(pMemberType, pMemberID, pName, pFees, lastPart);
            }
            listOfMembers.add(mbr);
        }
        return listOfMembers;
/*
    public void appendFile(String mem){

    }
    public void overwriteFile(LinkedList<Member> m){ //TODO
        FileOutputStream fileOutputStream = new FileOutputStream("members.temp");

    }*/
    }
}