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
@DiscriminatorValue("Reversal")
public class ReversalTransaction extends BaseTransaction {

  @NotNull
  @JdbcTypeCode(SqlTypes.CHAR)
  @Column(name = "reversal_to_authorize_transaction_id")
  private UUID authorizeTransactionId;
}
