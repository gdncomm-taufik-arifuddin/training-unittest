package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Member Service Test")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Test
    public void successTest(){
        when(memberRepository.getMember("member-id"))
                .thenReturn(null);
        Assertions.assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
    }

    @Test
    public void memberNotFound() {
        when(memberRepository.getMember("member-id"))
                .thenReturn(Member.builder()
                        .id("member-id")
                        .name("name")
                        .email("email")
                        .suspended(false)
                        .build());

        memberService.suspendMember("member-id");

        verify(memberRepository).getMember("member-id");
        ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);
        verify(memberRepository).save(memberArgumentCaptor.capture());
        Member member = memberArgumentCaptor.getValue();
        assertTrue(member.isSuspended());
        assertEquals("email", member.getEmail());
        assertEquals("name", member.getName());
        assertEquals("member-id", member.getId());
    }

    @Test
    public void memberSuspended() {
        when(memberRepository.getMember("member-id"))
                .thenReturn(Member.builder()
                        .id("member-id")
                        .name("name")
                        .email("email")
                        .suspended(true)
                        .build());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                memberService.suspendMember("member-id")
        );

        assertEquals("Member already suspended", ex.getMessage());

        verify(memberRepository).getMember("member-id");
        verify(memberRepository, never()).save(any());
    }

    @Test
    public void memberNoArgs() {
        Member member = new Member();
        assertEquals(null, member.getId());
        assertEquals(null, member.getName());
        assertEquals(null, member.getEmail());

    }

}