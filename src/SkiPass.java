public abstract class SkiPass {
    private static final double ONE_DAY = 1.0;
    private static final double THREE_DAYS = 2.4;
    private static final double MORNING = 0.8;
    private static final double MIN_PRICE = 100.0;

    private final String name;
    private double price;
    private String tariff;
    private Instructor appliedOption = null;

    public SkiPass(String name, double price) {
        this(name, price, "1 день");
    }

    public SkiPass(String name, double price, String tariff) {
        this.name = name;
        setPrice(price);
        setTariff(tariff);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getTariff() {
        return tariff;
    }

    public Instructor getAppliedOption() {
        return appliedOption;
    }

    public final void setPrice(double price) {
        if (!Double.isFinite(price) || price <= 0) {
            System.out.println("Неверная базовая цена. Установлено 100 руб.");
            price = MIN_PRICE;
        }
        this.price = price;
    }

    public final void setTariff(String tariff) {
        this.tariff = tariff == null ? "" : tariff;
    }

    protected double getRate() {
        return switch (tariff) {
            case "1 день" -> ONE_DAY;
            case "3 дня" -> THREE_DAYS;
            case "Утренний" -> MORNING;
            default -> {
                System.out.println("Неизвестный тариф: " + tariff + ". Расчет без наценки.");
                yield ONE_DAY;
            }
        };
    }

    protected double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public abstract double calculateBillingPrice();

    // Дополнительная перегрузка по требованию части 2.
    // Для варианта 15 промокоды не определены: неизвестный код не меняет цену.
    public double calculateBillingPrice(String promoCode) {
        System.out.println("Промокод не предусмотрен тарифами курорта. Цена без скидки.");
        return calculateBillingPrice();
    }

    public double calculateFinalPrice() {
        return calculateBillingPrice();
    }

    public double calculateFinalPrice(Instructor instructor) {
        return calculateFinalPrice(instructor, 1);
    }

    public double calculateFinalPrice(Instructor instructor, int hours) {
        if (instructor == null) {
            System.out.println("Инструктор не выбран. Расчет без занятий.");
            appliedOption = null;
            return calculateBillingPrice();
        }
        if (hours <= 0) {
            System.out.println("Неверное количество часов. Установлен 1 час.");
            hours = 1;
        }
        appliedOption = instructor;
        return round(calculateFinalPrice() + instructor.getPrice() * hours);
    }

    public double hireInstructor(Instructor instructor) {
        return calculateFinalPrice(instructor);
    }

    public double hireInstructor(Instructor instructor, int hours) {
        return calculateFinalPrice(instructor, hours);
    }
}
