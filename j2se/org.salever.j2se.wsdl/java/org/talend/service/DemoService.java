package org.talend.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-08-01T10:50:14.142+08:00
 * Generated source version: 2.5.2
 * 
 */
@WebServiceClient(name = "DemoService", 
                  wsdlLocation = "DemoService.wsdl",
                  targetNamespace = "http://www.talend.org/service/") 
public class DemoService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.talend.org/service/", "DemoService");
    public final static QName DemoServicePort = new QName("http://www.talend.org/service/", "DemoServicePort");
    static {
        URL url = DemoService.class.getResource("DemoService.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(DemoService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "DemoService.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public DemoService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DemoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DemoService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DemoService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DemoService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DemoService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns DemoServicePortType
     */
    @WebEndpoint(name = "DemoServicePort")
    public DemoServicePortType getDemoServicePort() {
        return super.getPort(DemoServicePort, DemoServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DemoServicePortType
     */
    @WebEndpoint(name = "DemoServicePort")
    public DemoServicePortType getDemoServicePort(WebServiceFeature... features) {
        return super.getPort(DemoServicePort, DemoServicePortType.class, features);
    }

}
