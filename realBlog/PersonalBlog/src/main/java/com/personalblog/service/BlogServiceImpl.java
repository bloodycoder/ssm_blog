package com.personalblog.service;

import com.personalblog.mapper.BlogMapper;
import com.personalblog.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public void writeBlog(Blog blog) {
        System.out.println(blog.getArticle());
        blogMapper.writeBlog(blog);
    }

    public List<Blog> selectAllBlog() {
        List<Blog> blogs = this.blogMapper.selectAllBlog();
        return blogs;
    }

    public List<Blog> selectBlogById(int blogid) {
        List<Blog> blogs = this.blogMapper.selectBlogById(blogid);
        return blogs;
    }

    public void deleteBlogById(int blogid) {
        blogMapper.deleteBlogById(blogid);

    }

}
