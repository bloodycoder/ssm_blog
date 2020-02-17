package com.personalblog.controller;

import com.personalblog.model.Blog;
import com.personalblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 标注controller层
 * @author shiyanlou
 */
@Controller
public class BlogController {

    /**
     * 自动注解
     */
    @Autowired
    private BlogService blogservice;

    /**
     * 存储博客信息
     *
     * @param blog the blog
     * @return the string
     */
    @RequestMapping("writeBlog")
    public String writeBlog(Blog blog) {
        //获取当前日期
        Date currentTime = new Date();
        //将日期转化为指定格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        blog.setTime(dateString);
        this.blogservice.writeBlog(blog);
        return "success";
    }

    /**
     * 查找全博客，用于主页显示
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("selectAllBlog")
    public String selectAllBlog(HttpServletRequest request) {
        try {
            List<Blog> blogs;
            blogs = this.blogservice.selectAllBlog();
            //将查询结果的list放入request返回给页面，页面用JSTL表达式获取显示
            request.setAttribute("blogs", blogs);
            return "index";
        } catch (Exception e) {
            System.out.println(e);
            return "index";
        }
    }

    /**
     * 与selectAllBlog的操作一样，但是返回跳转的页面不同
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("selectAllBlog2")
    public String selectAllBlog2(HttpServletRequest request) {

        try {
            List<Blog> blogs;
            blogs = this.blogservice.selectAllBlog();
            System.out.println("title:" + blogs.get(0).getBlogtitle());
            request.setAttribute("blogs", blogs);
            return "admin";
        } catch (Exception e) {
            System.out.println(e);
            return "admin";
        }
    }

    /**
     * 通过id查找博客，用于显示博客的正文
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("selectBlogById")
    public String selectBlogById(HttpServletRequest request) {
        try {
            //获取id并转化类型
            String id = request.getParameter("blogid");
            int blogid = Integer.parseInt(id);
            List<Blog> blogs = this.blogservice.selectBlogById(blogid);
            //将查询结果返回
            request.setAttribute("blog", blogs.get(0));
            return "blog";
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     同样是查询所有博客信息，用于管理博客
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("adminblog")
    public String adminblog(HttpServletRequest request) {
        try {
            List<Blog> blogs;
            blogs = this.blogservice.selectAllBlog();
            request.setAttribute("blogs", blogs);
            return "adminblog";
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 删除博客信息
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("deleteBlogById")
    public String deleteBlogById(HttpServletRequest request) {
        try {
            String id = request.getParameter("blogid");
            int blogid = Integer.parseInt(id);
            //调用删除
            this.blogservice.deleteBlogById(blogid);
            return "redirect:adminblog";
        } catch (Exception e) {
            return null;
        }
    }
}
