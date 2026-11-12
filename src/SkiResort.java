public class SkiResort {
    private final NotificationService notificationService;
    private static int totalOrdersCount = 0;
    private static double totalRevenue = 0;

    public SkiResort(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public static int getTotalOrdersCount() {
        return totalOrdersCount;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public void processPayment(SkiPass pass, String contact, double finalPrice) {
        if (pass == null || !Double.isFinite(finalPrice) || finalPrice <= 0) {
            System.out.println("Неверные данные заказа. Оплата не проведена.");
            return;
        }
        totalOrdersCount++;
        totalRevenue = Math.round((totalRevenue + finalPrice) * 100.0) / 100.0;
        String message = String.format("Оплачен ски-пасс «%s». Сумма: %.2f руб.",
                pass.getName(), finalPrice);
        System.out.println("Оплата проведена.");
        if (notificationService == null) {
            System.out.println("Сервис уведомлений не задан. Чек не отправлен.");
        } else {
            notificationService.sendNotification(message, contact);
        }
        System.out.println();
    }

    public static void printGlobalStats() {
        System.out.println("ИТОГИ РАБОТЫ КУРОРТА");
        System.out.println("Всего заказов: " + totalOrdersCount);
        System.out.printf("Общая выручка: %.2f руб.%n", totalRevenue);
    }

}
