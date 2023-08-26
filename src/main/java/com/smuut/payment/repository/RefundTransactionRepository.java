package com.smuut.payment.repository;

import com.smuut.payment.entity.RefundTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefundTransactionRepository extends JpaRepository<RefundTransaction, UUID> {
}
