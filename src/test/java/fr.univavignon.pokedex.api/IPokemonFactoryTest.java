package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory; // Interface mockée

    @BeforeEach
    public void setUp() {
        // Création du mock pour IPokemonFactory
        pokemonFactory = mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokemonBulbizarre() {
        // Paramètres pour le test
        int index = 0;
        int cp = 613;
        int hp = 64;
        int dust = 4000;
        int candy = 4;

        // Simulation du résultat attendu
        Pokemon expectedPokemon = new Pokemon(index, "Bulbizarre", 126, 126, 90, cp, hp, dust, candy, 56.0);

        // Configuration du comportement du mock
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        // Appel de la méthode
        Pokemon result = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérifications des valeurs retournées
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
        assertEquals(expectedPokemon.getIv(), result.getIv());

        // Vérification que le mock a bien été appelé
        verify(pokemonFactory).createPokemon(index, cp, hp, dust, candy);
    }

    @Test
    public void testCreatePokemonAquali() {
        // Paramètres pour le test
        int index = 133;
        int cp = 2729;
        int hp = 202;
        int dust = 5000;
        int candy = 4;

        // Simulation du résultat attendu
        Pokemon expectedPokemon = new Pokemon(index, "Aquali", 186, 168, 260, cp, hp, dust, candy, 100.0);

        // Configuration du comportement du mock
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        // Appel de la méthode
        Pokemon result = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérifications des valeurs retournées
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
        assertEquals(expectedPokemon.getIv(), result.getIv());

        // Vérification que le mock a bien été appelé
        verify(pokemonFactory).createPokemon(index, cp, hp, dust, candy);
    }

    @Test
    public void testCreatePokemonWithInvalidInput() {
        // Paramètres invalides
        int invalidIndex = -1;
        int cp = 0;
        int hp = -10;
        int dust = -4000;
        int candy = -5;

        // Configuration du mock pour retourner null ou jeter une exception si nécessaire
        when(pokemonFactory.createPokemon(invalidIndex, cp, hp, dust, candy)).thenReturn(null);

        // Appel de la méthode avec des paramètres invalides
        Pokemon result = pokemonFactory.createPokemon(invalidIndex, cp, hp, dust, candy);

        // Vérification que le résultat est null
        assertNull(result);

        // Vérification que le mock a été appelé
        verify(pokemonFactory).createPokemon(invalidIndex, cp, hp, dust, candy);
    }
}
