package groomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private int capacity;
    private List<Pet> data;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (this.data.size() < capacity) this.data.add(pet);
    }
    public boolean remove(String name) {
        for (Pet pet : data) {
            if (pet.getName().equals(name)) {
                return data.remove(pet);
            }
        }
        return false;
    }
    public Pet getPet(String name, String owner) {
        return this.data.stream()
                .filter(pet -> pet.getName().equals(name) && pet.getOwner().equals(owner))
                .findFirst().orElse(null);
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        String out = "The grooming salon has the following clients:\n";
        for (Pet pet : data) {
            out += (pet.getName() + " " + pet.getOwner() + "\n");
        }
        return out;

    }


}
