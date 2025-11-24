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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;
    @Mock
    private MemberRepository memberRepository;
    @Captor
    private ArgumentCaptor<Member> memberArgumentCaptor;

    @Test
    public void memberNotFound() {
        when(memberRepository.getMember("member-id"))
                .thenReturn(Member.builder()
                        .id("member-id")
                        .name("name")
                        .suspended(false)
                        .build());
//        Assertions.assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));

        memberService.suspendMember("member-id");

        verify(memberRepository).getMember("member-id");

//        ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);

//        verify(memberRepository).save(any(Member.class));
        verify(memberRepository).save(memberArgumentCaptor.capture());
        Member member = memberArgumentCaptor.getValue();
        assertTrue(member.isSuspended());
        assertEquals("name", member.getName());
        assertEquals("member-id", member.getId());


//        verify(memberService).suspendMember("member-id2");
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(memberRepository);
    }


}