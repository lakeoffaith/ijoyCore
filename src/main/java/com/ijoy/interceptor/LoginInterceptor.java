package com.ijoy.interceptor;

import java.io.PrintStream;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoginInterceptor extends AbstractPhaseInterceptor<SoapMessage>
{
  private SAAJInInterceptor saa = new SAAJInInterceptor();

  public LoginInterceptor() { super("pre-protocol");
    getAfter().add(SAAJInInterceptor.class.getName()); }

  public void handleMessage(SoapMessage message) throws Fault
  {
	  Header header = message.getHeader(new QName("RequestSOAPHeader"));
    
   
    if (header == null) {
      return;
    }
    Element el=(Element) header.getObject();

    String name  = el.getElementsByTagName("tns:spId").item(0).getTextContent();
    String password=el.getElementsByTagName("tns:spPassword").item(0).getTextContent();
    if (name.indexOf("wang") != -1) {
      if (password.equals("can"))
        System.out.println("认证成功");
    }
    else {
      SOAPException soapExc = new SOAPException("认证错误");
      throw new Fault(soapExc);
    }
  }
}