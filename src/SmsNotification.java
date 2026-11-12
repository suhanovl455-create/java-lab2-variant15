public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        if (recipient != null && (recipient.matches("\\+7[0-9]{10}")
                || recipient.matches("8[0-9]{10}"))) {
            System.out.println("[SMS] На номер " + recipient + " отправлено сообщение: " + message);
        } else {
            System.out.println("[SMS] Неверный номер. Нужен формат +7XXXXXXXXXX или 8XXXXXXXXXX.");
        }
    }
}
