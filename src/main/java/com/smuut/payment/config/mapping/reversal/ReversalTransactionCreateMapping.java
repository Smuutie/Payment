package com.smuut.payment.config.mapping.reversal;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.*;
import com.smuut.payment.repository.MerchantRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReversalTransactionCreateMapping implements MappingConfiguration {

  private final MerchantRepository merchantRepository;

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap = modelMapper.typeMap(TransactionCreateDTO.class, ReversalTransaction.class);

    final Converter<UUID, Merchant> pkToMerchantConverter =
        context -> merchantRepository.findById(context.getSource()).orElse(null);

    typemap
        .addMappings(
            context -> {
              context.skip(ReversalTransaction::setId);
              context.skip(ReversalTransaction::setCreatedAt);
              context
                  .when(Conditions.isNotNull())
                  .using(pkToMerchantConverter)
                  .map(TransactionCreateDTO::getMerchantId, ReversalTransaction::setMerchant);
              context
                  .when(Conditions.isNotNull())
                  .map(
                      TransactionCreateDTO::getTargetTransaction,
                      ReversalTransaction::setAuthorizeTransactionId);
            })
        .implicitMappings();
  }
}
