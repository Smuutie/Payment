package com.smuut.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorizeTransactionGetDTO extends TransactionGetDTO {

  @NotNull private Double amount;
}
