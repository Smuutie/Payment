package com.smuut.payment.dto;

import com.smuut.payment.entity.TransactionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Data;

@Data
public class TransactionGetDTO {

  private UUID id;

  @Email private String customerEmail;

  @NotEmpty
  @Pattern(regexp = "(\\+\\d{3}|0{1})\\d{9}")
  private String customerPhone;

  private String referenceId;

  private TransactionStatus transactionStatus;

  private UUID merchantId;
}
