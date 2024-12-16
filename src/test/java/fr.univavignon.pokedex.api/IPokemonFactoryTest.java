package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {

    @Test
    public void testCreatePokemon() {
        // Mock de l'interface
        IPokemonFactory factory = mock(IPokemonFactory.class);

        // Création d'une instance simulée
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        when(factory.createPokemon(133, 186, 168, 260, 100)).thenReturn(aquali);

        // Appel de la méthode
        Pokemon result = factory.createPokemon(133, 186, 168, 260, 100);

        // Assertions
        assertEquals(133, result.getIndex());
        assertEquals("Aquali", result.getName());
        assertEquals(186, result.getAttack());
        assertEquals(168, result.getDefense());
        assertEquals(260, result.getStamina());
        assertEquals(2729, result.getCp());

        verify(factory).createPokemon(133, 186, 168, 260, 100);
    }
}
