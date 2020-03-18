package com.web.scraping.webscraping;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * Clase para probar como funciona bien el webScraping.
 * 
 * Si todo va bien deberia traerme el contenido de la pagina que tengo de prueba.
 * 
 * El objeto Response es de la nueva libreria que vamos a utilizar para crear el ejemplo
 * 
 * 
 * Basicamente con esta libreria de Jsoup puedo acceder a los elementos del dom de una pagina HTML y a su contenido por supuesto.
 * Si tiene algun ID o lo que sea, podemos acceder al valor. Para cualquier tipo de dato podriamos manejarlo luego desde nuestro lado.
 * 
 * Por ahora este programa esta funcional.
 * 
 *  Hay que investigar bien lo que hace la libreria propiamente. Para entender todos los metodos que tiene
 */
public class WebScrapingOne {

	public static int getStatusConnectionCode(String url) {
		
	    Response response = null;
		
	    try {
		response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
	    } catch (IOException ex) {
		System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
	    }
	    return response.statusCode();
	}
	
	public static Document getHtmlDocument(String url) {

	    Document doc = null;
		try {
		    doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		    } catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		    }
	    return doc;
	}
	
public static final String url = "http://localhost:8080";
	
    public static void main (String args[]) {
		
        // Compruebo si me da un 200 al hacer la petición
        if (getStatusConnectionCode(url) == 200) {
			
            // Obtengo el HTML de la web en un objeto Document
            Document document = getHtmlDocument(url);
			
            // Busco todas las entradas que estan dentro de: 
//            Elements entradas = document.select("javier");--> Ojo que no se muy bien como funciona esta funcion.
            Elements entradas = document.getElementsByClass("javier");
            System.out.println("Número de entradas: "+entradas.size()+"\n");
			
            // Paseo cada una de las entradas
            for (Element elem : entradas) {
                String posicion = elem.getElementsByClass("posicion").text();
                String monto = elem.getElementsByClass("monto").text();
//                String fecha = elem.getElementsByClass("fecha").text();
				
                System.out.println(posicion+"\n"+monto+"\n\n");
				
                // Con el método "text()" obtengo el contenido que hay dentro de las etiquetas HTML
                // Con el método "toString()" obtengo todo el HTML con etiquetas incluidas
            }
				
        }else
            System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(url));
    }
	
	
}
