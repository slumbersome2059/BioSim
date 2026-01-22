// 1. The "Read-Only" view
interface IReadOnlyRank {
    String getTitle();
    int getSalary();
}

// 2. The internal state (extends the read-only view)
abstract class Rank implements IReadOnlyRank {
    // Only internal logic has access to "promote"
    abstract Rank promote() throws Exception;
}

class Lecturer extends Rank {
    public String getTitle() { return "Lecturer"; }
    public int getSalary() { return 50000; }
    public Rank promote() { return new Professor(); }
}
class Professor extends Rank {
    public String getTitle() { return "Lecturer"; }
    public int getSalary() { return 50000; }
    public Rank promote() throws Exception {throw new Exception();};
}

// 3. The Academic class
class Academic {
    private Rank rank = new Lecturer(); // Internal pointer

    public void promote() throws Exception {
        this.rank = this.rank.promote(); // Replaces the object
    }

    // THIS IS THE METHOD YOU ASKED ABOUT
    public IReadOnlyRank getRank() {
        return this.rank; // Returns the CURRENT memory address
    }
}