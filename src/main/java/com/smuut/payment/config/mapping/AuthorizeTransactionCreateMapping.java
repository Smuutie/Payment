package com.smuut.payment.config.mapping;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.entity.TransactionStatus;
import com.smuut.payment.repository.MerchantRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizeTransactionCreateMapping implements MappingConfiguration {

  private final MerchantRepository merchantRepository;

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap = modelMapper.typeMap(TransactionCreateDTO.class, AuthorizeTransaction.class);

    typemap.setProvider(request -> {
        final var authorizeTransaction = new AuthorizeTransaction();
        authorizeTransaction.setTransactionStatus(TransactionStatus.APPROVED);
        return authorizeTransaction;
    });

    final Converter<UUID, Merchant> pkToMerchantConverter =
        context -> merchantRepository.findById(context.getSource()).orElse(null);

    typemap.addMappings(
        context -> {
          context.skip(AuthorizeTransaction::setId);
          context.skip(AuthorizeTransaction::setCreatedAt);
          context
              .when(Conditions.isNotNull())
              .using(pkToMerchantConverter)
              .map(TransactionCreateDTO::getMerchantId, AuthorizeTransaction::setMerchant);
        });
  }
}
