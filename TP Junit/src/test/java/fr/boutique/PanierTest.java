package fr.boutique;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PanierTest {

    @Test
    void ajouterArticleDoitAugmenterLeNombreDeArticles() {
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

    // 04 — Cas limites

    @Test
    void quantiteUneDoitEtreAcceptee() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 9.99);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertEquals(9.99, panier.calculerTotal(), 0.001);
    }

    @Test
    void articleGratuitDoitEtreAccepte() {
        // Arranger
        Panier panier = new Panier();
        Article articleGratuit = new Article("OFFERT-01", "Stylo offert", 0.0);
        // Agir
        panier.ajouterArticle(articleGratuit, 1);
        // Affirmer
        assertEquals(0.0, panier.calculerTotal(), 0.001);
    }

    @Test
    void prixEleveDoitFonctionner() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Ordinateur", 999.99);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertEquals(999.99, panier.calculerTotal(), 0.001);
    }

    @Test
    void panierAvecUnSeulArticleDoitFonctionner() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);
        // Agir
        panier.ajouterArticle(article, 1);
        // Affirmer
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void plusieursArticlesDifferentsDansPanier() {
        // Arranger
        Panier panier = new Panier();
        Article a1 = new Article("REF-001", "Stylo", 1.00);
        Article a2 = new Article("REF-002", "Cahier", 2.00);
        Article a3 = new Article("REF-003", "Règle", 3.00);
        // Agir
        panier.ajouterArticle(a1, 1); // 1.00
        panier.ajouterArticle(a2, 1); // 2.00
        panier.ajouterArticle(a3, 1); // 3.00
        // Affirmer
        assertEquals(6.00, panier.calculerTotal(), 0.001);
    }
}
