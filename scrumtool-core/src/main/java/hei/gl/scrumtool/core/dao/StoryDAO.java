package hei.gl.scrumtool.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hei.gl.scrumtool.core.entity.Story;

public interface StoryDAO extends JpaRepository<Story, Long> {

}
