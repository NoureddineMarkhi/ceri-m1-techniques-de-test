package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    private Pokedex pokedex;

    @BeforeEach
    public void setUp() {
        pokedex = new Pokedex();
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size());

        Pokemon pokemon = mock(Pokemon.class);
        pokedex.addPokemon(pokemon);

        assertEquals(1, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        Pokemon pokemon = mock(Pokemon.class);

        int index = pokedex.addPokemon(pokemon);
        assertEquals(0, index); // Premier ajout, index 0

        Pokemon secondPokemon = mock(Pokemon.class);
        int secondIndex = pokedex.addPokemon(secondPokemon);
        assertEquals(1, secondIndex); // Deuxième ajout, index 1
    }

    @Test
    public void testGetPokemonValidId() throws PokedexException {
        Pokemon pokemon = new Pokemon(0, "Pikachu", 55, 40, 35, 120, 60, 1000, 4, 0.9);
        pokedex.addPokemon(pokemon);

        Pokemon result = pokedex.getPokemon(0);
        assertNotNull(result);
        assertEquals("Pikachu", result.getName());
    }

    @Test
    public void testGetPokemonInvalidIdThrowsException() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(-1));
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1)); // Aucun Pokémon ajouté
    }

    @Test
    public void testGetPokemonsReturnsUnmodifiableList() {
        Pokemon pokemon = mock(Pokemon.class);
        pokedex.addPokemon(pokemon);

        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(1, pokemons.size());
        assertThrows(UnsupportedOperationException.class, () -> pokemons.add(mock(Pokemon.class)));
    }

    @Test
    public void testGetPokemonsWithComparator() {
        // Créer des Pokémon simulés
        Pokemon p1 = new Pokemon(1, "Charmander", 60, 50, 40, 100, 50, 500, 4, 0.7);
        Pokemon p2 = new Pokemon(2, "Bulbasaur", 70, 60, 50, 200, 60, 600, 4, 0.8);

        pokedex.addPokemon(p1);
        pokedex.addPokemon(p2);

        // Trier par nom
        List<Pokemon> sortedByName = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertEquals("Bulbasaur", sortedByName.get(0).getName());
        assertEquals("Charmander", sortedByName.get(1).getName());
    }

    @Test
    public void testGetPokemonMetadataReturnsNull() {
        // Vérifie que la méthode non implémentée retourne null
        assertNull(pokedex.getPokemonMetadata(0));
    }

    @Test
    public void testCreatePokemonReturnsNull() {
        // Vérifie que la méthode non implémentée retourne null
        assertNull(pokedex.createPokemon(0, 100, 50, 500, 4));
    }
}
