package com.example.food_project.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper {
    private final long expiredDate = 8 * 60 * 60 * 1000;
    private final String strKey = "YWRtaW4xMjM0NTY3ODkwYWRtaW4xMjM0NTY3ODkwYWRtaW4xMjM0NTY3ODkwCg=="; //Chuỗi BASE64

    private Gson gson = new Gson();
    public String generateToken(String data, String type, long expiredDate){

        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + expiredDate);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        Map<String, Object> subJectData = new HashMap<>();
        subJectData.put("username", data);
        subJectData.put("type", type);

        String json = gson.toJson(subJectData);

        return Jwts.builder()
                .setSubject(json) // Lữu trữ dữ liệu vào trong token type String
                .setIssuedAt(now)//Thời gian tạo ra token
                .setExpiration(dateExpired)//Thời gian hết hạn của token
                .signWith(secretKey, SignatureAlgorithm.HS256)//Thuật toán mã hóa và secret key
                .compact();
    }

    public String decodeToken(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(strKey));
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validaToken(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(strKey));
        boolean isSuccess = false;
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build()
                    .parseClaimsJws(token);
            isSuccess = true;
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return isSuccess;
    }
}
