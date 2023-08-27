package com.smuut.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MerchantCreateDTO {

  private boolean admin;

  @NotEmpty private String name;

  private String description;

  @Email private String email;
}
