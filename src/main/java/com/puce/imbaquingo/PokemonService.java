package com.puce.imbaquingo;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.transaction.Transactional;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;
    private final PokemonRepository pokemonRepository;

    public PokemonService(RestTemplate restTemplate, PokemonRepository pokemonRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional
    public String getPokemonInfoAndSave(long pokemonCode) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonCode;

        try {
            String response = restTemplate.getForObject(url, String.class);

            // Parsear la respuesta JSON
            JSONObject jsonObject = new JSONObject(response);

            // Obtener el nombre y el id del Pokémon
            String nombre = jsonObject.getString("name");
            int codigo = jsonObject.getInt("id");

            // Crear una nueva instancia de Pokemon y establecer sus valores
            Pokemon pokemon = new Pokemon();
            pokemon.setCodigo((long) codigo);
            pokemon.setNombre(nombre);

            // Guardar el Pokémon en la base de datos
            pokemonRepository.save(pokemon);

            return "Pokémon guardado en la base de datos: " + pokemon.getNombre() + " [" + pokemon.getCodigo() + "]";
        } catch (Exception e) {
            return "No se pudo obtener la información del Pokémon: " + e.getMessage();
        }
    }
}
