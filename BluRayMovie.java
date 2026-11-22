/* Programmed by Anthony Ellinos
   Assignment: Project 4
   BluRayMovie class
   Subclass of MovieItem and implements 
   discountable to add retail pricing and 
   discounts */

public class BluRayMovie extends MovieItem implements Discountable {

    public static final double MARGIN = 0.20;
    private double discountPercent;

    // builds a movie and separates desc, minutes, and cost by tabs
    public BluRayMovie(String line) throws InvalidRecordException {
        String[] parts = line.split("\t", -1);

        if (parts.length != 3) {
            throw new InvalidRecordException("Expected exactly 3 tab-delimited fields.");
        }
        // use Movieitem setters and the split parts
        setDescription(parts[0]);
        setRunningMinutes(parts[1]);
        setCost(parts[2]);

    }

    // sets the discount between the range of the min and max
    public void applyDiscount(double p) {
        if (p > MAX_DISCOUNT) {
            System.out.printf("WARNING: Discount exceeds maximum. Using %.1f%%.%n", MAX_DISCOUNT);
            discountPercent = MAX_DISCOUNT;
        } else if (p < MIN_DISCOUNT) {
            System.out.printf("WARNING: Discount below minimum. Using %.1f%%.%n", MIN_DISCOUNT);
            discountPercent = MIN_DISCOUNT;
        } else {
            discountPercent = p;
        }
    }

    public double getDiscountedPrice() {
        return getRetailPrice() * (1 - (discountPercent / 100));
    }

    public double getRetailPrice() {
        return getCost() * (1 + MARGIN);
    }

    @Override
    public String toString() {
        String str = "Title: " + getDescription() + "\nRunning Time: " + getRunningMinutes() + " minutes"
                + "\nPrice: $" + String.format("%.2f", getRetailPrice());

        if (discountPercent > 0) {
            str += "\nDiscount: " + String.format("%.1f", discountPercent) + "%" + "\nDiscounted Price: $"
                    + String.format("%.2f", getDiscountedPrice());
        }
        return str;
    }

}
