package com.duotin.controller.handler;

import java.util.HashMap;
import java.util.Map;

import com.duotin.util.BeanUtils;
import com.duotin.util.JsonUtils;
import com.duotin.util.rsa.Base64Utils;
import com.duotin.util.rsa.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * @author lrh
 * @create 2015年4月10日下午10:03:14
 */
public class NewCustomArgumentResolver implements HandlerMethodArgumentResolver {

    private Logger logger = LoggerFactory.getLogger(NewCustomArgumentResolver.class);


    private static String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANE/DwtdEy74BJmt2hoG/gUqPpBY1s9e0fU/ADBYZW+GtHXRjP7/3V4vrODDE4dvGmNRANmfb8CwcvWJMvgQVti+VYHy9a3zKU3wyMD5LSU7jjefSKo8cTnIvuP/+eeSVbta8jdHGdT8j+wX6B/tH8m31uID3JFUyNcnqQl/CkRtAgMBAAECgYB9HbqOLeoxlIWTqruWAoZTak4cm7JeLrFWdoRTZBfLFv3C0TkApNel4NX7U0nTGqDpr97VQZtd2WPz40TBxWtXevesSQJRPZKRTdltWChhTz81WfbeKmrJ3i/k552pP9URKet9N6SCZfI2FbWuT9oXt06hdYLagQ9HaysgumJmQQJBAPkXsGMt8ZCJ0PqYswHObxz2wKL6Sh/vR8sRgXNpI1SsGjPdYMTRA0FyhdcvLZ49Rp8sx+q2rTtSPBUo7QTF9VECQQDXDIBBuqsvZpajqS8fc0sLDHnb3pXu3QX+lJKnM9UG4hlPZb7YqaKAos4a3Ch2HjfNr3yKLRVgdNhmKYNuTEZdAkANKIky1Ax8vCsD8ekS4VrRRt+qPFqKEVPTkFssQ5OQN38y2jDwdLIdVPVpBp78SvReGDl/VqMUDSYLYBwVMnfhAkEAuGNWoYUt/KGo7kFFT2Eq6hlTbyvGQR4wYp3/tO/ja1h4PTTPU7T3yfZOZId7BfRZArrCPTP0zVTCq65SrEMoXQJAVNgEMS+H4TNqeZyRRM4RUEEUWqdJoyro8v8E8WqqH3v8EH6hwDU5MH/K5uLs0N6GqilmjshNFJa6tKlO8IHLkg==";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class paramObjClass = methodParameter.getParameterType();
        if(CustomResolverParam.class.isAssignableFrom(paramObjClass)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class paramObjClass = methodParameter.getParameterType();
        if(CustomResolverParam.class.isAssignableFrom(paramObjClass)){
            Map<String,String> singleValueParam = new HashMap<String,String>();
            Map<String,String[]> param = nativeWebRequest.getParameterMap();

            Map<String,String[]> paramTemp;
            //加密
            if(param.containsKey("p")){
                paramTemp =new HashMap<>();
                String encodePara=param.get("p")[0]+"";

                byte[] encodeByte = Base64Utils.decode(encodePara);
                String encodeStr = new String(RSAUtils.decryptByPrivateKey(encodeByte, privateKey));

                logger.error("qiguo:"+encodeStr);

                String[] para=encodeStr.split("&");
                for(String s:para){
                    String[] paraPair=s.split("=");
                    paramTemp.put(paraPair[0],new String[]{paraPair[1]});
                }

                for(String key:param.keySet()){
                    if(!paramTemp.containsKey(key)){
                        paramTemp.put(key,param.get(key));
                    }
                }

            }else {
                paramTemp=param;
            }

            for(Map.Entry<String,String[]> entry:paramTemp.entrySet()){
                String[] val = entry.getValue();
                if(val != null && val.length == 1&&!val.equals("p")){
                    singleValueParam.put(entry.getKey(), val[0]);
                }
            }
            CustomResolverParam paramObj = (CustomResolverParam) paramObjClass.newInstance();
            Object o= paramObj.parse(singleValueParam);
            return o;
        }
        return WebArgumentResolver.UNRESOLVED;
    }
}
