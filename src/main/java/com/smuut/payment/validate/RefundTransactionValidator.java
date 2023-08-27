package com.smuut.payment.validate;

import com.smuut.payment.entity.RefundTransaction;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RefundTransactionValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return RefundTransaction.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (target instanceof RefundTransaction refundTransaction) {
      if (Objects.isNull(refundTransaction.getAmount())) {
        errors.rejectValue("amount", "no.amount", "Transaction cannot have amount null!");
      }
      if (Objects.isNull(refundTransaction.getChargeTransaction())) {
        errors.rejectValue(
            "chargeTransaction",
            "no.charge",
            "Refund transaction needs to have Charge transaction to which it is attached!");
      }
    }
    errors.reject("wrong.type", "Validated type was expected to be RefundTransaction but wasn't!");
  }
}
