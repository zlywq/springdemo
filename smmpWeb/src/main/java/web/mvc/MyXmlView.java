package web.mvc;


import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;

import org.springframework.oxm.Marshaller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cmn2.util.*;

public class MyXmlView extends AbstractView {

	/**
	 * Default content type. Overridable as bean property.
	 */
	public static final String DEFAULT_CONTENT_TYPE = "application/xml";


	

	/**
	 * Construct a new {@code MarshallingView} with no {@link Marshaller} set.
	 * The marshaller must be set after construction by invoking {@link #setMarshaller}.
	 */
	public MyXmlView() {
		setContentType(DEFAULT_CONTENT_TYPE);
		setExposePathVariables(false);
	}

	




//	@Override
//	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		String strXml = Util1.convertXmlStrFromMapSimple_ByElement(model);//TODO POJO attr to xml str
//		byte[] bytesXml = strXml.getBytes(Const.Charset_Encoding_UTF_8);
////		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
//		setResponseContentType(request, response);		
//		response.setContentLength(bytesXml.length);
//		response.getOutputStream().write(bytesXml);
////		baos.writeTo(response.getOutputStream());
//	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XStream xstream=new XStream(new DomDriver());
		String strXml=xstream.toXML(model);
		byte[] bytesXml = strXml.getBytes(Const.Charset_Encoding_UTF_8);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		setResponseContentType(request, response);		
		response.setContentLength(bytesXml.length);
		response.getOutputStream().write(bytesXml);
//		baos.writeTo(response.getOutputStream());
	}

	

}
