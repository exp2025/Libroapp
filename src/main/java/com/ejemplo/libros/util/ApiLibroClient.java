package com.ejemplo.libros.util;
import com.ejemplo.libros.model.Autor;
import com.ejemplo.libros.model.Libro;
import com.google.gson.*;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.ParseException; // ← ¡IMPORTANTE!

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiLibroClient {

    private static final String API_URL = "https://openlibrary.org/search.json?title=";
    private static final String AUTHOR_URL_BASE = "https://openlibrary.org";

    public Libro buscarLibroPorTitulo(String titulo) {
        String url = API_URL + titulo.replace(" ", "+");

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            var response = client.execute(request);

            String json = EntityUtils.toString(response.getEntity());
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();

            JsonArray docs = root.getAsJsonArray("docs");
            if (docs.size() == 0) return null;

            JsonObject libroJson = docs.get(0).getAsJsonObject();
            String tituloLibro = libroJson.get("title").getAsString();
            String autorNombre = libroJson.has("author_name")
                    ? libroJson.getAsJsonArray("author_name").get(0).getAsString()
                    : "Desconocido";

            String idioma = libroJson.has("language")
                    ? libroJson.getAsJsonArray("language").get(0).getAsString()
                    : "N/A";

            String authorKey = libroJson.has("author_key")
                    ? libroJson.getAsJsonArray("author_key").get(0).getAsString()
                    : null;

            int nacimiento = 0;
            Integer fallecimiento = null;

            if (authorKey != null) {
                String authorUrl = AUTHOR_URL_BASE + "/authors/" + authorKey + ".json";
                HttpGet authorRequest = new HttpGet(authorUrl);
                var authorResponse = client.execute(authorRequest);
                String authorJson = EntityUtils.toString(authorResponse.getEntity());
                JsonObject authorData = JsonParser.parseString(authorJson).getAsJsonObject();

                nacimiento = parseYear(authorData.get("birth_date"));
                fallecimiento = parseYearNullable(authorData.get("death_date"));
            }

            Autor autor = new Autor("Apellido", autorNombre, nacimiento, fallecimiento);
            int descargas = (int)(Math.random() * 10000);

            return new Libro(tituloLibro, autor, idioma.toUpperCase(), descargas);

        } catch (IOException | ParseException e) {
            System.out.println("Error de conexión o parseo.");
            return null;
        }
    }

    private int parseYear(JsonElement dateElement) {
        if (dateElement == null || dateElement.isJsonNull()) return 0;
        String dateStr = dateElement.getAsString();
        Matcher m = Pattern.compile("(\\d{4})").matcher(dateStr);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

    private Integer parseYearNullable(JsonElement dateElement) {
        int year = parseYear(dateElement);
        return year > 0 ? year : null;
    }
}
