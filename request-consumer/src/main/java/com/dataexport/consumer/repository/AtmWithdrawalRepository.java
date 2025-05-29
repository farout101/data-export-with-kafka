package com.dataexport.consumer.repository;

import com.dataexport.consumer.model.AtmWithdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmWithdrawalRepository extends JpaRepository<AtmWithdrawal, Integer> {
}
