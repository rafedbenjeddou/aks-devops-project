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
    Date dateCreation = new Date(); // Set an actual date here
    Date dateDerniereModification = new Date(); // Set an actual date here
    Produit produit = new Produit(1L,"code1", "Libellé du produit 1", 10.0f, dateCreation, dateDerniereModification);
    Produit addProduit = new Produit(1L,"code1", "Libellé du produit 1", 10.0f, dateCreation, dateDerniereModification);

    List<Produit> listProduits = new ArrayList<Produit>() {
        {
            add(new Produit(1L,"code1", "Libellé du produit 1", 10.0f, dateCreation, dateDerniereModification));
        }
    };
    @Test
    public void testRetrieveProduit() {
        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));
        Produit retrievedProduit = produitService.retrieveProduit(1L);
        Mockito.verify(produitRepository, Mockito.times(1)).findById(1L);
        Assertions.assertNotNull(retrievedProduit);
        Assertions.assertEquals("code1", retrievedProduit.getCodeProduit());
        Assertions.assertEquals("Libellé du produit 1", retrievedProduit.getLibelleProduit());
        Assertions.assertEquals(10.0f, retrievedProduit.getPrix());
    }

    @Test
    public void testRetrieveAllProduit(){
        Mockito.when(produitRepository.findAll()).thenReturn(listProduits);
        List<Produit> listProduits = produitService.retrieveAllProduits();
        Assertions.assertEquals(1, listProduits.size());
    }
    @Test
   public void testAddProduit() {
    // Prepare a Facture object to save

    // Define the behavior for the mocked repository when saving
    Mockito.when(produitRepository.save((addProduit))).thenReturn(addProduit);

    // Call the addFacture method
    Produit addProduit1 = produitService.addProduit(addProduit);

    Mockito.verify(produitRepository).save((addProduit));
    // Assertions to check if the returned Facture matches the expected one
    Assertions.assertEquals(addProduit, addProduit1);


}

}
