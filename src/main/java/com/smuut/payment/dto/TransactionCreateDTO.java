package com.smuut.payment.dto;

import com.smuut.payment.entity.TransactionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionCreateDTO {

  @NotNull private TransactionType type;

  @NotNull private UUID merchantId;

  @NotEmpty @Email private String customerEmail;

  @NotEmpty
  @Pattern(regexp = "(\\+\\d{3}|0{1})\\d{9}")
  private String customerPhone;
}
