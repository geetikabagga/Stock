import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class XPoster {
    public static void postContent(String tweetContent) {
        // Twitter API credentials
        String apiKey = "xki5zMSBHTuB0MtwW6CNx5Dhs";
        String apiSecretKey = "znbzd53sEgRWfd0uiW6My6l3ipgRnmP6uYjUlUVIehhaezYSNn";
        String accessToken = "793819875802087424-5iXUNwMhHQSIUcxUoFxkeMNLCHzNiDZ";
        String accessTokenSecret = "OYhh39ZF0s3kSDYVJSzKwetbIHAHqS1fByMn3sHa10xLf";

        // Set up Twitter instance
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(apiKey, apiSecretKey);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        try {
            // Post the tweet
            twitter.updateStatus(tweetContent);
            System.out.println("Tweet posted successfully!");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
