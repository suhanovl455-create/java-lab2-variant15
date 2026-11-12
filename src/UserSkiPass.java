public class UserSkiPass extends SkiPass {
    public UserSkiPass(String name, double price) {
        super(name, price);
    }

    public UserSkiPass(String name, double price, String tariff) {
        super(name, price, tariff);
    }

    @Override
    public double calculateBillingPrice() {
        return round(getPrice() * getRate());
    }
}
