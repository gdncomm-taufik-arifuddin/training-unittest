package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Member Not Found Test")
    void memberNotFoundTest(){
        when(memberRepository.getMember("423")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> memberService.suspendMember("423"));
        assertEquals("Member not found",exception.getMessage());
    }

    @Test
    @DisplayName("Member Already Suspended Test")
    void memberAlreadySuspendedTest(){
        Member member = new Member();
        member.setSuspended(true);

        when(memberRepository.getMember("423")).thenReturn(member);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> memberService.suspendMember("423"));
        assertEquals("Member already suspended",exception.getMessage());
    }

    @Test
    @DisplayName("Member Successfully Suspended Test")
    void memberSuccessfullySuspendedTest(){
        Member member = new Member();
        member.setSuspended(false);

        when(memberRepository.getMember("423")).thenReturn(member);
        memberService.suspendMember("423");

        assertTrue(member.isSuspended());

        verify(memberRepository, times(1)).getMember("423");
    }

    @Test
    @DisplayName("Created Member Successfully")
    void memberSuccessfullyCreatedTest(){
        String memberId = "J89284N8JK";
        String memberName = "Jovian Ng";
        String email = "jovian.ng@gdn-commerce.com";

        Member member = Member.builder().id(memberId).name(memberName).email(email).suspended(false).build();

        assertFalse(member.isSuspended());
        assertEquals(memberId, member.getId());
        assertEquals(memberName, member.getName());
        assertEquals(email, member.getEmail());
    }
}