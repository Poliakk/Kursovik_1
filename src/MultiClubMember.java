public class MultiClubMember extends Member {
    private int membershipPoints;

    public MultiClubMember(char pMemberType, int pMemberID, String pName, double pFees, int membershipPoints) {
        super(pMemberType, pMemberID, pName, pFees);
        this.setMembershipPoints(membershipPoints);
    }

    public int getMembershipPoints() {
        return membershipPoints;
    }

    public void setMembershipPoints(int membershipPoints) {
        this.membershipPoints = membershipPoints;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", " + getMembershipPoints() + '\n';
    }
}