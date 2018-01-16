package com.kaishengit.controller;

import com.kaishengit.pojo.Product;
import com.kaishengit.service.ProductService;
import com.kaishengit.util.RequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 添加备注,测试sourceTree用法
 * @author NativeBoy
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 展示商品列表
     * @param model
     * @return
     */
    @GetMapping
    public String home(Model model, HttpServletRequest request){
        List<RequestQuery> requestQueryList = RequestQuery.getRequestQueryFromURL(request);
        model.addAttribute("productList",productService.findByRequestQuery(requestQueryList));
        return "list";
    }

    /**
     * 新增商品
     * @return
     */
    @GetMapping("/new")
    public String newProduct(){
        return "new";
    }

    /**
     * 提交新增商品
     * @param product
     * @return
     */
    @PostMapping("/new")
    public String save(Product product){
        productService.save(product);
        return "redirect:/product";
    }

    /**
     * 商品详情
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String showDetail(Model model, @PathVariable Integer id){
        model.addAttribute("product",productService.findById(id));
        return "detail";
    }

    /**
     * 修改商品详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product",productService.findById(id));
        return "edit";
    }

    /**
     * 提交修改
     * @param product
     * @return
     */
    @PostMapping("/edit")
    public String eidtSave(Product product){
        productService.update(product);
        System.out.println(product);
        return "redirect:/product/" + product.getId();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}/delete")
    public String delete(@PathVariable Integer id){
        productService.delete(id);
        return "redirect:/product";
    }
}
