public class CorporateSkiPass extends SkiPass {
    private final int activeLicensesCount;

    public CorporateSkiPass(String name, double price, String tariff, int activeLicensesCount) {
        super(name, price, tariff);
        if (activeLicensesCount < 1) {
            System.out.println("Неверное количество сотрудников. Установлен 1 сотрудник.");
            activeLicensesCount = 1;
        }
        this.activeLicensesCount = activeLicensesCount;
    }

    public int getActiveLicensesCount() {
        return activeLicensesCount;
    }

    @Override
    public double calculateBillingPrice(String promoCode) {
        System.out.println("Для корпоративных клиентов промокоды не применяются.");
        return calculateBillingPrice();
    }

    @Override
    public double calculateBillingPrice() {
        return round(getPrice() * getRate() * activeLicensesCount);
    }
}
