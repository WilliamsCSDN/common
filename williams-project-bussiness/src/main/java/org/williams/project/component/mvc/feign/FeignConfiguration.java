//package org.williams.project.component.mvc.feign;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import feign.codec.Decoder;
//import feign.optionals.OptionalDecoder;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
//import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
//import org.springframework.cloud.openfeign.support.SpringDecoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author Williams
// * @date 2023-09-08-11:16
// * @Description
// */
//@Configuration
//@RequiredArgsConstructor
//public class FeignConfiguration {
//
//    private final ObjectFactory<HttpMessageConverters> messageConverters;
//
//    private final ObjectMapper objectMapper;
//
//    @Bean
//    public Decoder feignDecoder(ObjectProvider<HttpMessageConverterCustomizer> customizers) {
//        return new OptionalDecoder(new ResponseEntityDecoder(new FeignResultDecoder(new SpringDecoder(this.messageConverters, customizers),objectMapper)));
//    }
//
//}
