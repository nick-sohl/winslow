package ch.nicksohl.winslow.presentation.filter;

import ch.nicksohl.winslow.application.service.JwtService;
import ch.nicksohl.winslow.presentation.auth.WinslowUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    WinslowUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the Authorization header from the Request
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // If the request provides a header, and it is a Bearer-Token -> proceed.
        // Get the token
        // Get the username to later check if there is a logged-in user that is authenticated
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // return everything after "Bearer " -> 7 chars -> the token
            username = jwtService.extractUsername(token); // get the username from the token to
        }

        // if the context has a username and if this user is authenticated -> proceed.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
