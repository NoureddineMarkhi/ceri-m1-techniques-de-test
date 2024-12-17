package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    private IPokedexFactory pokedexFactory;
    private PokemonTrainerFactory pokemonTrainerFactory;

    @BeforeEach
    public void setUp() {
        pokedexFactory = mock(IPokedexFactory.class);
        pokemonTrainerFactory = new PokemonTrainerFactory();
    }

    @Test
    public void testCreateTrainerValidInputs() {
        // Mock pour IPokedex
        IPokedex pokedexMock = mock(IPokedex.class);
        when(pokedexFactory.createPokedex(any(), any())).thenReturn(pokedexMock);

        // Appel de la méthode
        String trainerName = "Ash";
        Team team = Team.MYSTIC;
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory);

        // Vérifications
        assertNotNull(trainer);
        assertEquals(trainerName, trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertEquals(pokedexMock, trainer.getPokedex());

        // Vérifier que createPokedex est appelé une seule fois
        verify(pokedexFactory).createPokedex(any(), any());
    }

    @Test
    public void testCreateTrainerWithNullArguments() {
        // Vérifier les exceptions si les arguments sont null
        assertThrows(IllegalArgumentException.class, () -> pokemonTrainerFactory.createTrainer(null, Team.MYSTIC, pokedexFactory));
        assertThrows(IllegalArgumentException.class, () -> pokemonTrainerFactory.createTrainer("Ash", null, pokedexFactory));
        assertThrows(IllegalArgumentException.class, () -> pokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, null));
    }
}
