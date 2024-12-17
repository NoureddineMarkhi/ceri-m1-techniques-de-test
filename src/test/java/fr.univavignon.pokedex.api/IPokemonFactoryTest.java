package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        assertEquals((101 + 101 + 101) / 3.0, createdPokemon.getIv(), 0.01);
    }

    @Test
    public void testPokemonSortingWithComparators() {
        // Créer plusieurs Pokémon avec la factory
        Pokemon pokemon1 = pokemonFactory.createPokemon(1, 500, 50, 3000, 3); // Nom : Pokemon-1
        Pokemon pokemon2 = pokemonFactory.createPokemon(2, 400, 40, 2000, 2); // Nom : Pokemon-2
        Pokemon pokemon3 = pokemonFactory.createPokemon(3, 600, 60, 4000, 4); // Nom : Pokemon-3

        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(pokemon3);
        pokemonList.add(pokemon1);
        pokemonList.add(pokemon2);

        // Vérifier le tri par nom
        pokemonList.sort(PokemonComparators.NAME);
        assertEquals("Pokemon-1", pokemonList.get(0).getName());
        assertEquals("Pokemon-2", pokemonList.get(1).getName());
        assertEquals("Pokemon-3", pokemonList.get(2).getName());

        // Vérifier le tri par index
        pokemonList.sort(PokemonComparators.INDEX);
        assertEquals(1, pokemonList.get(0).getIndex());
        assertEquals(2, pokemonList.get(1).getIndex());
        assertEquals(3, pokemonList.get(2).getIndex());

        // Vérifier le tri par CP (Combat Points)
        pokemonList.sort(PokemonComparators.CP);
        assertEquals(400, pokemonList.get(0).getCp());
        assertEquals(500, pokemonList.get(1).getCp());
        assertEquals(600, pokemonList.get(2).getCp());
    }
}
