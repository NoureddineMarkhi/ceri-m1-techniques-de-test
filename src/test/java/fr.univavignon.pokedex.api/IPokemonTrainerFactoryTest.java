package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory trainerFactory;  // Mock de l'interface IPokemonTrainerFactory
    private IPokedexFactory pokedexFactory;         // Mock de l'interface IPokedexFactory
    private IPokedex pokedex;                       // Mock de IPokedex

    @BeforeEach
    public void setUp() {
        // Initialiser les mocks
        trainerFactory = mock(IPokemonTrainerFactory.class);
        pokedexFactory = mock(IPokedexFactory.class);
        pokedex = mock(IPokedex.class);
    }

    @Test
    public void testCreateTrainer() {
        // Configurer le comportement de createPokedex
        when(pokedexFactory.createPokedex(any(), any())).thenReturn(pokedex);

        // Simuler le comportement réel de createTrainer
        doAnswer(invocation -> {
            String name = invocation.getArgument(0);
            Team team = invocation.getArgument(1);
            IPokedexFactory factory = invocation.getArgument(2);

            // Appeler la méthode createPokedex sur pokedexFactory
            IPokedex createdPokedex = factory.createPokedex(mock(IPokemonMetadataProvider.class), mock(IPokemonFactory.class));
            return new PokemonTrainer(name, team, createdPokedex);
        }).when(trainerFactory).createTrainer(anyString(), any(Team.class), any(IPokedexFactory.class));

        // Appel de la méthode createTrainer

        PokemonTrainer trainer = trainerFactory.createTrainer("Sacha", Team.VALOR, pokedexFactory);

        // Vérifications
        assertNotNull(trainer, "Le trainer ne doit pas être null.");
        assertEquals("Sacha", trainer.getName(), "Le nom du trainer doit être 'Sacha'.");
        assertEquals(Team.VALOR, trainer.getTeam(), "L'équipe doit être VALOR.");
        assertEquals(pokedex, trainer.getPokedex(), "Le Pokedex associé doit être celui créé par la factory.");

        // Vérifier que la méthode createPokedex a bien été appelée
        verify(pokedexFactory).createPokedex(any(IPokemonMetadataProvider.class), any(IPokemonFactory.class));
        verify(trainerFactory).createTrainer("Sacha", Team.VALOR, pokedexFactory);
    }
}
