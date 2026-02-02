package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @InjectMocks
  private MemberService memberService;

  @Captor
  private ArgumentCaptor<Member> memberArgumentCaptor;

  @Mock
  private MemberRepository memberRepository;

  @Test
  public void memberNotFound(){
    when(memberRepository.getMember("member-id"))
        .thenReturn(Member.builder()
            .id("member-id")
            .name("name")
            .suspended(false)
            .build());

    memberService.suspendMember("member-id");

    verify(memberRepository).getMember("member-id");

    verify(memberRepository).save(memberArgumentCaptor.capture());
    Member member = memberArgumentCaptor.getValue();
    assertTrue(member.isSuspended());
    assertEquals("name", member.getName());
    assertEquals("member-id", member.getId());
  }

  @Test
  public void testNotFoundMember(){
    when(memberRepository.getMember("123")).thenReturn(null);

    assertThrows(RuntimeException.class, () -> memberService.suspendMember("123"));
  }

  @Test
  public void testSuspendMember(){
    when(memberRepository.getMember("123")).thenReturn(
        Member.builder()
            .id("123")
            .email("test@gmail.com")
            .suspended(false).build());

    memberService.suspendMember("123");
    verify(memberRepository).getMember("123");
    ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
    verify(memberRepository).save(captor.capture());
    Member member = captor.getValue();
    assertTrue(member.isSuspended());
    assertEquals("test@gmail.com", member.getEmail());
    assertEquals("123", member.getId());
    //    cara manual:
    //    verify(memberRepository).save(Member.builder().id("123").email("test@gmail.com").suspended(true).build());
  }

  @Test
  public void testMemberAlreadySuspended(){
    when(memberRepository.getMember("123"))
        .thenReturn(Member.builder().id("123")
            .email("test@gmail.com")
            .suspended(true).build());
    assertThrows(RuntimeException.class, () -> memberService.suspendMember("123"));
  }
  @AfterEach
  public void tearDown(){
    verifyNoMoreInteractions(memberRepository);
  }
}