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
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

@DisplayName("Member Helper Test")
class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Captor
    private ArgumentCaptor<Member> memberArgumentCaptor;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("member found")
    public void memberFound() {
        when(memberRepository.getMember("member"))
                .thenReturn(Member.builder()
                        .id("id")
                        .name("name")
                        .suspended(false)
                        .build());

        memberService.suspendMember("member");
        verify(memberRepository).getMember("member");
        verify(memberRepository).save(memberArgumentCaptor.capture());

        Member memberResult = memberArgumentCaptor.getValue();
        assertTrue(memberResult.isSuspended());
        assertEquals("name", memberResult.getName());
        assertEquals("id", memberResult.getId());
    }

    @Test
    @DisplayName("member already suspended")
    public void memberAlreadySuspended() {
        when(memberRepository.getMember("member"))
                .thenReturn(Member.builder()
                        .id("id")
                        .name("name")
                        .suspended(true)
                        .build());

        try {
            memberService.suspendMember("member");
            verify(memberRepository).getMember("member");
        } catch (Exception e) {
            assertEquals("Member already suspended", e.getMessage());
            assertEquals(RuntimeException.class, e.getClass());
        }
    }


    @Test
    @DisplayName("member not found")
    public void memberNotFound() {
        when(memberRepository.getMember("member"))
                .thenReturn(null);

        try {
            memberService.suspendMember("member");
        } catch (Exception e) {
            assertEquals("Member not found", e.getMessage());
        }
        verify(memberRepository).getMember("member");
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(memberRepository);
    }


}