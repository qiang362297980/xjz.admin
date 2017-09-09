package com.secondBack.controller;

import com.secondBack.entity.Store;
import com.secondBack.service.StoreService;
import com.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping(value = "store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @ModelAttribute
    public Store get() {
        Store  entity = storeService.get();
        if (entity == null) {
            entity = new Store();
        }
        return entity;
    }

    @RequestMapping(value = "/doStore")
    public String doStore(Model model,Store store){
        if (store == null) {
            store = new Store();
            storeService.add(store);
        }
        model.addAttribute("store",store);
        return "/store/doStore";
    }

    /**
     * 显示预览图片
     */
    @RequestMapping(value = "/showViewImg")
    public void showViewImg(Store store, Integer i, HttpServletRequest request, HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream writer = null;
        try {
            String[] imgs = store.getUrl().split(",");
            inputStream = new FileInputStream(imgs[i]);
            writer = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes) ) != -1) {
                writer.write(bytes,0,len);
            }
            System.out.print("");
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "saveStore")
    public String saveStore(Model model,String name,String context,String mobile,
                            HttpServletRequest request,HttpServletResponse response){
        Store store = storeService.get();
        if (store == null) {
            store = new Store();
            store = storeService.upLoad(store,request,response);
            store.setName(name);
            store.setContext(context);
            store.setMobile(mobile);
            storeService.add(store);
        } else {
            store = storeService.upLoad(store,request,response);
            store.setName(name);
            store.setContext(context);
            store.setMobile(mobile);
            storeService.update(store);
        }
        model.addAttribute("store",store);
        return "redirect:/store/doStore";
    }

}
