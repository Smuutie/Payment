package com.smuut.payment.repository;

import com.smuut.payment.entity.BaseTransaction;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseTransactionRepository extends JpaRepository<BaseTransaction, UUID> {
  void deleteAllByCreatedAtBefore(OffsetDateTime createdAt);
}
