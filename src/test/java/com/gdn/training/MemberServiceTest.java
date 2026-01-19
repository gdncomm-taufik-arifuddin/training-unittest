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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    public void memberNotFound() {
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

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(memberRepository);
    }

    private void mockGetMember(boolean suspendStatus) {
        when(memberRepository.getMember(anyString()))
                .thenReturn(Member.builder().suspended(suspendStatus).build());
    }

    @Test
    public void memberAlreadySuspendedTest() {
        mockGetMember(true);
//        when(memberRepository.getMember("member-id"))
//                .thenReturn(Member.builder().suspended(true).build());

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
        assertEquals("Member already suspended", runtimeException.getMessage());
        verify(memberRepository).getMember("member-id");
    }
}