//package org.rahul.ecommercebackend.Config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//@Service
//public  class JwtProvider {
//    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//
//    public String generateToken(Authentication auth){
//
//        return Jwts.builder()
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
//                .claim("email",auth.getName())
//                .signWith(key).compact();
//    }
//
//    public String getEmailFromToken(String jwt){
//       jwt= jwt.substring(7);
//
//
//        Claims claims = Jwts.parser().decryptWith(key).build().parseClaimsJws(jwt).getPayload();
//
//        return String.valueOf(claims.get("email"));
//
//    }
//
//
//}
