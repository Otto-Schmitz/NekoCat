package br.com.nekocat.security.service;

import br.com.nekocat.security.contract.token.TokenInterface;
import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.contract.user.UserInterface;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService implements TokenInterface {
    //10h in ms
    private static final String EXPIRATION = "36000000";

    @Autowired
    private UserInterface userInterface;

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime()+Long.parseLong(EXPIRATION));
        Users logged = userInterface.getByEmail(authentication.getPrincipal().toString());

        return Jwts.builder()
                .setIssuer("Nekocat")
                .setSubject(logged.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Long getUserId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    @Override
    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
