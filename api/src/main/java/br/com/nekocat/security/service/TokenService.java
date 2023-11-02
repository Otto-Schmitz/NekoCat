package br.com.nekocat.security.service;

import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.domain.user.contract.UserInterface;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {
    //10h in ms
    private static final String EXPIRATION = "36000000";

    @Autowired
    private UserInterface userInterface;

    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime()+Long.parseLong(EXPIRATION));
        Users logged = userInterface.getByEmail(authentication.getPrincipal().toString());

        return Jwts.builder()
                .setSubject(logged.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean isValid(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
