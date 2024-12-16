package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    private IPokedex pokedex; // Mock de l'interface IPokedex
    private Pokemon bulbizarre, aquali;

    @BeforeEach
    public void setUp() {
        // Initialiser le mock
        pokedex = mock(IPokedex.class);

        // Instances de Pokémon pour les tests
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
    }

    @Test
    public void testAddPokemon() {
        // Configurer le mock pour ajouter un Pokémon
        when(pokedex.addPokemon(bulbizarre)).thenReturn(0);

        // Appel de la méthode
        int index = pokedex.addPokemon(bulbizarre);

        // Vérifications
        assertEquals(0, index);
        verify(pokedex).addPokemon(bulbizarre);
    }

    @Test
    public void testGetPokemonValid() throws PokedexException {
        // Configurer le mock pour récupérer un Pokémon existant
        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);

        // Appel de la méthode
        Pokemon result = pokedex.getPokemon(0);

        // Vérifications
        assertNotNull(result);
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        verify(pokedex).getPokemon(0);
    }

    @Test
    public void testGetPokemonInvalid() throws PokedexException {
        // Configurer le mock pour lever une exception sur un id invalide
        when(pokedex.getPokemon(-1)).thenThrow(new PokedexException("Invalid index"));

        // Vérifier qu'une exception est levée
        Exception exception = assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(-1);
        });

        // Vérifier le message de l'exception
        assertEquals("Invalid index", exception.getMessage());
        verify(pokedex).getPokemon(-1);
    }

    @Test
    public void testSize() {
        // Configurer le mock pour retourner la taille
        when(pokedex.size()).thenReturn(2);

        // Appel de la méthode
        int size = pokedex.size();

        // Vérifications
        assertEquals(2, size);
        verify(pokedex).size();
    }

    @Test
    public void testGetPokemons() {
        // Configurer le mock pour retourner une liste de Pokémons
        List<Pokemon> pokemons = Arrays.asList(bulbizarre, aquali);
        when(pokedex.getPokemons()).thenReturn(pokemons);

        // Appel de la méthode
        List<Pokemon> result = pokedex.getPokemons();

        // Vérifications
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Bulbizarre", result.get(0).getName());
        assertEquals("Aquali", result.get(1).getName());
        verify(pokedex).getPokemons();
    }

    @Test
    public void testGetPokemonsWithOrder() {
        // Définir un comparateur pour trier les Pokémons par index
        Comparator<Pokemon> comparator = Comparator.comparingInt(Pokemon::getIndex);

        // Configurer le mock pour retourner une liste triée
        List<Pokemon> sortedPokemons = Arrays.asList(bulbizarre, aquali);
        when(pokedex.getPokemons(comparator)).thenReturn(sortedPokemons);

        // Appel de la méthode
        List<Pokemon> result = pokedex.getPokemons(comparator);

        // Vérifications
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Bulbizarre", result.get(0).getName());
        assertEquals("Aquali", result.get(1).getName());
        verify(pokedex).getPokemons(comparator);
    }
}
