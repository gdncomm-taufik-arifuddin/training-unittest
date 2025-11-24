package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("member not found test")
    void memberNotFoundTest(){
        when(memberRepository.getMember("member-id")).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
    }

    @Test
    @DisplayName("member already suspended test")
    void memberAlreadySuspendedTest(){
        when(memberRepository.getMember("member-id"))
                .thenReturn(Member.builder()
                        .id("member-id")
                        .name("name")
                        .suspended(TRUE).build());

        Assertions.assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
    }

    @Test
    @DisplayName("empty member constructor test")
    void emptyMemberConstructorTest(){
        Member newMember = new Member();
        assertNull(newMember.getId());
        assertNull(newMember.getEmail());
        assertNull(newMember.getName());
    }
    
    @Test
    @DisplayName("active member not suspended yet got suspended test")
    void memberSuspendedTest(){
        Member newMember = Member.builder()
                .id("member-id")
                .name("name")
                .email("test@email.com")
                .suspended(FALSE).build();

        when(memberRepository.getMember("member-id")).thenReturn(newMember);

        memberService.suspendMember("member-id");
        verify(memberRepository).getMember("member-id");
        ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);
        verify(memberRepository).save(memberArgumentCaptor.capture());
        Member member = memberArgumentCaptor.getValue();
        assertTrue(member.isSuspended());
        assertEquals("member-id", member.getId());
        assertEquals("test@email.com", member.getEmail());
        assertEquals("name", member.getName());
    }

    @AfterEach
    void tearDown(){
        verifyNoMoreInteractions(memberRepository);
    }
}