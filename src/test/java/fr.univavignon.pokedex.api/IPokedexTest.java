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
    private PokemonMetadata metadataMock;

    @BeforeEach
    public void setUp() {
        // Initialisation du Pokedex et des mocks
        pokedex = new Pokedex();
        pokemonMock = mock(Pokemon.class);
        metadataMock = mock(PokemonMetadata.class);
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
        Pokemon result = pokedex.getPokemon(0);
        assertNotNull(result);
    }

    @Test
    public void testGetPokemonInvalid() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(0));
    }

    @Test
    public void testGetPokemons() {
        pokedex.addPokemon(pokemonMock);
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(1, pokemons.size());
    }

    @Test
    public void testGetPokemonsWithComparator() {
        pokedex.addPokemon(pokemonMock);
        List<Pokemon> sorted = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertEquals(1, sorted.size());
    }

    @Test
    public void testGetPokemonMetadata() {
        // Tester la méthode même si elle retourne null
        assertNull(pokedex.getPokemonMetadata(0));
    }

    @Test
    public void testCreatePokemon() {
        // Tester la méthode même si elle retourne null
        assertNull(pokedex.createPokemon(0, 100, 50, 1000, 4));
    }
}
