package fr.boutique;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PanierTest {

    @Test
    void ajouterArticleDeitAugmenterLeNombreDeArticles() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);
        // Agir
        panier.ajouterArticle(article, 2);
        // Affirmer
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void calculerTotalDoitRetournerLaSommeDessousTotaux() {
        // Arranger
        Panier panier = new Panier();
        Article article1 = new Article("REF-001", "Stylo bleu", 1.50);
        Article article2 = new Article("REF-002", "Cahier", 1.50);
        // Agir
        panier.ajouterArticle(article1, 3);
        panier.ajouterArticle(article2, 3);
        // Affirmer
        assertEquals(9.00, panier.calculerTotal(), 0.001);
    }

    @Test
    void panierVideDoitRetournerEstVideEgalTrue() {
        // Arranger
        Panier panier = new Panier();
        // Affirmer
        assertTrue(panier.estVide());
    }

    @Test
    void panierAvecArticlesNeDoitPasEtreVide() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertFalse(panier.estVide());
    }

    // 03 — Cas invalides

    @Test
    void articleNulDoitLeverException() {
        Panier panier = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(null, 1));
    }

    @Test
    void quantiteNulleDoitLeverException() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(article, 0));
    }

    @Test
    void quantiteNegativeDoitLeverException() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(article, -3));
    }

    @Test
    void codeReductionVideDoitLeverException() {
        Panier panier = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> panier.appliquerCodeReduction(""));
    }

    @Test
    void codeReductionNulDoitLeverException() {
        Panier panier = new Panier();
        assertThrows(IllegalArgumentException.class,
                () -> panier.appliquerCodeReduction(null));
    }
}
