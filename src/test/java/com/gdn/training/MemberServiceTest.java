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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Member Service Test")
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("success suspend member test")
    public void successSuspendMemberTest() {
        Mockito.when(memberRepository.getMember("member-id"))
                .thenReturn(Member.builder()
                        .id("member-id")
                        .email("member-email")
                        .name("name")
                        .suspended(false)
                        .build());

        memberService.suspendMember("member-id");

        Mockito.verify(memberRepository).getMember("member-id");

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        Mockito.verify(memberRepository).save(captor.capture());
        Member member = captor.getValue();
        Assertions.assertTrue(member.isSuspended());
        Assertions.assertEquals("name", member.getName());
        Assertions.assertEquals("member-email", member.getEmail());
        Assertions.assertEquals("member-id", member.getId());
    }

    @Test
    @DisplayName("member already suspended test")
    public void memberAlreadySuspendedTest() {
        Mockito.when(memberRepository.getMember("member-id"))
                .thenReturn(Member.builder()
                        .id("member-id")
                        .email("member-email")
                        .name("name")
                        .suspended(true)
                        .build());

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                () -> memberService.suspendMember("member-id"));

        Assertions.assertEquals("Member already suspended", ex.getMessage());
        Mockito.verify(memberRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("member not found test")
    public void memberNotFoundTest() {
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                () -> memberService.suspendMember("member-id"));

        Assertions.assertEquals("Member not found", ex.getMessage());
        Mockito.verify(memberRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("member not args test")
    void testNoArgsConstructor() {
        Member member = new Member();

        Assertions.assertNull(member.getId());
        Assertions.assertNull(member.getName());
        Assertions.assertNull(member.getEmail());
        Assertions.assertFalse(member.isSuspended());
    }
}
