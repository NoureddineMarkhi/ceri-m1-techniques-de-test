package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    // Simulation des métadonnées des Pokémon (index, name, attack, defense, stamina)
    private static final Map<Integer, PokemonMetadata> POKEMON_METADATA_DB = new HashMap<>();

    static {
        POKEMON_METADATA_DB.put(0, new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        POKEMON_METADATA_DB.put(133, new PokemonMetadata(133, "Aquali", 186, 168, 260));
        // Vous pouvez ajouter plus de Pokémon ici pour tester
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (!POKEMON_METADATA_DB.containsKey(index)) {
            throw new PokedexException("Invalid Pokémon index: " + index);
        }
        return POKEMON_METADATA_DB.get(index);
    }
}
