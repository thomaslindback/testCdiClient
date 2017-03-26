package org.teel.ejbT1Client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.teel.ejbT1.NewSessionBean;
import org.teel.ejbT1.NewsessionBeanRemote;

/**
 * Hello world!
 *
 */
public class App {

	public void run() {
		Properties prop = new Properties();
		  
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		prop.put(Context.SECURITY_PRINCIPAL, "thomas");
		prop.put(Context.SECURITY_CREDENTIALS, "bamboo88?");
		          
		prop.put("jboss.naming.client.ejb.context", true);
		  
		try {
			Context context = new InitialContext(prop);
			
			//String ss = "ejb:/testCdi-1.0-SNAPSHOT//NewSessionBean!org.teel.ejbT1.NewSessionBean";
			String jndiLookup = "testCdi-1.0-SNAPSHOT/NewSessionBean!org.teel.ejbT1.NewsessionBeanRemote";
			NewsessionBeanRemote nsb = (NewsessionBeanRemote) context.lookup(jndiLookup);
			nsb.businessMethod();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run2()  {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		props.put("jboss.naming.client.ejb.context", true);
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		props.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
		try {
			Context context = new InitialContext(props);

			String jndiLookup = "testCdi-1.0-SNAPSHOT/NewSessionBean!org.teel.ejbT1.NewsessionBeanRemote";
			NewsessionBeanRemote hello = (NewsessionBeanRemote) context.lookup(jndiLookup);
			System.out.println("---------------");
			hello.businessMethod();
		} catch (NamingException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		new App().run();
	}
}
