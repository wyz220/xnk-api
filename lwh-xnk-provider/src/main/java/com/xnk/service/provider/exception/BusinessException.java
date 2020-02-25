package com.xnk.service.provider.exception;

/**
 * 
 *  wuyanzhong
 */
public class BusinessException extends SystemException
{
	private static final long serialVersionUID = 1L;

	public BusinessException()
    {
    }

    public BusinessException(String message)
    {
        super(message);
    }

    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 性能问题，在必要时，可以通过 override 异常类的 fillInStackTrace() 方法为空方法，使其不拷贝栈信息
     */
	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}
    
    
}
