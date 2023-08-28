package com.smuut.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Refund")
public class RefundTransaction extends BaseTransaction {

  @NotNull
  @JdbcTypeCode(SqlTypes.CHAR)
  @Column(name = "charge_transaction_id")
  private UUID chargeTransactionId;

  @NotNull
  @Column(name = "refund_amount")
  private Double amount;
}
