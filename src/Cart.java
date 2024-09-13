import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Collections;

public class Cart{
  
	private ArrayList<Product> purchasedProducts = new ArrayList<>();
	private double total = 0;
  private String customerName;

  /**
	* Default constructor which takes in Customer's name in beginning of Main Program
	* */
	public Cart(String name) {
    this.customerName = name;
	}

  /**
	* Static method that takes in a product given a Sku, Name, Quantity, and getPrice
  * Only adds to quantity if product is already in the cart so that cart keeps only 1 
  * in arraylist
	* */
  	public void addProduct(Product p) {
    purchasedProducts.add(p);
    total += p.getPrice();
	}


  /**
	* Static method that returns total of all products in customer's cart
	* */
	public double getTransactionTotal() {
    return total;
	}
  
	/**
	* returns a formatted string containing the entire 
	* representation of the receipt for this cart,
	* including store name, list of items with subtotals,
	* grand total, payment, and change.
	* @return a String 
	* */
	public String toString() {
    DecimalFormat df = new DecimalFormat("#.##");
		String receipt = "";
    receipt += "---------------------------------------------------------------" + "\n";
    receipt += "Just Fooding" + "\n";
    receipt += "123 E. Lechuga St." + "\n";
    receipt += "Carrotville, CA 99999" +"\n\n";
    receipt += String.format("%-5s%60s", "Item", "Subtotal"+"\n");
    HashSet<Product> uniqueProducts = new HashSet<Product>(purchasedProducts);
    for (Product p : uniqueProducts) {
      int counter = Collections.frequency(purchasedProducts, p);
      receipt += String.format("%-40s%-15s%10s", p.getName().replaceAll("\\s+", " "), counter + "@" + "$"+ "("+ df.format(p.getPrice()) + ")", df.format(counter * p.getPrice()) +"\n\n");
    }
    receipt += "Total: $" + df.format(getTransactionTotal()) + "\n";
    return receipt;
	}
}