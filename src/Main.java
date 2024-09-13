import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;
import java.io.PrintWriter;  

class Main {
  /**
	* Main method that takes in a user's name per lab request. Program then takes in each inputted sku number and then creates a product class of it that is inserted into a Cart class. After the user is done inputting sku numbers, the program uses the Cart class to create a receipt.txt file that is outputted at the end of the program. 
	* */
  public static void main(String[] args) throws FileNotFoundException {
    Scanner myObj = new Scanner(System.in);
    System.out.println("What name would you like on the receipt?");
    String receiptName = myObj.nextLine();
    Cart receipt = new Cart(receiptName);
    boolean validSKUCheck = false;
    System.out.println("Please enter a SKU number for the item. Enter Q to exit and pay.");
    String skuNumber = myObj.nextLine();
    while (!(skuNumber.equalsIgnoreCase("Q"))) {
      File productList = new File("products.txt");
      Scanner fileScan = new Scanner(productList);
      Pattern p = Pattern.compile("(\\d+)\\s+((\\w+|\\W+)+)\\s+(\\d+\\s+\\w+)\\s+(\\d+.\\d+)");
      Matcher match;
      while (fileScan.hasNextLine()) {
        String currentLine = fileScan.nextLine();
        match = p.matcher(currentLine);
        if (match.find()) {
          if (skuNumber.equals(match.group(1))) {
            String sku = match.group(1);
            String name = match.group(2) + match.group(3);
            String quant = match.group(4);
            double price = Double.parseDouble(match.group(5));
            Product item = new Product(sku, name, quant, price);
            receipt.addProduct(item);
            validSKUCheck = true;
            break;
          } else {
            validSKUCheck = false;
          }
        }
      }
      if (validSKUCheck == false) {
        System.out.println("Invalid SKU"); 
        }
      fileScan.close();
      System.out.println("Please enter a SKU number for the item. Enter Q to exit and pay.");
      skuNumber = myObj.nextLine();
    }
    DecimalFormat df = new DecimalFormat("#.##");
    System.out.println("Amount due: " + df.format(receipt.getTransactionTotal()));
    System.out.print("Enter in payment: $");
    double customerPayment = myObj.nextDouble();
    double amountDue = receipt.getTransactionTotal();
    if (customerPayment >= amountDue) {
      System.out.println("Change: $" + df.format(customerPayment - amountDue));
    }
    else {
      while (customerPayment < amountDue){
      System.out.println("Amount due: " + df.format(amountDue - customerPayment));
      System.out.println("Insufficient payment!");
      System.out.print("Enter in remaining amount: $");
      customerPayment += myObj.nextDouble();
      if (customerPayment >= amountDue) {
        System.out.println("Change: $" + df.format(customerPayment - amountDue));
        }
      }
    }
      PrintWriter writer = new PrintWriter(receiptName + ".txt" );
      writer.print(receipt);
      writer.println("Payment: $" + df.format(customerPayment));
      writer.println("Change: $" + df.format(customerPayment - amountDue));
      writer.println("---------------------------------------------------------------" + "\n");
      writer.close(); 
    }
  }

