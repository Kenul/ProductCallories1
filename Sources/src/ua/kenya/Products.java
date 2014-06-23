package ua.kenya;

import java.io.*;
import java.util.*;

/**
 * Created by zidd on 3/23/14.
 */
public class Products {
    private Map<Integer, Product> products;

    public Products(String path) {
        readProductsFromFile(path);
    }

    public Object[][] getProductsModel() {
        Object[][] model = new Object[products.size()][4];
        for(int i = 1; i <= products.size(); i++) {
            Product product = products.get(i);
            model[i-1][0] = product.getId();
            model[i-1][1] = product.getName();
            model[i-1][2] = product.getCallories();
            model[i-1][3] = product.getDiscription();
        }
        return model;
    }

    public Product get(int key) {
        return products.get(key);
    }

    public Object[][] getProductsComputeModel() {
        Object[][] model = new Object[products.size()][2];
        for(int i = 1; i <= products.size(); i++) {
            Product product = products.get(i);
            model[i-1][0] = product.getName();
            model[i-1][1] = false;
        }
        return model;
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<Product>();
        for(Map.Entry<Integer,Product> p : products.entrySet()) {
            productList.add(p.getValue());
        }
        return productList;
    }

    private void readProductsFromFile(String path) {
        BufferedReader br;
        //FileReader fr;
        FileInputStream fis;
        InputStreamReader isr;
        products = new HashMap<Integer, Product>();
        try {
            //fr = new FileReader(new File(path));
            //br = new BufferedReader(fr);
            fis = new FileInputStream(new File(path));
            isr = new InputStreamReader(fis, "UTF8");
            br = new BufferedReader(isr);
            while(true)	{
                String id = br.readLine();
                if(id == null) break;
                String name = br.readLine();
                String callories = br.readLine();
                String disc = br.readLine();
                Product product = new Product(Integer.parseInt(id.trim()), Integer.parseInt(callories.trim()), name, disc);
                products.put(product.getId(), product);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
