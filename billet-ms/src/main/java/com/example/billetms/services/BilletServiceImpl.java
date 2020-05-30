package com.example.billetms.services;

import com.example.billetms.entities.Billet;
import com.example.billetms.repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    public void isExtendable(Long id) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            List<Billet> billetArrayList;
            Date currenDate = dateFormatter.parse(dateFormatter.format(new Date()));
            billetArrayList = this.getBilletsByBook(id.toString());
            for (int i = 0; i < billetArrayList.size(); i++) {
                if (!billetArrayList.get(i).getIsExtend()) {
                    if (billetArrayList.get(i).getEndDate().before(currenDate)) {
                        billetArrayList.get(i).setIsExtendable(false);
                        billetRepository.save(billetArrayList.get(i));
                        System.out.println("le billet up "+ billetArrayList.get(i).getId() +" son statut " + billetArrayList.get(i).getIsExtendable());
                    } else if (billetArrayList.get(i).getExtendDate().before(currenDate)) {
                        billetArrayList.get(i).setIsExtendable(false);
                        billetRepository.save(billetArrayList.get(i));
                        System.out.println("le billet up pck extend "+ billetArrayList.get(i).getId());
                    }
                }
        }
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
