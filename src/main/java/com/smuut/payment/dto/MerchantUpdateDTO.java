package com.smuut.payment.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MerchantUpdateDTO {

  private String name;

  private String description;

  @Email private String email;
}
