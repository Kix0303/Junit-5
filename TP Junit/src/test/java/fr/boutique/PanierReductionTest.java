package fr.boutique;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class PanierReductionTest {

    @ParameterizedTest
    @CsvSource({
        "       , 100.0",  // pas de code de réduction
        "REDUC10,  90.0",  // -10%
        "REDUC20,  80.0"   // -20%
    })
    void calculerTotalDoitAppliquerLaBonneReduction(String code, double totalAttendu) {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Classeur", 10.0);
        panier.ajouterArticle(article, 10); // sous-total = 100.0
        // Agir
        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }
        // Affirmer
        assertEquals(totalAttendu, panier.calculerTotal(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({
        "       , 200.0",  // pas de code de réduction : 10 * 2 * 10.0 = 200.0
        "REDUC10, 180.0",  // -10% : 200.0 * 0.90
        "REDUC20, 160.0"   // -20% : 200.0 * 0.80
    })
    void calculerTotalAvecPlusieursArticlesDoitAppliquerLaBonneReduction(
            String code, double totalAttendu) {
        // Arranger — quantite > 1
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Classeur", 10.0);
        panier.ajouterArticle(article, 20); // sous-total = 200.0
        // Agir
        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }
        // Affirmer
        assertEquals(totalAttendu, panier.calculerTotal(), 0.001);
    }
}
