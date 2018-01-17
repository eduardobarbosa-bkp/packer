package com.mobiquityinc.packer.validation;

import com.mobiquityinc.packer.util.Messages;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void testCheckNotNullWithNullValue(){
       Preconditions.checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithNotNullValue(){
        Preconditions.checkNotNull(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgumentFalseCondition(){
        Preconditions.checkArgument(false, null);
    }

    @Test
    public void testCheckArgumentTrueCondition(){
        Preconditions.checkArgument(true, null);
    }

    @Test
    public void testExceptionMessage(){
        try {
            Preconditions.checkArgument(false, Messages.ERROR_WEIGHT_KEY, 100);
        }catch (IllegalArgumentException e){
            assertThat(e.getMessage(), containsString("100"));
        }
    }

}
