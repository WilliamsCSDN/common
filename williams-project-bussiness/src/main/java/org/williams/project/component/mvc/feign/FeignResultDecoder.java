//package org.williams.project.component.mvc.feign;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import feign.FeignException;
//import feign.Response;
//import feign.codec.Decoder;
//import lombok.extern.slf4j.Slf4j;
//import org.williams.project.component.mvc.result.HttpError;
//import org.williams.project.component.mvc.result.HttpResponse;
//import org.williams.project.component.mvc.result.HttpResult;
//import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//
///**
// * @author Williams
// * @date 2023-09-08-10:59
// * @Description
// */
//@Slf4j
//public class FeignResultDecoder implements Decoder {
//
//    private final ObjectMapper objectMapper;
//    private final Decoder decoder;
//
//    public FeignResultDecoder(Decoder decoder,ObjectMapper objectMapper) {
//        this.decoder = decoder;
//        this.objectMapper=objectMapper;
//    }
//    @Override
//    public Object decode(Response response, Type type) throws IOException, FeignException {
//        Method method = response.request().requestTemplate().methodMetadata().method();
//        if (method.getReturnType() == HttpResult.class){
//            return this.decoder.decode(response, type);
//        }
//        if (method.getReturnType() == HttpResponse.class){
//            TypeFactory typeFactory = objectMapper.getTypeFactory();
//            HttpResult<?> httpResult = objectMapper.readValue(response.body().asInputStream(), HttpResult.class);
//            HttpResponse<?> httpResponse = httpResult.getResponse();
//            String json = objectMapper.writeValueAsString(httpResponse);
//            return objectMapper.readValue(json,typeFactory.constructType(type));
//        }
//        ParameterizedTypeImpl resultType = ParameterizedTypeImpl.make(HttpResult.class, new Type[]{type}, null);
//        HttpResult<?> result = (HttpResult<?>) this.decoder.decode(response, resultType);
//        if (result.getResponse().getError() == null){
//            return result.getResponse().getBody();
//        }
//        HttpError httpError = result.getResponse().getError();
//        throw new DecodeServiceException(httpError.getCode(), httpError.getMessage(),response.request());
//    }
//}
