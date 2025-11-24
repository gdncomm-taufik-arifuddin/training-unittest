package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
  @InjectMocks
  private MemberService memberService;
  @Mock
  private MemberRepository memberRepository;

  @Test
  public void isMemberSuspendedTest() {
    Member member = Member.builder()
        .id("member-id")
        .name("member")
        .email("email@gdn.com")
        .suspended(false)
        .build();
    when(memberRepository.getMember("member-id")).thenReturn(member);
    memberService.suspendMember("member-id");
    verify(memberRepository).getMember("member-id");
    member.setSuspended(true);

    verify(memberRepository).save(member);

    ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);
    verify(memberRepository).save(memberArgumentCaptor.capture());
    Member responseMember = memberArgumentCaptor.getValue();
    assertTrue(responseMember.isSuspended());
    assertTrue(responseMember.getEmail().equalsIgnoreCase("email@gdn.com"));
    assertTrue(responseMember.getId().equalsIgnoreCase("member-id"));
    assertTrue(responseMember.getName().equalsIgnoreCase("member"));
  }

  @Test
  public void isNullTest() {
    when(memberRepository.getMember("member-id")).thenReturn(null);
    assertThrows(RuntimeException.class, () -> {
      memberService.suspendMember("member-id");
    });
    try {
      memberService.suspendMember("member-id");
    } catch (RuntimeException e) {
      assertTrue(e.getMessage().equalsIgnoreCase("Member not found"));
    }
  }

  @Test
  public void isSuspendedTest() {
    when(memberRepository.getMember("member-id")).thenReturn(Member.builder()
        .id("member-id")
        .email("email@gdn.com")
        .suspended(true)
        .build());
    assertThrows(RuntimeException.class, () -> {
      memberService.suspendMember("member-id");
    });
    verify(memberRepository).getMember("member-id");
  }

  @AfterEach
  public void tearDown() {
    verifyNoMoreInteractions(memberRepository);
  }
}