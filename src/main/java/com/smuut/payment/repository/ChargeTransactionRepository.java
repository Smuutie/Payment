package com.smuut.payment.repository;

import com.smuut.payment.entity.ChargeTransaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeTransactionRepository extends JpaRepository<ChargeTransaction, UUID> {}
