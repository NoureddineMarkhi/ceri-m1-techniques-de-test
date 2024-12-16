package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testCreatePokemonBulbizarre() {
        // Définir les valeurs d'exemple pour Bulbizarre
        int index = 0;
        int cp = 613;
        int hp = 64;
        int dust = 4000;
        int candy = 4;

        // Créer une instance de Pokémon attendue
        Pokemon expectedPokemon = new Pokemon(index, "Bulbizarre", 126, 126, 90, cp, hp, dust, candy, 56);

        // Configurer le comportement du mock
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        // Appel de la méthode
        Pokemon result = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérifications
        assertNotNull(result);
        assertEquals(index, result.getIndex());
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        assertEquals(126, result.getDefense());
        assertEquals(90, result.getStamina());
        assertEquals(cp, result.getCp());
        assertEquals(hp, result.getHp());
        assertEquals(dust, result.getDust());
        assertEquals(candy, result.getCandy());
        assertEquals(56, result.getIv());

        // Vérifier que la méthode a bien été appelée
        verify(pokemonFactory).createPokemon(index, cp, hp, dust, candy);
    }

    @Test
    public void testCreatePokemonAquali() {
        // Définir les valeurs d'exemple pour Aquali
        int index = 133;
        int cp = 2729;
        int hp = 202;
        int dust = 5000;
        int candy = 4;

        // Créer une instance de Pokémon attendue
        Pokemon expectedPokemon = new Pokemon(index, "Aquali", 186, 168, 260, cp, hp, dust, candy, 100);

        // Configurer le comportement du mock
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        // Appel de la méthode
        Pokemon result = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérifications
        assertNotNull(result);
        assertEquals(index, result.getIndex());
        assertEquals("Aquali", result.getName());
        assertEquals(186, result.getAttack());
        assertEquals(168, result.getDefense());
        assertEquals(260, result.getStamina());
        assertEquals(cp, result.getCp());
        assertEquals(hp, result.getHp());
        assertEquals(dust, result.getDust());
        assertEquals(candy, result.getCandy());
        assertEquals(100, result.getIv());

        // Vérifier que la méthode a bien été appelée
        verify(pokemonFactory).createPokemon(index, cp, hp, dust, candy);
    }

    @Test
    public void testPokemonComparatorsByName() {
        // Instances de Pokémon pour comparaison
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);

        // Comparaison par NAME
        int result = PokemonComparators.NAME.compare(bulbizarre, aquali);

        // Vérification : Bulbizarre devrait être après Aquali en ordre alphabétique
        assertTrue(result > 0, "Bulbizarre should come after Aquali by NAME lexicographically");
    }
}
