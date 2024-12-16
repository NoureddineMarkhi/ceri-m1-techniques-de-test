package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory; // Mock de l'interface IPokedexFactory
    private IPokemonMetadataProvider metadataProvider; // Mock de IPokemonMetadataProvider
    private IPokemonFactory pokemonFactory; // Mock de IPokemonFactory
    private IPokedex pokedex; // Mock de IPokedex

    @BeforeEach
    public void setUp() {
        // Initialisation des mocks
        pokedexFactory = mock(IPokedexFactory.class);
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
        pokedex = mock(IPokedex.class);
    }

    @Test
    public void testCreatePokedex() {
        // Configurer le mock pour créer un pokedex

        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);

        // Appel de la méthode createPokedex
        IPokedex result = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Vérifications
        assertNotNull(result, "L'instance de Pokedex ne doit pas être null.");
        assertEquals(pokedex, result, "L'instance retournée doit être celle configurée par le mock.");

        // Vérifier que la méthode createPokedex a été appelée avec les bons arguments
        verify(pokedexFactory).createPokedex(metadataProvider, pokemonFactory);
    }
}
