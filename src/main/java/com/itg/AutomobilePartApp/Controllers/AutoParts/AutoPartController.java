package com.itg.AutomobilePartApp.Controllers.AutoParts;

import com.itg.AutomobilePartApp.DTO.AutoPart.AutoPartDTO;
import com.itg.AutomobilePartApp.Responses.AutoPart.AutoPartResponse;
import com.itg.AutomobilePartApp.Services.AutoPart.AutoPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AutoPart")
public class AutoPartController {
    private final AutoPartService autoPartService;

    @Autowired
    public AutoPartController(AutoPartService _autoPartService) {
        this.autoPartService = _autoPartService;
    }

    @GetMapping
    public List<AutoPartResponse> getProducts(){
        return autoPartService.getProducts();
    }

    @GetMapping("/recent")
    public List<AutoPartResponse> getRecentProducts(){
        return autoPartService.getRecentProducts();
    }

    @GetMapping("/get_by_code/")
    public AutoPartResponse getProductByCode(@RequestParam("code") String code){
        return autoPartService.getProductByCode(code);
    }

    @GetMapping("/get_by_category_or_name/")
    public List<AutoPartResponse> getProductByCategoryOrName(@RequestParam("category_or_name") String category_or_name){
        return autoPartService.getProductByCategoryOrName(category_or_name);
    }

    @GetMapping("/get_my_Basket/")
    public List<AutoPartResponse> getProductsInMyBasket(@RequestParam("codes") String Codes){
        return autoPartService.getProductsInMyBasket(Codes);
    }

    @PostMapping
    public boolean createProduct(@RequestBody AutoPartDTO new_product){
        return autoPartService.insertProduct(new_product);
    }

    @DeleteMapping("/{code}")
    public boolean deleteProduct(@PathVariable("code") String code){
        return autoPartService.deleteProduct(code);
    }

    @PutMapping("/update")
    public boolean updateProduct( @RequestBody AutoPartDTO new_product){
        return autoPartService.updateProduct(new_product.getCode(), new_product);
    }

    /*@PutMapping("/purchase")
    public boolean purchaseProduct(@RequestBody PurchaseInfoDTO purchaseInfo){
        return autoPartService.purchaseProduct(purchaseInfo);
    }*/

}