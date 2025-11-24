package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService {
  private MemberRepository memberRepository;

  public void suspendMember(String id){
    Member member = memberRepository.getMember(id);
    if(member == null){
      throw new RuntimeException("Member not found");
    }

    if(member.isSuspended()){
      throw new RuntimeException("Member already suspended");
    }

    member.setSuspended(true);
    memberRepository.save(member);
  }
}