package tn.esprit.rh.achat.services.produit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProduitServiceTest {
    @Mock
    ProduitRepository produitRepository;

    @InjectMocks
    ProduitServiceImpl produitService;

    @Test
    public void testRetrieveProduit() {
        // Create a sample Produit
        Date dateCreation = new Date(); // Set an actual date here
        Date dateDerniereModification = new Date(); // Set an actual date here
        Produit produit = new Produit("code1", "Libellé du produit 1", 10.0f, dateCreation, dateDerniereModification);

        // Define the behavior of the repository's findById method
        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));

        // Call the retrieveProduit method with a sample ID
        Produit retrievedProduit = produitService.retrieveProduit(1L);

        // Verify that the repository's findById method was called with the provided ID
        Mockito.verify(produitRepository, Mockito.times(1)).findById(1L);

        // Check that the retrieved Produit matches the sample Produit
        Assertions.assertNotNull(retrievedProduit);
        Assertions.assertEquals("code1", retrievedProduit.getCodeProduit());
        Assertions.assertEquals("Libellé du produit 1", retrievedProduit.getLibelleProduit());
        Assertions.assertEquals(10.0f, retrievedProduit.getPrix());
        // Add other assertions as needed for date attributes
    }
}
