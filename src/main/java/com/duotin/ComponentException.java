package com.duotin;


import com.duotin.common.EnumCode;import java.lang.Override;import java.lang.RuntimeException;import java.lang.String;import java.lang.Throwable;

/**
 * @author lrh
 * @create 2015年3月23日下午11:43:10
 */
public class ComponentException extends RuntimeException {

        private static final long serialVersionUID = -9155482435586236626L;
        
        private ErrCode errCode;
        
        /**
         * 异常码
         * @author lrh
         * @create 2015年3月23日下午11:44:53
         */
        public interface ErrCode extends EnumCode {
                
                ErrCode  系统异常= new ErrCode(){

                        @Override
                        public String code() {
                                return "0001";
                        }
                        @Override
                        public String name() {
                                return "系统异常";
                        }                                                
                };
                
                ErrCode  参数非法= new ErrCode(){

                        @Override
                        public String code() {
                                return "0002";
                        }
                        @Override
                        public String name() {
                                return "参数非法";
                        }                                                
                };
                
                ErrCode  业务异常= new ErrCode(){

                    @Override
                    public String code() {
                            return "0003";
                    }
                    @Override
                    public String name() {
                            return "业务异常";
                    }                                                
            };
                
                String name();
        }
        
        public ComponentException(ErrCode errCode,String messsage,Throwable cause){
                super(messsage,cause);
                this.errCode = errCode;
        }
        
        public ComponentException(ErrCode errCode,String messsage){
                this(errCode, messsage, null);
        }
        
        public ComponentException(ErrCode errCode,Throwable cause){
                this(errCode, "", cause);
        }
        
        public ComponentException(ErrCode errCode){
                this(errCode, "");
        }
        
        public final String getErrCode(){
                return errCode.code();
        }
}
