package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    private IPokedexFactory pokedexFactoryMock;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private IPokedex pokedexMock;
    private PokemonTrainerFactory pokemonTrainerFactory;

    @BeforeEach
    public void setUp() {
        // Création des mocks
        pokedexFactoryMock = mock(IPokedexFactory.class);
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        pokedexMock = mock(IPokedex.class);

        // Initialisation de la factory
        pokemonTrainerFactory = new PokemonTrainerFactory();
    }

    @Test
    public void testCreateTrainerValidInputs() {
        // Configurer le comportement du mock
        when(pokedexFactoryMock.createPokedex(any(), any())).thenReturn(pokedexMock);

        // Appel de la méthode
        String trainerName = "Ash";
        Team team = Team.VALOR;
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactoryMock);

        // Vérifications
        assertNotNull(trainer);
        assertEquals(trainerName, trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertEquals(pokedexMock, trainer.getPokedex());

        // Vérifier que createPokedex a été appelé une fois
        verify(pokedexFactoryMock, times(1)).createPokedex(any(), any());
    }

    @Test
    public void testCreateTrainerWithNullName() {
        // Vérifie qu'une exception est levée si le nom est null
        assertThrows(IllegalArgumentException.class,
                () -> pokemonTrainerFactory.createTrainer(null, Team.INSTINCT, pokedexFactoryMock));
    }

    @Test
    public void testCreateTrainerWithNullTeam() {
        // Vérifie qu'une exception est levée si l'équipe est null
        assertThrows(IllegalArgumentException.class,
                () -> pokemonTrainerFactory.createTrainer("Ash", null, pokedexFactoryMock));
    }

    @Test
    public void testCreateTrainerWithNullFactory() {
        // Vérifie qu'une exception est levée si le PokedexFactory est null
        assertThrows(IllegalArgumentException.class,
                () -> pokemonTrainerFactory.createTrainer("Ash", Team.VALOR, null));
    }

    @Test
    public void testCreateTrainerVerifyInteractions() {
        // Configurer le mock pour retourner un pokedex simulé
        when(pokedexFactoryMock.createPokedex(any(), any())).thenReturn(pokedexMock);

        // Appel de la méthode
        pokemonTrainerFactory.createTrainer("Brock", Team.MYSTIC, pokedexFactoryMock);

        // Vérifie que la méthode createPokedex a bien été appelée avec les bons paramètres
        verify(pokedexFactoryMock).createPokedex(any(IPokemonMetadataProvider.class), any(IPokemonFactory.class));
    }
}
