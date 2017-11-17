<%-- 
    Document   : up
    Created on : 16/11/2017, 08:37:23 AM
    Author     : Alumno
--%>

<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="java.io.File"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
		//Ruta donde se guardara el fichero
		
                
                String rutalocal=getServletContext().getRealPath("/");
                String rutacarp=rutalocal+"Uploads";
                String nruta=rutacarp.replace("\\","/");
                String ruta=nruta.replaceAll("/build",""); 
                String objeto = "";
                
                File destino = new File(ruta);
                
		ServletRequestContext src = new ServletRequestContext(request);
 
		if(ServletFileUpload.isMultipartContent(src)){
			DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
			ServletFileUpload upload = new  ServletFileUpload(factory);
 
			java.util.List lista = upload.parseRequest(src);
			File file = null;
			java.util.Iterator it = lista.iterator();
 
			while(it.hasNext()){
				FileItem item=(FileItem)it.next();
				if(item.isFormField())
					out.println(item.getFieldName()+"<br>");
				else
				{
					file=new File(item.getName());
					item.write(new File(destino,file.getName()));
                                        objeto = (ruta + "/" + item.getName());
					session.setAttribute("Imagen", objeto);
				} // end if
			} // end while
                }

	%>
    </body>
</html>
