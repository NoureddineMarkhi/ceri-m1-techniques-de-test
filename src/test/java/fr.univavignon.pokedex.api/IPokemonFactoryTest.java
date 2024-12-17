package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        pokemonFactory = new PokemonFactory();
    }

    @Test
    public void testCreatePokemon() {
        int index = 0;
        int cp = 613;
        int hp = 64;
        int dust = 4000;
        int candy = 4;

        Pokemon pokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);
        assertNotNull(pokemon);
        assertEquals(index, pokemon.getIndex());
    }
}
