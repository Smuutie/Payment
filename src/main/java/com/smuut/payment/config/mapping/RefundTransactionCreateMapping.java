package com.smuut.payment.config.mapping;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.ChargeTransaction;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.entity.RefundTransaction;
import com.smuut.payment.repository.ChargeTransactionRepository;
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

  private final ChargeTransactionRepository chargeTransactionRepository;

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap = modelMapper.typeMap(TransactionCreateDTO.class, RefundTransaction.class);

    final Converter<UUID, Merchant> pkToMerchantConverter =
        context -> merchantRepository.findById(context.getSource()).orElse(null);

    final Converter<UUID, ChargeTransaction> pkToChargeTransactionConverter =
        context -> chargeTransactionRepository.findById(context.getSource()).orElse(null);

    typemap
        .addMappings(
            context -> {
              context.skip(RefundTransaction::setId);
              context.skip(RefundTransaction::setCreatedAt);
              context
                  .when(Conditions.isNotNull())
                  .using(pkToMerchantConverter)
                  .map(TransactionCreateDTO::getMerchantId, RefundTransaction::setMerchant);
              context
                  .when(Conditions.isNotNull())
                  .using(pkToChargeTransactionConverter)
                  .map(
                      TransactionCreateDTO::getTargetTransaction,
                      RefundTransaction::setChargeTransaction);
            })
        .implicitMappings();
  }
}
