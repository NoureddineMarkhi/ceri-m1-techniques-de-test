package fr.univavignon.pokedex.api;

/**
 * Implementation of the IPokemonFactory interface.
 * This class creates Pokemon instances while computing their IVs.
 */
public class PokemonFactory implements IPokemonFactory {

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        // Logique simplifiée pour l'exemple : On utilise un calcul d'IV arbitraire
        int attack = 100 + index;    // Exemple : Attaque basée sur l'index
        int defense = 100 + index;   // Exemple : Défense basée sur l'index
        int stamina = 100 + index;   // Exemple : Endurance basée sur l'index
        double iv = (attack + defense + stamina) / 3.0;

        return new Pokemon(index, "Pokemon-" + index, attack, defense, stamina, cp, hp, dust, candy, iv);
    }
}
