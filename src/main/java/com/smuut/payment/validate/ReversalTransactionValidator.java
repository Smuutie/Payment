package com.smuut.payment.validate;

import com.smuut.payment.entity.ReversalTransaction;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReversalTransactionValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return ReversalTransaction.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (target instanceof ReversalTransaction reversalTransaction) {
      if (Objects.isNull(reversalTransaction.getAuthorizeTransaction())) {
        errors.rejectValue(
            "authorizeTransaction",
            "no.charge",
            "Reversal transaction needs to have Authorize transaction to which it is attached!");
      }
    }
    errors.reject(
        "wrong.type", "Validated type was expected to be ReversalTransaction but wasn't!");
  }
}
