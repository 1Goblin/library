package org.example.library.service;


import lombok.RequiredArgsConstructor;
import org.example.library.dto.MemberDto;
import org.example.library.entity.Member;
import org.example.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;

    public Member register(MemberDto memberDto) {
        Member member = Member.builder()
            .username(memberDto.getUsername())
//            .password(passwordEncoder.encode(memberDto.getPassword()))
            .role(memberDto.getRole())
            .build();
        return memberRepository.save(member);
    }

    // findByUsername 추가
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
}