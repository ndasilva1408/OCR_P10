package com.example.biblioms;

import com.example.biblioms.repository.BibliothequeRepository;
import com.example.biblioms.services.BiblioService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BiblioMsApplicationTests {
    @Mock
    private BibliothequeRepository bibliothequeRepository;
    @Mock
    private BiblioService biblioService;

    @Test
    void contextLoads() {
    }

}
