package interceptor;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import excepciones.ExcepcionFuncional;
import seguridad.Rol;
import seguridad.SesionBean;

@Interceptor
@Secured
public class SecurityInterceptor implements Serializable {

	private static Logger log = Logger.getLogger(SecurityInterceptor.class);

	@Inject
	private SesionBean sesion;

	@AroundInvoke
	public Object intercpetar(InvocationContext ctx) throws Exception {
		// verificar que el metodo que se invoca este anotado.
		if (ctx.getMethod().isAnnotationPresent(Secured.class)) {
			if (sesion.isLogged()) {
				Secured sec = ctx.getMethod().getAnnotation(Secured.class);
				String rol = sec.rol();
				List<Rol> roles = sesion.getRoles();
				boolean invocar = false;
				for (Rol r : roles) {
					if (r.getNombre().equals(rol)){
						invocar = true;
					}
				}
				if (invocar) {
					Object res = ctx.proceed();
					return res;
				} else {
					throw new ExcepcionFuncional("Usted no esta autorizado, salte de Aqui!!!");
				}
			} else {
				throw new ExcepcionFuncional("Usted no esta autorizado, salte de Aqui!!!");
			}
		} else {
			Object res = ctx.proceed();
			return res;
		}

	}
}
