public class Instructor {
    private final String name;
    private double price;

    public Instructor(String name, double price) {
        this.name = name;
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public final void setPrice(double price) {
        if (!Double.isFinite(price) || price < 0) {
            System.out.println("Неверная цена инструктора. Установлено 0 руб.");
            price = 0;
        }
        this.price = price;
    }
}
