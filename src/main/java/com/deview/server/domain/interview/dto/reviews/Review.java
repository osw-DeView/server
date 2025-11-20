package com.deview.server.domain.interview.dto.reviews;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private List<Question> questions;

}
