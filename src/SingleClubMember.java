public class SingleClubMember extends Member {
    private int club;

    public SingleClubMember(char pMemberType, int pMemberID, String pName, double pFees, int club) {
        super(pMemberType, pMemberID, pName, pFees);
        this.setClub(club);
    }

    public int getClub() {
        return club;
    }

    public void setClub(int club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberType=" + getMemberType() +
                ", memberID=" + getMemberID() +
                ", name='" + getName() + '\'' +
                ", fees=" + getFees() +
                ". SingleClubMember: club='" + getClub() + '\'' +
                '}';
    }
}