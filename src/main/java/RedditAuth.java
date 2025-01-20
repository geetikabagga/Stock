import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;

public class RedditAuth {
    private static final String CLIENT_ID = System.getenv("CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static final String USER_AGENT = System.getenv("USER_AGENT");
    private String accessToken = "";

    RedditAuth() throws IOException {
        generateAccessToken();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public static String getUserAgent() {
        return USER_AGENT;
    }

    public void generateAccessToken() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String credentials = Credentials.basic(CLIENT_ID, CLIENT_SECRET);

        RequestBody body = new FormBody.Builder()
                .add("grant_type", "password")
                .add("username", USERNAME)
                .add("password", PASSWORD)
                .build();

        Request request = new Request.Builder()
                .url("https://www.reddit.com/api/v1/access_token")
                .header("Authorization", credentials)
                .header("User-Agent", USER_AGENT)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String json = response.body().string();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(json);
                this.accessToken = root.path("access_token").asText();
            } else {
                System.err.println("Request failed: " + response.code());
            }
        }
    }
}
