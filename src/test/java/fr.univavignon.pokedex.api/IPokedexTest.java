package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    private Pokedex pokedex;
    private Pokemon pokemonMock;

    @BeforeEach
    public void setUp() {
        pokedex = new Pokedex();
        pokemonMock = mock(Pokemon.class);
    }

    @Test
    public void testAddPokemon() {
        int index = pokedex.addPokemon(pokemonMock);
        assertEquals(0, index);
        assertEquals(1, pokedex.size());
    }

    @Test
    public void testGetPokemonValid() throws PokedexException {
        pokedex.addPokemon(pokemonMock);
        assertEquals(pokemonMock, pokedex.getPokemon(0));
    }

    @Test
    public void testGetPokemonInvalid() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1));
    }

    @Test
    public void testGetPokemons() {
        assertTrue(pokedex.getPokemons().isEmpty());
        pokedex.addPokemon(pokemonMock);
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(1, pokemons.size());
    }

    @Test
    public void testGetPokemonsWithComparator() {
        Pokemon pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon pokemon2 = new Pokemon(1, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);

        pokedex.addPokemon(pokemon2);
        pokedex.addPokemon(pokemon1);

        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertEquals("Aquali", sortedPokemons.get(0).getName());
        assertEquals("Bulbizarre", sortedPokemons.get(1).getName());
    }

    @Test
    public void testGetPokemonMetadata() {
        assertNull(pokedex.getPokemonMetadata(0));
    }

    @Test
    public void testCreatePokemon() {
        assertNull(pokedex.createPokemon(0, 100, 50, 1000, 4));
    }

    @Test
    public void testEmptyPokedexSize() {
        assertEquals(0, pokedex.size());
    }
}
