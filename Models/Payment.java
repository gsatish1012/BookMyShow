package com.example.Models;

import java.util.UUID;

import com.example.Enums.PaymentStatus;

public class Payment {
  private final UUID paymentId;
  private final PaymentStatus status;

  public Payment(PaymentStatus status) {
    this.paymentId = UUID.randomUUID();
    this.status = status;
  }

  public UUID getPaymentId() {
    return paymentId;
  }

  public PaymentStatus getStatus() {
    return status;
  }
}
