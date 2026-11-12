public class Main {
    private static void printOrder(SkiPass pass, int hours, double total) {
        System.out.println("Ски-пасс: " + pass.getName());
        System.out.println("Тариф: " + pass.getTariff());
        System.out.printf("Базовая цена за человека: %.2f руб.%n", pass.getPrice());

        if (pass instanceof CorporateSkiPass group) {
            System.out.println("Тип: корпоративный");
            System.out.println("Количество сотрудников: " + group.getActiveLicensesCount());
        } else {
            System.out.println("Тип: индивидуальный");
        }
        System.out.printf("Цена пропусков по тарифу: %.2f руб.%n", pass.calculateBillingPrice());

        Instructor instructor = pass.getAppliedOption();
        if (instructor == null) {
            System.out.println("Инструктор: не нанят");
        } else {
            System.out.println("Инструктор: " + instructor.getName());
            System.out.printf("Цена за час: %.2f руб.%n", instructor.getPrice());
            System.out.println("Количество часов: " + hours);
            System.out.printf("Стоимость занятий: %.2f руб.%n", instructor.getPrice() * hours);
        }
        System.out.printf("Итого к оплате: %.2f руб.%n", total);
    }

    public static void main(String[] args) {
        System.out.println("ГОРНОЛЫЖНЫЙ КУРОРТ");
        System.out.println("Тарифы: 1 день — без наценки, 3 дня — +140%, Утренний — скидка 20%.");
        System.out.println();

        SkiResort sms = new SkiResort(new SmsNotification());
        SkiResort email = new SkiResort(new EmailNotification());
        Instructor instructor = new Instructor("Анна", 1500);

        SkiPass pass1 = new UserSkiPass("Обычный", 2000);
        SkiPass pass2 = new UserSkiPass("Выходные", 2300, "3 дня");
        SkiPass pass3 = new UserSkiPass("Утренний", 2000, "Утренний");
        SkiPass pass4 = new CorporateSkiPass("Отдых компании", 2300, "3 дня", 5);

        double price1 = pass1.calculateFinalPrice();
        double price2 = pass2.hireInstructor(instructor);
        double price3 = pass3.hireInstructor(instructor, 2);
        double price4 = pass4.calculateBillingPrice();

        printOrder(pass1, 0, price1);
        sms.processPayment(pass1, "+79991234567", price1);

        printOrder(pass2, 1, price2);
        sms.processPayment(pass2, "89991234567", price2);

        printOrder(pass3, 2, price3);
        sms.processPayment(pass3, "+79991234567", price3);

        printOrder(pass4, 0, price4);
        email.processPayment(pass4, "office@example.com", price4);

        System.out.println("ПРОВЕРКИ НЕКОРРЕКТНЫХ ДАННЫХ (без новых оплат)");
        SkiPass invalid = new CorporateSkiPass("Проверка", -10, "Неизвестный", 0);
        System.out.println("Цена после исправления: " + invalid.calculateBillingPrice());
        System.out.println("Корпоративный промокод: " + pass4.calculateBillingPrice("SALE"));
        new EmailNotification().sendNotification("Проверка", "invalid");
        new SmsNotification().sendNotification("Проверка", "123");
        sms.processPayment(pass1, "+79991234567", -1);
        System.out.println();
        SkiResort.printGlobalStats();
    }
}
