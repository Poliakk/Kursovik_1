import java.io.File;
import java.io.FileWriter;
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
            lines.forEach(System.out::println);
            System.out.println();
        } catch (IOException e) {
            System.out.println("Can't read file.");
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
    }

    public void appendFile(String mem) {
        try (FileWriter writer = new FileWriter("members.csv", true)) {
            writer.append(mem);
            writer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void overwriteFile(LinkedList<Member> m) {
        try (FileWriter writer = new FileWriter("members.tmp", true)) {
            for (Member mbr : m) {
                writer.append(mbr.toString());
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        try {
            File file = new File("members.csv");
            file.delete();
            File newFile = new File("members.tmp");
            File newFileName = new File("members.csv");
            newFile.renameTo(newFileName);
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}