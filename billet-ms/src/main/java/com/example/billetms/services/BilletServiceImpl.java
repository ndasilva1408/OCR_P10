package com.example.billetms.services;

import com.example.billetms.entities.Billet;
import com.example.billetms.repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BilletServiceImpl implements BilletService {

    @Autowired
    BilletRepository billetRepository;
    @Autowired
    BilletMapper billetMapper;


    @Override
    public List<Billet> getAllBillets() {
        return billetRepository.findAll();
    }

    @Override
    public List<Billet> getOutDatedBillets() {
        return billetRepository.findAllBilletsOutDated();
    }

    @Override
    public List<Billet> getWaitingList(String bookId) {
        List<Billet> waitingList = new ArrayList<>();

         List<Billet> allBillets = billetRepository.findBilletsByBookId(bookId);

            if (allBillets.size() > 0) {
                for (Billet billet2 : allBillets) {
                    if (billet2.getIsOnWaitList()) {
                        waitingList.add(billet2);
                    }
                }
            }

            return waitingList;
        }

    @Override
    public List<Billet> getBilletsByBook(String id) {
        return billetRepository.findBilletsByBookId(id);
    }


    @Override
    public List<Billet> getBilletsByBooker(String id) {
        return billetRepository.findAllByBookerId(id);
    }

    @Override
    public Billet getBillet(Long id) {
        return billetRepository.getOne(id);
    }

    @Override
    public Billet createBillet(BilletDTO billetDTO) {
        billetDTO.setBookingDate(LocalDateTime.now());
        billetDTO.setEndDate(LocalDateTime.now().plusWeeks(4));
        billetDTO.setExtendDate(LocalDateTime.now().plusWeeks(8));
        billetDTO.setIsExtend(false);
        Billet billet = billetMapper.fromDTO(billetDTO);

        return billetRepository.save(billet);
    }

    @Override
    public Billet createBilletForWaitList(BilletDTO billetDTO) {
        billetDTO.setBookingDate(LocalDateTime.now());
        billetDTO.setEndDate(LocalDateTime.now().plusWeeks(4));
        billetDTO.setExtendDate(LocalDateTime.now().plusWeeks(8));
        billetDTO.setIsExtend(false);
        billetDTO.setIsOnWaitList(true);
        Billet billet = billetMapper.fromDTO(billetDTO);

        return billetRepository.save(billet);
    }

    @Override
    public void updateBilletExtendStatus(Long id) {
        Billet billet = getBillet(id);
        billet.setIsExtend(true);
        billetRepository.save(billet);

    }

    @Override
    public void deleteBillets(Long id) {
        billetRepository.deleteById(id);

    }


}
