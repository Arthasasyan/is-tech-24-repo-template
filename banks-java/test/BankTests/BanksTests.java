package BankTests;

import Services.CentralBank;
import org.junit.jupiter.api.Test;
import org.testng;

public class BanksTests {
    private final CentralBank centralBank = new CentralBank();

    @Test
    public void BankCreation()
    {
        var bank3 = centralBank.CreateBank("Alpha", 0, 3.5f, 2.5f, 40000, 5000000, 10);
        Assert.False(bank3 is null);
    }

}
