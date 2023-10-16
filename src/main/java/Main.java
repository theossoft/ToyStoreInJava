public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Добавляем игрушки
        toyStore.addToy(new Toy(1, "Кукла", 10, 20.0));
        toyStore.addToy(new Toy(2, "Мяч", 15, 15.0));
        toyStore.addToy(new Toy(3, "Машинка", 20, 10.0));

        // Изменяем вес игрушек
        toyStore.updateToyWeight(1, 30.0);
        toyStore.updateToyWeight(2, 25.0);
        toyStore.updateToyWeight(3, 20.0);

        // Запускаем розыгрыш
        toyStore.runPrizeDraw();

        // Сохраняем призовые игрушки в файл
        toyStore.savePrizeToysToFile("prize_toys.txt");
    }

}
