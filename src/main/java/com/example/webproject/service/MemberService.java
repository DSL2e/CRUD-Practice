package com.example.webproject.service;

import com.example.webproject.domain.Member;
import com.example.webproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // 필드에 있는 값을 통해 생성자를 만듬
public class MemverService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validateDuplcateMemeber(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplcateMemeber(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("존재하는 회원입니다");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member fineOnd(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
