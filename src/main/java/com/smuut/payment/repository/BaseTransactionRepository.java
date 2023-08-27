package com.smuut.payment.repository;

import com.smuut.payment.entity.BaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseTransactionRepository extends JpaRepository<BaseTransaction, UUID> {
}
