package br.eaa.eng.security.configuration;

import br.eaa.eng.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = getToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        var subject = tokenService.getSubject(token);
        var authorities = tokenService.getRoles(token);

        if (subject != null) {
            var authentication = new UsernamePasswordAuthenticationToken(subject, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

//        if(token != null) {
//            var subject = tokenService.getSubject(token);
//            var authorities = tokenService.getRoles(token);
//            if (subject != null) {
//                var authentication = new UsernamePasswordAuthenticationToken(subject, null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }        }
//        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.replace("Bearer ", "").trim();
        }
        return null;
    }
}
