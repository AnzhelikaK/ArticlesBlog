package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
}
