package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        // Mock de l'interface
        IPokemonMetadataProvider provider = mock(IPokemonMetadataProvider.class);

        // Valeurs d'exemple
        PokemonMetadata bulbizarre = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        when(provider.getPokemonMetadata(0)).thenReturn(bulbizarre);

        // Appel de la méthode
        PokemonMetadata result = provider.getPokemonMetadata(0);

        // Assertions
        assertEquals(0, result.getIndex());
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        assertEquals(126, result.getDefense());
        assertEquals(90, result.getStamina());

        verify(provider).getPokemonMetadata(0);
    }
}
