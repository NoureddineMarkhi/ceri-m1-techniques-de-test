package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider; // Mock de l'interface

    @BeforeEach
    public void setUp() {
        // Initialisation du mock pour l'interface
        metadataProvider = mock(IPokemonMetadataProvider.class);
    }

    @Test
    public void testGetPokemonMetadataValidIndex() throws PokedexException {
        // Simuler les métadonnées pour Bulbizarre
        when(metadataProvider.getPokemonMetadata(0))
                .thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));

        // Simuler les métadonnées pour Aquali
        when(metadataProvider.getPokemonMetadata(133))
                .thenReturn(new PokemonMetadata(133, "Aquali", 186, 168, 260));

        // Test avec Bulbizarre
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());

        // Test avec Aquali
        metadata = metadataProvider.getPokemonMetadata(133);
        assertNotNull(metadata);
        assertEquals(133, metadata.getIndex());
        assertEquals("Aquali", metadata.getName());
        assertEquals(186, metadata.getAttack());
        assertEquals(168, metadata.getDefense());
        assertEquals(260, metadata.getStamina());

        // Vérification des appels au mock
        verify(metadataProvider).getPokemonMetadata(0);
        verify(metadataProvider).getPokemonMetadata(133);
    }

    @Test
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        // Simuler une exception pour un index invalide
        when(metadataProvider.getPokemonMetadata(999)).thenThrow(new PokedexException("Invalid index"));

        // Vérification que l'exception est bien lancée
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(999));

        // Vérification que le mock a bien été appelé
        verify(metadataProvider).getPokemonMetadata(999);
    }
}
