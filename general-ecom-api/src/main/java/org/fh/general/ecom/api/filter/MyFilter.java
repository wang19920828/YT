package org.fh.general.ecom.api.filter;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.factory.RequestFactory;
import org.fh.general.ecom.common.mongo.entity.Valid;
import org.fh.general.ecom.common.valid.VateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
//@Component
public class MyFilter implements Filter {

    //设置默认的字符编码
     private String charset = "UTF-8";

    @Autowired
    private MongoTemplate mongoTemplate;

     private static MyFilter myFilter;
     @PostConstruct
     public void init(){
         myFilter=this;
         myFilter.mongoTemplate=mongoTemplate;
     }

        @Override
        public void destroy() {
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain
                filterChain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            HttpServletResponse response = (HttpServletResponse) sresponse;
            response.setCharacterEncoding(charset);
            response.setContentType("text/html;charset="+charset);
            String url=request.getRequestURI();

           
            //打印请求Url
            log.info("this is MyFilter,url :" + url);
            Map<String,String> params = RequestFactory.getParams(request);
            try {
                if(!"/favicon.ico".equals(url)){
                    String interfaceNum=url.substring(url.lastIndexOf("/")+1,url.length() );
                    Object obj=commValidate(interfaceNum,params);
                    filterChain.doFilter(srequest, sresponse);
                }

            }catch (Exception e){
                e.printStackTrace();
                PrintWriter out = response.getWriter();
                PagingVO baseVO=new PagingVO();
                baseVO.exception(e.getMessage());
                out.print(JSONObject.fromObject(baseVO).toString());
                out.flush();
                out.close();
            }



        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
        }

    /**
    		 * 公共校验方法
    		 * @ param exeMethod
    		 * @param params
    		 * @return
    		 */
    		public Object commValidate(String interfaceNum,Map<String,String> params) throws Exception{
    			 Object obj = null;
                Query query=new Query(Criteria.where("interfaceNum").is(interfaceNum));
                Valid valid=myFilter.mongoTemplate.findOne(query, Valid.class,"Valid");
                if(valid!=null){
                      Class  cla=Class.forName(valid.getClassPath()); cla.getClasses();
                       obj = cla.newInstance();
                     obj = VateFactory.setValToObj(obj, params);
                    VateFactory.valid(obj);
                }

    			 return obj;
    		}


    }
