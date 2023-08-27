package com.smuut.payment.validate;

import com.smuut.payment.entity.BaseTransaction;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BaseTransactionValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return BaseTransaction.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (target instanceof BaseTransaction baseTransaction) {
      if (Objects.isNull(baseTransaction.getTransactionStatus())) {
        errors.rejectValue(
            "transactionStatus", "no.status", "Transaction cannot have status null!");
      }
      if (Objects.isNull(baseTransaction.getMerchant())
          || !baseTransaction.getMerchant().isActive()) {
        errors.rejectValue(
            "merchant", "no.merchant", "Transaction cannot have merchant null or inactive one!");
      }
    }
    errors.reject(
        "wrong.type",
        "Validated type was expected to be BaseTransaction or its' subclass but wasn't!");
  }
}
