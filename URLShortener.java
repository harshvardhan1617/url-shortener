import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Base64;
import java.util.Scanner;

public class URLShortener {

    private static final String BASE_URL = "http://short.url/";
    private static final int SHORT_URL_LENGTH = 6;
    private Map<String, String> urlMap = new HashMap<>();
    private Random random = new Random();

    // Shorten the given original URL
    public String shortenURL(String originalURL) {
        String shortURL;
        do {
            shortURL = generateRandomShortURL();
        } while (urlMap.containsKey(shortURL));
        
        urlMap.put(shortURL, originalURL);
        return BASE_URL + shortURL;
    }

    // Retrieve the original URL from a short URL
    public String getOriginalURL(String shortURL) {
        String key = shortURL.replace(BASE_URL, "");
        return urlMap.get(key);
    }

    // Generate a random short URL
    private String generateRandomShortURL() {
        byte[] randomBytes = new byte[4];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes).substring(0, SHORT_URL_LENGTH);
    }

    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();
        Scanner scanner = new Scanner(System.in);

        // Input URL from user
        System.out.print("Enter the URL to shorten: ");
        String originalURL = scanner.nextLine();
        
        // Shorten the URL
        String shortURL = shortener.shortenURL(originalURL);
        System.out.println("Short URL: " + shortURL);
        
        // Retrieve the original URL using the short URL
        System.out.print("Enter the short URL to retrieve the original URL: ");
        String inputShortURL = scanner.nextLine();
        String retrievedURL = shortener.getOriginalURL(inputShortURL);

        if (retrievedURL != null) {
            System.out.println("Original URL: " + retrievedURL);
        } else {
            System.out.println("Short URL not found.");
        }

        scanner.close();
    }
}