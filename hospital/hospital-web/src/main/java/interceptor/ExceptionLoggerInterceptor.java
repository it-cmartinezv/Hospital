package interceptor;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.apache.log4j.Logger;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Interceptor
@ExceptionLogger
public class ExceptionLoggerInterceptor implements Serializable {

	private static Logger log = Logger.getLogger(ExceptionLoggerInterceptor.class);

	@AroundInvoke
	public Object mostrarExcepcion(InvocationContext invocationContext) throws Exception {
		Long t1 = System.currentTimeMillis();
		log.info("Ingresando al metodo:" + invocationContext.getMethod().getName());
		try {
			Object ret = invocationContext.proceed();
			return ret;
		} catch (excepciones.ExcepcionFuncional exc) {
			Messages.addFlashGlobalError(exc.getMessage());
			log.error(exc.getMessage(), exc);
		} catch (Exception exc) {
			log.error(exc.getMessage(), exc);
			Faces.getApplication().getNavigationHandler().handleNavigation(Faces.getContext(), null,
					"/error.jsf?faces-redirect=true");
		}finally {
			log.info("Saliendo del metodo:" + invocationContext.getMethod().getName() + ", ts="
					+ (System.currentTimeMillis() - t1) + "ms");
		}

		return null;
	}

}