package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexFactoryTest {

    @Test
    public void testCreatePokedex() {
        // Création des mocks pour les paramètres
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);

        // Création de l'instance de la factory
        IPokedexFactory pokedexFactory = new PokedexFactory();

        // Appel de la méthode createPokedex
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Assertions
        assertNotNull(pokedex, "L'instance de Pokedex ne doit pas être null.");
        assertTrue(pokedex instanceof Pokedex, "L'instance retournée doit être de type Pokedex.");
    }
}
