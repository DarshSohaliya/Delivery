package com.example.Delivery.util;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    private static final String SECRET = "YourVeryLongSecretKeyMustBeAtLeast64CharactersLongToBeSecure!";

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private final long jwtExpirationMs = 86400000;


    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();

    }
    public String extractUsername(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public  boolean validation(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());

        }catch (Exception e){
            return true;
        }
        return false;
    }

}

//
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Base64;
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    private static final String SECRET = "YourVeryLongSecretKeyMustBeAtLeast64CharactersLongToBeSecure!";
//
//    private Key key =  Keys.hmacShaKeyFor(DecoderBASE64.decode(java.util.Base64.getEncoder().encodeToString(SECRET.getBytes())));
//     private final long jwtExpirationMs = 86400000;
//
//
//     public String generateToken(UserDetails userDetails){
//         return
//                 .builder()
//                 .setSubject(userDetails.getUsername())
//                  .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                 .signWith(key,SignatureAlgorithm.HS512)
//                 .compact();
//
//     }
//     public String extractUsername(String token){
//         return
//     }
//     public  boolean validation(String token,UserDetails userDetails){
//         final String username = extractUsername(token);
//         return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//     }
//
//    private boolean isTokenExpired(String token) {
//         try {
//             Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
//             return true;
//         }catch (Exception e){
//             return false;
//         }
//
//    }
//
//}
