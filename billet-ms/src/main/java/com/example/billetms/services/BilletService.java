package com.example.billetms.services;

import com.example.billetms.entities.Billet;

import java.util.List;
import java.util.PriorityQueue;

public interface BilletService {
    List<Billet> getAllBillets();
    List<Billet> getOutDatedBillets();

    List<Billet> getBilletsByBooker(String id);

    Billet getBillet  (Long id);
    Billet createBillet(BilletDTO billetDTO);
    Billet createBilletForWaitList(BilletDTO billetDTO);
    void updateBilletExtendStatus(Long id);
    void deleteBillets(Long id);

    List<Billet> getWaitingList(String bookId);

    List<Billet> getBilletsByBook(String id);
}
