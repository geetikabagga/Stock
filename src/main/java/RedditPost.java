import okhttp3.*;

import java.io.IOException;

public class RedditPost {
    private String ACCESS_TOKEN;
    private String content;
    private RedditAuth authenticator;

    RedditPost(RedditAuth authenticator) throws IOException {
        this.authenticator = authenticator;
        this.ACCESS_TOKEN = authenticator.getAccessToken();
        this.content = content;
//        this.postOnReddit();
    }

    public void postOnReddit(String content) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("sr", "r/TechStockPulse")
                .add("title", "Ranked by the previous day's trade volume")
                .add("text", content)
                .add("kind", "self")
                .build();

        Request request = new Request.Builder()
                .url("https://oauth.reddit.com/api/submit")
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("User-Agent", authenticator.getUserAgent())
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Post successful: " + response.body().string());
            } else {
                System.err.println("Post failed: " + response.code());
            }
        }
    }
}
