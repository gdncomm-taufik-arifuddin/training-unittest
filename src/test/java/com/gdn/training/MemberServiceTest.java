package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

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

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        verify(this.memberRepository).save(captor.capture());
        Member member = captor.getValue();
        Assertions.assertTrue(member.isSuspended());
        Assertions.assertEquals("name", member.getName());
        Assertions.assertEquals("member-id", member.getId());

        Assertions.assertThrows(RuntimeException.class, () -> {
            memberService.suspendMember("member-id-dummy");
        });

        Assertions.assertThrows(RuntimeException.class, () -> {
            memberService.suspendMember("member-id");
        });
    }

//    @AfterEach
//    public void tearDown() {
//        verifyNoMoreInteractions(memberRepository);
//    }
}