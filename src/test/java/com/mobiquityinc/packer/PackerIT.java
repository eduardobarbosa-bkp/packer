package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class PackerIT {

    private String SAMPLE_FILE = "/sample";

    private String SAMPLE_RESULT = "4\n-\n2,7\n8,9";

    private String INVALID_CONSTRAINTS_FILE = "/invalid_constraints";

    @Test
    public void testPackerSample(){
        String result = Packer.pack(getPathFromResource(SAMPLE_FILE));
        assertThat(result, notNullValue());
        assertThat(result, equalTo(SAMPLE_RESULT));
    }

    @Test(expected = APIException.class)
    public void testInvalidConstraints(){
        Packer.pack(getPathFromResource(INVALID_CONSTRAINTS_FILE));
    }


    private String getPathFromResource(String resource){
        try{
            return Paths.get(getClass().getResource(resource).toURI()).toString();
        }catch (URISyntaxException e){
          e.printStackTrace();
        }
        return null;
    }

}
