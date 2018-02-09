package enkan.middleware.nablarch;

import enkan.Middleware;
import enkan.MiddlewareChain;
import enkan.data.HttpRequest;
import nablarch.fw.Handler;
import nablarch.fw.HandlerQueueManager;
import nablarch.fw.web.HttpResponse;
import nablarch.fw.web.servlet.ServletExecutionContext;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

public class NablarchInvokerMidleware<RES> extends HandlerQueueManager<NablarchInvokerMidleware<RES>> implements Middleware<HttpRequest, RES> {
    @Override
    public RES handle(HttpRequest request, MiddlewareChain chain) {
        ServletContext servletContext = new ServletContextImpl();
        ServletExecutionContext context = new ServletExecutionContext(
                new HttpServletRequestImpl(request),
                new HttpServletResponseImpl(),
                servletContext);
        return context.setHandlerQueue(getHandlerQueue())
                .handleNext(request);
    }

    @Override
    public List<Handler> getHandlerQueue() {
        return handlerQueue;
    }

    private List<Handler> handlerQueue = new ArrayList<>();
}
