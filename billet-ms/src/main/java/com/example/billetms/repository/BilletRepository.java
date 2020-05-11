package com.example.billetms.repository;

import com.example.billetms.entities.Billet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilletRepository extends JpaRepository<Billet, Long> {
    List<Billet> findAllByBookerId(String userId);

    @Query("SELECT b FROM Billet b WHERE current_date>b.endDate AND b.isExtend=false OR b.isExtend=true AND current_date>b.extendDate")
    List<Billet> findAllBilletsOutDated();


    List<Billet> findBilletsByBookId(String bookId);
}
