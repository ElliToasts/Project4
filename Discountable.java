/* Programmed by Anthony Ellinos
   Assignment: Project 4
   Discountable Interface 
   Any item that can be discounted 
   must use these methods */

public interface Discountable {
    public final double MAX_DISCOUNT = 100.0;
    public final double MIN_DISCOUNT = 0.0;

    public void applyDiscount(double p);

    public double getDiscountedPrice();
}
