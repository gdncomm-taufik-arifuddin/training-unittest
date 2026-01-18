package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService {

  private MemberRepository memberRepository;

  public void suspendMember(String id){
    Member member = getMemberById(id);
    if(member == null){
      throw new RuntimeException("Member not found");
    }

    if(member.isSuspended()){
      throw new RuntimeException("Member already suspended");
    }

    member.setSuspended(true);
    memberRepository.save(member);
  }

  private Member getMemberById(String id) {
    return memberRepository.getMember(id);
  }

  public boolean isSuspendedMember(String id){
    Member member = getMemberById(id);
    return member != null && member.isSuspended();
  }

}