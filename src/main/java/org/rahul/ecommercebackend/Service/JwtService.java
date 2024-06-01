//package org.rahul.ecommercebackend.Service;//package org.rahul.ecommercebackend.Service;
////
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
////import io.jsonwebtoken.io.Decoders;
////import io.jsonwebtoken.security.Keys;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Service;
////
////import javax.crypto.KeyGenerator;
////import javax.crypto.SecretKey;
////import java.security.Key;
////import java.security.NoSuchAlgorithmException;
////import java.security.PublicKey;
////import java.util.*;
////import java.util.function.Function;
////
////
////@Service
////public class JwtService {
////
//////    @Autowired
//////    private Claims claims;
////
////    //private static  final String SECRET ="aksjhdfkjhasdkjfhaiuhuejmcxncvbsdhfiuwowijsdifhumcnsd";
////
////    private  String secreatKey =null;
////    private JwtService(){
////         secreatKey = generateSecretKey();
////    }
//////    private String secretKey;
//////
//////    public JwtService() {
//////        secretKey = System.getenv("JWT_SECRET_KEY");
//////        if (secretKey == null) {
//////            throw new IllegalStateException("JWT_SECRET_KEY environment variable is not set");
//////        }
////
////
////    public String generateSecretKey(){
////        try{
////            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
////            SecretKey secretKey = keyGenerator.generateKey();
////            System.out.println("Secret Key : "+ secretKey.toString());
////            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
////        } catch (NoSuchAlgorithmException e) {
////            throw new RuntimeException("Error in generating secret key",e);
////        }
////
////
////    }
////
////
////    public String generateToken(String email) {
////
////        Map<String, Object> claims = new HashMap<>();
////
////        return Jwts.builder()
////                .setClaims(claims)
////                .setSubject(email)
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
////                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
////    }
////
////    private Key getKey() {
////        byte[] keyBytes = Decoders.BASE64.decode(secreatKey);
////
////        return Keys.hmacShaKeyFor(keyBytes);
////    }
////
////
////    //-> Extracting the username from the token
////    public String extractUserName(String token) {
////
////        return extractClaim(token, Claims::getSubject);
////
////    }
////
////
////
////    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
////        final Claims claims = extractAllClaims(token);
////        return claimsResolver.apply(claims);
////    }
////
////
////
////    private Claims extractAllClaims(String token) {
////
////        return Jwts.parser()
////                .setSigningKey(getKey())
////                .parseClaimsJws(token).getBody();
////    }
////
////
////    public boolean validateToken(String token, UserDetails userDetails) {
////
////        final String userName = extractUserName(token);
////        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
////
////    }
////
////
////    private boolean isTokenExpired(String token) {
////        return extractExpiration(token).before(new Date());
////    }
////
////    private Date extractExpiration(String token) {
////        return extractClaim(token, Claims::getExpiration);
////    }
////
////    //----------------
////
////
//////
//////    private static final String SECRET = "TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg=\r\n";
//////
//////    private String secretKey;
//////
//////    public JwtService(){
//////        secretKey = generateSecretKey();
//////    }
//////
//////    public String generateSecretKey() {
//////        try {
//////            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//////            SecretKey secretKey = keyGen.generateKey();
//////            System.out.println("Secret Key : " + secretKey.toString());
//////            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//////        } catch (NoSuchAlgorithmException e) {
//////            throw new RuntimeException("Error generating secret key", e);
//////        }
//////    }
//////
//////    public String generateToken(String username) {
//////
//////        Map<String, Object> claims = new HashMap<>();
//////
//////        return Jwts.builder()
//////                .setClaims(claims)
//////                .setSubject(username)
//////                .setIssuedAt(new Date(System.currentTimeMillis()))
//////                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
//////                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
//////
//////    }
//////
//////    private Key getKey() {
//////        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//////        return Keys.hmacShaKeyFor(keyBytes);
//////    }
//////
//////    public String extractUserName(String token) {
//////        // extract the username from jwt token
//////        return extractClaim(token, Claims::getSubject);
//////    }
//////
//////    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//////        final Claims claims = extractAllClaims(token);
//////        return claimResolver.apply(claims);
//////    }
//////
//////    private Claims extractAllClaims(String token) {
//////        return Jwts.parserBuilder()
//////                .setSigningKey(getKey())
//////                .build().parseClaimsJws(token).getBody();
//////    }
//////
//////
//////    public boolean validateToken(String token, UserDetails userDetails) {
//////        final String userName = extractUserName(token);
//////        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
//////    }
//////
//////    private boolean isTokenExpired(String token) {
//////        return extractExpiration(token).before(new Date());
//////    }
//////
//////    private Date extractExpiration(String token) {
//////        return extractClaim(token, Claims::getExpiration);
//////    }
////
////
////}
//
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//
//    private static final String SECRET = "TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg=\r\n";
//
//    private String secretKey;
//
//    public JwtService() {
//        secretKey = generateSecretKey();
//    }
//
//    public String generateSecretKey() {
//        try {
//            System.out.println("Secret Key : " + secretKey.toString());
//            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey secretKey = keyGen.generateKey();
//            System.out.println("Secret Key : " + secretKey.toString());
//            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("Error generating secret key", e);
//        }
//    }
//
//    public String generateToken(String username) {
//
//        Map<String, Object> claims = new HashMap<>();
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
//                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
//
//    }
//
//    private Key getKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String extractUserName(String token) {
//        // extract the username from jwt token
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getKey())
//                .build().parseClaimsJws(token).getBody();
//    }
//
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String userName = extractUserName(token);
//        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//}

package org.rahul.ecommercebackend.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET = "TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg=\r\n";

    private String secretKey;

    public JwtService() {
        secretKey = SECRET;
    }

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}