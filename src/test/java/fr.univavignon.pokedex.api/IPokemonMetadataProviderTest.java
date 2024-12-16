package fr.univavignon.pokedex.api;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider; // Mock de l'interface

    @BeforeEach
    public void setUp() {
        // Création du mock avant chaque test
        metadataProvider = mock(IPokemonMetadataProvider.class);
    }

    @Test
    public void testGetPokemonMetadataValidIndex() throws PokedexException {
        // Création des métadonnées attendues
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

        // Définir le comportement du mock
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(metadata);

        // Appel de la méthode
        PokemonMetadata result = metadataProvider.getPokemonMetadata(0);

        // Assertions pour vérifier les valeurs renvoyées
        assertNotNull(result);
        assertEquals(0, result.getIndex());
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        assertEquals(126, result.getDefense());
        assertEquals(90, result.getStamina());

        // Vérifier que la méthode a bien été appelée avec l'index 0
        verify(metadataProvider).getPokemonMetadata(0);
    }

    @Test
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        // Définir le comportement pour un index invalide
        when(metadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid index"));

        // Vérification que l'exception est bien lancée
        Exception exception = assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(-1);
        });

        // Vérifier le message de l'exception
        assertEquals("Invalid index", exception.getMessage());

        // Vérifier que la méthode a été appelée avec l'index -1
        verify(metadataProvider).getPokemonMetadata(-1);
    }
}
