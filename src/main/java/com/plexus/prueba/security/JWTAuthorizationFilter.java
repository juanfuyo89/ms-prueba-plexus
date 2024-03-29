package com.plexus.prueba.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.*;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    public static final String JWT_ID = "mymJWT";
    public static final String SECRET = "mymSecretKey";
    public static final String PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (request.getRequestURI().contains("token")) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, null));
            } else if(request.getRequestURI().contains("productPrice")) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, null));
            } else if(request.getRequestURI().contains("h2-console")) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, null));
            } else if(request.getRequestURI().contains("swagger-ui.html")) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, null));
            }else if (checkJWTToken(request, response)) {
                Claims claims = validateToken(request);
                if (claims.getId().equals(JWT_ID)) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            chain.doFilter(request, response);
        } catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    /**
     * Authentication method in Spring flow
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }

}
