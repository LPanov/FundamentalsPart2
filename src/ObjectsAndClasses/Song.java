package ObjectsAndClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Song {
    private String type;
    private String name;
    private String time;

    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getTime() {
        return time;
    }
    public void setType (String type) {
        this.type = type;
    }
    public void setName (String name) {
        this.name = name;
    }
    public void setTime (String time) {
        this.time = time;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        List<Song> songs = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String[] data = sc.nextLine().split("_");

            Song song = new Song();

            song.setType(data[0]);
            song.setName(data[1]);
            song.setTime(data[2]);

            songs.add(song);
        }

        String type = sc.nextLine();
        if (type.equals("all")) {
            for (Song song : songs) {
                System.out.println(song.getName());
            }
        }
        else {
            for (Song song : songs) {
                if (song.getType().equals(type)) {
                    System.out.println(song.getName());
                }
            }
        }

        /*List<ObjectsAndClasses.Song> filterSong = songs.stream().filter(e -> e.getType().equals(type))
                                .collect(Collectors.toList());
        for (ObjectsAndClasses.Song song : songs) {
            System.out.println(song.getName());
        }*/
    }
}
