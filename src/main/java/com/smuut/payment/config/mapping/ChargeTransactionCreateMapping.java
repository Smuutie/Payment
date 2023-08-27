package com.smuut.payment.config.mapping;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import com.smuut.payment.entity.ChargeTransaction;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.repository.AuthorizeTransactionRepository;
import com.smuut.payment.repository.MerchantRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChargeTransactionCreateMapping implements MappingConfiguration {

  private final MerchantRepository merchantRepository;

  private final AuthorizeTransactionRepository authorizeTransactionRepository;

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap = modelMapper.typeMap(TransactionCreateDTO.class, ChargeTransaction.class);

    final Converter<UUID, Merchant> pkToMerchantConverter =
        context -> merchantRepository.findById(context.getSource()).orElse(null);

    final Converter<UUID, AuthorizeTransaction> pkToAuthorizeTransactionConverter =
        context -> authorizeTransactionRepository.findById(context.getSource()).orElse(null);

    typemap.addMappings(
        context -> {
          context.skip(ChargeTransaction::setId);
          context.skip(ChargeTransaction::setCreatedAt);
          context
              .when(Conditions.isNotNull())
              .using(pkToMerchantConverter)
              .map(TransactionCreateDTO::getMerchantId, ChargeTransaction::setMerchant);
          context
              .when(Conditions.isNotNull())
              .using(pkToAuthorizeTransactionConverter)
              .map(
                  TransactionCreateDTO::getTargetTransaction,
                  ChargeTransaction::setAuthorizeTransaction);
        });
  }
}
