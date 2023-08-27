package com.smuut.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;
import lombok.Data;

@Data
public class MerchantGetDTO {

  private UUID id;

  private boolean admin;

  @NotEmpty private String name;

  private String description;

  @Email private String email;

  private Double totalTransactionSum;
}
