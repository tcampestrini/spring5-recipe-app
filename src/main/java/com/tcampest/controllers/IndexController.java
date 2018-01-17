package com.tcampest.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcampest.model.Product;


/**
 * @author Thiago Campestrini em 14 de jan de 2018
 *
 */
@Controller
public class IndexController {
	
	 private static final List<Product> DADOS = new ArrayList<>(Arrays.asList(
	            new Product(1L, "Smartphone Samsung Galaxy On 7", new BigDecimal(849.99)),
	            new Product(2L, "Geladeira Electrolux Frost Free", new BigDecimal(1947.50)),
	            new Product(3L, "Purificador de Água Electrolux", new BigDecimal(533.30)),
	            new Product(4L, "Smart Watch Relogio Bluetooth", new BigDecimal(64.99)),
	            new Product(5L, "Smart TV LED 32\" Samsung", new BigDecimal(1249.00)),
	new Product(6L, "Kit Pneu Aro 14 Dunlop 175/65r14", new BigDecimal(759.60)))); 
	
	@RequestMapping({"", "/", "/index"})
	public String getIndexPage() {
		return "index";
	}
	
	@GetMapping("/products")
    public ModelAndView getProducts(Product produto) {
        ModelAndView model = new ModelAndView("/fragments/products.html");
          
        List<Product> lista = DADOS.stream()
                .filter(p -> produto.getId() == null || produto.getId().equals(p.getId()))
                .filter(p -> StringUtils.isEmpty(produto.getNome()) || p.getNome().startsWith(produto.getNome()))
.collect(Collectors.toList());
        
        model.addObject("products", DADOS);
        
        return model;
	}
}
