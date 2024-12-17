package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexFactoryTest {

    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private IPokedexFactory pokedexFactory;

    @BeforeEach
    public void setUp() {
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        pokedexFactory = new PokedexFactory();
    }

    @Test
    public void testCreatePokedex() {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);
        assertNotNull(pokedex);
    }
}
