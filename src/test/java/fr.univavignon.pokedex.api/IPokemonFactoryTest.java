package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        pokemonFactory = new PokemonFactory();
    }

    @Test
    public void testCreatePokemon() {
        // Test de création d'un Pokémon
        int index = 1;
        int cp = 500;
        int hp = 50;
        int dust = 3000;
        int candy = 3;

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Assertions pour vérifier les valeurs
        assertNotNull(createdPokemon);
        assertEquals(index, createdPokemon.getIndex());
        assertEquals("Pokemon-1", createdPokemon.getName());
        assertEquals(101, createdPokemon.getAttack());
        assertEquals(101, createdPokemon.getDefense());
        assertEquals(101, createdPokemon.getStamina());
        assertEquals(cp, createdPokemon.getCp());
        assertEquals(hp, createdPokemon.getHp());
        assertEquals(dust, createdPokemon.getDust());
        assertEquals(candy, createdPokemon.getCandy());
        assertEquals((101 + 101 + 101) / 3.0, createdPokemon.getIv(), 0.01); // Tolerance pour les doubles
    }

    @Test
    public void testCreatePokemonWithDifferentIndex() {
        // Test avec un index différent
        int index = 25;
        int cp = 1000;
        int hp = 120;
        int dust = 5000;
        int candy = 6;

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérifications des valeurs
        assertNotNull(createdPokemon);
        assertEquals(index, createdPokemon.getIndex());
        assertEquals("Pokemon-25", createdPokemon.getName());
        assertEquals(125, createdPokemon.getAttack());
        assertEquals(125, createdPokemon.getDefense());
        assertEquals(125, createdPokemon.getStamina());
        assertEquals(cp, createdPokemon.getCp());
        assertEquals(hp, createdPokemon.getHp());
        assertEquals(dust, createdPokemon.getDust());
        assertEquals(candy, createdPokemon.getCandy());
        assertEquals((125 + 125 + 125) / 3.0, createdPokemon.getIv(), 0.01);
    }
}
