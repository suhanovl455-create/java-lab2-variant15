public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        if (recipient != null && recipient.contains("@") && recipient.contains(".")) {
            System.out.println("[EMAIL] На адрес " + recipient + " отправлено письмо: " + message);
        } else {
            System.out.println("[EMAIL] Неверный адрес. Письмо не отправлено.");
        }
    }
}
