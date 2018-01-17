package com.mobiquityinc.packer;

/**
 * This class is responsible to init the process
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class PackerStandalone
{
    public static void main( String[] args )
    {
        String result = Packer.pack(args[0]);
        System.out.println(result);
    }
}
