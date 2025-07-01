package br.mil.mar.saudenaval.senpe.config;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class GenerateProtocol {

    public static String set(){
        String uuid = UUID.randomUUID().toString();

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(uuid.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            String hash = Hex.encodeHexString(digest);
            return hash.substring(0, 8);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
