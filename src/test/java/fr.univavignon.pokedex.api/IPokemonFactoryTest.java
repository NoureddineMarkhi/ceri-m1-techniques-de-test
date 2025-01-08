package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.RocketPokemonFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour l'interface IPokemonFactory et ses implémentations.
 */
public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactoryMock; // Mock de l'interface
    private RocketPokemonFactory rocketPokemonFactory; // Implémentation réelle pour RocketPokemonFactory

    @BeforeEach
    public void setUp() {
        // Initialiser le mock et l'implémentation réelle
        pokemonFactoryMock = mock(IPokemonFactory.class);
        rocketPokemonFactory = new RocketPokemonFactory();
    }

    @Test
    public void testCreatePokemonWithMock() {
        // Définir les valeurs d'exemple pour un Pokémon
        int index = 1;
        int cp = 500;
        int hp = 100;
        int dust = 3000;
        int candy = 3;

        // Créer un Pokémon attendu (avec des valeurs arbitraires)
        Pokemon expectedPokemon = new Pokemon(index, "Pikachu", 55, 40, 35, cp, hp, dust, candy, 80.0);

        // Configurer le comportement du mock
        when(pokemonFactoryMock.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        // Appeler la méthode testée
        Pokemon result = pokemonFactoryMock.createPokemon(index, cp, hp, dust, candy);

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
        verify(pokemonFactoryMock).createPokemon(index, cp, hp, dust, candy);
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

    @Test
    public void testRealPokemonFactoryImplementation() {
        IPokemonFactory realFactory = new PokemonFactory();

        // Test avec différents index pour couvrir la logique interne
        Pokemon p1 = realFactory.createPokemon(0, 613, 64, 4000, 4);
        assertNotNull(p1);
        assertEquals("Pokemon-0", p1.getName());
        assertEquals(100, p1.getAttack());
        assertEquals(100, p1.getDefense());
        assertEquals(100, p1.getStamina());
        assertEquals((100 + 100 + 100) / 3.0, p1.getIv(), 0.01);

        Pokemon p2 = realFactory.createPokemon(10, 700, 70, 5000, 5);
        assertNotNull(p2);
        assertEquals("Pokemon-10", p2.getName());
        assertEquals(110, p2.getAttack());
        assertEquals(110, p2.getDefense());
        assertEquals(110, p2.getStamina());
        assertEquals((110 + 110 + 110) / 3.0, p2.getIv(), 0.01);
    }

    // === Tests ajoutés pour RocketPokemonFactory ===

    @Test
    public void testRocketPokemonFactoryNegativeIndex() {
        Pokemon pokemon = rocketPokemonFactory.createPokemon(-1, 500, 100, 3000, 3);
        assertNotNull(pokemon);
        assertEquals("Ash's Pikachu", pokemon.getName());
        assertEquals(1000, pokemon.getAttack());
        assertEquals(1000, pokemon.getDefense());
        assertEquals(1000, pokemon.getStamina());
        assertEquals(0.0, pokemon.getIv(), 0.01);
    }

    @Test
    public void testRocketPokemonFactoryUnknownIndex() {
        Pokemon pokemon = rocketPokemonFactory.createPokemon(999, 500, 100, 3000, 3);
        assertNotNull(pokemon);
        assertEquals("MISSINGNO", pokemon.getName());
    }

    @Test
    public void testRocketPokemonFactoryRandomStats() {
        Pokemon pokemon = rocketPokemonFactory.createPokemon(1, 500, 100, 3000, 3);
        assertNotNull(pokemon);
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 15);
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 15);
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 15);
        assertEquals(1.0, pokemon.getIv(), 0.01);
    }
}
