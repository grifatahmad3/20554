


public class PieceWorker extends Employee{

    private double wagePerPiece;
    private double numOfPieces;

    public PieceWorker(String firstName, String lastName, DateOfBirth dob, String socialSecurityNumber,
                       double wagePerPiece, double numOfPieces) throws IllegalArgumentException {
        super(firstName, lastName, dob, socialSecurityNumber);
        if (wagePerPiece < 0) {
            throw new IllegalArgumentException("wage per piece must be greater than or equal to 0");
        }
        if (numOfPieces < 0) {
            throw new IllegalArgumentException("number of pieces must be greater than or equal to 0");
        }
        this.wagePerPiece = wagePerPiece;
        this.numOfPieces = numOfPieces;
    }

    public double getWagePerPiece() {
        return wagePerPiece;
    }

    public double getNumOfPieces() {
        return numOfPieces;
    }

    public void setWagePerPiece(double wagePerPiece) {
        if (wagePerPiece < 0) {
            throw new IllegalArgumentException("wage per piece must be greater than or equal to 0");
        }
        this.wagePerPiece = wagePerPiece;
    }

    public void setNumOfPieces(double numOfPieces) {
        if (numOfPieces < 0) {
            throw new IllegalArgumentException("number of pieces must be greater than or equal to 0");
        }
        this.numOfPieces = numOfPieces;
    }

    @Override
    public double earnings() {
        return this.numOfPieces * this.wagePerPiece;
    }

    @Override
    public String toString() {
        return String.format("piece worker employee: %s%n%s: $%,.2f; %s: %,.2f",
                super.toString(), "wage per piece", getWagePerPiece(),
                "pieces made", getNumOfPieces());
    }
}
