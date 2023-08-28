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
@DiscriminatorValue("Charge")
public class ChargeTransaction extends BaseTransaction {

  @NotNull
  @Column(name = "charge_amount")
  private Double amount;

  @NotNull
  @JdbcTypeCode(SqlTypes.CHAR)
  @Column(name = "charge_to_authorize_transaction_id")
  private UUID authorizeTransactionId;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "charge_transaction_id")
  private RefundTransaction refundTransaction;
}
