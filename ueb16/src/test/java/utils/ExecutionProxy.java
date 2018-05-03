package utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ExecutionProxy implements InvocationHandler {

	public static <T> T getProxy(Class<T> type) {
		return getProxy(type, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Class<T> type, T actual) {
		if (type == null) {
			throw new IllegalArgumentException("Type must not be null");
		} else if (!type.isInterface()) {
			throw new IllegalArgumentException(
					"Proxy Type must be an interface");
		} else if (actual != null && !type.isInstance(actual)) {
			throw new IllegalArgumentException(
					"Actual object must be of type " + type
							.getSimpleName());
		}
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<
				?>[] { type }, new ExecutionProxy(actual));
	}

	public static Method getLastCalledMethod(Object o) {
		return resolve(o).lastCalledMethod;
	}
	
	public static Object[] getLastCalledMethodArgs(Object o) {
		return resolve(o).lastArgs;
	}
	
	public static Object getActual(Object o) {
		return resolve(o).actual;
	}
	
	public static void setActual(Object o, Object actual) {
		ExecutionProxy obj = resolve(o);
		if(obj.type.isInstance(actual)) {
			obj.actual = actual;
		}
	}
	
	public static void setReturnValue(Object o, Object returnV) {
		resolve(o).mannagedReturnValue = returnV;
	}
	
	public static void reset(Object o) {
		ExecutionProxy obj = resolve(o);
		obj.lastArgs = null;
		obj.lastCalledMethod = null;
	}

	private static ExecutionProxy resolve(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("Object must not be null");
		}
		if (!Proxy.isProxyClass(o.getClass())) {
			throw new IllegalArgumentException("passed object is not a proxy");
		}
		InvocationHandler handler = Proxy.getInvocationHandler(o);
		if(!(handler instanceof ExecutionProxy)) {
			throw new IllegalArgumentException("passed object is not a execution proxy");
		}
		return (ExecutionProxy) handler;
	}

	private Class<?> type;
	private Object actual;
	private Method lastCalledMethod;
	private Object[] lastArgs;
	private Object mannagedReturnValue;

	private ExecutionProxy(Object actual) {
		this.actual = actual;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		lastCalledMethod = method;
		lastArgs = args;
		if (actual != null) {
			try {
				return method.invoke(actual, args);
			} catch (InvocationTargetException e) {
				throw e.getTargetException();
			}
		}
		return mannagedReturnValue;
	}

}
