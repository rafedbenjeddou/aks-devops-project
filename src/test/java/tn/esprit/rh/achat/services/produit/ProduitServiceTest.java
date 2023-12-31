package tn.esprit.rh.achat.services.produit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.apache.el.lang.ELArithmetic.add;

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
    public void testDeleteProduit() {
        long idProduit = 1L;
        produitService.deleteProduit(idProduit);
        Mockito.verify(produitRepository).deleteById(idProduit);
    }
    
    @Test
    public void testAddProduit() {
        Mockito.when(produitRepository.save((addProduit))).thenReturn(addProduit);
        Produit addProduit1 = produitService.addProduit(addProduit);
        Mockito.verify(produitRepository).save((addProduit));
        Assertions.assertEquals(addProduit, addProduit1);


}

}
