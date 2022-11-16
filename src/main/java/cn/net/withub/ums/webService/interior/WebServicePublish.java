package cn.net.withub.ums.webService.interior;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import javax.xml.ws.Endpoint;

public class WebServicePublish {

    public static void main(String[] args) {
//        Endpoint.publish("http://149.0.45.238:8096/PersonService",new PersonServiceImpl());
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://149.0.45.238:8096/PersonService");
        factory.setServiceClass(PersonServiceImpl.class);
        factory.setServiceBean(new PersonServiceImpl());
//        factory.getInInterceptors().add(new AuthenticationInInterceptor());
        factory.create();
    }
}
