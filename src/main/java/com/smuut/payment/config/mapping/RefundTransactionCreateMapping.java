package com.smuut.payment.config.mapping;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.entity.RefundTransaction;
import com.smuut.payment.repository.MerchantRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefundTransactionCreateMapping implements MappingConfiguration {

  private final MerchantRepository merchantRepository;

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap = modelMapper.typeMap(TransactionCreateDTO.class, RefundTransaction.class);

    final Converter<UUID, Merchant> pkToMerchantConverter =
        context -> merchantRepository.findById(context.getSource()).orElse(null);

    typemap
        .addMappings(
            context -> {
              context.skip(RefundTransaction::setId);
              context.skip(RefundTransaction::setCreatedAt);
              context.map(TransactionCreateDTO::getAmount, RefundTransaction::setAmount);
              context
                  .when(Conditions.isNotNull())
                  .using(pkToMerchantConverter)
                  .map(TransactionCreateDTO::getMerchantId, RefundTransaction::setMerchant);
              context
                  .when(Conditions.isNotNull())
                  .map(
                      TransactionCreateDTO::getTargetTransaction,
                      RefundTransaction::setChargeTransactionId);
            })
        .implicitMappings();
  }
}
