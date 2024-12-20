package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of the IPokedex interface.
 * A Pokedex stores information about captured Pokémon,
 * including their default metadata and details.
 *
 * @author Your Name
 */
public class Pokedex implements IPokedex {

    /** List of Pokémon stored in the Pokedex. */
    private final List<Pokemon> pokemons = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return pokemons.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemons.indexOf(pokemon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id >= pokemons.size()) {
            throw new PokedexException("Invalid ID");
        }
        return pokemons.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(pokemons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedList = new ArrayList<>(pokemons);
        sortedList.sort(order);
        return Collections.unmodifiableList(sortedList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) {
        return null; // To be implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return null; // To be implemented
    }
}
