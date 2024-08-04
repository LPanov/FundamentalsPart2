package FinalExamPrep;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ThePianist {
    private String composer;
    private String key;

    public ThePianist(String composer, String key) {
        this.composer = composer;
        this.key = key;
    }

    public String getComposer() {
        return composer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(sc.nextLine());
        Map<String, ThePianist> pieces = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] pcs = sc.nextLine().split("\\|");
            ThePianist pianist = new ThePianist(pcs[1], pcs[2]);
            pieces.putIfAbsent(pcs[0], pianist);
        }

        while (true) {
            String[] token = sc.nextLine().split("\\|");
            if (token[0].equals("Stop")) break;

            String piece = token[1];
            if (token[0].equals("Add")) {
                String composer = token[2];
                String key = token[3];
                if (!pieces.containsKey(piece)) {
                    pieces.put(piece, new ThePianist(composer, key));
                    System.out.println(piece+" by "+composer+" in "+key+" added to the collection!");
                } else {
                    System.out.println(piece+" is already in the collection!");
                }
            }
            else if (token[0].equals("Remove")) {
                if (pieces.containsKey(piece)) {
                    pieces.remove(piece);
                    System.out.println("Successfully removed "+piece+"!");
                } else {
                    System.out.println("Invalid operation! "+piece+" does not exist in the collection.");
                }
            }
            else if (token[0].equals("ChangeKey")) {
                String newKey = token[2];
                if (pieces.containsKey(piece)) {
                    pieces.get(piece).setKey(newKey);
                    System.out.println("Changed the key of "+piece+" to "+newKey+"!");
                } else {
                    System.out.println("Invalid operation! "+piece+" does not exist in the collection.");
                }
            }
        }

        pieces.entrySet().stream().forEach(f -> System.out.printf("%s -> Composer: %s, Key: %s%n", f.getKey(), f.getValue().getComposer(), f.getValue().getKey()));
    }

    }

