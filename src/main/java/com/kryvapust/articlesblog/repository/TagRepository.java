package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Integer> {
   Tag findByName(String name);

}
