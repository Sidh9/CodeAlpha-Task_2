package trader;


	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.Scanner;

	// Class representing a User
	class User {
	    private String username;
	    private Portfolio portfolio;

	    public User(String username) {
	        this.username = username;
	        this.portfolio = new Portfolio();
	    }

	    public String getUsername() {
	        return username;
	    }

	    public Portfolio getPortfolio() {
	        return portfolio;
	    }
	}

	// Class representing a Stock
	class Stock {
	    private String name;
	    private double price;

	    public Stock(String name, double price) {
	        this.name = name;
	        this.price = price;
	    }

	    public String getName() {
	        return name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }
	}

	// Class managing a user's portfolio
	class Portfolio {
	    private Map<String, Integer> stocks; // Stock name and quantity

	    public Portfolio() {
	        stocks = new HashMap<>();
	    }

	    public void buyStock(String stockName, int quantity) {
	        stocks.put(stockName, stocks.getOrDefault(stockName, 0) + quantity);
	    }

	    public void sellStock(String stockName, int quantity) {
	        if (stocks.containsKey(stockName) && stocks.get(stockName) >= quantity) {
	            stocks.put(stockName, stocks.get(stockName) - quantity);
	            if (stocks.get(stockName) == 0) {
	                stocks.remove(stockName);
	            }
	        } else {
	            System.out.println("Insufficient stock to sell.");
	        }
	    }

	    public void displayPortfolio() {
	        System.out.println("Portfolio:");
	        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
	            System.out.println(entry.getKey() + ": " + entry.getValue());
	        }
	    }
	}

	// Main class for the Trading Platform
	public class Tradingplatform {
	    private static List<User> users = new ArrayList<>();
	    private static Map<String, Stock> stockMarket = new HashMap<>();

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        initializeStockMarket();

	        System.out.println("Welcome to the Stock Trading Platform!");

	        // User registration
	        System.out.print("Enter username: ");
	        String username = scanner.nextLine();
	        User user = new User(username);
	        users.add(user);

	        boolean running = true;

	        while (running) {
	            System.out.println("\nOptions: 1. Buy Stock 2. Sell Stock 3. View Portfolio 4. Exit");
	            System.out.print("Choose an option: ");
	            int option = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (option) {
	                case 1: // Buy Stock
	                    System.out.print("Enter stock name: ");
	                    String buyStockName = scanner.nextLine();
	                    System.out.print("Enter quantity: ");
	                    int buyQuantity = scanner.nextInt();
	                    if (stockMarket.containsKey(buyStockName)) {
	                        user.getPortfolio().buyStock(buyStockName, buyQuantity);
	                        System.out.println("Bought " + buyQuantity + " shares of " + buyStockName);
	                    } else {
	                        System.out.println("Stock not found.");
	                    }
	                    break;

	                case 2: // Sell Stock
	                    System.out.print("Enter stock name: ");
	                    String sellStockName = scanner.nextLine();
	                    System.out.print("Enter quantity: ");
	                    int sellQuantity = scanner.nextInt();
	                    user.getPortfolio().sellStock(sellStockName, sellQuantity);
	                    break;

	                case 3: // View Portfolio
	                    user.getPortfolio().displayPortfolio();
	                    break;

	                case 4: // Exit
	                    running = false;
	                    System.out.println("Exiting platform. Goodbye!");
	                    break;

	                default:
	                    System.out.println("Invalid option. Try again.");
	            }
	        }
	        scanner.close();
	    }

	    // Initialize some stocks in the market
	    private static void initializeStockMarket() {
	        stockMarket.put("AAPL", new Stock("AAPL", 150.00));
	        stockMarket.put("GOOGL", new Stock("GOOGL", 2800.00));
	        stockMarket.put("AMZN", new Stock("AMZN", 3500.00));
	        stockMarket.put("MSFT", new Stock("MSFT", 300.00));
	    }
	}


