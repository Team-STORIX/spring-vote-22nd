package com.storix.spring_vote_22nd.api.auth.dto;

import com.storix.spring_vote_22nd.domains.user.domain.Part;

public record PartResponse(
        String code,
        String label
) {
    public static PartResponse from(Part part) {
        return new PartResponse(part.name(), part.getValue());
    }
}