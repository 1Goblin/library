package org.example.library.controller;


import lombok.RequiredArgsConstructor;
import org.example.library.dto.MemberDto;
import org.example.library.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody MemberDto memberDto) {
//        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberService.register(memberDto);
        return "회원가입 성공";
    }

//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody AuthRequest authRequest) {
//        Member member = memberService.findByUsername(authRequest.getUsername());
//
//        if (!passwordEncoder.matches(authRequest.getPassword(), member.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 틀립니다.");
//        }
//
//        String token = jwtUtil.generateToken(member.getUsername(), member.getRole().name());
//        return new AuthResponse(token);
//    }
}