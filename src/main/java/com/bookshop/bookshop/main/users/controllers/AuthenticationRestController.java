package com.bookshop.bookshop.main.users.controllers;

import com.bookshop.bookshop.main.security.authlog.AuthLogService;
import com.bookshop.bookshop.main.security.jwt.JwtTokenProvider;
import com.bookshop.bookshop.main.security.jwt.JwtUser;
import com.bookshop.bookshop.main.users.UserService;
import com.bookshop.bookshop.main.users.dtos.AuthenticationRequestDto;
import com.bookshop.bookshop.main.users.model.User;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Slf4j
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final AuthLogService authLogService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider, UserService userService,
            AuthLogService authLogService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.authLogService = authLogService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

//            authLogService.createAuthlogByParam(requestDto.getUsername(), requestDto.getPassword(),true, new Date());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {

//            authLogService.createAuthlogByParam(requestDto.getUsername(), requestDto.getPassword(),false, new Date());
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @EventListener
    public void authSuccessEventListener(AuthenticationSuccessEvent event) {

        String username = ((JwtUser) ((UsernamePasswordAuthenticationToken) event.getSource()).getPrincipal())
                .getUsername();
        String password = ((JwtUser) ((UsernamePasswordAuthenticationToken) event.getSource()).getPrincipal())
                .getPassword();

        authLogService.createAuthlogByParam(username, password, true, new Date());
        log.info("Login successed");
    }

    @EventListener
    public void authFailedEventListener(AbstractAuthenticationFailureEvent event) {

//        String username = ((JwtUser) ((UsernamePasswordAuthenticationToken) event.getSource()).getPrincipal())
//       `         .getUsername();
//        String password = ((JwtUser) ((UsernamePasswordAuthenticationToken) event.getSource()).getPrincipal())
//                .getPassword();

        authLogService.createAuthlogByParam(null, null, false, new Date());

        log.info("Login failed");
    }
}
