package com.itg.AutomobilePartApp.Services.AutoPart;

import com.itg.AutomobilePartApp.DTO.AutoPart.AutoPartDTO;
import com.itg.AutomobilePartApp.DTO.AutoPart.PurchaseInfoDTO;
import com.itg.AutomobilePartApp.Entities.AutoParts.AutoPart;
import com.itg.AutomobilePartApp.Entities.AutoParts.FakeFactory;
import com.itg.AutomobilePartApp.Mappers.AutoPart.AutoPartMapper;
import com.itg.AutomobilePartApp.Repositories.AutoPart.AutoPartRepository;
import com.itg.AutomobilePartApp.Responses.AutoPart.AutoPartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutoPartService {

    private final AutoPartRepository autoPartRepository;
    private final Locale lang= new Locale("en");
    private final FakeFactory factory = new FakeFactory();

    @Autowired
    public AutoPartService(AutoPartRepository _autoPartRepository) {
        this.autoPartRepository = _autoPartRepository;
    }

    public List<AutoPartResponse> getProducts(){
        List<AutoPart> products = autoPartRepository.getProducts();
        for(int i=0; i<products.size(); i++){
            AutoPart product = factory.getAutoPart(products.get(i));
            products.set(i, product);
        }
        return AutoPartMapper.INSTANCE.entityListToResponseList(products);
    }

    public List<AutoPartResponse> getRecentProducts() {
        List<AutoPart> products = autoPartRepository.getRecentProducts();
        for(int i=0; i<products.size(); i++){
            AutoPart product = factory.getAutoPart(products.get(i));
            products.set(i, product);
        }
        return AutoPartMapper.INSTANCE.entityListToResponseList(products);
    }

    public AutoPartResponse getProductByCode(String code){
        Optional<AutoPart> product = autoPartRepository.findProductByCode(code);
        if(product.isPresent()) {
            AutoPart _product = factory.getAutoPart(product.get());
            return AutoPartMapper.INSTANCE.entityToResponse(_product);
        }
        else return null;
    }

    public List<AutoPartResponse> getProductsInMyBasket(String Codes) {
        String ids = Codes.substring(0, Codes.length()-1);
        List<String> IDList = Arrays.asList(ids.split(","));
        List<AutoPartResponse> list = new ArrayList<>();
        for(int i=0; i<IDList.size(); i++){
            AutoPartResponse product = getProductByCode(IDList.get(i));
            if(product != null){
                list.add(product);
            }
        }
        return list;
    }

    public List<AutoPartResponse> getProductByCategoryOrName(String category_or_name){
        List<AutoPart> products = autoPartRepository.findProductByCategoryOrName(category_or_name);
        for(int i=0; i<products.size(); i++){
            AutoPart product = factory.getAutoPart(products.get(i));
            products.set(i, product);
        }
        return AutoPartMapper.INSTANCE.entityListToResponseList(products);
    }

    public boolean insertProduct(AutoPartDTO new_product){  //String name, String brand, String description, int stock, String category, double price, String code
        int a = autoPartRepository.insertProduct(new_product.getName(), new_product.getBrand(), new_product.getDescription(), new_product.getStock(), new_product.getCategory(), new_product.getPrice(), new_product.getCode());
        return a > 0;
    }

    public boolean deleteProduct(String code){
        autoPartRepository.deleteProduct(code);
        return autoPartRepository.findProductByCode(code).isEmpty();
    }

    public boolean updateProduct(String code, AutoPartDTO new_product) {
        Optional<AutoPart> ap = autoPartRepository.findProductByCode(code);
        if(ap.isPresent()){
            int res = autoPartRepository.updateProduct(new_product);
            if(res>0)
                return true;
            else return false;
        }
        return false;
    }

}