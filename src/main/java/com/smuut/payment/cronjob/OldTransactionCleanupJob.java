package com.smuut.payment.cronjob;

import com.smuut.payment.repository.BaseTransactionRepository;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OldTransactionCleanupJob {

  private final BaseTransactionRepository baseTransactionRepository;

  @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
  @Transactional
  public void cleanupOldTransactions() {
    baseTransactionRepository.deleteAllByCreatedAtBefore(OffsetDateTime.now().minusHours(1));
  }
}
