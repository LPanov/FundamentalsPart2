package ObjectsAndClasses;

import java.util.Random;
import java.util.Scanner;

public class AdvertisementMessage {
    public static String[] phrases = {"Excellent product.", "Such a great product.",
                        "I always use that product.", "Best product of its category.", "Exceptional product.",
                        "I can’t live without this product."};
    public static String[] events = {"Now I feel good.", "I have succeeded with this product.",
                                "Makes miracles. I am happy of the results!",
                                "I cannot believe but now I feel awesome.",
                                "Try it yourself, I am very satisfied.",
                                "I feel great!"};
    public static String[] authors = {"Diana", "Petya", "Stella",
                                "Elena", "Katya", "Iva", "Annie", "Eva"};

    public static String[] cities = {"Burgas", "Sofia", "Plovdiv",
                                "Varna", "Ruse"};



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            int phrase = rnd.nextInt(AdvertisementMessage.phrases.length);
            int event = rnd.nextInt(AdvertisementMessage.events.length);
            int author = rnd.nextInt(AdvertisementMessage.authors.length);
            int city = rnd.nextInt(AdvertisementMessage.cities.length);

            System.out.printf("%s %s %s - %s\n",
                    AdvertisementMessage.phrases[phrase], AdvertisementMessage.events[event],
                    AdvertisementMessage.authors[author], AdvertisementMessage.cities[city]);
        }
    }

}
