package com.asseco.controller.lookups;

import com.asseco.controller.AbstractCrudControler;
import com.asseco.dao.AbstractDAO;
import com.asseco.dao.ProductDAO;
import com.asseco.model.Product;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;


@ViewScoped
@Named
public class ProductController extends AbstractCrudControler<Product>{

    private List<Product> productsList;
    private Product product;

    @EJB
    private ProductDAO facade;

    @PostConstruct
    public void init() {
        productsList = facade.findAll();
        product=new Product();
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected Product prepareNew() {
        Product u = new Product();
        return u;
    }

    public void saveNewProduct(){
        product=facade.edit(product);
        productsList.add(product);
        facade.flush();
        product = new Product();
    }

    public void removeProduct(Product p){
        facade.remove(p);
        facade.flush();
        productsList.remove(p);
        productsList = facade.findAll();
    }

    public void editProduct(){
        facade.edit(selected);
        facade.flush();
        productsList = facade.findAll();
        selected = null;
        RequestContext.getCurrentInstance().execute("PF('viewProductDialog').hide()");
    }

    @Override
    public LazyDataModel<Product> getLazyDataModel() {
        return super.getLazyDataModel();
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    protected AbstractDAO<Product> getDAO() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return facade;
    }

}
