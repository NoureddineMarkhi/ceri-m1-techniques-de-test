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
        // Initialiser le Pokedex et les mocks
        pokedex = new Pokedex();
        pokemonMock = mock(Pokemon.class);
    }

    @Test
    public void testAddPokemon() {
        // Ajouter un Pokemon mocké
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
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1));
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
        List<Pokemon> sorted = pokedex.getPokemons(Comparator.comparingInt(Pokemon::getCp));
        assertEquals(1, sorted.size());
    }
}
