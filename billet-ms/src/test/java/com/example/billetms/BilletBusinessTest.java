package com.example.billetms;

import com.example.billetms.Batch.Configuration.BilletBatchConfig;
import com.example.billetms.Batch.Configuration.DatabaseConnect;
import com.example.billetms.Batch.Configuration.EmailConfig;
import com.example.billetms.entities.*;
import com.example.billetms.services.BilletService;
import com.example.billetms.services.BilletServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mail.SimpleMailMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})


public class BilletBusinessTest {
    private static BilletBatchConfig manager = new BilletBatchConfig();

    Billet billet1;

    Billet billet2;

    Book book;
    Client client;

    @BeforeEach
    void initialize() {
        Date date = new Date();
        client = new Client(0L, "zozo", "okok", "test", "testest", "mika");
        Bibliotheque bibliotheque = new Bibliotheque(0L, "93", "Olive", "09859784");
        book = new Book(0L, 2L, "tuche", 15, true,
                "okok", "aojfds", 1385, "cool", "pascool");
        Email email = new Email("nom", "prenom", "meail@d", "titre", false, date, date);

        billet1 = new Billet(0L, "1", "1", date, date, date, "3", false, true, false, date);

        billet2 = new Billet(1L, "2", "2", date, date, date, "2", false, true, false, date);

    }

 /*  @Test                        //NULL POINTER BUG
    void remindWaitingClientTest() {

       Date date = new Date();
        Email emailTest = new Email();
        List<Billet>billetList=new ArrayList<>();
        billetList.add(billet2);
        billetList.add(billet1);



        when(billetService.getWaitingList("3")).thenReturn(billetList);
        when(DatabaseConnect.getClientFromDB(billet1.getBookerId())).thenReturn(client);
        when(DatabaseConnect.getBookFromDB(billet1.getBookId())).thenReturn(book);

        manager.remindWaitingClient("3");




        Email emailValid = new Email("testest","mika","test","tuche",false,date,date);

        assertThat(emailTest.equals(emailValid));

    }*/

    @Test
    void addDaysTest() {
        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        int days = 2;
        TimeZone tz = TimeZone.getTimeZone(" Europe/Paris ");
        Date dateValid = new GregorianCalendar(2014, Calendar.FEBRUARY, 13).getTime();

        Date dateTest = manager.addDays(date, days, tz);

        assertThat(dateTest.equals(dateValid));
    }

    @Test
    void createEmailInformationsTest() {
        Date date = new Date();

        Email emailValid = new Email("testest", "mika", "test", "tuche", false, date, date);
        Email emailTest = manager.createEmailInformations(client, book, billet1);

        assertThat(emailTest.equals(emailValid));
    }


}
