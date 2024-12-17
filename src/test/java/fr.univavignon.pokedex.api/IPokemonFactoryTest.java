package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory; // Mock de l'interface

    @BeforeEach
    public void setUp() {
        // Initialiser le mock avant chaque test
        pokemonFactory = mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokemon() {
        // Définir les valeurs d'exemple pour un Pokémon
        int index = 1;
        int cp = 500;
        int hp = 100;
        int dust = 3000;
        int candy = 3;

        // Créer un Pokémon attendu
        Pokemon expectedPokemon = new Pokemon(index, "Pikachu", 55, 40, 35, cp, hp, dust, candy, 80.0);

        // Configurer le comportement du mock
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        // Appeler la méthode testée
        Pokemon result = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérifications
        assertNotNull(result);
        assertEquals(expectedPokemon.getIndex(), result.getIndex());
        assertEquals(expectedPokemon.getName(), result.getName());
        assertEquals(expectedPokemon.getAttack(), result.getAttack());
        assertEquals(expectedPokemon.getDefense(), result.getDefense());
        assertEquals(expectedPokemon.getStamina(), result.getStamina());
        assertEquals(expectedPokemon.getCp(), result.getCp());
        assertEquals(expectedPokemon.getHp(), result.getHp());
        assertEquals(expectedPokemon.getDust(), result.getDust());
        assertEquals(expectedPokemon.getCandy(), result.getCandy());
        assertEquals(expectedPokemon.getIv(), result.getIv(), 0.01);

        // Vérifier que la méthode a bien été appelée
        verify(pokemonFactory).createPokemon(index, cp, hp, dust, candy);
    }

    @Test
    public void testPokemonComparatorsByName() {
        // Créer des mocks de Pokémon
        Pokemon pokemon1 = mock(Pokemon.class);
        Pokemon pokemon2 = mock(Pokemon.class);

        when(pokemon1.getName()).thenReturn("Bulbizarre");
        when(pokemon2.getName()).thenReturn("Aquali");

        // Vérification du comparateur NAME
        Comparator<Pokemon> comparator = PokemonComparators.NAME;

        assertTrue(comparator.compare(pokemon1, pokemon2) > 0);
        assertTrue(comparator.compare(pokemon2, pokemon1) < 0);
        assertEquals(0, comparator.compare(pokemon1, pokemon1));
    }

    @Test
    public void testPokemonComparatorsByIndex() {
        // Créer des mocks de Pokémon
        Pokemon pokemon1 = mock(Pokemon.class);
        Pokemon pokemon2 = mock(Pokemon.class);

        when(pokemon1.getIndex()).thenReturn(10);
        when(pokemon2.getIndex()).thenReturn(5);

        // Vérification du comparateur INDEX
        Comparator<Pokemon> comparator = PokemonComparators.INDEX;

        assertTrue(comparator.compare(pokemon1, pokemon2) > 0);
        assertTrue(comparator.compare(pokemon2, pokemon1) < 0);
        assertEquals(0, comparator.compare(pokemon1, pokemon1));
    }

    @Test
    public void testPokemonComparatorsByCP() {
        // Créer des mocks de Pokémon
        Pokemon pokemon1 = mock(Pokemon.class);
        Pokemon pokemon2 = mock(Pokemon.class);

        when(pokemon1.getCp()).thenReturn(500);
        when(pokemon2.getCp()).thenReturn(300);

        // Vérification du comparateur CP
        Comparator<Pokemon> comparator = PokemonComparators.CP;

        assertTrue(comparator.compare(pokemon1, pokemon2) > 0);
        assertTrue(comparator.compare(pokemon2, pokemon1) < 0);
        assertEquals(0, comparator.compare(pokemon1, pokemon1));
    }

    @Test
    public void testComparatorsConsistency() {
        // Créer des mocks de Pokémon
        Pokemon pokemon1 = mock(Pokemon.class);
        Pokemon pokemon2 = mock(Pokemon.class);

        when(pokemon1.getName()).thenReturn("Bulbizarre");
        when(pokemon2.getName()).thenReturn("Bulbizarre");
        when(pokemon1.getIndex()).thenReturn(1);
        when(pokemon2.getIndex()).thenReturn(2);
        when(pokemon1.getCp()).thenReturn(300);
        when(pokemon2.getCp()).thenReturn(400);

        // Vérifier tous les comparateurs
        assertEquals(0, PokemonComparators.NAME.compare(pokemon1, pokemon2));
        assertTrue(PokemonComparators.INDEX.compare(pokemon1, pokemon2) < 0);
        assertTrue(PokemonComparators.CP.compare(pokemon1, pokemon2) < 0);
    }
}
