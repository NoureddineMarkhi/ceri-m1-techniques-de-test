package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IPokedexTest {

    private Pokedex pokedex;

    @BeforeEach
    public void setUp() {
        pokedex = new Pokedex();
    }

    @Test
    public void testSizeInitiallyEmpty() {
        assertEquals(0, pokedex.size(), "Pokedex should initially have size 0");
    }

    @Test
    public void testAddPokemonAndSize() {
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        int index = pokedex.addPokemon(pokemon);
        assertEquals(0, index, "First added pokemon should have index 0");
        assertEquals(1, pokedex.size(), "Pokedex size should be 1 after adding a pokemon");
    }

    @Test
    public void testGetPokemonValidId() throws PokedexException {
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        pokedex.addPokemon(pokemon);
        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertEquals(pokemon, retrievedPokemon, "Retrieved pokemon should match the added one");
    }

    @Test
    public void testGetPokemonInvalidId() {
        PokedexException exception = assertThrows(PokedexException.class, () -> pokedex.getPokemon(0));
        assertEquals("Invalid ID", exception.getMessage(), "Exception message should indicate invalid ID");
    }

    @Test
    public void testGetPokemonsUnmodifiableList() {
        Pokemon pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon pokemon2 = new Pokemon(1, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);

        List<Pokemon> pokemonList = pokedex.getPokemons();
        assertEquals(2, pokemonList.size(), "The returned list should have 2 pokemons");

        // Ensure the list is unmodifiable
        assertThrows(UnsupportedOperationException.class, () -> pokemonList.add(pokemon1));
    }

    @Test
    public void testGetPokemonsSorted() {
        Pokemon pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon pokemon2 = new Pokemon(1, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);
        pokedex.addPokemon(pokemon2);
        pokedex.addPokemon(pokemon1);

        List<Pokemon> sortedList = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertEquals("Aquali", sortedList.get(0).getName(), "First pokemon should be Aquali after sorting by name");
        assertEquals("Bulbizarre", sortedList.get(1).getName(), "Second pokemon should be Bulbizarre after sorting by name");
    }

    @Test
    public void testGetPokemonMetadataNotImplemented() {
        assertNull(pokedex.getPokemonMetadata(0), "getPokemonMetadata is not implemented and should return null");
    }

    @Test
    public void testCreatePokemonNotImplemented() {
        assertNull(pokedex.createPokemon(0, 613, 64, 4000, 4), "createPokemon is not implemented and should return null");
    }
}
