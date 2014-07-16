package com.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import com.library.ServiceResponse.ResponseExceptionType;

public class APIRequest
{
	private static final String TAG = "APIRequest";

	private static final int CONNECTION_TIMEOUT = 30000;
	private static final int SOCKET_CONN_TIMEOUT = 30000;

	private ArrayList<NameValuePair> params = null;
	private ArrayList<NameValuePair> headers = null;

	private String serviceUrl = null;
	private String message = null;
	private String response = null;

	private int responseCode = 0;

	private RequestMethod method;

	private StringEntity entity = null;

	public APIRequest(String url, RequestMethod method)
	{
		
		
		this.serviceUrl = url;
		this.method = method;
		params = new ArrayList<NameValuePair>();
		headers = new ArrayList<NameValuePair>();
	}

	public String getResponse()
	{
		return response;
	}

	public String getErrorMessage()
	{
		return message;
	}

	public int getResponseCode()
	{
		return responseCode;
	}

	public void addParam(String name, String value)
	{
		params.add(new BasicNameValuePair(name, value));
	}

	public void addHeader(String name, String value)
	{
		headers.add(new BasicNameValuePair(name, value));
	}

	/**
	 * @return the entity
	 */
	public StringEntity getEntity()
	{
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setStringEntity(String value)
	{
		try
		{
			entity = new StringEntity(value);
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}

	/*public ServiceResponse execute() throws ConnectTimeoutException, SocketTimeoutException, 
	SocketException, IOException, ClientProtocolException 
	{
		ServiceResponse retVal = null;

		switch(method) 
		{
		case GET :
		{
			//add parameters
			String combinedParams = "";

			if(!params.isEmpty())
			{
				combinedParams += "?";

				for(NameValuePair p : params)
				{
					String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");

					if(combinedParams.length() > 1)
					{
						combinedParams  +=  "&" + paramString;
					}
					else
					{
						combinedParams += paramString;
					}
				}
			}

			HttpGet request = new HttpGet(url + combinedParams);

			AndroidLog.d(TAG, "Service Called ===> " + url + combinedParams);

			//add headers
			for(NameValuePair h : headers)
			{
				AndroidLog.d(TAG, "Header Name and Value ===> " + h.getName() + " " + h.getValue());
				request.addHeader(h.getName(), h.getValue());
			}

			retVal = executeRequest(request, url);

			break;
		}

		case POST:
		{
			HttpPost request = new HttpPost(url);

			//add headers
			for(NameValuePair h : headers)
			{
				request.addHeader(h.getName(), h.getValue());
			}

			//Add Parameters
			if(!params.isEmpty())
			{
				request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}

			retVal = executeRequest(request, url);

			break;
		}

		case PUT:
		{
			HttpPut request = new HttpPut(url);

			//add headers
			for(NameValuePair h : headers)
			{
				request.addHeader(h.getName(), h.getValue());
			}

			//Add Parameters
			if(!params.isEmpty())
			{
				request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}

			if(entity != null)
			{
				entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, HTTP.UTF_8));
				request.setEntity(entity);
			}

			retVal = executeRequest(request, url);

			break;
		}

		case DELETE:
		{
			HttpDelete request = new HttpDelete(url);

			//add headers
			for(NameValuePair h : headers)
			{
				request.addHeader(h.getName(), h.getValue());
			}

			//Add Parameters
			if(!params.isEmpty())
			{
				((HttpResponse) request).setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}

			retVal = executeRequest(request, url);

			break;
		}

		}

		return retVal;
	}*/

	/*private ServiceResponse executeRequest(HttpRequestBase request, String url) throws ConnectTimeoutException, SocketTimeoutException, 
	SocketException, IOException, 
	ClientProtocolException
	{
		ServiceResponse serviceResponse = new ServiceResponse();

		HttpParams httpParameters = new BasicHttpParams();

		SSLContext sc;
		try {
			sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Set the timeout in milliseconds until a connection is established.
		//		HttpConnectionParams.setConnectionTimeout(httpParameters, CONNECTION_TIMEOUT);

		// Set the default socket timeout (SO_TIMEOUT) 
		// in milliseconds which is the timeout for waiting for data.
		//		HttpConnectionParams.setSoTimeout(httpParameters, SOCKET_CONN_TIMEOUT);

		HttpClient client = new DefaultHttpClient(httpParameters);

		HttpResponse httpResponse;

		httpResponse = client.execute(request);
		responseCode = httpResponse.getStatusLine().getStatusCode();

		if(responseCode == HttpStatus.SC_OK)
		{
			message = httpResponse.getStatusLine().getReasonPhrase();

			HttpEntity entity = httpResponse.getEntity();

			if(entity != null)
			{
				InputStream instream = entity.getContent();
				response = convertStreamToString(instream);

				serviceResponse.setData(response);
				serviceResponse.setResponseExceptionType(ResponseExceptionType.NONE);

				// Closing the input stream will trigger connection release
				if(instream != null)
					instream.close();
			}
		}
		return serviceResponse;
	}*/

	private static String convertStreamToString(InputStream is)
	{

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try
		{
			while((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
		}
		catch(IOException e)
		{
			AndroidLog.printStackTrace(TAG, e);
		}
		finally
		{
			try
			{
				is.close();
			}
			catch(IOException e)
			{
				AndroidLog.printStackTrace(TAG, e);
			}
		}
		return sb.toString();
	}

	public enum RequestMethod 
	{
		POST, GET, PUT, DELETE
	}


	public ServiceResponse getStreaming()
	{
		ServiceResponse serviceResponse = new ServiceResponse();	
		try {


			URL url = new URL(serviceUrl);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			if(con!=null){
				AndroidLog.e("Response Code", "" + con.getResponseCode());
				AndroidLog.e("Cipher Suite :", "" + con.getCipherSuite());					 

				Certificate[] certs = con.getServerCertificates();
				for(Certificate cert : certs){
					AndroidLog.e("Cert Type : ", cert.getType());
					AndroidLog.e("Cert Hash Code : ", "" + cert.hashCode());
					AndroidLog.e("Cert Public Key Algorithm : ", 
							cert.getPublicKey().getAlgorithm());
					AndroidLog.e("Cert Public Key Format : ", 
							cert.getPublicKey().getFormat());

				}					
				BufferedReader br = 
						new BufferedReader(
								new InputStreamReader(con.getInputStream()));

				String inputLine;
				StringBuffer html = new StringBuffer();

				while ((inputLine = br.readLine()) != null) {
					html.append(inputLine);
				}

				br.close();
				serviceResponse.setData(html.toString());
				serviceResponse.setResponseExceptionType(ResponseExceptionType.NONE);
				return serviceResponse;		
			}
			else
			{
				serviceResponse = new ServiceResponse();
				serviceResponse.setData(null);
				serviceResponse.setResponseExceptionType(ResponseExceptionType.NO_CONNECTION);
				return serviceResponse;
			}

		} 
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			serviceResponse = new ServiceResponse();
			serviceResponse.setData(null);
			serviceResponse.setResponseExceptionType(ResponseExceptionType.SSLPeerUnverifiedException);
			serviceResponse.setExceptionMessage(e.getMessage());
			return serviceResponse;
		}
		catch (SSLPeerUnverifiedException e) {
			e.printStackTrace();
			serviceResponse = new ServiceResponse();
			serviceResponse.setData(null);
			serviceResponse.setResponseExceptionType(ResponseExceptionType.SSLPeerUnverifiedException);
			serviceResponse.setExceptionMessage(e.getMessage());
			return serviceResponse;
		}
		catch (Exception e) {
			e.printStackTrace();
			serviceResponse = new ServiceResponse();
			serviceResponse.setData(null);
			serviceResponse.setResponseExceptionType(ResponseExceptionType.UNKONWN_EXCEPTION);
			serviceResponse.setExceptionMessage(e.getMessage());
			return serviceResponse;
		}
	}


	private static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()
	{
		public java.security.cert.X509Certificate[] getAcceptedIssuers()
		{
			return null;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
		{

		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
		{

		}
	}

	};
}
