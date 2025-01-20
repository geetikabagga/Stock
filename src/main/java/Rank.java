import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Rank {
    private static  final String POLYGON_API_ENV = "POLYGON_API_KEY";
    private static final String[] STOCK_SYMBOLS = {"AAPL", "META", "AMZN", "MSFT", "GOOGL"};

    public static void main(String[] args) {
        String API_KEY = System.getenv(POLYGON_API_ENV);
        List<Stock> stocks = new ArrayList<>();
        System.out.println("I will use this class to make my API call to get the get the data for the big 5 stocks");
        for (String symbol : STOCK_SYMBOLS) {
            String apiUrl = String.format(
                    "https://api.polygon.io/v2/aggs/ticker/%s/prev?adjusted=true&apiKey=%s",
                    symbol, API_KEY
            );
            try {
                String response = fetchStockData(apiUrl);
                Long volume = processStockData(symbol, response);
                Stock stock = new Stock(symbol, volume);
                stocks.add(stock);
                Thread.sleep(200);
            } catch (Exception e) {
                System.err.println("Error fetching data for " + symbol + ": " + e.getMessage());
            }
        }
        sortSocksBasedOnVolume(stocks);
        String content = generateStockPost(stocks);
        System.out.println(content);

        try {
            RedditAuth authenticator = new RedditAuth();
            authenticator.generateAccessToken();
            RedditPost redditPost = new RedditPost(authenticator);
            redditPost.postOnReddit(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateStockPost(List<Stock> stocks){
        String content = "Here's a list of the top 5 big tech stocks ranked in order of their previous day's trading volume";
        int counter = 1;
        for(Stock stock : stocks){
            content+= "\n" + counter + " " + stock.getStockName() + ": " + stock.getVolume();
            counter+= 1;
        }
        return content;
    }

    private static void sortSocksBasedOnVolume(List<Stock> stocks){
        stocks.sort((s1, s2) -> Long.compare(s1.getVolume(), s2.getVolume()));
    }

    private static String fetchStockData(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static Long processStockData(String symbol, String json) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode results = root.path("results").get(0);
            long volume = results.path("v").asLong(); //
            return volume;
    }
}
