package comp3350.pbbs.objects;

import java.io.Serializable;

import comp3350.pbbs.objects.Cards.DebitCard;

public class BankAccount  implements Serializable
{
    private String accountName;
    private String accountNumber;
    private DebitCard linkedCard;

    public BankAccount(String accountName, String accountNumber, DebitCard linkedCard)
    {
        if (accountNumber == null)
        {
            throw new IllegalArgumentException("account number cannot be null");
        }
        if (linkedCard == null)
        {
            throw new IllegalArgumentException("A bank account must be linked to a debit card");
        }

        this.accountName = (accountName == null || accountName.isEmpty()) ? "No Name" : accountName;
        this.accountNumber = accountNumber;
        this.linkedCard = linkedCard;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public DebitCard getLinkedCard()
    {
        return linkedCard;
    }

    public boolean equals(BankAccount o)
    {
        return this.accountNumber.equals(o.accountNumber);
    }
}