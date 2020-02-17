package com.personalblog.service;

import com.personalblog.model.Blog;

import java.util.List;

public interface BlogService {

    void writeBlog(Blog blog);

    List<Blog> selectAllBlog();

    List<Blog> selectBlogById(int blogid);

    void deleteBlogById(int blogid);

}
