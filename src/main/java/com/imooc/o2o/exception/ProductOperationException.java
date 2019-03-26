package com.imooc.o2o.exception;
/**
 * 看到这样的异常 需要知道是shop_operation的异常  做了一层薄薄的封装
 * @author WangChao
 */
public class ProductOperationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -552463239137877992L;

	/**
	 * 
	 */

	/**
	 * 
	 */

	public ProductOperationException(String msg) {
		super(msg);
	}
}
