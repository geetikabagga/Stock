public class Stock {

    // Properties
    private String symbol; // The stock symbol or name, e.g., "AAPL"
    private Long volume; // The trading volume of the stock

    // Constructor
    public Stock(String stockName, Long volume) {
        this.symbol = stockName;
        this.volume = volume;
    }

    // Getter for stockName
    public String getStockName() {
        return symbol;
    }

    // Setter for stockName
    public void setStockName(String stockName) {
        this.symbol = stockName;
    }

    // Getter for volume
    public Long getVolume() {
        return volume;
    }

    // Setter for volume
    public void setVolume(Long volume) {
        this.volume = volume;
    }

    // Main method for testing (optional)
//    public static void main(String[] args) {
//        Stock stock = new Stock("AAPL", 1000);
//        System.out.println("Stock Name: " + stock.getStockName());
//        System.out.println("Volume: " + stock.getVolume());
//
//        // Update values
//        stock.setStockName("GOOGL");
//        stock.setVolume(2000);
//
//        System.out.println("Updated Stock Name: " + stock.getStockName());
//        System.out.println("Updated Volume: " + stock.getVolume());
//    }
}
