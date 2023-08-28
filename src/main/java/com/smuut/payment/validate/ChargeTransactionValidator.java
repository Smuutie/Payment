package com.smuut.payment.validate;

import com.smuut.payment.entity.ChargeTransaction;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChargeTransactionValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return ChargeTransaction.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (target instanceof ChargeTransaction chargeTransaction) {
      if (Objects.isNull(chargeTransaction.getAmount())) {
        errors.rejectValue("amount", "no.amount", "Transaction cannot have amount null!");
      }
      if (Objects.isNull(chargeTransaction.getAuthorizeTransactionId())) {
        errors.rejectValue(
            "authorizeTransaction",
            "no.authorize",
            "Charge transaction needs to have Authorize transaction to which it is attached!");
      }
    }
    errors.reject("wrong.type", "Validated type was expected to be ChargeTransaction but wasn't!");
  }
}
