package br.com.nekocat.security.config;

import br.com.nekocat.security.domain.user.Users;
import br.com.nekocat.security.service.TokenService;
import br.com.nekocat.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserService userService;

    public TokenAuthenticationFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getToken(request);

        boolean valid = tokenService.isValid(token);

        if (valid) {
            clientAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

    private void clientAuthentication(String token) {
        Long userId = tokenService.getUserId(token);
        Users u = userService.getById(userId);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(u,null,u.getRoles());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        } else {
            return token.substring(7);
        }
    }
}
