package com.smuut.payment.repository;

import com.smuut.payment.entity.ChargeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChargeTransactionRepository extends JpaRepository<ChargeTransaction, UUID> {
}
