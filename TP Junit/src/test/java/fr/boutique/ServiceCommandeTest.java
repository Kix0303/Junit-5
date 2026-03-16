package fr.boutique;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceCommandeTest {

    private DepotStock stockDisponible = reference -> 100;
    private ServiceCommande service;
    private Panier panier;
    private Article articleTest;

    @BeforeEach
    void initialiser() {
        service     = new ServiceCommande(stockDisponible);
        panier      = new Panier();
        articleTest = new Article("REF-001", "Cahier", 3.50);
    }

    @Test
    void commandeValideDoitRetournerUneCommande() {
        // Arranger
        panier.ajouterArticle(articleTest, 2);
        // Agir
        Commande commande = service.passerCommande(panier, "CLIENT-42");
        // Affirmer
        assertNotNull(commande);
        assertEquals(7.0, commande.total(), 0.001);
    }

    @Test
    void panierVideDoitLeverIllegalStateException() {
        // panier vide (aucun article ajouté)
        assertThrows(IllegalStateException.class,
                () -> service.passerCommande(panier, "C1"));
    }

    @Test
    void identifiantClientNulDoitLeverException() {
        panier.ajouterArticle(articleTest, 1);
        assertThrows(IllegalArgumentException.class,
                () -> service.passerCommande(panier, null));
    }

    @Test
    void identifiantClientVideDoitLeverException() {
        panier.ajouterArticle(articleTest, 1);
        assertThrows(IllegalArgumentException.class,
                () -> service.passerCommande(panier, ""));
    }

    @Test
    void stockInsuffisantDoitLeverStockInsuffisantException() {
        // Arranger — stock retourne 1, on en demande 5
        DepotStock stockInsuffisant = reference -> 1;
        ServiceCommande serviceStockLimite = new ServiceCommande(stockInsuffisant);
        panier.ajouterArticle(articleTest, 5);
        // Affirmer
        assertThrows(StockInsuffisantException.class,
                () -> serviceStockLimite.passerCommande(panier, "CLIENT-01"));
    }

    @Test
    void totalCommandeDoitCorrespondreAuTotalDuPanier() {
        // Arranger
        panier.ajouterArticle(articleTest, 2);
        double totalAttendu = panier.calculerTotal();
        // Agir
        Commande commande = service.passerCommande(panier, "CLIENT-42");
        // Affirmer
        assertEquals(totalAttendu, commande.total(), 0.001);
    }
}
