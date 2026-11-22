/* Programmed by Anthony Ellinos
   Assignment: Project 4
   Abstract MovieItem class 
   Base class for all movie items */



public abstract class MovieItem {

    private String description;
    private int runningMinutes;
    private double cost;

    public MovieItem() {
        description = "";
        runningMinutes = 0;
        cost = 0.0;
    }

    public String getDescription() {
        return description;
    }

    public int getRunningMinutes() {
        return runningMinutes;
    }

    public double getCost() {
        return cost;
    }

    // takes in a string from file
    // throws if blank
    protected void setDescription(String desc) throws InvalidRecordException {
        // trim desc before check to ensure if its empty or not
        if (desc.trim().isEmpty()) {
            throw new InvalidRecordException("Description must not be blank.");
        }
        this.description = desc.trim();
    }

    // takes in a string from file
    protected void setRunningMinutes(String count) throws InvalidRecordException {
        int minutes = 0;
        // converts the string to an int,
        // throw if not a valid integer
        // or if it is 0 or less

        try {
            minutes = Integer.parseInt(count.trim());
        } catch (Exception e) {
            throw new InvalidRecordException("Running time must be a valid integer.");
        }
        if (minutes <= 0) {
            throw new InvalidRecordException("Running time must be greater than 0.");
        }
        // saves value after everything passes
        this.runningMinutes = minutes;
    }

    // takes in a string from file
    protected void setCost(String cost) throws InvalidRecordException {
        double amount;
        // throws if not a valid double or is 0 or less
        try {
            amount = Double.parseDouble(cost.trim());
        } catch (Exception e) {
            throw new InvalidRecordException("Cost must be a valid number.");
        }
        // checks for 0, negatives, and infinity
        if (amount <= 0 || !Double.isFinite(amount)) {
            throw new InvalidRecordException("Cost must be a finite number greater than 0.");
        }
        // saves value after everything passes
        this.cost = amount;
    }

    public abstract String toString();
}
