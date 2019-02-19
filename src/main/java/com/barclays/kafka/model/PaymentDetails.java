package com.barclays.kafka.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * {@link PaymentDetails} used to send as kafka message
 */
public class PaymentDetails implements Serializable {


    private Long id;

    private String cardNumber;

    private String cardHolderName;

    private Double amount;


    public PaymentDetails() {

    }

    public PaymentDetails(Long id, String cardNumber, String cardHolderName, Double amount) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.amount = amount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "PaymentDetails{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", amount=" + amount +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDetails that = (PaymentDetails) o;
        return id == that.id &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(cardHolderName, that.cardHolderName) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cardNumber, cardHolderName, amount);
    }
}
