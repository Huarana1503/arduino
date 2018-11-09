package hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;
import com.sap.security.um.service.UserManagementAccessor;
/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		PrintWriter out = response.getWriter();
		String producto = request.getParameter("Producto");
		String lote = request.getParameter("Lote");
		String comitente = request.getParameter("Comitente");
	    if(producto != null && lote!=null){
			out.println("<p>Producto recibido:" + producto );
			out.println("<p>Lote recibido:" + lote );
			out.println("<p>Comitente recibido:" + comitente );
	    	String mensaje= liberarLote(producto,lote,comitente);  
	    	out.println("<p>Resultado de la operación: " + mensaje);
	    } else {
	    	out.println("No text entered.");
	    }
	    out.close();		
	}
	protected String liberarLote(String Producto, String Lote, String Comitente) 
	{
		// Seteo el destino a utilizar
		JCoDestination destination;
		try {
			destination = JCoDestinationManager.getDestination("ERPQAS");
			JCoRepository repo=destination.getRepository();
	        
	        
	        // Seteo la función RFC a utilizar
	        JCoFunction stfcConnection=repo.getFunction("Z_MM_LIBLOQ_LOTES_COMITENTES");
	        // Creo el objeto para los imports
	        JCoParameterList tablaImport= stfcConnection.getTableParameterList();
	        JCoTable T_DATA= tablaImport.getTable("T_DATA");
	        // Seteo los valores de los imports
	        T_DATA.appendRow();
	        T_DATA.setValue("SPART", Comitente);
	        T_DATA.setValue("BISMT", Producto);
	        T_DATA.setValue("CHARG_D", Lote);
	        T_DATA.setValue("ESTADO_1", "X");
	        T_DATA.setValue("ESTADO_2", "");
	        //Ejecuto la función RFC
	        stfcConnection.execute(destination);
	        //Objento el valor de la tabla RETURN
	        JCoParameterList tablaExports=stfcConnection.getExportParameterList();
	        JCoTable T_RETURN=tablaExports.getTable("T_RETURN");
	        String mensaje=T_RETURN.getString("MENSAJE");
	        return(mensaje);
	        
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return(e.getMessage());
		}
	}
}
