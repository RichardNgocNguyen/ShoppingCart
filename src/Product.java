public class Product {

	private String productSKU;
	private String productName;
	private String productSize;
	private double productPrice;
	
	/**
	 * creates a Product object with given name, size, and price
	 * @param productName - the name of this product 
	 * @param productSize - the size of this product as a string
	 * @param productPrice  - the price of this product as a double
	 * */
	public Product(String productSKU, String productName, String productSize, double productPrice) {
		this.productSKU = productSKU;
		this.productName = productName;
		this.productSize = productSize;
		this.productPrice = productPrice;
	}
	
	/**
	* Static method that returns product's name as a String
	* */
	public String getName() {
		return this.productName;
	}
	
  /**
	* Static method that returns product's sku number as a String
	* */
	public String getSKU() {
		return this.productSKU;
	}
	
  /**
	* Static method that returns product's size as a String
	* */
	public String getSize() {
		return this.productSize;
	}
	
  /**
	* Static method that returns product's price as a double
	* */
	public double getPrice() {
		return this.productPrice;
	}
	
	/**
	 * overrides equals() so that two Product objects are considered equal if the
	 * strings representing their SKU numbers are equal
	 * */
	public boolean equals(Object other) {
		
		Product otherProduct = (Product) other;
		
		if(this.productSKU.equals(otherProduct.getSKU())) {
			return true;
		}
		
		return false;
	}

	public final int hashCode() {
		int result = 17;
		if (productSKU != null) {
			result = 31 * result + productSKU.hashCode();
		}
		if (productName != null) {
			result = 31 * result + productName.hashCode();
		}
		if (productSize != null) {
			result = 31 * result + productSize.hashCode();
		}
		return result;
	}

  /**
	* Static method that returns formatted of string of a product with its sku, product name, size and price 
	* */
  public String toString() {
    return this.productSKU + "     " + this.productName +"                       "+this.productSize +"     "+"$"+ this.productPrice;
  }
	
	
	public static void main(String[] args) {
		System.out.println("--------------PRODUCT TESTER-----------------");
		Product p = new Product("12354","Cookies", "12 dz", 3.50);
		System.out.println("Printing object: "+p);
		System.out.println("Expected: Cookies                       12 dz     $3.50");
	}

}
