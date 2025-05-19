package juego;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static Map<String, String> cache = new HashMap<>();

    public static void guardarEnCache(String clave, String valor) {
        cache.put(clave, valor);
    }

    public static String obtenerDeCache(String clave) {
        return cache.get(clave);
    }

    public static void eliminarDeCache(String clave) {
        cache.remove(clave);
    }
}