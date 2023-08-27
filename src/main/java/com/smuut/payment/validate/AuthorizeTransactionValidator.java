package com.smuut.payment.validate;

import com.smuut.payment.entity.AuthorizeTransaction;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AuthorizeTransactionValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return AuthorizeTransaction.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (target instanceof AuthorizeTransaction authorizeTransaction) {
      if (Objects.isNull(authorizeTransaction.getAmount())) {
        errors.rejectValue("amount", "no.amount", "Transaction cannot have amount null!");
      }
    }
    errors.reject(
        "wrong.type", "Validated type was expected to be AuthorizeTransaction but wasn't!");
  }
}
