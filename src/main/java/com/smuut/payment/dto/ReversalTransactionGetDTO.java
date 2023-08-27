package com.smuut.payment.dto;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReversalTransactionGetDTO extends TransactionGetDTO {

  private UUID authorizeTransactionId;
}
