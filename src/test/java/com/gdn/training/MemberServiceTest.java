package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @InjectMocks
  private MemberService memberService;

  @Captor
  private ArgumentCaptor<Member> memberArgumentCaptor;

  @Mock
  private MemberRepository memberRepository;

  @Test
  public void successSuspendMember() {
    when(memberRepository.getMember("member-id")).thenReturn(Member.builder()
        .id("member-id")
        .name("name")
        .email("name@mail.com")
        .suspended(false)
        .build());

    memberService.suspendMember("member-id");

    verify(memberRepository).getMember("member-id");

    verify(memberRepository).save(memberArgumentCaptor.capture());
    Member member = memberArgumentCaptor.getValue();
    assertTrue(member.isSuspended());
    assertEquals("name", member.getName());
    assertEquals("member-id", member.getId());
    assertEquals("name@mail.com", member.getEmail());
  }

  @Test
  public void memberNotFound() {
    when(memberRepository.getMember("member-id")).thenReturn(null);
    assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
  }

  @Test
  public void memberAlreadySuspended() {
    when(memberRepository.getMember("member-id")).thenReturn(Member.builder()
        .id("member-id")
        .name("name")
        .email("name@mail.com")
        .suspended(true)
        .build());

    assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
  }

  @AfterEach
  public void tearDown() {
    verifyNoMoreInteractions(memberRepository);
  }
}
