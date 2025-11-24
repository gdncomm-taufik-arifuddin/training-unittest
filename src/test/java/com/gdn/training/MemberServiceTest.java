package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
  @InjectMocks
  private MemberService memberService;

  @Mock
  private MemberRepository memberRepository;

  @Test
  public void doSuspendMember(){
    Mockito.when(memberRepository.getMember("member-id"))
      .thenReturn(Member.builder()
        .id("member-id")
        .email("member-id@gmail.com")
        .name("memberName")
        .suspended(false)
        .build());
    memberService.suspendMember("member-id");

    Mockito.verify(memberRepository).getMember("member-id");

    ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);
    Mockito.verify(memberRepository).save(memberArgumentCaptor.capture());
    Member member = memberArgumentCaptor.getValue();
    assertEquals("memberName",member.getName());
    assertEquals("member-id",member.getId());
    assertEquals("member-id@gmail.com",member.getEmail());
    assertEquals(true,member.isSuspended());
  }

  @Test
  public void memberNotFound(){
    Mockito.when(memberRepository.getMember("member-id"))
      .thenReturn(null);
    RuntimeException ex = assertThrows(RuntimeException.class,() -> memberService.suspendMember("member-id"));
    assertEquals("Member not found",ex.getMessage());
  }
  @Test
  public void memberIsAlreadySuspended(){
    Mockito.when(memberRepository.getMember("member-id"))
      .thenReturn(Member.builder()
        .id("member-id")
        .email("member-id@gmail.com")
        .name("memberName")
        .suspended(true)
      .build());
    RuntimeException ex = assertThrows(RuntimeException.class,() -> memberService.suspendMember("member-id"));
    assertEquals("Member already suspended",ex.getMessage());
  }

  @AfterEach
  public void tearDown(){
    Mockito.verifyNoMoreInteractions(memberRepository);
  }
}