package com.smuut.payment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Reversal")
public class ReversalTransaction extends BaseTransaction {

  @OneToOne(mappedBy = "reversalTransaction", fetch = FetchType.EAGER)
  private AuthorizeTransaction authorizeTransaction;
}
