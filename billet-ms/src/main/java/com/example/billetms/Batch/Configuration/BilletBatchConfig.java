package com.example.billetms.Batch.Configuration;

import com.example.billetms.entities.Billet;
import com.example.billetms.entities.Book;
import com.example.billetms.entities.Client;
import com.example.billetms.entities.Email;
import com.example.billetms.services.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;


@Component
public class BilletBatchConfig {

    Email email = new Email();
    Book book = new Book();
    Client client = new Client();
    List<Billet> billetsOutDated = new ArrayList<>();

    @Autowired
    BilletService billetService;

    @Autowired
    EmailConfig emailConfig;




@Scheduled(cron = "0 0 0 * * *") //Everyday at midnight

    public void runTask() {

          billetsOutDated = getAllBilletsOutDated();

            System.out.println("Scheduled task  work");
            System.out.println(billetsOutDated);

           if (billetsOutDated.size() > 0) {
                for (Billet billet1 : billetsOutDated) {

                    client = DatabaseConnect.getClientFromDB(billet1.getBookerId());
                    System.out.println("ClientDB ok " + client);
                    book = DatabaseConnect.getBookFromDB(billet1.getBookId());
                    System.out.println("BookDB ok " + book);
                    email = createEmailInformations(client, book, billet1);
                    System.out.println("email ok" + email);

                    if (email.getIsExtend()) emailConfig.sendEmailwithExtension(email);
                    else emailConfig.sendEmailwithoutExtension(email);
                }


            }
        }


    private Email createEmailInformations(Client client, Book book, Billet billet1) {
        Email emailToSend = new Email();

        emailToSend.setPrenom(client.getPrenom());
        emailToSend.setNom(client.getNom());
        emailToSend.setEmailClient(client.getMail());
        emailToSend.setBookTitle(book.getTitre());
        emailToSend.setIsExtend(billet1.getIsExtend());
        emailToSend.setEndDate(billet1.getEndDate());
        emailToSend.setExtendDate(billet1.getExtendDate());

        return emailToSend;
    }


    private List<Billet> getAllBilletsOutDated() {
        return billetService.getOutDatedBillets();
    }




}
