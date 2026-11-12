public class Checks {
  private static void equal(double expected, double actual) {
    if (Math.abs(expected - actual) > 0.001) throw new AssertionError(expected + " != " + actual);
  }
  public static void main(String[] args) {
    SkiPass user = new UserSkiPass("Лев", 2000);
    equal(2000, user.calculateBillingPrice());
    equal(4800, new UserSkiPass("Лев", 2000, "3 дня").calculateBillingPrice());
    equal(1600, new UserSkiPass("Лев", 2000, "Утренний").calculateBillingPrice());
    equal(5000, user.hireInstructor(new Instructor("Анна", 1500), 2));
    SkiPass company = new CorporateSkiPass("Компания", 2300, "3 дня", 5);
    equal(27600, company.calculateBillingPrice());
    equal(27600, company.calculateBillingPrice("SALE"));
    equal(100, new CorporateSkiPass("Тест", 0, "?", 0).calculateBillingPrice());
    equal(100, new UserSkiPass("Тест", Double.NaN).calculateBillingPrice());
    equal(3500, user.hireInstructor(new Instructor("Анна", 1500), 0));
    equal(2000, user.hireInstructor(null));
    int[] notices = {0};
    NotificationService fake = (message, recipient) -> notices[0]++;
    new SkiResort(fake).processPayment(user, "test", 2000);
    new SkiResort(fake).processPayment(company, "test", 27600);
    new SkiResort(fake).processPayment(user, "test", -1);
    equal(29600, SkiResort.getTotalRevenue());
    equal(2, SkiResort.getTotalOrdersCount());
    equal(2, notices[0]);
    System.out.println("Все проверки пройдены.");
  }
}
