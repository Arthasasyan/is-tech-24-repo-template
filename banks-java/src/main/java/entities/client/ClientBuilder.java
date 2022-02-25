package entities.client;

import tools.BanksException;

public class ClientBuilder {
    private String name;
    private String address;
    private long passport;
    private boolean isNeedNotifications;


    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setNotifications(boolean isNeedNotifications)
    {
        this.isNeedNotifications = isNeedNotifications;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    public Client BuildClient()
    {
        return new Client(name, isNeedNotifications,address, passport);
    }
}