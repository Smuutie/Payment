package com.smuut.payment.cronjob;

import com.smuut.payment.repository.BaseTransactionRepository;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
public class OldTransactionCleanupJob {

  private final BaseTransactionRepository baseTransactionRepository;

  @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
  public void cleanupOldTransactions() {
    baseTransactionRepository.deleteAllByCreatedAtBefore(OffsetDateTime.now().minusHours(1));
  }
}
