package com.google.refine.ENSUploader.commands;

import it.okkam.rdf2okkam.ens.EnsPerson;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;


public class HelloWorld {
  private static Logger logger = Logger.getLogger(HelloWorld.class);
public  void hello(){
    System.out.println("Succefully Enjected");
}
public static void main(String...argv){
    logger.info("this is a sample log message.");
}
}
