package boot.http.message;

import boot.dto.EosDictType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesPersonHttpMessageConverter extends AbstractHttpMessageConverter<EosDictType> {

    public PropertiesPersonHttpMessageConverter(){
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(EosDictType.class);
    }

    @Override
    protected EosDictType readInternal(Class<? extends EosDictType> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream i = httpInputMessage.getBody();
        Properties p = new Properties();
        p.load(new InputStreamReader(i,getDefaultCharset()));
        EosDictType d = new EosDictType();
        d.setDicttypeid(p.getProperty("eosDictType.dicttypeid"));
        d.setDicttypename(p.getProperty("eosDictType.dicttypename"));
        return d;
    }

    @Override
    protected void writeInternal(EosDictType eosDictType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream o = httpOutputMessage.getBody();
        Properties p = new Properties();
        p.setProperty("eosDictType.dicttypeid",eosDictType.getDicttypeid());
        p.setProperty("eosDictType.dicttypename",eosDictType.getDicttypename());
        p.store(new OutputStreamWriter(o,getDefaultCharset()),"Written by web server");


    }
}
