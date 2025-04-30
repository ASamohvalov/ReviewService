package mr.tips.ReviewService.integration.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import mr.tips.ReviewService.dto.UserImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class TestJwtUtils {
    private final long EXP_MS = 86400000;

    @Value("${token.signingKey}")
    private String signingKey;

    public String generateToken(UserImage userImage) {
        return Jwts.builder()
                .subject(userImage.getPhoneNumber())
                .claims(Map.of(
                        "email", userImage.getEmail(),
                        "id", userImage.getId()
                ))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXP_MS))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(signingKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
