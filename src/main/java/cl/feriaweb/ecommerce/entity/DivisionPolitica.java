/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.entity;

import feign.Feign;
import feign.Param;
import feign.okhttp.OkHttpClient;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class DivisionPolitica {

  private static Dpa dpa = Feign.builder()
          .client(new OkHttpClient())
          .encoder(new JacksonEncoder())
          .decoder(new JacksonDecoder())
          .target(Dpa.class, "https://apis.digital.gob.cl/dpa");

  interface Dpa {

    @RequestLine("GET /comunas/{codigo}")
    Comuna getComunaByCodigo(@Param("codigo") String codigo);

    @RequestLine("GET /provincias/{codigo}")
    Provincia getProvinciaByCodigo(@Param("codigo") String codigo);

    @RequestLine("GET /regiones/{codigo}")
    Region getRegionByCodigo(@Param("codigo") String codigo);
  }

  public static Comuna getComunaByCodigo(String codigo) {
    return dpa.getComunaByCodigo(codigo);
  }

  public static Provincia getProvinciaByCodigo(String codigo) {
    return dpa.getProvinciaByCodigo(codigo);
  }

  public static Region getRegionByCodigo(String codigo) {
    return dpa.getRegionByCodigo(codigo);
  }
}
