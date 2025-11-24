package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Member Service Test")
class MemberServiceTest {
  @Mock
  private MemberRepository memberRepository;

  @InjectMocks
  private MemberService memberService;

  @Test
  @DisplayName("validate suspendMember with the not found result")
  void suspendMemberNotFound() {
    String memberId = "BN260231";
    when(memberRepository.getMember(memberId)).thenReturn(null);

    RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> memberService.suspendMember(memberId));
    assertEquals("Member not found", runtimeException.getMessage());

    verify(memberRepository).getMember(memberId);
    verify(memberRepository, never()).save(any(Member.class));
  }

  @Test
  @DisplayName("validate suspendMember is already suspended")
  void suspendMemberAlreadySuspended() {
    String memberId = "BN250201";
    Member member = new Member();
    member.setSuspended(true);

    when(memberRepository.getMember(memberId)).thenReturn(member);
    RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> memberService.suspendMember(memberId));
    assertEquals("Member already suspended", runtimeException.getMessage());

    verify(memberRepository).getMember(memberId);
    verify(memberRepository,never()).save(any(Member.class));
  }

  @Test
  @DisplayName("validate suspendMember set suspended to false at first and then set to true and save member")
  void saveMember() {
    String memberId = "BN270221";
    String memberName = "Edmund";
    String memberEmail = "edmund@gdn-commerce.com";
    boolean isSuspended = false;

    Member member = Member.builder().id(memberId).name(memberName).email(memberEmail).suspended(false).build();

//    Member member = new Member();
//    member.setId(memberId);
//    member.setName(memberName);
//    member.setEmail(memberEmail);
//    member.setSuspended(isSuspended);
//
//    Member.builder().id(memberId).name(memberName).email(memberEmail).suspended(false);

    when(memberRepository.getMember(memberId)).thenReturn(member);
    memberService.suspendMember(memberId);

    assertTrue(member.isSuspended());
    assertEquals(memberId, member.getId());
    assertEquals(memberName, member.getName());
    assertEquals(memberEmail, member.getEmail());
  }
}
