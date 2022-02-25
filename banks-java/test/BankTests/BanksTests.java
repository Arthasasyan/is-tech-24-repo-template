package BankTests;

import entities.client.Client;
import entities.client.ClientBuilder;
import services.CentralBank;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import services.SimpleBank;
import tools.BanksException;

public class BanksTests {
    private  CentralBank centralBank = new CentralBank();
    private  SimpleBank bank1 = centralBank.CreateBank("Tinkoff", 3.5f, 4.0f, 1.5f, 50000, 5000000, 5);
    private  SimpleBank bank2 = centralBank.CreateBank("Sber", 0, 3.5f, 2.5f, 40000, 5000000, 10);

    @Test
    public void BankCreation()
    {
        var bank3 = centralBank.CreateBank("Alpha", 0, 3.5f, 2.5f, 40000, 5000000, 10);
        Assert.assertFalse(bank3 == null);
    }

    @Test
    public void TransferMoneyTest() throws BanksException {
        var bank1 = centralBank.findBankByName("Tinkoff");
        var builder1 = new ClientBuilder();
        builder1.setName("Misha Suslov");
        builder1.setNotifications(true);
        builder1.setAddress("Parnas");
        builder1.setPassport(1234);
        var B1Client1 = bank1.addBankClient(builder1.BuildClient());
        var B1Client1Account = bank1.addDebitAccountForClient(B1Client1);

        var builder2 = new ClientBuilder();
        builder2.setName("Vika Zaharova");
        builder2.setNotifications(true);
        var B1Client2 = bank1.addBankClient(builder2.BuildClient());
        var B1Client2Account = bank1.addDebitAccountForClient(B1Client2);

        B1Client1Account.addMoneyToAccount(5000);
        B1Client2Account.addMoneyToAccount(10000);
        bank1.transferMoney(B1Client1Account.getId(), B1Client2Account.getId(), 2000);
        Assert.assertEquals(B1Client1Account.getMoney(), 3000);
        Assert.assertEquals(B1Client2Account.getMoney(), 12000);
    }
        
    @Test
    public void CreditAccountTest() throws BanksException {
        var bank1 = centralBank.findBankByName("Tinkoff");
        var builder1 = new ClientBuilder();
        builder1.setName("Misha Suslov");
        builder1.setNotifications(true);
        builder1.setAddress("Parnas");
        builder1.setPassport(1234);
        var B1Client1 = bank1.addBankClient(builder1.BuildClient());
        var B1Client1Account = bank1.addDebitAccountForClient(B1Client1);
        try {
            B1Client1Account.getMoneyFromAccount(10000000);
        }
        catch (BanksException e) {
            System.out.println(e.getMessage());
        }

    }
        
    @Test
    public void DepositAccountTest() throws BanksException {
        var bank1 = centralBank.findBankByName("Tinkoff");
        var builder1 = new ClientBuilder();
        builder1.setName("Misha Suslov");
        builder1.setNotifications(true);
        builder1.setAddress("Parnas");
        builder1.setPassport(1234);
        var B1Client1 = bank1.addBankClient(builder1.BuildClient());
        var B1Client1Account = bank1.addDepositAccountForClient(B1Client1, 5000);
        try {
                B1Client1Account.getMoneyFromAccount(500);
            }
        catch (BanksException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void DebitAccountTest()
    {
        var bank1 = centralBank.findBankByName("Tinkoff");
        var builder1 = new ClientBuilder();
        builder1.setName("Misha Suslov");
        builder1.setNotifications(true);
        builder1.setAddress("Parnas");
        builder1.setPassport(1234);
        var B1Client1 = bank1.addBankClient(builder1.BuildClient());
        var B1Client1Account = bank1.addDebitAccountForClient(B1Client1);
        try {
                    B1Client1Account.getMoneyFromAccount(500);
            }
        catch (BanksException e) {
            System.out.println(e.getMessage());
        }
    }

}

