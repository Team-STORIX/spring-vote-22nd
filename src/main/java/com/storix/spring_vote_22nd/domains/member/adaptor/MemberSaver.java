package com.storix.spring_vote_22nd.domains.member.adaptor;

import com.storix.spring_vote_22nd.domains.member.domain.MemberEntity;
import com.storix.spring_vote_22nd.domains.member.repository.MemberRepository;
import com.storix.spring_vote_22nd.domains.member.service.CreateMemberCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSaver {

    private final MemberRepository memberRepository;

    /** 회원 가입 */
    @Transactional
    public Long execute(CreateMemberCommand createMemberCommand) {  // member join command
        MemberEntity saved = memberRepository.save(createMemberCommand.toEntity());
        return saved.getId();
    }

}