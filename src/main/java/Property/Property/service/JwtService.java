package Property.Property.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final SecretKey signingKey;
    private final long expiryMilliseconds;

    public JwtService(
            @Value("${app.secret.jwt}") String secret,
            @Value("${app.jwt.expiry-minutes}") long expiryMinutes) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiryMilliseconds = expiryMinutes * 60 * 1000;
    }

    public String createToken(String email) {
        Date now = new Date();
        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiryMilliseconds))
                .signWith(signingKey)
                .compact();
    }
}
