package controller.dao;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class TokenUtil {

    private static final String SECRET_KEY = "my_secret_key";  // Cambia esto por una clave secreta más segura.

    public static String generateToken(Integer idPersona, String correo) {
        return Jwts.builder()
                .setSubject(correo) 
                .claim("idPersona", idPersona)  
                .setIssuedAt(new Date())  
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token); 
            return true;  
        } catch (Exception e) {
            return false;  
        }
    }
}
