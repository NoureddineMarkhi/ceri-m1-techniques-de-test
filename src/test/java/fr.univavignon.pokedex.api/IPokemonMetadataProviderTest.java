package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() {
        // Utiliser directement l'implémentation concrète
        metadataProvider = new PokemonMetadataProvider();
    }

    @Test
    public void testGetPokemonMetadataValidIndex() throws PokedexException {
        // Vérifier les métadonnées pour Bulbizarre (index 0)
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());

        // Vérifier les métadonnées pour Aquali (index 133)
        metadata = metadataProvider.getPokemonMetadata(133);
        assertNotNull(metadata);
        assertEquals(133, metadata.getIndex());
        assertEquals("Aquali", metadata.getName());
        assertEquals(186, metadata.getAttack());
        assertEquals(168, metadata.getDefense());
        assertEquals(260, metadata.getStamina());
    }

    @Test
    public void testGetPokemonMetadataInvalidIndex() {
        // Vérifier qu'une exception est lancée pour un index invalide
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(999));
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(-1));
    }

    @Test
    public void testGetPokemonMetadataBoundaryValues() throws PokedexException {
        // Tester les valeurs limites de l'index valide
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals(0, metadata.getIndex());

        metadata = metadataProvider.getPokemonMetadata(133);
        assertNotNull(metadata);
        assertEquals(133, metadata.getIndex());
    }
}
