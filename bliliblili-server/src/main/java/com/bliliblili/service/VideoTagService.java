package com.bliliblili.service;

import com.bliliblili.domain.entity.Tag;
import org.springframework.stereotype.Service;

/**
 * @ author ljw
 * @ data 2024/9/12 15:43
 */

public interface VideoTagService {

    Long addVideoTag(Tag tag);

    void deleteVideoTag(Long tagId);
}
