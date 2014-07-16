package com.library;

public class ServiceResponse
{
	private Object data = null;
	private ResponseExceptionType responseExceptionType ;
	private String exceptionMessage;

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public ResponseExceptionType getResponseExceptionType()
	{
		return responseExceptionType;
	}

	public void setResponseExceptionType(ResponseExceptionType responseExceptionType)
	{
		this.responseExceptionType = responseExceptionType;
	}

	public String getExceptionMessage()
	{
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage)
	{
		this.exceptionMessage = exceptionMessage;
	}

	public enum ResponseExceptionType
	{
		SSLPeerUnverifiedException,IO_EXCEPTION, TOKEN_EXPIRED, UNKONWN_EXCEPTION, CONNECTION_TIMEOUT, RESPONSE_TIMEOUT, NO_CONNECTION, CLIENT_PROTOCOL_EXCEPTION, IOEXCEPTION, UNKONWN, NONE
	}
}
