package com.study.web.springboot.service.posts;

import com.study.web.springboot.domain.posts.Posts;
import com.study.web.springboot.domain.posts.PostsRepository;
import com.study.web.springboot.web.dto.PostsResponseDto;
import com.study.web.springboot.web.dto.PostsSaveRequestDto;
import com.study.web.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional // 데이터 저장
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional // 데이터 수정
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }
}
