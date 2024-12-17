package fr.univavignon.pokedex.api;

/**
 * Implementation of IPokedexFactory.
 * This factory creates instances of IPokedex.
 *
 * @author fv
 */
public class PokedexFactory implements IPokedexFactory {

    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        // Retourne une instance de Pokedex.
        return new Pokedex();
    }
}
