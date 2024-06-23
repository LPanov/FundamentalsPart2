package ObjectsAndClasses;

import java.util.Scanner;

public class Articles {
    private String title;
    private  String content;
    private String author;

    public Articles(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return  title + " - " + content + ": " + author;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int n = Integer.parseInt(sc.nextLine());
        String[] input = s.split(", ");
        Articles article = new Articles(input[0], input[1], input[2]);


        for (int i = 0; i < n; i++) {
            String in = sc.nextLine();
            String[] command = in.split(": ");

            if (command[0].equals("Edit")) {
                article.setContent(command[1]);
            }
            else if (command[0].equals("ChangeAuthor")) {
                article.setAuthor(command[1]);
            }
            else if (command[0].equals("Rename")) {
                article.setTitle(command[1]);
            }
        }
        System.out.println(article.toString());



    }
}
