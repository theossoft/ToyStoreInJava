import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyStore() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateToyWeight(int toyId, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(weight);
                break;
            }
        }
    }

    public void runPrizeDraw() {
        Random random = new Random();
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();

        while (!toys.isEmpty()) {
            double randomValue = random.nextDouble() * totalWeight;
            double cumulativeWeight = 0;

            for (Toy toy : toys) {
                cumulativeWeight += toy.getWeight();
                if (randomValue <= cumulativeWeight) {
                    prizeToys.add(toy);
                    decreaseQuantity(toy);
                    break;
                }
            }

            totalWeight -= prizeToys.get(prizeToys.size() - 1).getWeight();
            toys.remove(prizeToys.get(prizeToys.size() - 1));
        }
    }

    private void decreaseQuantity(Toy toy) {
        toy.setQuantity(toy.getQuantity() - 1);
    }

    public void savePrizeToysToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Toy prizeToy : prizeToys) {
                writer.println(prizeToy.getName());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }
}