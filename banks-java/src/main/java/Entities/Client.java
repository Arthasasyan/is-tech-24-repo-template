package Entities;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private boolean isFishy;
    private int passportId;
    private String name;
    private boolean notifications;
    private String address;
    private ArrayList<String> notificationsHistory;

    public Client(String name, boolean notifications, String address, int passportId)
    {
        this.name = name;
        this.notifications = notifications;
        this.address = address;
        this.passportId = passportId;
        this.address = address;
        List<String> notificationsHistory = new ArrayList<>() { };
        isFishy = true;
    }

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getNotificationsHistory() {
        return notificationsHistory;
    }

    public void setNotificationsHistory(ArrayList<String> notificationsHistory) {
        this.notificationsHistory = notificationsHistory;
    }

    public boolean isFishy() {
        return isFishy;
    }

    public void setFishy(boolean fishy) {
        isFishy = fishy;
    }
}
