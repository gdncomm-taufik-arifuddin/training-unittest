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
  @DisplayName("suspendMember should throw RuntimeException when member not found")
  void suspendMember_memberNotFound_throwsException() {
    String memberId = "123";

    when(memberRepository.getMember(memberId)).thenReturn(null);

    RuntimeException ex = assertThrows(RuntimeException.class, () -> memberService.suspendMember(memberId));

    assertEquals("Member not found", ex.getMessage());
    verify(memberRepository).getMember(memberId);
    verify(memberRepository, never()).save(any(Member.class));
  }

  @Test
  @DisplayName("suspendMember should throw RuntimeException when member already suspended")
  void suspendMember_alreadySuspended_throwsException() {
    String memberId = "123";
    Member member = new Member();
    member.setSuspended(true);

    when(memberRepository.getMember(memberId)).thenReturn(member);

    RuntimeException ex = assertThrows(RuntimeException.class, () -> memberService.suspendMember(memberId));

    assertEquals("Member already suspended", ex.getMessage());
    verify(memberRepository).getMember(memberId);
    verify(memberRepository, never()).save(any(Member.class));
  }

  @Test
  @DisplayName("suspendMember should set suspended to true and save member when valid")
  void suspendMember_validMember_suspendsAndSaves() {
    String id = "123";
    String name = "ivan";
    String email = "ivan@ivan.com";
    Member member = Member.builder().suspended(false).email(email).id(id).name(name).build();

    when(memberRepository.getMember(id)).thenReturn(member);

    memberService.suspendMember(id);

    assertTrue(member.isSuspended());
    assertEquals(email, member.getEmail());
    assertEquals(id, member.getId());
    assertEquals(name, member.getName());

    verify(memberRepository).getMember(id);
    verify(memberRepository).save(member);
  }
}
