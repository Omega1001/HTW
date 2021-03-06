package ueb15;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SpeedAnalyser implements InvocationHandler {

	@SuppressWarnings("unchecked")
	public static <T> T create(Class<T> type, T actual) {
		if (type.isInterface()) {
			return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] { type },
					new SpeedAnalyser(actual));
		} else {
			throw new IllegalArgumentException("Must be an Interface");
		}
	}

	public static long getLastExecutionTime(Object obj) {
		if (obj == null || Proxy.isProxyClass(obj.getClass())) {
			InvocationHandler handler = Proxy.getInvocationHandler(obj);
			if (handler instanceof SpeedAnalyser) {
				return ((SpeedAnalyser) handler).lastExecutionNanos;
			} else {
				throw new IllegalArgumentException("Not a valid speed Analyser");
			}
		} else {
			throw new IllegalArgumentException("Not a valid speed Analyser");
		}
	}

	private Object actual;
	private ThreadMXBean tBean;
	private long lastExecutionNanos = 0;

	private SpeedAnalyser(Object actual) {
		this.actual = actual;
		tBean = ManagementFactory.getThreadMXBean();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object res = null;
		long start = tBean.isThreadCpuTimeSupported() ? tBean.getCurrentThreadCpuTime() : 0;
		long startTime = System.nanoTime();

		try {
			res = method.invoke(actual, args);

			long endTime = System.nanoTime();
			lastExecutionNanos = (tBean.isThreadCpuTimeSupported() ? tBean.getCurrentThreadCpuTime()
					: 0) - start;
			if(lastExecutionNanos < 10) {
				lastExecutionNanos= endTime - startTime;
			}
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
		return res;
	}

}
