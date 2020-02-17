package com.personalblog.mapper;

import com.personalblog.model.Blog;

import java.util.List;

public interface BlogMapper {

    void writeBlog(Blog blog);

    List<Blog> selectAllBlog();

    List<Blog> selectBlogById(int blogid);

    void deleteBlogById(int blogid);

}
