package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
  @DisplayName("Should suspend member successfully")
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
  @DisplayName("Should throw exception when member not found")
  void testMemberNotFound() {
    Mockito.when(memberRepository.getMember("member-id")).thenReturn(null);

    RuntimeException exception = assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
    assertEquals("Member not found", exception.getMessage());
  }

  @Test
  @DisplayName("Should throw exception when member already suspended")
  void testMemberAlreadySuspended() {
    when(memberRepository.getMember("member-id"))
        .thenReturn(Member.builder()
            .id("member-id")
            .name("name")
            .suspended(true)
            .build());

    RuntimeException exception = assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
    assertEquals("Member already suspended", exception.getMessage());
  }

  @AfterEach
  public void tearDown(){
    verifyNoMoreInteractions(memberRepository);
  }
}