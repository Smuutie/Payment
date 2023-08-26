package com.smuut.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Refund")
public class RefundTransaction extends BaseTransaction {

  @NotNull
  @OneToOne(mappedBy = "refundTransaction", fetch = FetchType.EAGER, optional = false)
  private ChargeTransaction chargeTransaction;

  @NotNull
  @Column(name = "refund_amount")
  private Double amount;
}
