package com.yaorange.controller;

import com.yaorange.entity.Article;
import com.yaorange.service.ArticleService;
import com.yaorange.utils.FreeMarkerUtil;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新闻控制器
 */
@WebServlet("/news.do")
public class ArticleController extends HttpServlet {
    private static final long serialVersionUID = 890860589580965806L;

    private ArticleService articleService= new ArticleService();
    private FreeMarkerUtil freeMarkerUtil = new FreeMarkerUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        //首页新闻列表(/index.html)路径
        String indexPath = request.getServletContext().getRealPath("/index.html");
        File file = new File(indexPath);

        //如果该页面(/index.html)不存在, 我们就用FreeMarker动态生成
        if(!file.exists()){
            try {
                //准备新闻列表数据
                Map<String,Object> root = new HashMap<>();
                List<Article> articles = articleService.getArticles();
                root.put("articles",articles);
                //准备/index.html的目录
                String outDirPath = request.getServletContext().getRealPath("/");

                //生成/index.html
                freeMarkerUtil.toHTML("newsList.ftl",root,outDirPath,"index.html");

                //前面已经生成了新闻列表页面(/index.html), 现在就要继续生成每条新闻的详情(/news/每个新闻id.html)
                root.clear();  //清空数据模型是可选操作

                //继续根据每条新闻详情的数据(已有), 生成/news/每个新闻id.html
                String article_outDirPath = request.getServletContext().getRealPath("/news");
                for (Article article : articles) {
                    root.put("article",article);
                    freeMarkerUtil.toHTML("news.ftl",root,article_outDirPath,article.getId()+".html");
                }
                System.out.println("页面静态化全部完成...");

            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }

        //如果新闻列表(/index.html)存在了,就直接跳转到"新闻列表"页面就可以了
        request.getRequestDispatcher("index.html").forward(request,response);
    }
}
