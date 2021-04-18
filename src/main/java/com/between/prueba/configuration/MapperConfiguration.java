package com.between.prueba.configuration;

import com.between.prueba.dto.ProductPriceResponse;
import com.between.prueba.repository.entity.Price;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

;import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * Configuracion utilitaria transversal  
 * 
 *@author: Juan Carlos Fuyo
 */
@Configuration
public class MapperConfiguration {

	@Bean
	public DozerBeanMapper dozerBeanMapper() throws Exception {
		List<String> mappingFiles = new ArrayList();
		mappingFiles.add("dozerJdk8Converters.xml");
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(mappingFiles);
		mapper.addMapping(builderPriceToProductPriceResponse);
		return mapper;
	}

	private BeanMappingBuilder builderPriceToProductPriceResponse = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping(Price.class, ProductPriceResponse.class, TypeMappingOptions.trimStrings())
					.fields("productId","productId")
					.fields("brandId","brandId")
					.fields("priceList","priceList")
					.fields("startDate","startDate")
					.fields("endDate","endDate")
					.fields("price","price");
		}
	};

}
